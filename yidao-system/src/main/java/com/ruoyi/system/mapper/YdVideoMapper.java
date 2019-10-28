package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdVideo;
import com.ruoyi.system.domain.YdVideoUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频相关数据访问层
 */
public interface YdVideoMapper {
    /**
     * 新增视频信息
     * @param ydVideo
     * @return
     */
    public int insertVideo(YdVideo ydVideo);

    /**
     * 删除视频信息
     * @param id
     * @return
     */
    public int deleteVideo(long id);

    /**
     * 获取视频简单信息
     * @return
     */
    public List<YdVideo> selectAllVideo(@Param("userId") long userId,@Param("currIndex") int currIndex,@Param("pageSize")  int pageSize);

    /**
     * 通过userid查询视频信息
     * @param userId
     * @return
     */
    public List<YdVideo> selectVideoByUserId(@Param("userId") long userId, @Param("sellerId") long sellerId,@Param("currIndex") int currIndex,
                                             @Param("pageSize") int pageSize);

    /**
     * 根据视频id获取详细信息
     * @param videoId 视频id
     * @return
     */
    public  YdVideo selectVideoById(long videoId);

    /**
     * 修改视屏评论数
     * @param ydVideo 视频实体
     * @return
     */
    public int updateCommentNum(YdVideo ydVideo);

    /**
     *  修改视频信息
     * @param ydVideo
     * @return
     */
    public int updateInfo(YdVideo ydVideo);

    /**
     * 修改想吃数
     * @param ydVideo
     * @return
     */
    public int updateWantEatNum(YdVideo ydVideo);

    /**
     * 增加转发数
     * @param id
     * @return
     */
    public int addForwardNum(long id);

}
