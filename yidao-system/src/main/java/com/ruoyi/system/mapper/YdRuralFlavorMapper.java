package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdRuralFlavor;
import com.ruoyi.system.domain.YdRuralFlavorPicture;
import com.ruoyi.system.provider.InsertRuralFlavorPicture;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *乡味相关数据层
 */
public interface YdRuralFlavorMapper {
    /**
     * 新增乡味信息
     * @param ydRuralFlavor
     * @return
     */
    public int insertRuralFlavor(YdRuralFlavor ydRuralFlavor);

    /**
     * 新增乡味图片信息
     * @param ydRuralFlavorPicture
     * @return
     */
    @InsertProvider(type = InsertRuralFlavorPicture.class, method = "insertAll")
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
     * 根据用户查询乡味信息
     * @param userId
     * @param lookUserId
     * @return
     */
    public List<YdRuralFlavor> selectRuralFlavorsByUserId(@Param("userId") long userId,@Param("lookUserId") long lookUserId,
                                                          @Param("currIndex") int currIndex,@Param("pageSize") int pageSize);

    /**
     * 根据地点查询乡味信息
     * @param hometown
     * @return
     */
    public List<YdRuralFlavor> selectRuralFlavorsByHometown(@Param("userId") long userId,@Param("hometown") String hometown,
                                                            @Param("currIndex") int currIndex,@Param("pageSize") int pageSize);

    /**
     * 查询乡味草稿信息
     * @param userId
     * @return
     */
    public List<YdRuralFlavor> selectDraftRuralFlavorsByUserId(@Param("userId") long userId,@Param("currIndex") int currIndex,
                                                               @Param("pageSize") int pageSize);

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

    /**
     * 修改乡味内容
     * @param ydRuralFlavor
     * @return
     */
    public int updateRuralFlavor(YdRuralFlavor ydRuralFlavor);

}
