package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;

import java.util.List;

public interface YdSellerHotCommentService {
    /**
     * 到店吃订单评论
     * @return
     */
    Body sellerComment(Integer userId,Integer shopId, Integer orderId,Integer score,String content,Integer isAnonymity,String images,String video,List<Integer> likeIds);

    /**
     * 外卖订单评论
     * @return
     */
    Body foodComment(Integer userId,Integer shopId, Integer orderId,Integer score,String content,Integer isAnonymity,String images,String video,
                     Integer deliverId,Integer isGood,String deliverTitle,String deliverContent,String timeCalibration,List<Integer> likeIds);
}
