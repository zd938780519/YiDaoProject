package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdFoodOrderDetail;
import java.util.List;

public interface YdFoodOrderDetailMapper {
    int insert(YdFoodOrderDetail record);

    /**
     * 卖家接单，添加卖家信息
     * @param record
     * @return
     */
    int updateOfSeller(YdFoodOrderDetail record);

    /**
     * 骑手配送，添加骑手信息
     * @param record
     * @return
     */
    int updateOfDeliver(YdFoodOrderDetail record);

    /**
     * 逻辑删除订单详情信息
     * @param foId
     * @return
     */
    int delete(Integer foId);

    List<YdFoodOrderDetail> selectAll();
}