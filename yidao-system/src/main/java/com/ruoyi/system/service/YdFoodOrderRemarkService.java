package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdFoodOrderRemark;

public interface YdFoodOrderRemarkService {
    /**
     * 根据用户id查询备注
     * @param userId
     * @return
     */
    Body selectAll(Integer userId);

    /**
     *用户备注新增
     * @return
     */
    Body insert(YdFoodOrderRemark record);

    /**
     * 用户备注删除
     * @return
     */
    Body delete(Integer id,Integer userId);
}
