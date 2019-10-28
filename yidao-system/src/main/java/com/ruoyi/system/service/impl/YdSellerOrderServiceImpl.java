package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.FlagUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.YdParameterUtils;
import com.ruoyi.common.utils.formatUtil.SellerOrderUtil;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.YdSellerBaskerService;
import com.ruoyi.system.service.YdSellerOrderAutoCancelService;
import com.ruoyi.system.service.YdSellerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class YdSellerOrderServiceImpl implements YdSellerOrderService {
    @Autowired(required = false)
    private YdSellerBasketMapper basketMapper;
    @Autowired
    private YdSellerBaskerService baskerService;
    @Autowired(required = false)
    private YdSellerBasketGoodsMapper basketGoodsMapper;
    @Autowired(required = false)
    private YdSellerDiscountDrawMapper discountDrawMapper;
    @Autowired(required = false)
    private YdFlagMapper flagMapper;
    @Autowired(required = false)
    private YdSellerShopMapper shopMapper;
    @Autowired(required = false)
    private YdSellerOrderMapper orderMapper;
    @Autowired(required = false)
    private YdSellerOrderItemMapper orderItemMapper;
    @Autowired(required = false)
    private YdSellerFlowAccountMapper flowAccountMapper;
    @Autowired(required = false)
    private YdSellerHotGoodsMapper hotGoodsMapper;
    @Autowired
    private YdSellerOrderAutoCancelService orderAutoCancelService;
    /**
     * 确认订单
     * @param userId
     * @param shopId
     * @return
     */
    @Override
    public Body affirmOrder(Integer userId, Integer shopId) throws ParseException {
        //查询购物车信息，验证商品有效期
        Map<String,Integer> param = new HashMap<>();
        param.put("userId",userId);
        param.put("shopId",shopId);
        YdSellerBasket basket = basketMapper.getBasketByUserAndShop(param);
        if(basket == null)return Body.newInstance(400,"购物车信息不存在。");
        for(YdSellerBasketGoods basketGoods : basket.getBasketGoodsList()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = sdf.format(new Date());
            if(basketGoods.getHotGoods().getComboEndTime().compareTo(sdf.parse(nowDate)) == -1)
                return Body.newInstance(400,"商品“" + basketGoods.getHotGoods().getHotName() + "”已超出使用时间，无法下单。");
        }
        //计算购物车金额
        basket = baskerService.calculateBasketInfo(basket);
        //查询是否存在可用红包
        Map<String,Object> disParam = new HashMap<>();
        disParam.put("shopId",shopId);
        disParam.put("userId",userId);
        disParam.put("condition",basket.getSalePrice());
        List<YdSellerDiscountDraw> discountDrawList = discountDrawMapper.getDiscountByCondition(disParam);

        Map<String,Object> info = new HashMap<>();
        info.put("basketInfo",basket);
        info.put("discountInfo",discountDrawList);

        return Body.newInstance(info);
    }
    /**
     * 生成订单
     * @param userId
     * @param shopId
     * @param baskerId
     * @param discountDrawId
     * @param payWay
     * @return
     */
    @Override
    public Body createOrder(Integer userId, String userName,Integer shopId, Integer baskerId,Integer discountDrawId,Integer payWay) throws ParseException {
        //一、查询购物车信息
        Map<String,Integer> param = new HashMap<>();
        param.put("userId",userId);
        param.put("shopId",shopId);
        YdSellerBasket basket = basketMapper.getBasketByUserAndShopOfOrder(param);
        if(basket == null)return Body.newInstance(400,"购物车信息不存在");
        //二、验证红包是否可用
        BigDecimal discountCondition = new BigDecimal("0");
        BigDecimal discountPrice = new BigDecimal("0");
        //如果红包id为null，跳过验证
        if(discountDrawId == null) {
            YdSellerDiscountDraw discountDraw = discountDrawMapper.getDiscountById(discountDrawId);
            if (discountDraw == null) return Body.newInstance(400, "优惠券不存在");
            if (discountDraw.getDiscount().getStatus() != 1) return Body.newInstance(400, "检测到异常数据：优惠券不是上架状态");
            if (discountDraw.getStatus() == -1) return Body.newInstance(400, "检测到异常数据：优惠券已被删除");
            if (discountDraw.getStatus() == 1) return Body.newInstance(400, "检测到异常数据：优惠券已被使用");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = sdf.format(new Date());
            String disEndDate = sdf.format(discountDraw.getDiscount().getEndTime());
            if (sdf.parse(disEndDate).compareTo(sdf.parse(nowDate)) == -1) return Body.newInstance(400, "优惠券已过期");
            discountCondition = discountDraw.getDiscount().getDiscountCondition();
            discountPrice = discountDraw.getDiscount().getDiscountMoney();
        }
        //三、计算购物车金额，并减去红包优惠金额（若小于0，就按照0计算）计算各种优惠力度
        basket = baskerService.calculateBasketInfo(basket);
        if(discountCondition.compareTo(basket.getSalePrice()) == 1)
            return Body.newInstance(400,"检测到异常数据：优惠券不满足使用条件");
        //活动抵扣金额
        BigDecimal activityPrice = basket.getTotalPrice().subtract(basket.getSalePrice());
        if(activityPrice.signum() == -1)return Body.newInstance(400,"检测到异常数据：购物车商品总售价大于总原价");
        //应付总价
        BigDecimal salePrice = basket.getSalePrice().subtract(discountPrice);
        if(salePrice.signum() == -1)salePrice = new BigDecimal("0");
        //四、生成订单编号
        Long orderNum = FlagUtil.createOrderNum(FlagUtil.SELL_ORDER,flagMapper.findFlag());
        //五、新增订单主表信息并返回自增id
        //查询店铺表数据
        YdSellerShop shop = shopMapper.getById(shopId);
        //新增订单主表
        YdSellerOrder order = new YdSellerOrder();
        order.setUserId(userId);
        order.setUserName(userName);
        order.setSellerId(shopId);
        order.setSellerName(shop.getShopName());
        order.setSellerAddress(shop.getAddress());
        order.setSellerPhone(shop.getPhone());
        order.setOrderNum(orderNum);
        order.setCouponMoney(discountPrice);
        order.setActivityMoney(activityPrice);
        order.setAmountPayable(salePrice);
        order.setTotalAmount(basket.getTotalPrice());
        order.setType(SellerOrderUtil.SELLER);
        orderMapper.insert(order);
        //六、新增订单子表信息；统计、更新店铺和商品销量信息
        //循环购物车数据拿到商品信息
        List<YdSellerOrderItem> orderItemList = new ArrayList<>();
        //商品数量集合
        List<Map<String,Integer>> countList = new ArrayList<>();
        //统计总数量
        Integer totalCount = 0;
        for(YdSellerBasketGoods basketGoods : basket.getBasketGoodsList()){
            //获取商品id和商品数量（用于计算销量）;统计总数量
            Map<String,Integer> countParam = new HashMap<>();
            countParam.put("id",basketGoods.getHotGoods().getId());
            countParam.put("sellerCount",basketGoods.getOrderCount());
            countList.add(countParam);
            totalCount += basketGoods.getOrderCount();
            //构建订单子表实体
            YdSellerOrderItem orderItem = new YdSellerOrderItem();
            orderItem.setSellerId(shopId);
            orderItem.setOrderId(order.getId());
            orderItem.setDishId(basketGoods.getHotGoods().getId());
            orderItem.setDishName(basketGoods.getHotGoods().getHotName());
            orderItem.setThumbnailPath(basketGoods.getHotGoods().getPhotoPath());
            orderItem.setFoodNum(basketGoods.getOrderCount());
            orderItem.setOriginalPrice(basketGoods.getOrderPrice());
            orderItem.setActualPrice(basketGoods.getOrderSalePrice());
            orderItem.setDiscount(basketGoods.getHotGoods().getDiscount());
            orderItem.setComboGoods(basketGoods.getHotGoods().getComboGoods());
            orderItem.setComboStartTime(basketGoods.getHotGoods().getComboStartTime());
            orderItem.setComboEndTime(basketGoods.getHotGoods().getComboEndTime());
            orderItem.setComboTimeOn(basketGoods.getHotGoods().getComboTimeOn());
            orderItem.setComboTimeOff(basketGoods.getHotGoods().getComboTimeOff());
            orderItem.setComboRemark(basketGoods.getHotGoods().getComboRemark());
            orderItemList.add(orderItem);
        }
        //添加订单子表信息
        orderItemMapper.insert(orderItemList);

        //七、修改优惠券使用信息，清空购物车
        //修改优惠券使用信息
        if(discountDrawId == null) {
            YdSellerDiscountDraw updateDiscountDraw = new YdSellerDiscountDraw();
            updateDiscountDraw.setId(discountDrawId);
            updateDiscountDraw.setOrderId(order.getId());
            updateDiscountDraw.setUseTime(new Date());
            discountDrawMapper.update(updateDiscountDraw);
        }
        //清空购物车
        basketGoodsMapper.deleteAll(basket.getId());
        //八、订单加入延时队列
        YdSellerOrderAutoEntity orderAutoEntity = new YdSellerOrderAutoEntity(orderNum,System.currentTimeMillis() + YdParameterUtils.ORDER_OVERDUE_TIME);
        orderAutoCancelService.add(orderAutoEntity);
        //九、调用支付接口

        //十、生成二维码

        //十一、支付成功，修改订单状态为已支付、支付显示值、支付时间等
        if(true){
            //十二、支付成功，从延时队列移除
            orderAutoCancelService.remove(orderAutoEntity);
            //判断商品集合（销量）
            if(!countList.isEmpty()){
                //批量更新商品销量信息
                hotGoodsMapper.updateOfBatchForSellerCount(countList);
                //更新店铺商品销量信息
                YdSellerShop countShop = new YdSellerShop();
                countShop.setId(shopId);
                countShop.setSellCount(totalCount);
                shopMapper.updateSellerCount(countShop);
            }
            YdSellerOrder updateOrder = new YdSellerOrder();
            updateOrder.setId(order.getId());
            updateOrder.setStatus(SellerOrderUtil.UNUSE);
            updateOrder.setPayWay(payWay);
            //TODO 尝试获取支付接口返回的数据
            updateOrder.setPayWayShow(null);
            updateOrder.setPayWayTime(new Date());
            orderMapper.updateStatusAndPayWay(updateOrder);
            //十三、产生订单流水
            YdSellerFlowAccount flowAccount = new YdSellerFlowAccount();
            flowAccount.setSellerId(shopId);
            flowAccount.setUserId(userId);
            flowAccount.setIsTakeOut(SellerOrderUtil.UNTAKE_OUT);
            flowAccount.setType(SellerOrderUtil.SELLER_ORDER);
            flowAccount.setTypeShow(SellerOrderUtil.SELLER_ORDER_TEXT);
            flowAccount.setMoney(salePrice);
            flowAccount.setOrderNum(orderNum);
            flowAccount.setPaymentMethod(payWay);
            flowAccountMapper.insert(flowAccount);
            //开始进行使用时间倒计时，加入延时队列
            orderAutoCancelService.add(orderNum,System.currentTimeMillis() + YdParameterUtils.ORDER_USE_TIME_OUT);
            //TODO 十四、向商家端推送定单信息

            return Body.newInstance(200,"这里要返回二维码信息");
        }
        return Body.newInstance(200,"用户尚未支付");
    }

    /**
     * 使用订单
     * @param shopId
     * @param orderNum
     * @return
     */
    @Override
    public Body useOrder(Integer shopId, Long orderNum) {
        Map<String,Object> param = new HashMap<>();
        param.put("sellerId",shopId);
        param.put("orderNum",orderNum);
        YdSellerOrder order  = orderMapper.selectByIdAndOrderNum(param);
        if(order == null)return Body.newInstance(400,"检测到异常数据：订单不存在");
        if(order.getStatus() != SellerOrderUtil.UNUSE)return Body.newInstance(400,"检测到异常数据：订单状态异常");
        //TODO 验证套餐使用时间问题
        //修改订单状态
        YdSellerOrder updateOrder = new YdSellerOrder();
        updateOrder.setId(order.getId());
        updateOrder.setStatus(SellerOrderUtil.UNEVAL);
        orderMapper.updateStatus(updateOrder);
        //使用成功，从延时队列移除
        orderAutoCancelService.remove(orderNum);
        return Body.BODY_200;
    }

    /**
     * 取消订单
     * @param orderNum
     * @return
     */
    @Override
    public Body cancelOrder(Long orderNum,String remark) {
        if(StringUtils.isBlank(remark))remark = "用户主动取消订单";
        YdSellerOrder order  = orderMapper.selectByOrderNum(orderNum);
        if(order == null)return Body.newInstance(400,"检测到异常数据：订单不存在");
        if(order.getStatus() != SellerOrderUtil.UNPAID)return Body.newInstance(400,"检测到异常数据：订单状态异常");
        //逻辑删除主表信息
        YdSellerOrder updateOrder = new YdSellerOrder();
        updateOrder.setId(order.getId());
        updateOrder.setStatus(-1);
        updateOrder.setRemark(remark);
        orderMapper.updateStatus(updateOrder);
        //逻辑删除子表信息
        orderItemMapper.delete(order.getId());
        return Body.BODY_200;
    }

//    /**
//     * 周边商品点赞
//     * @param userId
//     * @param goodId
//     * @return
//     */
//    @Override
//    public Body giveLike(Integer userId, Integer goodId) {
//        YdSellerUserLikePost userLikePost = new YdSellerUserLikePost();
//        userLikePost.setUserId(userId);
//        userLikePost.setHotGoodId(goodId);
//        userLikePostMapper.insert(userLikePost);
//        return Body.newInstance(userLikePost.getId());
//    }
//
//    /**
//     * 周边商品取消赞
//     * @param id
//     * @return
//     */
//    @Override
//    public Body cancelLike(Integer id) {
//        userLikePostMapper.delete(id);
//        return Body.BODY_200;
//    }
}
