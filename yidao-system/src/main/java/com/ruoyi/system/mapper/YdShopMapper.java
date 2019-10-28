package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YdShopMapper {
    int insert(YdShop record);

    List<YdShop> selectAll();

    /**
     * 修改店铺余额和积分
     * @param shopId
     * @param balance
     * @param integral
     * @return
     */
    int updateBalanceAndIntegral(@Param("shopId") long shopId,@Param("balance") double balance,@Param("integral") int integral);
}