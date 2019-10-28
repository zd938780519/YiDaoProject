package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopOrderProcess;
import com.ruoyi.system.domain.YdShopOrders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单相关数据库访问层
 */
public interface YdShopOrdersMapper {

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
    public List<YdShopOrders> selectByType(@Param("shopId") long shopId,@Param("type")  long type);

    /**
     * 关闭订单
     * @param id
     * @return
     */
    public int closeOrders(long id);

    /**
     * 新增订单状态信息
     * @param ydShopOrderProcess
     * @return
     */
    public int insertOrderProcess(YdShopOrderProcess ydShopOrderProcess);

    /**
     * 删除订单状态信息
     * @param soId
     * @return
     */
    public int removeOrdersProcess(long soId);

    /**
     * 删除订单
     * @param id
     * @return
     */
    public int removeOrders(long id);

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
     * 拒绝退款
     * @param id
     * @return
     */
    public int refusalRefund(long id);

    /**
     *
     * 查询当前订单状态
     * @param id
     * @return
     */
    public long selectSopMax(long id);


    //移动端

    /**
     * 新增订单
     * @param ydShopOrders
     * @return
     */
    public int insertOrder(YdShopOrders ydShopOrders);

    /**
     * 修改订单里的用户流水编号
     * @param orderId
     * @param userRunningAccountId
     * @return
     */
    public int updateUserRunningAccountId(@Param("orderId") long orderId,@Param("userRunningAccountId")long userRunningAccountId);

    /**
     * 根据id查询订单
     * @param orderId
     * @return
     */
    public YdShopOrders selectShopOrderById(long orderId);

}
