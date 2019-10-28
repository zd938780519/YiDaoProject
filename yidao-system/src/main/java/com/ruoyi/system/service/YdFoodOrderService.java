package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;

public interface YdFoodOrderService {
    /**
     * 外卖模块确认订单
     * @param userId
     * @param shopId
     * @return
     */
    Body foodAffirmOrder(Integer userId,Integer shopId);

    /**
     * 外卖模块生成订单
     * @param userId
     * @param shopId
     * @param baskerId
     * @param discountDrawId
     * @param payWay
     * @return
     */
    Body foodCreateOrder(Integer userId, Integer shopId, Integer baskerId,Integer discountDrawId,Integer distributionId,String remark,Integer tableWareNum,Integer payWay);

    /**
     * 外卖模块卖家接单
     * @param shopId
     * @param orderId
     * @return
     */
    Body sellerTakeOrder(Integer shopId,Integer orderId,Long orderNum);

    /**
     * 外卖模块取消订单
     * @param ordernum
     * @param reson
     * @return
     */
    Body cancelOrder(Long ordernum,String reson);

}
