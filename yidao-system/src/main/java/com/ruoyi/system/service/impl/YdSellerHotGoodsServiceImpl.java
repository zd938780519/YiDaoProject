package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.formatUtil.DistributionUtil;
import com.ruoyi.common.utils.formatUtil.LocationUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.YdSellerDiscountDraw;
import com.ruoyi.system.domain.YdSellerHotComment;
import com.ruoyi.system.domain.YdSellerHotGoods;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.YdSellerHotGoodsService;
import com.ruoyi.system.service.YdSellerShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YdSellerHotGoodsServiceImpl implements YdSellerHotGoodsService {
    @Autowired(required = false)
    private YdSellerHotGoodsMapper hotGoodsMapper;
    @Autowired(required = false)
    private YdSellerDiscountDrawMapper discountDrawMapper;
    @Autowired
    private YdSellerShopService shopService;
    @Autowired(required = false)
    private YdSellerHotCommentMapper hotCommentMapper;


    /**
     * 视频点击进入周边主页（根据商品id查询商品和店铺信息）
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getShopAndGoodByGoodId(int id,Integer userId,Double lng,Double lat, HttpServletRequest request) {
        //获取店铺和商品简要信息
        YdSellerHotGoods ydSellerHotGoods = hotGoodsMapper.getShopAndGoodByGoodId(id);
        //创建距离信息
        //判断四个经纬度信息，如果没有空值则进行距离计算
        if(null != lng && null != lat && StringUtils.isNotBlank(ydSellerHotGoods.getYdSellerShop().getLongitude()) &&
                StringUtils.isNotBlank(ydSellerHotGoods.getYdSellerShop().getLatitude())){

            //计算用户和商家的距离
            double distance = LocationUtils.getDistance(lng,lat,Double.parseDouble(ydSellerHotGoods.getYdSellerShop().getLongitude()),
                    Double.parseDouble(ydSellerHotGoods.getYdSellerShop().getLatitude()));

            //距离信息写入实体
            ydSellerHotGoods.getYdSellerShop().setDistance(distance);
        }

        //判断是否有活动（isActivity 0:否 1：是）
        List<YdSellerDiscountDraw> ydSellerDiscountList = new ArrayList<>();
        if(ydSellerHotGoods.getYdSellerShop().getIsActivity() == 1){
            //查询优惠券列表（null：只显示缩略两个；其他：展示全部）
            Map<String,Integer> param = new HashMap<>();
            param.put("shopId",ydSellerHotGoods.getSellerShopId());
            param.put("userId",userId);
            param.put("functionType",null);
            ydSellerDiscountList  = discountDrawMapper.getDiscountByShopId(param);
            for (YdSellerDiscountDraw discountDraw: ydSellerDiscountList) {
                if(discountDraw.getId() != null)discountDraw.getDiscount().setIsDraw(1);
            }
        }
        Map<String,Object> info = new HashMap<>();
        info.put("goodAndShop",ydSellerHotGoods);
        info.put("discount",ydSellerDiscountList);

        //验证session、更新session、清空购物车
        HttpSession session = request.getSession();
        shopService.verifyShop(session,userId,ydSellerHotGoods.getSellerShopId());

        return info;
    }

    /**
     * 搜索框检索商品
     * @param shopId
     * @param searchName
     * @return
     */
    @Override
    public List<YdSellerHotGoods> searchGoods(Integer shopId, String searchName,Integer currIndex,Integer pageSize) {
        Map<String,Object> param = new HashMap<>();
        param.put("shopId",shopId);
        param.put("searchName",searchName);
        param.put("currIndex",currIndex);
        param.put("pageSize",pageSize);
        return hotGoodsMapper.searchGoods(param);
    }

    /**
     * 查看商品详情
     * @return
     */
    @Override
    public Body getGoodDetails(Integer goodId,Double lng,Double lat) {
        //根据id查询商品信息
        YdSellerHotGoods hotGoods = hotGoodsMapper.getInfoByGoodId(goodId);
        if (hotGoods != null) {
            String duration = "超出配送范围";
            //获取店铺距离
            Double location = LocationUtils.getDistance(lng, lat, Double.parseDouble(hotGoods.getYdSellerShop().getLongitude()), Double.parseDouble(hotGoods.getYdSellerShop().getLatitude()));
            //获取配送时间
            if (location != null && location < DistributionUtil.MAX_DISTANCE)
                duration = DistributionUtil.calculateDuration(location).toString();
            //获取评论信息
            Map<String, Object> param = new HashMap<>();
            param.put("goodId", hotGoods.getId());
            param.put("type", 4);
            List<YdSellerHotComment> hotComment = hotCommentMapper.getCommentOfCond(param);
            //构建返回数据集合
            Map<String, Object> info = new HashMap<>();
            info.put("duration", duration);
            info.put("goodInfo", hotGoods);
            info.put("commentInfo", hotComment);
            return Body.newInstance(info);
        }
        return Body.newInstance(204,"无数据");
    }

    /**
     * 查看商品评价
     * @param goodId
     * @param type
     * @return
     */
    @Override
    public Body getGoodComments(Integer goodId, Integer type,Integer currIndex,Integer pageSize) {
        Map<String,Object> param = new HashMap<>();
        param.put("goodId",goodId);
        param.put("type",type);
        param.put("currIndex",currIndex);
        param.put("pageSize",pageSize);
        List<YdSellerHotComment> hotComment = hotCommentMapper.getCommentOfCond(param);
        if(hotComment != null && !hotComment.isEmpty()){
            return Body.newInstance(hotComment);
        }
        return Body.newInstance(204,"无数据");
    }
}
