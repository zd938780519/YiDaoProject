package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdSellerHotGoods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface YdSellerHotGoodsService {
    /**
     * 视频点击进入周边主页（根据商品id查询商品和店铺信息）
     * @param id
     * @return
     */
    Map<String, Object> getShopAndGoodByGoodId(int id, Integer userId, Double lng, Double lat, HttpServletRequest request);

    /**
     * 搜索框检索商品
     * @param shopId
     * @param searchName
     * @return
     */
    List<YdSellerHotGoods> searchGoods(Integer shopId,String searchName,Integer currIndex,Integer pageSize);

    /**
     * 查看商品详情
     * @return
     */
    Body getGoodDetails(Integer goodId,Double lng,Double lat);

    /**
     * 查看商品评价
     * @param goodId
     * @param type
     * @return
     */
    Body getGoodComments(Integer goodId,Integer type,Integer currIndex,Integer pageSize);
}
