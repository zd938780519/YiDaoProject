package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShop;
import com.ruoyi.system.domain.YdShoppingCartGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车商品数据库访问层
 */
public interface YdShoppingCartGoodsMapper {

    /**
     * 查询购物车商品
     * @return
     */
    public List<YdShop> selectShoppingCartGoods(long basketId);

    /**
     * 根据商品ids查询商品信息
     * @param list
     * @return
     */
    public List<YdShop> selectGoodsByIds(List<Long> list);

    /**
     * 查询用户是否已添加过此商品
     * @param userId
     * @param goodsId
     * @param sgpId
     * @return
     */
    public YdShoppingCartGoods selectShoppingCartGoodsByUserIdAndGoodsIdAndShopGoodsParameterId(@Param("userId") long userId,
                                                                                                @Param("goodsId") long goodsId,
                                                                                                @Param("sgpId") long sgpId);

    /**
     * 查询用户是否已添加过此商品
     * @param basketId
     * @param goodsId
     * @param sgpId
     * @return
     */
    public YdShoppingCartGoods selectShoppingCartGoodsByBasketIdAndGoodsIdAndShopGoodsParameterId(@Param("basketId") long basketId,
                                                                                                @Param("goodsId") long goodsId,
                                                                                                @Param("sgpId") long sgpId);


    /**
     * 新增购物车商品
     * @param ydShoppingCartGoods
     * @return
     */
    public int insertShoppingCartGoods(YdShoppingCartGoods ydShoppingCartGoods);

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
    public int deleteSomeShoppingCartGoods(@Param("list") List<Long> list);


    /**
     * 逻辑删除购物车商品
     * @param list
     * @return
     */
    public int logicalDeleteSomeShoppingCartGoods(@Param("list") List<Long> list);
    /**
     * 修改购物车商品参数
     * @param ydShoppingCartGoods
     * @return
     */
    public int updateShoppingCartGoodsParameter(YdShoppingCartGoods ydShoppingCartGoods);

    /**
     * 根据订单id删除购物车商品
     * @param orderId
     * @return
     */
    public int deleteCartGoodsByOrderId(long orderId);


}
