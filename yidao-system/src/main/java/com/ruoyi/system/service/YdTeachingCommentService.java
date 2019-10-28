package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdTeachingComment;
import com.ruoyi.system.domain.YdTeachingCommentUser;

import java.util.List;

public interface YdTeachingCommentService {
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
     *  查找评论
     * @param commentObject
     * @param type
     * @return
     */
    public List<YdTeachingComment>selectCommentInfoByIdToDelete(long commentObject,int type);

    /**
     * 根据id查询单个评论信息
     * @param id
     * @return
     */
    public YdTeachingComment selectCommentById(long id);

    /**
     *  新增评论
     * @param ydTeachingComment 评论信息
     * @return
     */
    public int insertComment(YdTeachingComment ydTeachingComment);

    /**
     *  删除评论
     * @param ydTeachingComment 评论实体
     * @return
     */
    public int deleteComment(YdTeachingComment ydTeachingComment);

    /**
     * 删除教学的同时，连锁删除所有相关信息
     * @param ydTeachingComment
     * @return
     */
    public int deleteCommentByTeaching(YdTeachingComment ydTeachingComment);

    /**
     *  删除评论
     * @param ids "("+ids+")"
     * @return
     */
    public int deleteCommentByIds(String ids);

    /**
     * 修改视屏评论数
     * @param ydTeachingComment 评论回复实体
     * @return
     */
    public int updateCommentReplyNum(YdTeachingComment ydTeachingComment);

    /**
     * 删除评论与用户的关联关系
     * @param ids
     * @return
     */
    public int deleteCommentUserRelation(String ids);

    /**
     * 删除评论@的人的关联数据
     * @param ids
     * @return
     */
    public int deleteAtUserInfo(String ids);


    /**
     * 保存点赞数据
     * @param ydTeachingCommentUser 评论用户关联表实体
     * @return
     */
    public int fabulousComment(YdTeachingCommentUser ydTeachingCommentUser);

    /**
     * 评论点赞
     * @param ydTeachingCommentUser
     * @return
     */
    public boolean fabulous(YdTeachingCommentUser ydTeachingCommentUser);

    /**
     * 删除点赞数据
     * @param ydTeachingCommentUser 评论用户关联表实体
     * @return
     */
    public int deleteFabulous(YdTeachingCommentUser ydTeachingCommentUser);

    /**
     * 取消评论点赞
     * @param ydTeachingCommentUser
     * @return
     */
    public boolean cancelFabulous(YdTeachingCommentUser ydTeachingCommentUser);

    /**
     * 修改评论热度
     * @param ydTeachingComment
     * @return
     */
    public int updateCommentHeatNum(YdTeachingComment ydTeachingComment);

}
