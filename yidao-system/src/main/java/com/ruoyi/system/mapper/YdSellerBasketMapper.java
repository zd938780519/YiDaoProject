package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerBasket;
import java.util.List;
import java.util.Map;

public interface YdSellerBasketMapper {

    /**
     * 周边主页查询购物车信息
     * @param param
     * @return
     */
    YdSellerBasket getBasketByUserAndShop(Map<String,Integer> param);

    /**
     * 周边主页查询购物车信息(字段较多，订单用)
     * @param param
     * @return
     */
    YdSellerBasket getBasketByUserAndShopOfOrder(Map<String,Integer> param);

    /**
     * 根据用户id查询购物车信息
     * @param userId
     * @return
     */
    YdSellerBasket getBasketByUserId(Integer userId);

    /**
     * 新增购物车信息并返回主键id
     * @param record
     * @return
     */
    int insertBasketReturnId(YdSellerBasket record);

    List<YdSellerBasket> selectAll();
}