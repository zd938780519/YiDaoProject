package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerShop;
import java.util.List;
import java.util.Map;

public interface YdSellerShopMapper {
    /**
     * 视频点击进入周边主页（搜索附近店铺）
     * @param param
     * @return
     */
    List<YdSellerShop> getShopByDistance(Map<String,Object> param);

    /**
     * 查询店铺和热卖商品列表
     * @param param
     * @return
     */
    YdSellerShop getShowShop(Map<String,Object> param);

    /**
     * 根据id查询店铺信息
     * @param id
     * @return
     */
    YdSellerShop getById(Integer id);

    /**
     * 更新店铺销量数据
     * @param record
     * @return
     */
    int updateSellerCount(YdSellerShop record);

    /**
     * 更新店铺评分数据
     * @param record
     * @return
     */
    int updateScore(YdSellerShop record);

    int insert(YdSellerShop record);

    List<YdSellerShop> selectAll();
}