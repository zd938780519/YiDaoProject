package com.ruoyi.system.service;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdFoodDistributionInfo;

public interface YdFoodDistributionInfoService {
    /**
     * 新增用户收货地址
     * @param record
     * @return
     */
    Body insert(YdFoodDistributionInfo record,Integer shopId);
    /**
     * 查询用户收货地址列表
     * @param userId
     * @return
     */
    Body selectDistributionList(Integer userId,Integer shopId);

    /**
     * 修改用户收货地址信息
     * @param record
     * @return
     */
    Body update(YdFoodDistributionInfo record,Integer shopId);

    /**
     * 删除收货地址
     * @param id
     * @param userId
     * @return
     */
    Body delete(Integer id,Integer userId,Integer shopId);
}
