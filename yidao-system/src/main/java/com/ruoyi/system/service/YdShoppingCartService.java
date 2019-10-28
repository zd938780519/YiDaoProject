package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShoppingCart;

public interface YdShoppingCartService {
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
