package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopGoodComment;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface YdShopGoodCommentMapper {
    int insert(YdShopGoodComment record);

    List<YdShopGoodComment> selectAll();

    /**
     * 获取商品评论数量
     * @param goodsId
     * @return
     */
    int getCommentNumByGoodsId(long goodsId);

    /**
     * 获取最新的一条评论
     * @param goodsId
     * @return
     */
    YdShopGoodComment getCommentByGoodsIdIsNew(long goodsId);

    /**
     * 获取所有评论
     * @param goodsId
     * @return
     */
    List<YdShopGoodComment> getFavorableRate(long goodsId);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int deleteComment(long id);

    /**
     * 根据id获取评论
     * @param id
     * @return
     */
    YdShopGoodComment selectCommentById(long id);

    /**
     * 获取评论信息包括图片
     * @param goodsId
     * @return
     */
    List<YdShopGoodComment> selectCommentByGoodsId(long goodsId);

    /**
     * 获取好评数、中评数、差评数
     * @param goodsId
     * @return
     */
    HashMap<String,Integer> selectCommentNumToType(long goodsId);

    /**
     * 根据type获取好评、中评、差评
     * @param goodsId
     * @return
     */
    List<YdShopGoodComment> selectCommentByGoodsIdAndType(@Param("goodsId") long goodsId,@Param("type") int type);

}