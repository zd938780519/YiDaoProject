package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdVideoComment;
import com.ruoyi.system.domain.YdVideoCommentUser;

import java.util.List;

public interface YdVideoCommentService {
    /**
     * 获取视频评论总数
     * @param videoId 视频id
     * @return
     */
    public int selectCommentNumById(long videoId);

    /**
     * 获取视频评论信息
     * @param ydVideoComment 评论信息
     * @return
     */
    public List<YdVideoComment> selectCommentInfoById(YdVideoComment ydVideoComment);

    /**
     *  查找评论
     * @param commentObject
     * @param type
     * @return
     */
    public List<YdVideoComment>selectCommentInfoByIdToDelete(long commentObject,int type);

    /**
     * 根据id查询单个评论信息
     * @param id
     * @return
     */
    public YdVideoComment selectCommentById(long id);

    /**
     *  新增评论
     * @param ydVideoComment 评论信息
     * @return
     */
    public int insertComment(YdVideoComment ydVideoComment);

    /**
     * 新增评论
     * @param videoComment
     * @param atUserIds
     * @return
     */
    public boolean addComment(YdVideoComment videoComment,List<Long> atUserIds);
    /**
     *  删除评论
     * @param ydVideoComment 评论实体
     * @return
     */
    public int deleteComment(YdVideoComment ydVideoComment);

    /**
     * 删除视频时连锁删除评论
     * @param ydVideoComment
     * @return
     */
    public int deleteCommentByVideo(YdVideoComment ydVideoComment);

    /**
     *  删除评论
     * @param ids "("+ids+")"
     * @return
     */
    public int deleteCommentByIds(String ids);

    /**
     * 修改视屏评论数
     * @param ydVideoComment 评论回复实体
     * @return
     */
    public int updateCommentReplyNum(YdVideoComment ydVideoComment);

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
     * @param ydVideoCommentUser 评论用户关联表实体
     * @return
     */
    public int fabulousComment(YdVideoCommentUser ydVideoCommentUser);

    /**
     * 评论点赞
     * @param ydVideoCommentUser
     * @return
     */
    public boolean fabulous(YdVideoCommentUser ydVideoCommentUser);

    /**
     * 删除点赞数据
     * @param ydVideoCommentUser 评论用户关联表实体
     * @return
     */
    public int deleteFabulous(YdVideoCommentUser ydVideoCommentUser);

    /**
     * 取消点赞
     * @param ydVideoCommentUser
     * @return
     */
    public boolean cancelFabulous(YdVideoCommentUser ydVideoCommentUser);

    /**
     * 修改评论热度
     * @param ydVideoComment
     * @return
     */
    public int updateCommentHeatNum(YdVideoComment ydVideoComment);

}
