package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdSellerDiscountDraw;

import java.util.List;

public interface YdSellerDiscountDrawService {
    /**
     * 查询店铺所有优惠券
     * @param shopId
     * @param userId
     * @return
     */
    List<YdSellerDiscountDraw> getDiscountOfShop(Integer shopId,Integer userId);

    /**
     * 新增
     * @param userId
     * @param discountId
     * @return
     */
    Body insert(Integer userId, Integer discountId);
}
