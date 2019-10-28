package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerDiscount;
import java.util.List;
import java.util.Map;

public interface YdSellerDiscountMapper {
    int insert(YdSellerDiscount record);

    List<YdSellerDiscount> selectAll();

    /**
     * 查询过期优惠券
     * @return
     */
    List<Integer> getPastDue();

    /**
     * 修改优惠券状态为：已过期
     * @param list
     * @return
     */
    int updateToPastDue(List<Integer> list);
}