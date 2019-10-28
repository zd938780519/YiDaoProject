package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopOrderCancelReason;
import java.util.List;

/**
 * 订单取消原因数据库连接层
 */
public interface YdShopOrderCancelReasonMapper {
    int insert(YdShopOrderCancelReason record);

    List<YdShopOrderCancelReason> selectAll();
}