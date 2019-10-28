package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdTeachingContent;
import com.ruoyi.system.domain.YdTeachingMaterials;
import com.ruoyi.system.domain.YdTeachingStep;
import com.ruoyi.system.provider.InsertTeachingMaterials;
import com.ruoyi.system.provider.InsertTeachingSteps;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学相关数据层
 */
public interface YdTeachingContentMapper {
    /**
     * 根据userID查询教学简单信息
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdTeachingContent> selectTeachingSimpleInfoByUserId(@Param("userId") long userId,@Param("currIndex") int currIndex,
                                                                    @Param("pageSize") int pageSize);

    /**
     * 查询全部的教学简单信息
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdTeachingContent> selectAllTeachingSimpleInfoByUserId(@Param("userId") long userId,@Param("currIndex") int currIndex,
                                                                       @Param("pageSize") int pageSize);


    /**
     *  查询教学草稿简单信息
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdTeachingContent> selectDraftTeachingSimpleInfoByUserId(@Param("userId") long userId,@Param("currIndex") int currIndex,
                                                                         @Param("pageSize") int pageSize);

    /**
     * 根据userID查询教学详细信息
     * @param id
     * @return
     */
    public YdTeachingContent selectTeachingDetailedInfoByUserId(@Param("id") long id,@Param("userId") long userId);


    /**
     * 新增教学信息
     * @param ydTeachingContent
     * @return
     */
    public int insertTeachingContent(YdTeachingContent ydTeachingContent);

    /**
     * 新增配料信息
     * @param tms
     * @return
     */
    @InsertProvider(type = InsertTeachingMaterials.class, method = "insertAll")
    public int insertTeachingMaterials(List<YdTeachingMaterials> tms);

    /**
     * 查询教学配料信息
     * @param id
     * @param type
     * @return
     */
    public List<YdTeachingMaterials> selectTeachingMaterials(@Param("id") long id,@Param("type") int type);

    /**
     * 新增步骤信息
     * @param tss
     * @return
     */
    @InsertProvider(type = InsertTeachingSteps.class, method = "insertAll")
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
     * 修改视屏评论数
     * @param ydTeachingContent
     * @return
     */
    public int updateCommentNum(YdTeachingContent ydTeachingContent);



    /**
     * 增加转发数
     * @param id
     * @return
     */
    public int updateForwardNum(long id);

    /**
     * 增加浏览量
     */
    public int insertBrowseNum(long id);
    /**
     * 修改收藏数
     */
    public int updateCollectionNum(YdTeachingContent ydTeachingContent);

    /**
     * 修改热度
     */
    public int updateHeatNum(YdTeachingContent ydTeachingContent);




}
