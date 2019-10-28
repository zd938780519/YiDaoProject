package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdUser;

import java.util.HashMap;
import java.util.List;

public interface YdIntegralService {
    /**
     * 获取用户的积分
     * @param userId
     * @return
     */
    YdUser selectIntegralByUserId(long userId);

    /**
     * 获取用户可用积分、全积分支付优惠、总金额、总积分
     * @param userId
     * @return
     */
    HashMap<String,Object> getAvailableIntegral(long userId, List<Long> ids);
}
