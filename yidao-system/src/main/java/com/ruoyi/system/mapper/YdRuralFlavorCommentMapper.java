package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdRuralFlavorComment;
import com.ruoyi.system.domain.YdRuralFlavorCommentUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 乡味评论数据访问层
 */
public interface YdRuralFlavorCommentMapper {

    /**
     * 获取乡味评论总数
     * @param rfId 乡味id
     * @return
     */
    public int selectCommentNumById(long rfId);

    /**
     * 获取乡味评论信息
     * @param ydRuralFlavorComment 评论信息
     * @return
     */
    public List<YdRuralFlavorComment> selectCommentInfoById(YdRuralFlavorComment ydRuralFlavorComment);

    /**
     * 根据id查询单个评论信息
     * @param id
     * @return
     */
    public YdRuralFlavorComment selectCommentById(@Param("id") long id);

    /**
     *  查找评论
     * @param id
     * @param type
     * @return
     */
    public List<YdRuralFlavorComment>selectCommentInfoByIdToDelete(@Param("id") long id,@Param("type") int type);

    /**
     *  新增评论
     * @param ydRuralFlavorComment 评论实体
     * @return
     */
    public int insertComment(YdRuralFlavorComment ydRuralFlavorComment);

    /**
     *  删除评论
     * @param ids "("+ids+")"
     * @return
     */
    public int deleteCommentByIds(@Param(value = "ids") String ids);

    /**
     * 修改乡味评论数
     * @param ydRuralFlavorComment 评论回复实体
     * @return
     */
    public int updateCommentReplyNum(YdRuralFlavorComment ydRuralFlavorComment);

    /**
     * 删除评论与用户的关联关系
     * @param ids
     * @return
     */
    public int deleteCommentUserRelation(@Param(value = "ids") String ids);

    /**
     * 删除评论@的人的关联数据
     * @param ids
     * @return
     */
    public int deleteAtUserInfo(@Param(value = "ids") String ids);

    /**
     * 保存点赞数据
     * @param ydRuralFlavorCommentUser 评论用户关联表实体
     * @return
     */
    public int fabulousComment(YdRuralFlavorCommentUser ydRuralFlavorCommentUser);

    /**
     * 删除点赞数据
     * @param ydRuralFlavorCommentUser 评论用户关联表实体
     * @return
     */
    public int deleteFabulous(YdRuralFlavorCommentUser ydRuralFlavorCommentUser);

    /**
     * 修改评论热度
     * @param ydRuralFlavorComment
     * @return
     */
    public int updateCommentHeatNum(YdRuralFlavorComment ydRuralFlavorComment);

}
