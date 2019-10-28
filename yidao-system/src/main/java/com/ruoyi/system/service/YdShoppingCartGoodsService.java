package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShop;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.domain.YdShoppingCartGoods;

import java.util.HashMap;
import java.util.List;

public interface YdShoppingCartGoodsService {

    /**
     *查询购物车商品
     * @param userId
     * @return
     */
    public List<YdShop> getShoppingCartGoods(long userId);

    /**
     * 新增购物车商品
     * @param userId
     * @param shopId
     * @param goodsId
     * @param sgpId
     * @param count
     * @return
     */
    public int insertShoppingCartGoods(long userId,long shopId,long goodsId,long sgpId,int count);

    /**
     * 修改购物车商品数量
     * @param ydShoppingCartGoods
     * @return
     */
    public int updateShoppingCartGoodsOrderCount(YdShoppingCartGoods ydShoppingCartGoods);

    /**
     * 覆盖购物车商品数量
     * @param ydShoppingCartGoods
     * @return
     */
    public int updateShoppingCartGoodsCoverOrderCount(YdShoppingCartGoods ydShoppingCartGoods);

    /**
     * 删除购物车商品
     * @param id
     * @return
     */
    public int deleteShoppingCartGoods(long id);

    /**
     * 批量删除购物车商品
     * @param list
     * @return
     */
    public int deleteSomeShoppingCartGoods(List<Long> list);

    /**
     * 修改购物车商品参数
     * @param ydShoppingCartGoods
     * @return
     */
    public boolean updateShoppingCartGoodsParameter(YdShoppingCartGoods ydShoppingCartGoods);

    /**
     * 根据商品ids查询商品信息
     * @param list
     * @return
     */
    public List<YdShop> selectGoodsByIds(List<Long> list);
}
