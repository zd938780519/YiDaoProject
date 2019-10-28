package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShopOrderAutoEntity;

public interface YdShopOrderAutoCancelService {
    /**
     * 取消订单
     */
    public void start();


    /**
     * 添加待取消订单
     * @param entity
     */
    public void add(YdShopOrderAutoEntity entity);

    /**
     * 添加待取消订单
     * @param orderId 订单号
     * @param timeout 过期时间
     */
    public void add(long orderId,long timeout);

    /**
     * 移除
     * @param entity
     */
    public void remove(YdShopOrderAutoEntity entity);

    /**
     * 移除
     * @param orderId 订单号
     * @param timeout
     */
    public void remove(long orderId,long timeout);

    /**
     * 移除
     * @param orderId 订单号
     */
    public void remove(long orderId);
}
