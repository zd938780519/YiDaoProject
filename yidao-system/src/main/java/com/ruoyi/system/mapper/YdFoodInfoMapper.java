package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdFoodInfo;
import java.util.List;

public interface YdFoodInfoMapper {
    int insert(List<YdFoodInfo> record);

    /**
     * 根据订单id和卖家id查询菜品信息
     * @param record
     * @return
     */
    List<YdFoodInfo> selectByShopIdAndOrderId(YdFoodInfo record);

    /**
     * 逻辑删除菜品表
     * @param foId
     * @return
     */
    int delete(Integer foId);

    List<YdFoodInfo> selectAll();
}