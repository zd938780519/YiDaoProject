package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShopGoodComment;
import com.ruoyi.system.domain.YdShopGoodCommentImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface YdShopGoodCommentService {
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
     * 新增评论
     * @param ydShopGoodComment
     * @param video
     * @param images
     * @return
     */
    boolean insertComment(YdShopGoodComment ydShopGoodComment,MultipartFile video,MultipartFile[] images);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int deleteComment(long id);

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
    List<YdShopGoodComment> selectCommentByGoodsIdAndType(long goodsId,int type);

}
