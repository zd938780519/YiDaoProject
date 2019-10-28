package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.domain.YdShopOrderGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品相关数据库访问层
 */
public interface YdShopGoodsMapper {
    //后台管理
    /**
     * sId
     * 查询所有商品
     * @return
     */
    public List<YdShopGoods> selectAll(YdShopGoods shopGoods);

    /**
     *
     * 新增商品数据
     * @param shopGoods
     * @return
     */
    public int insertGoods(YdShopGoods shopGoods);

    /**
     *
     * 删除已上架的商品
     * @param id
     * @return
     */
    public int deleteGoods(long id);

    /**
     * 根据id查询图片路径
     * @param id
     * @return
     */
    public String selectPhotoPathById(long id);

    /**
     *
     * 修改商品上架信息
     * @param shopGoods
     * @return
     */
    public int updateGoods(YdShopGoods shopGoods);

    /**
     * 根据订单id查询商品信息
     * @param orderId
     * @return
     */
    public  List<YdShopGoods> selectGoodsByOrderId(long orderId);

    //移动端接口

    /**
     * 获取首页商品-->根据总销量、月销量、浏览量排序
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdShopGoods> selectGoods(@Param("currIndex") int currIndex, @Param("pageSize")  int pageSize);

    /**
     * 获取单个商品详情
     * @param goodsId
     * @return
     */
    public YdShopGoods selectGoodsById(long goodsId);

    /**
     * 模糊查询商品-->根据总销量、月销量、浏览量排序
     * @param content
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdShopGoods> selectGoodsByName(@Param("content") String content,@Param("currIndex") int currIndex, @Param("pageSize")  int pageSize);

    /**
     * 查询商品参数
     * @param goodsId
     * @return
     */
    public YdShopGoods selectGoodsParameters(@Param("goodsId") long goodsId);

    /**
     * 批量修改库存
     * @return
     */
    public int  updateStock(List<YdShopOrderGoods> list);


}
