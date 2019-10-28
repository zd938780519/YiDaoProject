package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.formatUtil.FoodOrderUtil;
import com.ruoyi.common.utils.formatUtil.SellerOrderUtil;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.YdSellerHotCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class YdSellerHotCommentServiceImpl implements YdSellerHotCommentService {
    @Autowired(required = false)
    private YdSellerHotCommentMapper commentMapper;
    @Autowired(required = false)
    private YdSellerOrderItemMapper orderItemMapper;
    @Autowired(required = false)
    private YdSellerOrderMapper orderMapper;
    @Autowired(required = false)
    private YdFoodOrderMapper foodOrderMapper;
    @Autowired(required = false)
    private YdFoodInfoMapper foodInfoMapper;
    @Autowired(required = false)
    private YdFoodDeliverCommentMapper deliverCommentMapper;
    @Autowired(required = false)
    private YdSellerShopMapper shopMapper;
    @Autowired(required = false)
    private YdSellerUserLikePostMapper userLikePostMapper;
    @Autowired(required = false)
    private YdSellerHotGoodsMapper hotGoodsMapper;
    /**
     * 到店吃订单评论
     * @return
     */
    @Override
    public Body sellerComment(Integer userId,Integer shopId, Integer orderId,Integer score,String content,Integer isAnonymity,String images,String video,
                              List<Integer> likeIds) {
        //验证评分
        if(score < 1 || score > 5)return Body.newInstance(400,"检测到异常信息：评分应为1~5");
        //验证是否有评论内容
        int isContent = 0;
        if(null != content)isContent = 1;
        else content = "";
        //检测是否存在图片或视频,若存在is_image=1
        int isImage = 0;
        if(StringUtils.isNotBlank(images) || StringUtils.isNotBlank(video)) isImage = 1;
        //验证订单状态
        YdSellerOrder sellerOrder = orderMapper.selectById(orderId);
        if(sellerOrder == null)return Body.newInstance(400,"检测到异常信息：订单信息不存在");
        if(sellerOrder.getStatus() != SellerOrderUtil.UNEVAL)return Body.newInstance(400,"检测到异常信息：订单状态异常");
        //查询订单子表信息
        YdSellerOrderItem orderItem = new YdSellerOrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setSellerId(shopId);
        List<YdSellerOrderItem> orderItemList = orderItemMapper.selectByOrderIdAndShopId(orderItem);
        if(orderItemList == null || orderItemList.isEmpty())return Body.newInstance(400,"检测到异常信息：订单信息不存在");

        //循环订单子表信息，拼接订单商品标签
        StringBuilder sb = new StringBuilder();
        for(YdSellerOrderItem tab : orderItemList){
            sb.append("#" + tab.getDishName());
        }
        sb.append("|");
        List<YdSellerHotComment> hotCommentList = new ArrayList<>();
        //循环订单子表信息，构建评论对象
        for(YdSellerOrderItem item : orderItemList){
            YdSellerHotComment hotComment = new YdSellerHotComment();
            hotComment.setUserId(userId);
            hotComment.setSellerId(shopId);
            hotComment.setOrderNum(sellerOrder.getOrderNum());
            hotComment.setHotGoodId(item.getDishId());
            hotComment.setScore(score);
            hotComment.setIsAnonymity(isAnonymity);
            hotComment.setIsImage(isImage);
            hotComment.setIsContent(isContent);
            hotComment.setContent(sb.toString() + content.replaceAll("\\s*",""));
            hotComment.setImages(images);
            hotComment.setVideo(video);
            hotCommentList.add(hotComment);
        }
        commentMapper.insert(hotCommentList);
        //更新店铺评分
        YdSellerShop shop = new YdSellerShop();
        shop.setId(shopId);
        shop.setRateOfAcclaim(avgScore(shopId));
        shopMapper.updateScore(shop);
        //商品点赞统计，更新商品点赞数
        if (likeIds != null && !likeIds.isEmpty()){
            List<YdSellerUserLikePost> likeList = new ArrayList<>();
            for (Integer likeGoodId : likeIds){
                YdSellerUserLikePost userLikePost = new YdSellerUserLikePost();
                userLikePost.setUserId(userId);
                userLikePost.setHotGoodId(likeGoodId);
                likeList.add(userLikePost);
            }
            userLikePostMapper.insertOfBatch(likeList);
            hotGoodsMapper.updateOfBatchForLikeCount(likeIds);
        }

        //修改订单状态：未退款
        YdSellerOrder order = new YdSellerOrder();
        order.setId(orderId);
        order.setStatus(SellerOrderUtil.UNREFUND);
        orderMapper.updateStatus(order);
        return Body.BODY_200;
    }
    /**
     * 外卖订单评论
     * @return
     */
    @Override
    public Body foodComment(Integer userId, Integer shopId, Integer orderId,Integer score, String content, Integer isAnonymity,String images, String video,
                            Integer deliverId,Integer isGood,String deliverTitle,String deliverContent,String timeCalibration,
                            List<Integer> likeIds) {
        //验证评分
        if(score < 1 || score > 5)return Body.newInstance(400,"检测到异常信息：评分应为1~5");
        //验证是否有评论内容
        int isContent = 0;
        if(null != content)isContent = 1;
        else content = "";
        //对送餐员的评价，因为不对外展示，所以没填写就默认：用户未填写评价
        if(null == deliverContent && StringUtils.isNotBlank(deliverContent))deliverContent = "用户未填写评价";

        //检测是否存在图片或视频,若存在is_image=1
        int isImage = 0;
        if(StringUtils.isNotBlank(images) || StringUtils.isNotBlank(video)) isImage = 1;
        //验证订单状态
        YdFoodOrder foodOrder = foodOrderMapper.selectByTrueId(orderId);
        if(foodOrder == null)return Body.newInstance(400,"检测到异常信息：订单信息不存在");
        if(foodOrder.getStatus() != FoodOrderUtil.UNEVAL)return Body.newInstance(400,"检测到异常信息：订单状态异常");
        //查询订单菜品信息
        YdFoodInfo foodInfo = new YdFoodInfo();
        foodInfo.setSellerId(shopId);
        foodInfo.setFoId(orderId);
        List<YdFoodInfo> foodInfoList = foodInfoMapper.selectByShopIdAndOrderId(foodInfo);
        if(foodInfoList == null || foodInfoList.isEmpty())return Body.newInstance(400,"检测到异常信息：订单信息不存在");

        //循环订单子表信息，拼接订单商品标签
        StringBuilder sb = new StringBuilder();
        for(YdFoodInfo tab : foodInfoList){
            sb.append("#" + tab.getDishName());
        }
        sb.append("|");
        List<YdSellerHotComment> hotCommentList = new ArrayList<>();
        //循环订单子表信息，构建评论对象
        for(YdFoodInfo info : foodInfoList){
            YdSellerHotComment hotComment = new YdSellerHotComment();
            hotComment.setUserId(userId);
            hotComment.setSellerId(shopId);
            hotComment.setOrderNum(foodOrder.getOrderNum());
            hotComment.setHotGoodId(info.getDishId());
            hotComment.setIsAnonymity(isAnonymity);
            hotComment.setIsImage(isImage);
            hotComment.setIsContent(isContent);
            hotComment.setScore(score);
            hotComment.setContent(sb.toString() + content.replaceAll("\\s*",""));
            hotComment.setImages(images);
            hotComment.setVideo(video);
            hotCommentList.add(hotComment);
        }
        commentMapper.insert(hotCommentList);
        //添加送餐员评价
        YdFoodDeliverComment deliverComment = new YdFoodDeliverComment();
        deliverComment.setDeliverId(deliverId);
        deliverComment.setUserId(userId);
        deliverComment.setOrderNum(foodOrder.getOrderNum());
        deliverComment.setIsAnonymity(isAnonymity);
        deliverComment.setIsGood(isGood);
        deliverComment.setTitle(deliverTitle);
        deliverComment.setContent(deliverContent.replaceAll("\\s*",""));
        if(StringUtils.isNotBlank(timeCalibration))
            deliverComment.setTimeCalibration(Date.from(LocalDateTime.parse(timeCalibration).atZone(ZoneId.systemDefault()).toInstant()));

        deliverCommentMapper.insert(deliverComment);
        //更新店铺评分
        YdSellerShop shop = new YdSellerShop();
        shop.setId(shopId);
        shop.setRateOfAcclaim(avgScore(shopId));
        shopMapper.updateScore(shop);
        //商品点赞统计，更新商品点赞数
        if (likeIds != null && !likeIds.isEmpty()){
            List<YdSellerUserLikePost> likeList = new ArrayList<>();
            for (Integer likeGoodId : likeIds){
                YdSellerUserLikePost userLikePost = new YdSellerUserLikePost();
                userLikePost.setHotGoodId(likeGoodId);
                userLikePost.setUserId(userId);
                likeList.add(userLikePost);
            }
            userLikePostMapper.insertOfBatch(likeList);
            hotGoodsMapper.updateOfBatchForLikeCount(likeIds);
        }
        //修改订单状态：未退款
        YdFoodOrder order = new YdFoodOrder();
        order.setId(orderId);
        order.setStatus(FoodOrderUtil.UNREFUND);
        foodOrderMapper.updateStatus(order);
        return Body.BODY_200;
    }

    /**
     * 统计店铺评分
     */
    private BigDecimal avgScore(Integer sellerId){
        BigDecimal avg = commentMapper.getAvgScore(sellerId).setScale(1,BigDecimal.ROUND_HALF_UP);
        return avg;
    }
}
