package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdVideo;
import com.ruoyi.system.domain.YdVideoUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface YdVideoService {

    /**
     * 新增视频信息
     * @param userId
     * @param thumbnail
     * @param video
     * @param score
     * @param explanatoryText
     * @param bgmName
     * @param bgm
     * @param supplementaryNotes
     * @return
     */
    public boolean insertVideo(long userId,MultipartFile thumbnail,MultipartFile video,int score,String explanatoryText,String bgmName,
                           MultipartFile bgm,String supplementaryNotes);

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
    public List<YdVideo> selectAllVideo(long userId,int currIndex,int pageSize);

    /**
     * 通过userid查询视频信息
     * @param userId
     * @return
     */
    public List<YdVideo> selectVideoByUserId(long userId,long sellerId,int currIndex,int pageSize);

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
     *
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
     * 想吃
     * @param ydVideoUser
     * @return
     */
    public boolean wantEat(YdVideoUser ydVideoUser);

    /**
     * 增加转发数
     * @param id
     * @return
     */
    public int addForwardNum(long id);

    /**
     * 增加转发数
     * @param ydVideoUser
     * @return
     */
    public boolean addForwardNum(YdVideoUser ydVideoUser);

    /**
     * 删除视频信息，连锁删除所有相关信息
     * @param id
     * @return
     */
    public int deleteRelevantInfo(long id);

}
