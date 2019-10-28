package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerBasketGoods;
import java.util.List;
import java.util.Map;

public interface YdSellerBasketGoodsMapper {
    /**
     * 根据购物车id和商品id查询购物子表信息
     * @param param
     * @return
     */
    YdSellerBasketGoods getBasketGoodByBasketIdAndGoodId(Map<String,Integer> param);

    int insert(YdSellerBasketGoods record);

    int update(YdSellerBasketGoods record);

    int delete(Integer id);

    int deleteAll(Integer basketId);

    List<YdSellerBasketGoods> selectAll();
}