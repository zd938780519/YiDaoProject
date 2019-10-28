package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdTeachingComment;
import com.ruoyi.system.domain.YdTeachingCommentUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学评论数据访问层
 */
public interface YdTeachingCommentMapper {

    /**
     * 获取教学评论总数
     * @param tcId 教学id
     * @return
     */
    public int selectCommentNumById(long tcId);

    /**
     * 获取教学评论信息
     * @param ydTeachingComment 评论信息
     * @return
     */
    public List<YdTeachingComment> selectCommentInfoById(YdTeachingComment ydTeachingComment);

    /**
     * 根据id查询单个评论信息
     * @param id
     * @return
     */
    public YdTeachingComment selectCommentById(@Param("id") long id);

    /**
     *  查找评论
     * @param id
     * @param type
     * @return
     */
    public List<YdTeachingComment>selectCommentInfoByIdToDelete(@Param("id") long id,@Param("type") int type);

    /**
     *  新增评论
     * @param ydTeachingComment 评论实体
     * @return
     */
    public int insertComment(YdTeachingComment ydTeachingComment);

    /**
     *  删除评论
     * @param ids "("+ids+")"
     * @return
     */
    public int deleteCommentByIds(@Param(value = "ids") String ids);

    /**
     * 修改教学评论数
     * @param ydTeachingComment 评论回复实体
     * @return
     */
    public int updateCommentReplyNum(YdTeachingComment ydTeachingComment);

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
     * @param ydTeachingCommentUser 评论用户关联表实体
     * @return
     */
    public int fabulousComment(YdTeachingCommentUser ydTeachingCommentUser);

    /**
     * 删除点赞数据
     * @param ydTeachingCommentUser 评论用户关联表实体
     * @return
     */
    public int deleteFabulous(YdTeachingCommentUser ydTeachingCommentUser);

    /**
     * 修改评论热度
     * @param ydTeachingComment
     * @return
     */
    public int updateCommentHeatNum(YdTeachingComment ydTeachingComment);

}
