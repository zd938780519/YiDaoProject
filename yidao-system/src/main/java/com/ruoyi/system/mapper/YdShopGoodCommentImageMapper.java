package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopGoodCommentImage;
import com.ruoyi.system.provider.InsertShopGoodsCommentImage;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

public interface YdShopGoodCommentImageMapper {
    int insert(YdShopGoodCommentImage record);

    List<YdShopGoodCommentImage> selectAll();

    @InsertProvider(type = InsertShopGoodsCommentImage.class, method = "insertAll")
    public int insertShopGoodCommentImage(List<YdShopGoodCommentImage> shopGoodCommentImages);

    /**
     * 删除评论图片
     * @param sgcId
     * @return
     */
    int deleteCommentImage(long sgcId);

    /**
     * 根据评论id获取评论图片
     * @param sgcId
     * @return
     */
    List<YdShopGoodCommentImage> selectCommentImageBySgcId(long sgcId);
}