package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;

public interface YdFoodDeliverService {
    /**
     * 订单开始配送
     * @param shopId
     * @param orderId
     * @param orderNum
     * @return
     */
    Body sendOrder(Integer shopId, Integer orderId,Integer deliverId, Long orderNum);

    /**
     * 订单已送达
     * @param shopId
     * @param orderId
     * @param deliverId
     * @param orderNum
     * @return
     */
    Body sendOver(Integer shopId, Integer orderId,Integer deliverId, Long orderNum);
}
