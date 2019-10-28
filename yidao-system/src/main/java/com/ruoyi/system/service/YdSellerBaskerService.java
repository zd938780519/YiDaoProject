package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdSellerBasket;


public interface YdSellerBaskerService {
    /**
     * 周边主页查询购物车信息
     * @param shopId
     * @param userId
     * @return
     */
    YdSellerBasket getBasketByUserAndShop(Integer userId, Integer shopId);

    /**
     * 点击订餐按钮创建购物车
     * @param userId
     * @param goodId
     * @return
     */
    YdSellerBasket insertBasket(Integer userId,Integer goodId);

    /**
     * 修改商品数量
     * @param userId
     * @param goodId
     * @param operateType
     * @return
     */
    YdSellerBasket updateGoodNumOfBasket(Integer userId,Integer goodId,Integer operateType);

    /**
     * 清空购物车
     * @param userId
     * @param shopId
     * @return
     */
    YdSellerBasket emptyBasket(Integer userId,Integer shopId);

    /**
     * 购物车价格信息计算
     */
    YdSellerBasket calculateBasketInfo(YdSellerBasket basket);
}
