package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerOrderItem;
import java.util.List;

public interface YdSellerOrderItemMapper {
    /**
     * 批量新增
     * @param record
     * @return
     */
    int insert(List<YdSellerOrderItem> record);

    /**
     * 根据订单id和店铺id查询子表信息
     * @param record
     * @return
     */
    List<YdSellerOrderItem> selectByOrderIdAndShopId(YdSellerOrderItem record);

    /**
     * 逻辑删除订单子表信息
     * @param orderId
     * @return
     */
    int delete(Integer orderId);
}