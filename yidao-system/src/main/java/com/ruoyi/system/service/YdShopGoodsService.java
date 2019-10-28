package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShopGoods;

import java.util.HashMap;
import java.util.List;

public interface YdShopGoodsService {
    //后台管理系统
    /**
     * sId
     * 获取所有商品
     * @return
     */
    public List<YdShopGoods> getAll(YdShopGoods shopGoods);

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
    public List<YdShopGoods> selectGoods(int currIndex, int pageSize);

    /**
     * 获取单个商品详情
     * @param goodsId
     * @return
     */
    public HashMap<String,Object> selectGoodsById(long goodsId);

    /**
     *
     * 模糊查询商品-->根据总销量、月销量、浏览量排序
     * @param content
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdShopGoods> selectGoodsByName(String content,int currIndex, int pageSize);

    /**
     * 查询商品参数
     * @param goodsId
     * @return
     */
    public YdShopGoods selectGoodsParameters(long goodsId);

}
