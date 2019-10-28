package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdSellerShop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface YdSellerShopService {
    /**
     * 视频点击进入周边主页（搜索附近店铺）
     * @param lng
     * @param lat
     * @param currIndex
     * @param pageSize
     * @return
     */
    List<YdSellerShop> getShopByDistance(Double lng, Double lat, Integer currIndex, Integer pageSize);

    /**
     * 查询店铺和热卖商品列表
     * @param shopId
     * @param currIndex
     * @param pageSize
     * @return
     */
    YdSellerShop getShowShop(Integer userId,Integer shopId,Double lng,Double lat,int currIndex,int pageSize,HttpServletRequest request);

    /**
     * 周边店铺收藏
     * @param userId
     * @param shopId
     * @param shopName
     * @return
     */
    Body giveCollect(Integer userId,Integer shopId,String shopName);

    /**
     * 周边店铺取消收藏
     * @param id
     * @return
     */
    Body cancelCollect(Integer userId,Integer id);

    /**
     * 周边店铺取消收藏（批量）
     * @param ids
     * @return
     */
    Body cancelCollectOfBatch(Integer userId,List<String> ids);

    /**
     * 验证session中的店铺id和打开的店铺id是否一致，如不一致则更新session并清空购物车
     * @param session
     * @param shopId
     * @return
     */
    void verifyShop(HttpSession session, Integer userId, Integer shopId);
}
