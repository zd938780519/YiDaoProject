package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.FlagUtil;
import com.ruoyi.common.utils.YdParameterUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.YdShopOrderAutoCancelService;
import com.ruoyi.system.service.YdShopSettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Transactional
@Service
public class YdShopSettlementServiceImpl implements YdShopSettlementService {
    @Autowired(required = false)
    private YdShoppingCartGoodsMapper ydShoppingCartGoodsMapper;
    @Autowired(required = false)
    private YdIntegralMapper ydIntegralMapper;
    @Autowired(required = false)
    private YdMallVoucherExchangeMapper ydMallVoucherExchangeMapper;
    @Autowired(required = false)
    private YdUserFlowAccountMapper ydUserFlowAccountMapper;
    @Autowired(required = false)
    private YdShopMapper ydShopMapper;
    @Autowired(required = false)
    private YdShopFlowAccountMapper ydShopFlowAccountMapper;
    @Autowired(required = false)
    private YdShopOrdersMapper ydShopOrdersMapper;
    @Autowired(required = false)
    private YdShopOrderGoodsMapper ydShopOrderGoodsMapper;
    @Autowired(required = false)
    private YdFlagMapper ydFlagMapper;
    @Autowired(required = false)
    private YdShopGoodsMapper ydShopGoodsMapper;
    @Autowired(required = false)
    private YdShopOrderAutoCancelService ydShopOrderAutoCancelService;

    @Override
    public HashMap<String, String> inventoryInspection(List<Long> list) {
        HashMap<String, String> item = new HashMap<String, String>();
        List<YdShop> ydShops = ydShoppingCartGoodsMapper.selectGoodsByIds(list);
        for (YdShop ydShop:ydShops) {
            List<YdShoppingCartGoods> scGoods = ydShop.getScGoods();
            for (YdShoppingCartGoods scGood:scGoods) {
                int orderCount = scGood.getOrderCount();
                int storedCount = scGood.getStoredCount();
                if(storedCount>=orderCount){
                    item.put("code","1");
                    item.put("msg","库存充足");
                }else{
                    item.put("code","-1");
                    item.put("msg","库存不足");
                }
            }
        }
        return item;
    }

    @Override
    public Body placeOrder(long userId, List<Long> list, long receivingAddress, Long voucherId, int integral, int type,String remark,Long orderId) {

        //考虑好预留支付接口的位置。金额在后台计算、库存量校验。所有钱和积分先转到平台的账户上

        //查库存
        HashMap<String, String> stringStringHashMap = this.inventoryInspection(list);
        String code = stringStringHashMap.get("code");
        if(code.equals("1")){
            //计算应支付金额和积分
            YdUser ydUser = ydIntegralMapper.selectIntegralByUserId(userId);
            int integralByUser = ydUser.getIntegral();//积分
            int giftIntegral = ydUser.getGiftIntegral();//赠送的积分
            BigDecimal allIntegral = new BigDecimal(integralByUser+"").setScale(2).add(new BigDecimal(giftIntegral+"").setScale(2));
            int allIntegralI = Integer.parseInt(allIntegral.setScale(0)+"");
            List<YdShop> ydShops = ydShoppingCartGoodsMapper.selectGoodsByIds(list);
            BigDecimal allSalePrice = new BigDecimal("0").setScale(2);//所有商品销售价格
            BigDecimal allSalePriceIsAllowCoupon = new BigDecimal("0").setScale(2);//所有可用券的商品销售价格
            BigDecimal allSalePriceIntegral = new BigDecimal("0").setScale(0);//所有商品销售价格对应积分
            BigDecimal allIntegralLimit = new BigDecimal("0").setScale(0);//所有商品总积分限额
            BigDecimal allIntegralPrice = new BigDecimal("0").setScale(0);//所有商品总积分价格
            BigDecimal allIntegralPaymentPrice = new BigDecimal("0").setScale(0);//所有商品全积分付款金额
            BigDecimal allGiveIntegralPrice = new BigDecimal("0").setScale(0);//所有商品返积分金额
            int exchangeRate = YdParameterUtils.EXCHANGE_RATE;//汇率

            //卖家流水信息
            List<HashMap<String,Object>> shopRunningAccountInfoList = new ArrayList<HashMap<String,Object>>();
            //订单商品信息
            List<YdShopOrderGoods> ydShopOrderGoods = new  ArrayList<YdShopOrderGoods>();
            for (YdShop shop:ydShops) {
                HashMap<String,Object> item = new HashMap<String,Object>();
                Long shopId = shop.getId();
                item.put("shopId",shopId);
                BigDecimal goodsesSalePrice = new BigDecimal("0").setScale(2);//此店铺商品总价
                BigDecimal goodsesIntegralPrice = new BigDecimal("0").setScale(0);//此店铺商品总积分
                BigDecimal postage = new BigDecimal("0").setScale(2);//这家店铺所有不包邮的商品的邮费
                BigDecimal postageOfFreeShipping  = new BigDecimal("0").setScale(2);//这家店铺所有包邮的商品的邮费
                BigDecimal goodsMoney = new BigDecimal("0").setScale(2);//包邮商品金额
                //包邮限额
                BigDecimal postalQuota = shop.getPostalQuota();//包邮限额

                List<YdShoppingCartGoods> scGoods = shop.getScGoods();
                for (int i=0;i<scGoods.size();i++){
                    YdShoppingCartGoods ydShoppingCartGoods = scGoods.get(i);
                    int orderCount = ydShoppingCartGoods.getOrderCount();//订购数量
                    double salePrice1 = ydShoppingCartGoods.getSalePrice();//销售价格
                    int integralLimit = ydShoppingCartGoods.getIntegralLimit();//积分限额
                    int integralPrice = ydShoppingCartGoods.getIntegralPrice();//积分价格
                    int integralPaymentPrice = ydShoppingCartGoods.getIntegralPaymentPrice();//全积分付款金额
                    int giveIntegralPrice = ydShoppingCartGoods.getGiveIntegralPrice();//返积分金额
                    BigDecimal thisSalePrice = new BigDecimal(orderCount+"").setScale(2).multiply(new BigDecimal(salePrice1+"").setScale(2));
                    int isAllowCoupon = ydShoppingCartGoods.getIsAllowCoupon();//是否可用券
                    if(isAllowCoupon == 1){
                        allSalePriceIsAllowCoupon = allSalePriceIsAllowCoupon.add(thisSalePrice);
                    }
                    int isFixedMoneyAndIntegral = ydShoppingCartGoods.getIsFixedMoneyAndIntegral();//是否固定金额加固定积分
                    int isPureIntegral = ydShoppingCartGoods.getIsPureIntegral();//是否纯积分

                    allSalePrice = allSalePrice.add(thisSalePrice);
                    allSalePriceIntegral = allSalePriceIntegral.add(thisSalePrice.setScale(2).multiply(new BigDecimal(exchangeRate+"").setScale(2)));
                    BigDecimal thisIntegralLimit = new BigDecimal(orderCount+"").setScale(2).multiply(new BigDecimal(integralLimit+"").setScale(2));
                    allIntegralLimit = allIntegralLimit.add(thisIntegralLimit.setScale(2));
                    BigDecimal thisIntegralPaymentPrice = new BigDecimal(orderCount+"").setScale(2).multiply(new BigDecimal(integralPaymentPrice+"").setScale(2));
                    allIntegralPaymentPrice = allIntegralPaymentPrice.add(new BigDecimal(thisIntegralPaymentPrice+"").setScale(2));
                    BigDecimal thisGiveIntegralPrice = new BigDecimal(orderCount+"").setScale(2).multiply(new BigDecimal(giveIntegralPrice+"").setScale(2));
                    allGiveIntegralPrice = allGiveIntegralPrice.add(new BigDecimal(thisGiveIntegralPrice+"").setScale(2));
                    BigDecimal thisIntegralPrice = new BigDecimal(orderCount+"").setScale(2).multiply(new BigDecimal(integralPrice+"").setScale(2));
                    allIntegralPrice = allIntegralPrice.add(thisIntegralPrice);

                    goodsesSalePrice = goodsesSalePrice.add(thisSalePrice);
                    goodsesIntegralPrice = goodsesIntegralPrice.add(thisIntegralPrice);

                    int isFreeShipping = ydShoppingCartGoods.getIsFreeShipping();//是否包邮
                    if(isFreeShipping == 1){
                        if(isFixedMoneyAndIntegral == 1 || isPureIntegral == 1){//将积分换算成人民币后累加进去
                            goodsMoney = goodsMoney.add(new BigDecimal(orderCount+"").setScale(2).multiply(new BigDecimal(integralPrice+"").setScale(2).divide(new BigDecimal(YdParameterUtils.EXCHANGE_RATE+""),2,BigDecimal.ROUND_HALF_UP)));
                        }
                        if(isPureIntegral != 1){
                            goodsMoney = goodsMoney.add(thisSalePrice);
                        }
                        if(orderCount == 1){
                            postageOfFreeShipping = postageOfFreeShipping.add(new BigDecimal(ydShoppingCartGoods.getFirstPostage()+"").setScale(2));
                        }else{
                            postageOfFreeShipping = postageOfFreeShipping.add(new BigDecimal(ydShoppingCartGoods.getFirstPostage()+"").setScale(2))
                                    .add(new BigDecimal((orderCount-1)+"").multiply(new BigDecimal(ydShoppingCartGoods.getAdditionalPostage()+"").setScale(2)));
                        }
                    }else{
                        if(orderCount == 1){
                            postage = postage.add(new BigDecimal(ydShoppingCartGoods.getFirstPostage()+"").setScale(2));
                        }else{
                            postage = postage.add(new BigDecimal(ydShoppingCartGoods.getFirstPostage()+"").setScale(2))
                                    .add(new BigDecimal((orderCount-1)+"").multiply(new BigDecimal(ydShoppingCartGoods.getAdditionalPostage()+"").setScale(2)));
                        }
                    }

                    YdShopOrderGoods orderGoods = new YdShopOrderGoods();
                    orderGoods.setGoodsId(ydShoppingCartGoods.getGoodsId());
                    orderGoods.setSgpId(ydShoppingCartGoods.getSgpId());
                    orderGoods.setSbgId(ydShoppingCartGoods.getId());
                    orderGoods.setShopId(shopId);
                    orderGoods.setGoodsUnitPrice(new BigDecimal(salePrice1+""));
                    orderGoods.setGoodsCount(orderCount);
                    orderGoods.setIntegralLimit(integralLimit);
                    orderGoods.setIntegralPrice(integralPrice);
                    orderGoods.setGiveIntegralPrice(giveIntegralPrice);
                    orderGoods.setIsAllowCoupon(isAllowCoupon);
                    orderGoods.setIsFixedMoneyAndIntegral(isFixedMoneyAndIntegral);
                    orderGoods.setIsPureIntegral(isPureIntegral);
                    orderGoods.setIntegralPaymentPrice(integralPaymentPrice);
                    ydShopOrderGoods.add(orderGoods);
                }
                item.put("goodsesSalePrice",bigDecimalToDouble(goodsesSalePrice));
                item.put("goodsesIntegralPrice",bigDecimalToInt(goodsesIntegralPrice));

                int isFreeShipping = postalQuota.setScale(2).compareTo(goodsMoney.setScale(2));
                if(isFreeShipping == 1 ){//不满足包邮限额
                    postage = postage.add(postageOfFreeShipping);
                }
                item.put("postage",bigDecimalToDouble(postage));
                shopRunningAccountInfoList.add(item);
            }

            if(integral>allIntegralI){
                stringStringHashMap.put("code","-2");
                stringStringHashMap.put("msg","积分不足");
                return Body.newInstance(stringStringHashMap);
            }
            //先生成未付款状态的订单
            YdShopOrders ydShopOrders = new YdShopOrders();
            double actualPayment = 0;
            //运费
            BigDecimal postageBD = new BigDecimal("0").setScale(2);
            for (HashMap<String,Object> map :shopRunningAccountInfoList) {
                String postage = map.get("postage").toString();
                postageBD = postageBD.add(new BigDecimal(postage).setScale(2));
            }
            //用券抵扣
            BigDecimal amount = new BigDecimal("0");
            //积分抵扣
            if(integral != Integer.parseInt(allIntegralPrice.add(allIntegralPaymentPrice).setScale(0)+"")){//不是全积分支付
                BigDecimal integralDeduction  = (new BigDecimal(integral + "").setScale(0).subtract(allIntegralPrice.setScale(0)))
                        .divide(new BigDecimal(exchangeRate + "").setScale(0), 2, BigDecimal.ROUND_DOWN);
                BigDecimal actualPaymentBD = allSalePrice.subtract(integralDeduction);
                ydShopOrders.setIntegralMoney(bigDecimalToDouble(integralDeduction));

                if(voucherId != null){
                    YdMallVoucherExchange ydMallVoucherExchange = ydMallVoucherExchangeMapper.selectVoucherByIdAndUserId(voucherId, userId);
                    if(ydMallVoucherExchange != null){
                        amount = ydMallVoucherExchange.getAmount();
                        BigDecimal limitedAmount = ydMallVoucherExchange.getLimitedAmount();
                        if(limitedAmount.setScale(2).compareTo(allSalePriceIsAllowCoupon.setScale(2)) == -1){
                            actualPaymentBD = actualPaymentBD.subtract(amount);
                            if(Double.parseDouble(actualPaymentBD+"")<0){
                                ydShopOrders.setCouponMoney(bigDecimalToDouble(amount.add(actualPaymentBD)));
                                actualPaymentBD = new BigDecimal("0").setScale(2);
                            }else{
                                ydShopOrders.setCouponMoney(bigDecimalToDouble(amount));
                            }
                        }else{
                            stringStringHashMap.put("code","-4");
                            stringStringHashMap.put("msg","优惠券不可用");
                            return Body.newInstance(stringStringHashMap);
                        }
                    }else{
                        stringStringHashMap.put("code","-3");
                        stringStringHashMap.put("msg","优惠券不存在");
                        return Body.newInstance(stringStringHashMap);
                    }
                }
                actualPayment = bigDecimalToDouble(actualPaymentBD.add(postageBD));
                ydShopOrders.setTotalPrice(bigDecimalToDouble(actualPaymentBD));
            }else{//全积分抵扣
                actualPayment = bigDecimalToDouble(postageBD);
                for(int i=0;i<ydShopOrderGoods.size();i++){
                    YdShopOrderGoods orderGoods = ydShopOrderGoods.get(i);
                    orderGoods.setIsIntegralPayment(1);
                    ydShopOrderGoods.remove(i);
                    ydShopOrderGoods.add(i,orderGoods);
                }
            }

            if(orderId == null){
                //生成新订单
                if(voucherId != null){
                    ydShopOrders.setVoucherId(voucherId);
                }
                ydShopOrders.setUserId(userId);
                ydShopOrders.setRaId(receivingAddress);
                ydShopOrders.setFreightMoney(bigDecimalToDouble(postageBD));
                ydShopOrders.setOrderFormNum(FlagUtil.createOrderNum(FlagUtil.MALL_ORDER,ydFlagMapper.findFlag())+"");
                ydShopOrders.setAmountPayable(actualPayment);
                ydShopOrders.setPayWay(type);
                ydShopOrders.setRemark(remark == null?"":remark);
                ydShopOrders.setTotalPrice(bigDecimalToDouble(allSalePrice));//总价
                ydShopOrders.setCouponMoney(bigDecimalToDouble(amount));//优惠券抵扣金额
                ydShopOrders.setIntegralMoney(bigDecimalToDouble(allSalePrice.subtract(amount)));//积分抵扣金额
                ydShopOrders.setTotalIntegral(bigDecimalToInt(allIntegralPrice));//总积分
                ydShopOrders.setIntegralPayable(integral);//应付积分
                ydShopOrdersMapper.insertOrder(ydShopOrders);
                orderId=ydShopOrders.getId();
                YdShopOrderProcess ydShopOrderProcess = new YdShopOrderProcess();
                ydShopOrderProcess.setSoId(orderId);
                ydShopOrderProcess.setContent("待付款");
                ydShopOrderProcess.setReason("未付款");
                ydShopOrderProcess.setOrderStatus(1);
                ydShopOrdersMapper.insertOrderProcess(ydShopOrderProcess);
                //添加订单商品
                for(int i=0;i<ydShopOrderGoods.size();i++){
                    YdShopOrderGoods orderGoods = ydShopOrderGoods.get(i);
                    orderGoods.setSoId(orderId);
                    ydShopOrderGoods.remove(i);
                    ydShopOrderGoods.add(i,orderGoods);
                }
                ydShopOrderGoodsMapper.insertShopOrderGoods(ydShopOrderGoods);
                //逻辑删除购物车内商品
                ydShoppingCartGoodsMapper.logicalDeleteSomeShoppingCartGoods(list);
                //修改优惠券使用状态
                if(voucherId != null){
                    ydMallVoucherExchangeMapper.updateStatus(voucherId,1);
                }
                //加入延迟队列和redis，超时自动取消
                ydShopOrderAutoCancelService.add(orderId, System.currentTimeMillis()+YdParameterUtils.ORDER_OVERDUE_TIME);

                stringStringHashMap.put("code","1");
                stringStringHashMap.put("orderId",orderId+"");
                stringStringHashMap.put("msg","提交成功");
                return Body.newInstance(stringStringHashMap);
            }else{








                //TODO 预留支付接口
                //先判断积分是否充足
                //用户   --》  实付款  actualPayment ，支付类型 type 1：微信；2：支付宝 ， 实付积分  integral

                if(true){//支付成功
                    //支付成功后
                    //进行积分扣除、积分奖励、客户和店铺的流水账记录
                    Long userRunningAccountId = null;//用户流水账id
                    int integralRunningAccount=0;
                    int giftIntegralRunningAccount=0;
                    YdUser ydUser1 = new YdUser();
                    ydUser1.setId(userId);
                    if(integralByUser>=integral){//充值积分大于等于实付积分
                        ydUser1.setIntegral(integralByUser-integral);
                        ydUser1.setGiftIntegral(giftIntegral);
                        integralRunningAccount=-integral;
                    }else if((integralByUser+giftIntegral)>=integral){
                        ydUser1.setIntegral(0);
                        ydUser1.setGiftIntegral(giftIntegral-(integral-integralByUser));
                        integralRunningAccount=-integralByUser;
                        giftIntegralRunningAccount=-(integral-integralByUser);
                    }
                    int updateIntegral = ydIntegralMapper.updateIntegral(ydUser1);
                    if(updateIntegral == 1){
                        //流水账
                        YdUserFlowAccount ydUserFlowAccount = new YdUserFlowAccount();
                        ydUserFlowAccount.setUserId(userId);
                        ydUserFlowAccount.setType(4);
                        ydUserFlowAccount.setTypeShow("消费");
                        ydUserFlowAccount.setRenminbi(new BigDecimal(-actualPayment+""));
                        ydUserFlowAccount.setIntegral(integralRunningAccount);
                        ydUserFlowAccount.setGiftIntegral(giftIntegralRunningAccount);
                        ydUserFlowAccountMapper.insert(ydUserFlowAccount);
                        userRunningAccountId = ydUserFlowAccount.getId();
                    }

                    if(userRunningAccountId == null){
                        stringStringHashMap.put("code","-5");
                        stringStringHashMap.put("msg","积分扣除失败");
                        return Body.newInstance(stringStringHashMap);
                    }

                    //要更新订单表里的用户流水账id字段
                    ydShopOrdersMapper.updateUserRunningAccountId(orderId,userRunningAccountId);
                    //赠送积分
                    int giftPoints = Integer.parseInt(allGiveIntegralPrice.setScale(0) + "");
                    if(giftPoints>0){
                        ydIntegralMapper.bonusIntegral(userId, giftPoints);
                    }

                    //处理店铺相关数据
                    //余额新增、积分新增、商家流水
                    for (HashMap<String,Object> map :shopRunningAccountInfoList) {
                        long shopId = Long.parseLong(map.get("shopId").toString());
                        double goodsesSalePrice = Double.parseDouble(map.get("goodsesSalePrice").toString());
                        int goodsesIntegralPrice = Integer.parseInt(map.get("goodsesIntegralPrice").toString());
                        double postage = Double.parseDouble(map.get("postage").toString());
                        BigDecimal money = new BigDecimal(goodsesSalePrice + "").setScale(2).add(new BigDecimal(postage + "").setScale(2));
                        ydShopMapper.updateBalanceAndIntegral(shopId,bigDecimalToDouble(money),goodsesIntegralPrice);
                        YdShopFlowAccount ydShopFlowAccount = new YdShopFlowAccount();
                        ydShopFlowAccount.setShopId(shopId);
                        ydShopFlowAccount.setType(1);
                        ydShopFlowAccount.setTypeShow("卖出");
                        ydShopFlowAccount.setOrderId(orderId);
                        ydShopFlowAccount.setMoney(money);
                        ydShopFlowAccount.setIntegral(goodsesIntegralPrice);
                        ydShopFlowAccountMapper.insert(ydShopFlowAccount);
                    }

                    //商品库存减掉
                    ydShopGoodsMapper.updateStock(ydShopOrderGoods);
                    //删除购物车内商品
                    ydShoppingCartGoodsMapper.deleteSomeShoppingCartGoods(list);
                    stringStringHashMap.put("code","1");
                    stringStringHashMap.put("msg","支付成功");
                    YdShopOrderProcess ydShopOrderProcess = new YdShopOrderProcess();
                    ydShopOrderProcess.setSoId(orderId);
                    ydShopOrderProcess.setContent("待发货");
                    ydShopOrderProcess.setReason("未发货");
                    ydShopOrderProcess.setOrderStatus(2);
                    ydShopOrdersMapper.insertOrderProcess(ydShopOrderProcess);

                    //从自动取消订单的延迟队列里和redis里删除
                    ydShopOrderAutoCancelService.remove(orderId);
                }else{
                    stringStringHashMap.put("code","-6");
                    stringStringHashMap.put("msg","支付失败");
                }
            }
            return Body.newInstance(stringStringHashMap);
        }else{
            return Body.newInstance(stringStringHashMap);
        }
    }

    public int bigDecimalToInt(BigDecimal bd){
        bd = bd.setScale(0);
        if(bd.toString().startsWith("0")){
            return 0;
        }else{
            return Integer.parseInt(bd+"");
        }
    }

    public double bigDecimalToDouble(BigDecimal bd){
        if(bd.toString().startsWith("0")){
            return 0;
        }else{
            return Double.parseDouble(bd+"");
        }
    }



    /**
     * 扣除积分
     * @param userId
     * @param integral
     * @param integralByUser
     * @param giftIntegral
     * @return
     */
    private Long  deductionIntegral(long userId,double actualPayment,int integral,int integralByUser,int giftIntegral){
        int integralRunningAccount=0;
        int giftIntegralRunningAccount=0;
        YdUser ydUser = new YdUser();
        ydUser.setId(userId);
        if(integralByUser>=integral){//充值积分大于等于实付积分
            ydUser.setIntegral(integralByUser-integral);
            integralRunningAccount=-integral;
        }else if((integralByUser+giftIntegral)>=integral){
            ydUser.setIntegral(0);
            ydUser.setGiftIntegral(giftIntegral-(integral-integralByUser));
            integralRunningAccount=-integralByUser;
            giftIntegralRunningAccount=-(integral-integralByUser);
        }
        int updateIntegral = ydIntegralMapper.updateIntegral(ydUser);
        if(updateIntegral == 1){
            //流水账
            Long userRunningAccountId = this.addRunningAccount(userId, 4, "消费", -actualPayment, integralRunningAccount, giftIntegralRunningAccount);
            return userRunningAccountId;
        }else{
            return null;
        }
    }

    /**
     * 增加奖励积分
     * @param userId
     * @param integral
     * @return
     */
    private boolean bonusIntegral(long userId, int integral){
        int bonusIntegral = ydIntegralMapper.bonusIntegral(userId, integral);
        return bonusIntegral == 1?true:false;
    }

    /**
     * 记录用户流水账
     * @param userId
     * @param type
     * @param typeShow
     * @param renminbi
     * @param integral
     * @param giftIntegral
     * @return
     */
    private Long addRunningAccount(long userId,int type,String typeShow,double renminbi,int integral,int giftIntegral){
        YdUserFlowAccount ydUserFlowAccount = new YdUserFlowAccount();
        ydUserFlowAccount.setUserId(userId);
        ydUserFlowAccount.setType(type);
        ydUserFlowAccount.setTypeShow(typeShow);
        ydUserFlowAccount.setRenminbi(new BigDecimal(renminbi+""));
        ydUserFlowAccount.setIntegral(integral);
        ydUserFlowAccount.setGiftIntegral(giftIntegral);
        ydUserFlowAccountMapper.insert(ydUserFlowAccount);
        return ydUserFlowAccount.getId();
    }

    /**
     * 处理店铺相关信息
     * @param list
     * @return
     */
    private void handleShopInfo(List<HashMap<String,Object>> list,long orderId){
        //余额新增、积分新增、商家流水
        for (HashMap<String,Object> map :list) {
            long shopId = Long.parseLong(map.get("shopId").toString());
            double goodsesSalePrice = Double.parseDouble(map.get("goodsesSalePrice").toString());
            int goodsesIntegralPrice = Integer.parseInt(map.get("goodsesIntegralPrice").toString());;
            int postage = Integer.parseInt(map.get("postage").toString());
            BigDecimal money = new BigDecimal(goodsesSalePrice + "").setScale(2).add(new BigDecimal(postage + "").setScale(2));
            ydShopMapper.updateBalanceAndIntegral(shopId,Double.parseDouble(money+""),goodsesIntegralPrice);
            YdShopFlowAccount ydShopFlowAccount = new YdShopFlowAccount();
            ydShopFlowAccount.setOrderId(orderId);
            ydShopFlowAccount.setMoney(money);
            ydShopFlowAccount.setIntegral(goodsesIntegralPrice);
            ydShopFlowAccountMapper.insert(ydShopFlowAccount);
        }
    }




}
