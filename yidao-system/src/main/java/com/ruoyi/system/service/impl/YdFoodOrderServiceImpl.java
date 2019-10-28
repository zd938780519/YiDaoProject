package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.YdParameterUtils;
import com.ruoyi.common.utils.formatUtil.DistributionUtil;
import com.ruoyi.common.utils.formatUtil.FoodOrderUtil;
import com.ruoyi.common.utils.FlagUtil;
import com.ruoyi.common.utils.formatUtil.LocationUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.YdFoodOrderService;
import com.ruoyi.system.service.YdSellerBaskerService;
import com.ruoyi.system.service.YdSellerOrderAutoCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class YdFoodOrderServiceImpl implements YdFoodOrderService {
    @Autowired(required = false)
    private YdFoodOrderMapper foodOrderMapper;
    @Autowired(required = false)
    private YdSellerBasketMapper basketMapper;
    @Autowired
    private YdSellerBaskerService baskerService;
    @Autowired(required = false)
    private YdSellerDiscountDrawMapper discountDrawMapper;
    @Autowired(required = false)
    private YdSellerShopMapper shopMapper;
    @Autowired(required = false)
    private YdFoodDistributionInfoMapper distributionInfoMapper;
    @Autowired(required = false)
    private YdFlagMapper flagMapper;
    @Autowired(required = false)
    private YdFoodInfoMapper foodInfoMapper;
    @Autowired(required = false)
    private YdSellerBasketGoodsMapper basketGoodsMapper;
    @Autowired(required = false)
    private YdUserMapper userMapper;
    @Autowired(required = false)
    private YdFoodOrderDetailMapper foodOrderDetailMapper;
    @Autowired(required = false)
    private YdSellerHotGoodsMapper hotGoodsMapper;
    @Autowired(required = false)
    private YdSellerFlowAccountMapper flowAccountMapper;
    @Autowired
    private YdSellerOrderAutoCancelService sellerOrderAutoCancelService;
    /**
     * 外卖模块确认订单
     * @param userId
     * @param shopId
     * @return
     */
    @Override
    public Body foodAffirmOrder(Integer userId, Integer shopId) {
        //查询购物车信息
        Map<String,Integer> param = new HashMap<>();
        param.put("userId",userId);
        param.put("shopId",shopId);
        YdSellerBasket basket = basketMapper.getBasketByUserAndShop(param);
        if(basket == null)return Body.newInstance(400,"购物车信息不存在。");
        //验证店铺营业时间
        YdSellerShop shop = shopMapper.getById(shopId);
        LocalTime localTime = LocalTime.now();
        if(localTime.isBefore(LocalTime.parse(shop.getTimeOn())) || localTime.isAfter(LocalTime.parse(shop.getTimeOff())))
            return Body.newInstance(400,"不在店铺营业时间内");

        //计算购物车金额
        basket = baskerService.calculateBasketInfo(basket);
        //验证是否达到起送金额
        if(shop.getStartMoney().compareTo(basket.getTotalPrice()) == 1)
            return Body.newInstance(400,"未达到店铺起送金额");
        //查询是否存在可用红包
        Map<String,Object> disParam = new HashMap<>();
        disParam.put("shopId",shopId);
        disParam.put("userId",userId);
        disParam.put("condition",basket.getSalePrice());
        List<YdSellerDiscountDraw> discountDrawList = discountDrawMapper.getDiscountByCondition(disParam);

        //查询默认地址
        YdFoodDistributionInfo distributionInfo = distributionInfoMapper.selectDefault(userId);

        Map<String,Object> info = new HashMap<>();
        info.put("shopName",shop.getShopName());
        info.put("basketInfo",basket);
        info.put("discountInfo",discountDrawList);
        if(distributionInfo != null){
            info.put("distributionInfo",distributionInfo);

            //计算距离
            Double location = LocationUtils.getDistance(Double.parseDouble(shop.getLongitude()),Double.parseDouble(shop.getLatitude()),
                    Double.parseDouble(distributionInfo.getLongitude()),Double.parseDouble(distributionInfo.getLatitude()));
            //超出配送距离，给出提示
            if(location > DistributionUtil.MAX_DISTANCE) {
                distributionInfo.setIsBeyond(1);
            }else {
                //计算配送费和配送距离
                info.put("time", DistributionUtil.calculateTime(location));
                info.put("money", DistributionUtil.calculateMoney(location));
            }
        }
        return Body.newInstance(info);
    }
    /**
     * 外卖模块生成订单
     * @param userId
     * @param shopId
     * @param baskerId
     * @param discountDrawId
     * @param payWay
     * @return
     */
    @Override
    public Body foodCreateOrder(Integer userId, Integer shopId, Integer baskerId, Integer discountDrawId,Integer distributionId,String remark,Integer tableWareNum, Integer payWay) {
        //一、查询购物车信息
        Map<String,Integer> param = new HashMap<>();
        param.put("userId",userId);
        param.put("shopId",shopId);
        YdSellerBasket basket = basketMapper.getBasketByUserAndShopOfOrder(param);
        if(basket == null)return Body.newInstance(400,"购物车信息不存在");
        //验证店铺营业时间
        YdSellerShop shop = shopMapper.getById(shopId);
        LocalTime localTime = LocalTime.now();
        if(localTime.isBefore(LocalTime.parse(shop.getTimeOn())) || localTime.isAfter(LocalTime.parse(shop.getTimeOff())))
            return Body.newInstance(400,"不在店铺营业时间内");
        //二、验证红包是否可用
        BigDecimal discountCondition = new BigDecimal("0");
        BigDecimal discountPrice = new BigDecimal("0");
        //如果红包id为null，跳过验证
        if(discountDrawId != null){
            YdSellerDiscountDraw discountDraw = discountDrawMapper.getDiscountById(discountDrawId);
            if(discountDraw == null)return Body.newInstance(400,"优惠券不存在");
            if(discountDraw.getDiscount().getStatus() != 1)return Body.newInstance(400,"检测到异常数据：优惠券不是上架状态");
            if(discountDraw.getStatus() == -1)return Body.newInstance(400,"检测到异常数据：优惠券已被删除");
            if(discountDraw.getStatus() == 1)return Body.newInstance(400,"检测到异常数据：优惠券已被使用");
            LocalDate disEndDate = discountDraw.getDiscount().getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate nowDate = LocalDate.now();
            if(disEndDate.compareTo(nowDate) == -1)return Body.newInstance(400,"优惠券已过期");
            discountCondition = discountDraw.getDiscount().getDiscountCondition();
            discountPrice = discountDraw.getDiscount().getDiscountMoney();
        }
        //三、验证地址是否存在，计算配送费和配送距离，验证距离是否合理
        Map<String,Object> disParam = new HashMap<>();
        disParam.put("id",distributionId);
        disParam.put("userId",userId);
        YdFoodDistributionInfo distributionInfo = distributionInfoMapper.selectByIdAndUserId(disParam);
        if(distributionInfo == null)return Body.newInstance(400,"地址不存在");
        //计算距离
        Double location = LocationUtils.getDistance(Double.parseDouble(shop.getLongitude()),Double.parseDouble(shop.getLatitude()),
                Double.parseDouble(distributionInfo.getLongitude()),Double.parseDouble(distributionInfo.getLatitude()));
        if(location > DistributionUtil.MAX_DISTANCE)return Body.newInstance(400,"检测到异常数据：超出配送距离");
        //计算配送费
        Double distributionMoney = DistributionUtil.calculateMoney(location);
        //四、计算购物车金额，加上包装费和配送费，减去红包优惠金额（若小于0，就按照0计算）计算各种优惠力度
        basket = baskerService.calculateBasketInfo(basket);
        if(discountCondition.compareTo(basket.getSalePrice()) == 1)
            return Body.newInstance(400,"检测到异常数据：优惠券不满足使用条件");
        //活动抵扣金额
        BigDecimal activityPrice = basket.getTotalPrice().subtract(basket.getSalePrice());
        if(activityPrice.signum() == -1)return Body.newInstance(400,"检测到异常数据：购物车商品总售价大于总原价");
        //应付总价
        BigDecimal salePrice = basket.getSalePrice().subtract(discountPrice).add(basket.getPackingExpense()).add(new BigDecimal(distributionMoney));
        if(salePrice.signum() == -1)salePrice = new BigDecimal("0");
        //总金额
        BigDecimal totalPrice = basket.getTotalPrice().add(basket.getPackingExpense()).add(new BigDecimal(distributionMoney));
        //总金额
        //五、生成订单编号
        Long orderNum = FlagUtil.createOrderNum(FlagUtil.FOOD_ORDER,flagMapper.findFlag());
        //六、新增订单主表信息并返回自增id
        //新增订单主表
        YdFoodOrder order = new YdFoodOrder();
        order.setUserId(userId);
        order.setSellerId(shopId);
        order.setOrderNum(orderNum);
        LocalDateTime distributionDetailTime = DistributionUtil.calculateDetailTime(location);
        order.setEstimatedServiceTime(Date.from(distributionDetailTime.atZone(ZoneId.systemDefault()).toInstant()));
        order.setPackingFee(basket.getPackingExpense());
        order.setDistributionFee(new BigDecimal(distributionMoney));
        if(remark == null)remark = "";
        order.setRemark(remark.replaceAll("\\s*",""));
        order.setTablewareNum(tableWareNum);
        order.setCouponMoney(discountPrice);
        order.setActivityMoney(activityPrice);
        order.setAmountPayable(salePrice);
        order.setTotalAmount(totalPrice);
        order.setType(FoodOrderUtil.SELLER);
        order.setPayWay(payWay);
        foodOrderMapper.insert(order);
        //七、新增订单菜品信息；统计店铺和商品销量信息
        //循环购物车数据拿到商品信息
        //商品数量集合
        List<Map<String,Integer>> countList = new ArrayList<>();
        //统计总数量
        Integer totalCount = 0;
        List<YdFoodInfo> foodInfoList = new ArrayList<>();
        for(YdSellerBasketGoods basketGoods : basket.getBasketGoodsList()){
            //获取商品id和商品数量（用于计算销量）;统计总数量
            Map<String,Integer> countParam = new HashMap<>();
            countParam.put("id",basketGoods.getHotGoods().getId());
            countParam.put("sellerCount",basketGoods.getOrderCount());
            countList.add(countParam);
            totalCount += basketGoods.getOrderCount();
            //构建订单子表实体
            YdFoodInfo foodInfo = new YdFoodInfo();
            foodInfo.setSellerId(shopId);
            foodInfo.setFoId(order.getId());
            foodInfo.setDishId(basketGoods.getHotGoods().getId());
            foodInfo.setDishName(basketGoods.getHotGoods().getHotName());
            foodInfo.setThumbnailPath(basketGoods.getHotGoods().getPhotoPath());
            foodInfo.setFoodNum(basketGoods.getOrderCount());
            foodInfo.setOriginalPrice(basketGoods.getOrderPrice());
            foodInfo.setActualPrice(basketGoods.getOrderSalePrice());
            foodInfo.setDiscount(basketGoods.getHotGoods().getDiscount());
            foodInfoList.add(foodInfo);
        }
        foodInfoMapper.insert(foodInfoList);

        //八、新增订单详情表信息
        YdUser user = userMapper.selectByIdOfOrder((long)userId);
        YdFoodOrderDetail orderDetail = new YdFoodOrderDetail();
        orderDetail.setFoId(order.getId());
        orderDetail.setUserId(userId);
        orderDetail.setUserName(user.getUserName());
        orderDetail.setUserMobile(user.getPhoneNum());
        orderDetail.setFdiId(distributionId);
        orderDetail.setDistributionShow(distributionInfo.getReceivingAddress());
        orderDetail.setConsignee(distributionInfo.getConsignee());
        orderDetail.setReceivingTelephone(distributionInfo.getReceivingTelephone());
        foodOrderDetailMapper.insert(orderDetail);
        //九、修改优惠券使用信息，清空购物车
        //修改优惠券使用信息
        if(discountDrawId == null) {
            YdSellerDiscountDraw updateDiscountDraw = new YdSellerDiscountDraw();
            updateDiscountDraw.setId(discountDrawId);
            updateDiscountDraw.setUseTime(new Date());
            updateDiscountDraw.setOrderId(order.getId());
            discountDrawMapper.update(updateDiscountDraw);
        }
        //清空购物车
        basketGoodsMapper.deleteAll(basket.getId());
        //八、订单加入延时队列
        sellerOrderAutoCancelService.add(orderNum,System.currentTimeMillis() + YdParameterUtils.ORDER_OVERDUE_TIME);

        //九、调用支付接口

        if(false){
            //十更新销量信息
            if(!countList.isEmpty()){
                //批量更新商品销量信息
                hotGoodsMapper.updateOfBatchForSellerCount(countList);
                //更新店铺商品销量信息
                YdSellerShop countShop = new YdSellerShop();
                countShop.setSellCount(totalCount);
                countShop.setId(shopId);
                shopMapper.updateSellerCount(countShop);
            }
            //十一、支付成功，修改订单状态为已支付、支付显示值、支付时间等
            YdFoodOrder updateOrder = new YdFoodOrder();
            updateOrder.setId(order.getId());
            updateOrder.setStatus(FoodOrderUtil.UNORDER);
            updateOrder.setPayWay(payWay);
            //TODO 尝试获取支付接口返回的数据
            updateOrder.setPayWayShow(null);
            updateOrder.setPayWayTime(new Date());
            foodOrderMapper.updateStatusAndPayWay(updateOrder);
            //十二、产生订单流水
            YdSellerFlowAccount flowAccount = new YdSellerFlowAccount();
            flowAccount.setSellerId(shopId);
            flowAccount.setUserId(userId);
            flowAccount.setIsTakeOut(FoodOrderUtil.TAKE_OUT);
            flowAccount.setType(FoodOrderUtil.FOOD_ORDER);
            flowAccount.setTypeShow(FoodOrderUtil.FOOD_ORDER_TEXT);
            flowAccount.setMoney(salePrice);
            flowAccount.setOrderNum(orderNum);
            flowAccount.setPaymentMethod(payWay);
            flowAccountMapper.insert(flowAccount);
            //十三、向商家端推送定单信息，向骑手端发布订单信息

            return Body.newInstance(200,"等待商家接单");
        }
        return Body.newInstance(200,"用户尚未支付");
    }

    /**
     * 外卖模块卖家接单
     * @param shopId
     * @param orderId
     * @return
     */
    @Override
    public Body sellerTakeOrder(Integer shopId, Integer orderId,Long orderNum) {
        //验证订单是否存在
        YdFoodOrder foodOrder = new YdFoodOrder();
        foodOrder.setId(orderId);
        foodOrder.setSellerId(shopId);
        foodOrder.setOrderNum(orderNum);
        foodOrder = foodOrderMapper.selectById(foodOrder);
        if(foodOrder == null)return Body.newInstance(400,"检测到异常数据：订单不存在或已被删除");
        if(foodOrder.getStatus() != FoodOrderUtil.UNORDER)return Body.newInstance(400,"检测到异常数据：订单状态异常");
        //获取店铺信息
        YdSellerShop shop  = shopMapper.getById(shopId);
        //订单详情表添加卖方信息
        YdFoodOrderDetail orderDetail = new YdFoodOrderDetail();
        orderDetail.setFoId(foodOrder.getId());
        orderDetail.setSellerId(shop.getId());
        orderDetail.setSellerShopname(shop.getShopName());
        orderDetail.setSellerAddress(shop.getAddress());
        orderDetail.setSellerMobile(shop.getPhone());
        foodOrderDetailMapper.updateOfSeller(orderDetail);
        //订单主表修改状态为已接单
        YdFoodOrder updateFoodOrder = new YdFoodOrder();
        updateFoodOrder.setId(orderId);
        updateFoodOrder.setStatus(FoodOrderUtil.UNDISTR);
        foodOrderMapper.updateStatus(updateFoodOrder);
        return Body.BODY_200;
    }
    /**
     * 外卖模块取消订单
     * @param ordernum
     * @param reson
     * @return
     */
    @Override
    public Body cancelOrder(Long ordernum, String reson) {
        if (reson == null)reson = "用户主动取消订单";
        YdFoodOrder foodOrder = foodOrderMapper.selectByOrderNum(ordernum);
        if (foodOrder == null)return Body.newInstance(400,"检测到异常数据：订单不存在或已被删除");
        if(foodOrder.getStatus() != FoodOrderUtil.UNPAID)return Body.newInstance(400,"检测到异常数据：订单状态异常");
        //逻辑删除订单主表信息
        YdFoodOrder updateFoodOrder = new YdFoodOrder();
        updateFoodOrder.setId(foodOrder.getId());
        updateFoodOrder.setStatus(-1);
        updateFoodOrder.setReason(reson);
        foodOrderMapper.updateStatus(updateFoodOrder);
        //逻辑删除菜品表信息
        foodInfoMapper.delete(foodOrder.getId());
        //逻辑删除订单详情表信息
        foodOrderDetailMapper.delete(foodOrder.getId());
        return Body.BODY_200;
    }
}
