package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdFoodOrder;

import java.util.List;
import java.util.Map;

public interface YdFoodOrderMapper {
    int insert(YdFoodOrder record);

    /**
     * 修改订单为已支付，记录支付信息
     * @param record
     * @return
     */
    int updateStatusAndPayWay(YdFoodOrder record);

    /**
     * 修改订单状态
     * @param record
     * @return
     */
    int updateStatus(YdFoodOrder record);

    /**
     * 根据id查询订单
     * @return
     */
    YdFoodOrder selectById(YdFoodOrder record);

    /**
     * 真·根据id查询订单
     * @param id
     * @return
     */
    YdFoodOrder selectByTrueId(Integer id);

    /**
     * 根据订单编号查询订单
     * @param orderNum
     * @return
     */
    YdFoodOrder selectByOrderNum(Long orderNum);

    List<YdFoodOrder> selectAll();
}