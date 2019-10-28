package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdUserAppeal;

/**
 * 申诉数据访问层
 */
public interface YdUserAppealMapper {
    /**
     * 新增申诉数据
     * @param ydUserAppeal
     * @return
     */
    public int insertUserAppeal(YdUserAppeal ydUserAppeal);

    /**
     * 修改申诉数据
     * @param ydUserAppeal
     * @return
     */
    public int updateUserAppeal(YdUserAppeal ydUserAppeal);

    /**
     * 根据userId查询是否有未处理的申诉记录
     * @param userId
     * @return
     */
    public int selectUserAppeal(long userId);
}
