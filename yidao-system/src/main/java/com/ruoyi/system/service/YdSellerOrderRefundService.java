package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;

import java.util.List;

public interface YdSellerOrderRefundService {
    /**
     *订单退款
     * @param orderNum
     * @param dictIds
     * @return
     */
    Body refundOrder(Long orderNum,List<Integer> dictIds,String dictContent);
    /**
     *订单退款
     * @param orderNum
     * @return
     */
    Body refundOrder(Long orderNum,String dictContent);
}
