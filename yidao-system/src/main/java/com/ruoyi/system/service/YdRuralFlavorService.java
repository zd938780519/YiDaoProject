package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdRuralFlavor;
import com.ruoyi.system.domain.YdRuralFlavorPicture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface YdRuralFlavorService {
    /**
     * 新增乡味信息
     * @param userId
     * @param content
     * @param video
     * @param stateType
     * @param pictures
     * @param video
     * @return
     */
    public boolean insertRuralFlavor(long userId, String content, int stateType, MultipartFile[] pictures,MultipartFile video);

    /**
     * 修改乡味信息
     * @param userId
     * @param rfId
     * @param content
     * @param video
     * @param stateType
     * @param pictures
     * @param video
     * @return
     */
    public boolean updateRuralFlavor(long userId,long rfId, String content, int stateType, MultipartFile[] pictures,MultipartFile video);

    /**
     * 新增乡味图片信息
     * @param ydRuralFlavorPicture
     * @return
     */
    public int insertRuralFlavorPicture(List<YdRuralFlavorPicture> ydRuralFlavorPicture);

    /**
     * 删除乡味信息
     * @param id
     * @return
     */
    public int deleteRuralFlavor(long id);

    /**
     * 删除乡味图片信息
     * @param id
     * @return
     */
    public int deleteRuralFlavorPicture(long id);

    /**
     * 删除乡味相关的所有数据
     * @param id
     * @return
     */
    public boolean deleteRuralFlavorRelevantInfo(long id);

    /**
     * 修改发布状态
     * @param id
     * @return
     */
    public int updateReleaseType(long id);

    /**
     * 查询单个乡味信息
     * @param id
     * @return
     */
    public YdRuralFlavor selectRuralFlavorById(long id);

    /**
     * 根据地点查询乡味信息
     * @param userId
     * @param hometown
     * @return
     */
    public List<YdRuralFlavor> selectRuralFlavorsByHometown(long userId,String hometown,int currIndex,int pageSize);

    /**
     * 根据用户查询乡味信息
     * @param userId
     * @param lookUserId
     * @return
     */
    public List<YdRuralFlavor> selectRuralFlavorsByUserId( long userId,long lookUserId,int currIndex,int pageSize);

    /**
     * 查询乡味草稿信息
     * @param userId
     * @return
     */
    public List<YdRuralFlavor> selectDraftRuralFlavorsByUserId(long userId,int currIndex,int pageSize);

    /**
     *  查询乡味信息所对应的图片信息
     * @param rfId
     * @return
     */
    public List<YdRuralFlavorPicture> selectRuralFlavorPictureByRfid(long rfId);

    /**
     * 增加转发数
     * @param id
     * @return
     */
    public int updateForwardNum(long id);

    /**
     * 修改收藏数
     * @param ydRuralFlavor
     * @return
     */
    public int updateCollectionNum(YdRuralFlavor ydRuralFlavor);

    /**
     * 修改乡味评论数
     * @param ydRuralFlavor
     * @return
     */
    public int updateCommentNum(YdRuralFlavor ydRuralFlavor);

    /**
     * 修改赞的人数
     * @param ydRuralFlavor
     * @return
     */
    public int updateFabulousNum(YdRuralFlavor ydRuralFlavor);

    /**
     * 修改踩的人数
     * @param ydRuralFlavor
     * @return
     */
    public int updateStepNum(YdRuralFlavor ydRuralFlavor);





}
