package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerOrder;
import java.util.List;
import java.util.Map;

public interface YdSellerOrderMapper {
    /**
     * 新增订单
     * @param record
     * @return
     */
    int insert(YdSellerOrder record);

    /**
     * 修改订单为已支付，记录支付信息
     * @param record
     * @return
     */
    int updateStatusAndPayWay(YdSellerOrder record);

    /**
     * 修改订单状态
     * @param record
     * @return
     */
    int updateStatus(YdSellerOrder record);

    /**
     * 根据店铺id和订单编号查找订单信息
     * @param param
     * @return
     */
    YdSellerOrder selectByIdAndOrderNum(Map<String,Object> param);

    /**
     * 根据订单id查找订单信息
     * @param id
     * @return
     */
    YdSellerOrder selectById(Integer id);

    /**
     * 根据订单编号查找订单信息
     * @param orderNum
     * @return
     */
    YdSellerOrder selectByOrderNum(Long orderNum);
}