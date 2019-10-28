package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdVideoComment;
import com.ruoyi.system.domain.YdVideoCommentUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频评论数据访问层
 */
public interface YdVideoCommentMapper {

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
     * 根据id查询单个评论信息
     * @param id
     * @return
     */
    public YdVideoComment selectCommentById(@Param("id") long id);

    /**
     *  查找评论
     * @param id
     * @param type
     * @return
     */
    public List<YdVideoComment>selectCommentInfoByIdToDelete(@Param("id") long id,@Param("type") int type);

    /**
     *  新增评论
     * @param ydVideoComment 评论实体
     * @return
     */
    public int insertComment(YdVideoComment ydVideoComment);

    /**
     *  删除评论
     * @param ids "("+ids+")"
     * @return
     */
    public int deleteCommentByIds(@Param(value="ids") String ids);

    /**
     * 修改视频评论数
     * @param ydVideoComment 评论回复实体
     * @return
     */
    public int updateCommentReplyNum(YdVideoComment ydVideoComment);

    /**
     * 删除评论与用户的关联关系
     * @param ids
     * @return
     */
    public int deleteCommentUserRelation(@Param(value="ids") String ids);

    /**
     * 删除评论@的人的关联数据
     * @param ids
     * @return
     */
    public int deleteAtUserInfo(@Param(value="ids") String ids);

    /**
     * 保存点赞数据
     * @param ydVideoCommentUser 评论用户关联表实体
     * @return
     */
    public int fabulousComment(YdVideoCommentUser ydVideoCommentUser);

    /**
     * 删除点赞数据
     * @param ydVideoCommentUser 评论用户关联表实体
     * @return
     */
    public int deleteFabulous(YdVideoCommentUser ydVideoCommentUser);

    /**
     * 修改评论热度
     * @param ydVideoComment
     * @return
     */
    public int updateCommentHeatNum(YdVideoComment ydVideoComment);

}
