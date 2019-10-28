package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;

import java.util.HashMap;
import java.util.List;

public interface YdShopSettlementService {
    /**
     * 检查库存
     * @param ids
     * @return
     */
    public HashMap<String,String>  inventoryInspection(List<Long> ids);

    /**
     * 提交订单
     * @param userId
     * @param ids
     * @param receivingAddress
     * @param voucherId
     * @param integral
     * @param type
     * @param remark
     * @param orderId
     * @return
     */
    public Body placeOrder(long userId,List<Long> ids,long receivingAddress,Long voucherId,int integral,int type,String remark,Long orderId);

}
