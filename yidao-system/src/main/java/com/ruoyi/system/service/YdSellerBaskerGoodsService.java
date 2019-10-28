package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdSellerBasketGoods;

public interface YdSellerBaskerGoodsService {

    /**
     * 根据购物车id和商品id查询购物子表信息
     * @param basketId
     * @param goodId
     * @return
     */
    YdSellerBasketGoods getBasketGoodByBasketIdAndGoodId(Integer basketId, Integer goodId);

    int insert(YdSellerBasketGoods record);

    int update(YdSellerBasketGoods record);

    int delete(Integer id);

    int deleteAll(Integer basketId);
}
