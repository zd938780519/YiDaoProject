package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;

import java.text.ParseException;

public interface YdSellerOrderService {
    /**
     * 确认订单
     * @param userId
     * @param shopId
     * @return
     */
    Body affirmOrder(Integer userId,Integer shopId) throws ParseException;

    /**
     * 生成订单
     * @param userId
     * @param shopId
     * @param baskerId
     * @param discountDrawId
     * @param payWay
     * @return
     */
    Body createOrder(Integer userId,String userName,Integer shopId,Integer baskerId,Integer discountDrawId,Integer payWay) throws ParseException;

    /**
     * 使用订单
     * @param shopId
     * @param orderNum
     * @return
     */
    Body useOrder(Integer shopId,Long orderNum);

    /**
     * 取消订单
     * @param orderNum
     * @return
     */
    Body cancelOrder(Long orderNum,String remark);

//    /**
//     * 周边商品点赞
//     * @param userId
//     * @param goodId
//     * @return
//     */
//    Body giveLike(Integer userId,Integer goodId);
//
//    /**
//     * 周边商品取消赞
//     * @param id
//     * @return
//     */
//    Body cancelLike(Integer id);
}
