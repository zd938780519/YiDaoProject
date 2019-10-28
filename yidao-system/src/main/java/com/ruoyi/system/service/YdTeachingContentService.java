package com.ruoyi.system.service;

import com.ruoyi.system.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface YdTeachingContentService {
    /**
     * 根据userID查询教学简单信息
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdTeachingContent> selectTeachingSimpleInfoByUserId(long userId,int currIndex,int pageSize);

    /**
     * 根据userID查询教学简单信息
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdTeachingContent> selectAllTeachingSimpleInfoByUserId(long userId,int currIndex,int pageSize);

    /**
     *  查询教学草稿简单信息
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdTeachingContent> selectDraftTeachingSimpleInfoByUserId(long userId,int currIndex,int pageSize);

    /**
     * 根据userID查询教学详细信息
     * @param id
     * @return
     */
    public YdTeachingContent selectTeachingDetailedInfoByUserId(long id,long userId);

    /**
     * 新增教学信息
     * @param ydTeachingContent
     * @return
     */
    public int insertTeachingContent(YdTeachingContent ydTeachingContent);

    /**
     * 新增教学内容
     * @param userId
     * @param dishName
     * @param resultVideoPath
     * @param thumbnailPath
     * @param resultPicturePath
     * @param experience
     * @param skill
     * @param cookingTime
     * @param cookingTimeShow
     * @param flavor
     * @param flavorShow
     * @param difficulty
     * @param difficultyShow
     * @param stateType
     * @param model
     * @param files
     * @return
     */
    public boolean addTeachingInfo(long  userId, String  dishName , MultipartFile  resultVideo , MultipartFile  thumbnail ,
                                   MultipartFile  resultPicture, String  experience, String  skill , int  cookingTime, String  cookingTimeShow  ,
                                   int  flavor, String  flavorShow, int  difficulty , String  difficultyShow , int  stateType ,
                                   TeachingActivityModel model, MultipartFile[] files);

    /**
     * 新增配料信息
     * @param tms
     * @return
     */
    public int insertTeachingMaterials(List<YdTeachingMaterials> tms);

    /**
     * 查询教学配料信息
     * @param id
     * @param type
     * @return
     */
    public List<YdTeachingMaterials> selectTeachingMaterials(long id, int type);

    /**
     * 新增步骤信息
     * @param tss
     * @return
     */
    public int insertTeachingSteps(List<YdTeachingStep> tss);

    /**
     * 查询教学步骤信息
     * @param id
     * @return
     */
    public List<YdTeachingStep> selectTeachingStep(long id);


    /**
     * 删除教学信息
     * @param id
     * @return
     */
    public int deleteTeachingInfo(long id);

    /**
     * 删除配料信息
     * @param id
     * @return
     */
    public int deleteTeachingMaterialsInfo(long id);

    /**
     * 删除步骤信息
     * @param id
     * @return
     */
    public int deleteTeachingStepsInfo(long id);


    /**
     * 修改发布状态
     * @param id
     * @return
     */
    public int updateReleaseType(long id);

    /**
     * 增加转发数
     * @param id
     * @return
     */
    public int updateForwardNum(long id);

    /**
     * 增加转发数
     * @param ydTeachingUser
     * @return
     */
    public boolean addForwardNum(YdTeachingUser ydTeachingUser);

    /**
     * 增加浏览量
     */
    public int insertBrowseNum(long id);
    /**
     * 修改收藏数
     */
    public int updateCollectionNum(YdTeachingContent ydTeachingContent);

    /**
     * 收藏
     * @param ydTeachingUser
     * @return
     */
    public boolean collection(YdTeachingUser ydTeachingUser);

    /**
     * 修改热度
     */
    public int updateHeatNum(YdTeachingContent ydTeachingContent);

    /**
     * 修改教学点赞的状态
     * @param ydTeachingUser
     * @return
     */
    public boolean wantEat(YdTeachingUser ydTeachingUser);

    /**
     * 修改教学评论数
     * @param ydTeachingContent 教学实体
     * @return
     */
    public int updateCommentNum(YdTeachingContent ydTeachingContent);

    /**
     * 删除教学信息，连锁删除所有相关信息
     * @param id
     * @return
     */
    public int deleteRelevantInfo(long id);


}
