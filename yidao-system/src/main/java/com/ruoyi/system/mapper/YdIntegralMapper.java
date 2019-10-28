package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdUser;
import org.apache.ibatis.annotations.Param;

public interface YdIntegralMapper {
    /**
     * 获取用户的积分
     * @param userId
     * @return
     */
    YdUser selectIntegralByUserId(long userId);

    /**
     * 修改用户积分
     * @param ydUser
     * @return
     */
    int updateIntegral(YdUser ydUser);

    /**
     * 增加用户的赠送的积分
     * @param userId
     * @param integral
     * @return
     */
    int bonusIntegral(@Param("userId") long userId,@Param("integral") int integral);



}
