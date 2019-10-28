package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdFoodOrderRemark;
import java.util.List;
import java.util.Map;

public interface YdFoodOrderRemarkMapper {

    int insert(YdFoodOrderRemark record);

    /**
     * 根据用户id查询备注
     * @param userId
     * @return
     */
    List<YdFoodOrderRemark> selectAll(Integer userId);

    /**
     * 查询用户备注是否存在
     * @param record
     * @return
     */
    int selectExistRemark(YdFoodOrderRemark record);

    /**
     * 查询用户备注数量
     * @param userId
     * @return
     */
    int selectRemarkNum(int userId);

    /**
     * 删除用户最早的备注
     * @param userId
     * @return
     */
    int deleteLastRemark(int userId);

    /**
     * 根据id删除备注
     * @return
     */
    int deleteById(Map<String,Object> param);
}