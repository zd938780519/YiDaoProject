package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdUserAppeal;

public interface YdUserAppealService {
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
