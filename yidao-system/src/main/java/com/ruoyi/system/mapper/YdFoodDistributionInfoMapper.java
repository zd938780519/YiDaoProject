package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdFoodDistributionInfo;
import java.util.List;
import java.util.Map;

public interface YdFoodDistributionInfoMapper {
    /**
     * 新增
     * @param record
     * @return
     */
    int insert(YdFoodDistributionInfo record);

    /**
     * 查询用户收货地址列表
     * @param userId
     * @return
     */
    //List<YdFoodDistributionInfo> selectAll(Integer userId);

    /**
     * 查询用户收货地址列表（包含距离判断）
     * @param param
     * @return
     */
    List<YdFoodDistributionInfo> selectAllByDistance(Map<String,Object> param);

    /**
     * 修改用户默认收货地址
     * @param userId
     * @return
     */
    int updateDefault(Integer userId);

    /**
     * 修改用户收货地址信息
     * @param record
     * @return
     */
    int update(YdFoodDistributionInfo record);

    /**
     * 删除用户收货地址信息
     * @param param
     * @return
     */
    int delete(Map<String,Object> param);

    /**
     * 查询用户默认地址
     * @param userId
     * @return
     */
    YdFoodDistributionInfo selectDefault(Integer userId);

    /**
     * 根据地址id和用户id查询地址信息
     * @param param
     * @return
     */
    YdFoodDistributionInfo selectByIdAndUserId(Map<String,Object> param);
}