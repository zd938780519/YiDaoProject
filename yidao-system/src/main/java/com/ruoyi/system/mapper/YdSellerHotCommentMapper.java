package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerHotComment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface YdSellerHotCommentMapper {
    int insert(List<YdSellerHotComment> record);

    /**
     * 统计店铺评分
     * @param sellerId
     * @return
     */
    BigDecimal getAvgScore(Integer sellerId);

    /**
     * 显示商品评论
     * @param param
     * @return
     */
    List<YdSellerHotComment> getCommentOfCond(Map<String,Object> param);
}