package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdSellerOrderAutoEntity;

public interface YdSellerOrderAutoCancelService {
    /**
     * 取消订单
     */
    void start();


    /**
     * 添加待取消订单
     * @param entity
     */
    void add(YdSellerOrderAutoEntity entity);

    /**
     * 添加待取消订单
     * @param orderNum 订单号
     * @param timeout 过期时间
     */
    void add(long orderNum, long timeout);

    /**
     * 移除
     * @param entity
     */
    void remove(YdSellerOrderAutoEntity entity);

    /**
     * 移除
     * @param orderNum 订单号
     * @param timeout
     */
    void remove(long orderNum, long timeout);

    /**
     * 移除
     * @param orderNum 订单号
     */
    void remove(long orderNum);
}
