package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.formatUtil.LocationUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.YdSellerBasket;
import com.ruoyi.system.domain.YdSellerCollect;
import com.ruoyi.system.domain.YdSellerShop;
import com.ruoyi.system.mapper.YdSellerBasketGoodsMapper;
import com.ruoyi.system.mapper.YdSellerBasketMapper;
import com.ruoyi.system.mapper.YdSellerCollectMapper;
import com.ruoyi.system.mapper.YdSellerShopMapper;
import com.ruoyi.system.service.YdSellerShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YdSellerShopServiceImpl implements YdSellerShopService {
    @Autowired(required = false)
    private YdSellerShopMapper sellerShopMapper;
    @Autowired(required = false)
    private YdSellerBasketMapper basketMapper;
    @Autowired(required = false)
    private YdSellerBasketGoodsMapper basketGoodsMapper;
    @Autowired(required = false)
    private YdSellerCollectMapper collectMapper;
    /**
     * 视频点击进入周边主页（搜索附近店铺）
     * @param lng
     * @param lat
     * @param currIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<YdSellerShop> getShopByDistance(Double lng, Double lat, Integer currIndex, Integer pageSize) {
        Map<String,Object> param = new HashMap<>();
        param.put("lng",lng);
        param.put("lat",lat);
        param.put("currIndex",currIndex);
        param.put("pageSize",pageSize);
        return sellerShopMapper.getShopByDistance(param);
    }
    /**
     * 查询店铺和热卖商品列表
     * @param shopId
     * @param currIndex
     * @param pageSize
     * @return
     */
    @Override
    public YdSellerShop getShowShop(Integer userId,Integer shopId,Double lng,Double lat,int currIndex,int pageSize,HttpServletRequest request) {
        Map<String,Object> param = new HashMap<>();
        param.put("shopId",shopId);
        param.put("currIndex",currIndex);
        param.put("pageSize",pageSize);
        YdSellerShop shop = sellerShopMapper.getShowShop(param);

        //判断四个经纬度信息，如果没有空值则进行距离计算
        if(null != lng && null != lat && StringUtils.isNotBlank(shop.getLongitude()) &&
                StringUtils.isNotBlank(shop.getLatitude())){

            //计算用户和商家的距离
            double distance = LocationUtils.getDistance(lng,lat,Double.parseDouble(shop.getLongitude()),
                    Double.parseDouble(shop.getLatitude()));

            //距离信息写入实体
            shop.setDistance(distance);
        }

        //验证session、更新session、清空购物车
        HttpSession session = request.getSession();
        verifyShop(session,userId,shopId);
        return shop;
    }
    /**
     * 周边店铺收藏
     * @param userId
     * @param shopId
     * @param shopName
     * @return
     */
    @Override
    public Body giveCollect(Integer userId, Integer shopId, String shopName) {
        YdSellerCollect collect = new YdSellerCollect();
        collect.setUserId(userId);
        collect.setSellerId(shopId);
        int count = collectMapper.selectCount(collect);
        if(count > 0)return Body.newInstance(204,"已经收藏过了");
        collect.setSellerName(shopName);
        collectMapper.insert(collect);
        return Body.BODY_200;
    }
    /**
     * 周边店铺取消收藏
     * @param id
     * @return
     */
    @Override
    public Body cancelCollect(Integer userId, Integer id) {
        Map<String,Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("id",id);
        collectMapper.delete(param);
        return Body.BODY_200;
    }
    /**
     * 周边店铺取消收藏（批量）
     * @param ids
     * @return
     */
    @Override
    public Body cancelCollectOfBatch(Integer userId,List<String> ids) {
        if(userId == null || ids == null)return Body.newInstance(204,"未做任何修改");
        Map<String,Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("ids",ids);
        collectMapper.deleteOfBatch(param);
        return Body.BODY_200;
    }

    /**
     * 验证session中的店铺id和打开的店铺id是否一致，如不一致则更新session并清空购物车
     * @param session
     * @param shopId
     * @return
     */
    public void verifyShop(HttpSession session,Integer userId,Integer shopId){
        //获取session中的店铺信息
        Integer shopIdInt = (Integer) session.getAttribute("zhoubianShopId");
        //对比；如果信息不一致则进行更新session、清空购物车操作
        if (shopId != shopIdInt){
            session.setAttribute("zhoubianShopId",shopId);
            YdSellerBasket basket = basketMapper.getBasketByUserId(userId);
            if(basket == null)return;
            basketGoodsMapper.deleteAll(basket.getId());
        }
    }
}
