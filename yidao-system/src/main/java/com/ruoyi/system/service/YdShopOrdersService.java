package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShopOrderProcess;
import com.ruoyi.system.domain.YdShopOrders;

import java.util.List;

public interface YdShopOrdersService {
    /**
     * 全部订单
     * @param shopId
     * @return
     */
    public List<YdShopOrders> selectALl(long shopId);

    /**
     * 根据订单状态查询订单
     * @param shopId
     * @return
     */
    public List<YdShopOrders> selectByType(long shopId,long type);

    /**
     * 关闭订单
     * @param id
     * @return
     */
    public int closeOrders(long id);

    /**
     * 新增订单状态
     * @param ydShopOrderProcess
     * @return
     */
    public int insertOrderProcess(YdShopOrderProcess ydShopOrderProcess);

    /**
     * 删除订单
     * @param id
     * @param type
     * @param typeShow
     * @return
     */
    public int removeOrders(long id,int type,String typeShow);

    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    public YdShopOrders selectOrderInfo(long orderId);

    /**
     * 修改订单备注和应付金额
     * @param ydShopOrders
     * @return
     */
    public int updateRemarkAndMoney(YdShopOrders ydShopOrders);

    /**
     * 查找送货信息
     * @param orderId
     * @return
     */
    public YdShopOrders selectDeliverGoodsInfo(long orderId);

    /**
     * 修改配送信息
     * @param ydShopOrders
     * @return
     */
    public int updateOrderDelivery(YdShopOrders ydShopOrders);

    /**
     * 退款
     * @param id
     * @return
     */
    public int refund(long id);

    /**
     * 拒绝退款
     * @param id
     * @return
     */
    public int refusalRefund(long id);

    /**
     * 查询待付款订单信息
     * @param orderId
     * @return
     */
    public YdShopOrders selectOrderInfoOfUnpaid(long orderId);
}
