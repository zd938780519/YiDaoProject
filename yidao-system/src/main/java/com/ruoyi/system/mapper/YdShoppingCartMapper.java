package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShoppingCart;

/**
 * 购物车数据库访问层
 */
public interface YdShoppingCartMapper {
    /**
     * 根据用户查询购物车
     * @param userId
     * @return
     */
    public YdShoppingCart selectShoppingCart(long userId);

    /**
     * 新增购物车
     * @param ydShoppingCart
     * @return
     */
    public int insertShoppingCart(YdShoppingCart ydShoppingCart);

}
