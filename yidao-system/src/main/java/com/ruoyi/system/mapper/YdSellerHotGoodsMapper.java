package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerHotGoods;
import java.util.List;
import java.util.Map;

public interface YdSellerHotGoodsMapper {
    /**
     * 视频点击进入周边主页（根据商品id查询商品和店铺信息）
     * @param id
     * @return
     */
    YdSellerHotGoods getShopAndGoodByGoodId(Integer id);

    /**
     * 根据商品id查询商品和店铺信息
     * @param id
     * @return
     */
    YdSellerHotGoods getInfoByGoodId(Integer id);

    /**
     * 根据商品id查询商品信息
     * @param id
     * @return
     */
    YdSellerHotGoods getGoodById(Integer id);

    /**
     * 搜索框检索商品
     * @param param
     * @return
     */
    List<YdSellerHotGoods> searchGoods(Map<String,Object> param);

    /**
     * 批量更新商品总销量数据
     * @param list
     * @return
     */
    int updateOfBatchForSellerCount(List<Map<String,Integer>> list);

    /**
     * 批量更新商品点赞数据
     * @param list
     * @return
     */
    int updateOfBatchForLikeCount(List<Integer> list);

    int insert(YdSellerHotGoods record);

    List<YdSellerHotGoods> selectAll();
}