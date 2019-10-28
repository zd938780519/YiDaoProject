package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopFlowAccount;
import java.util.List;

/**
 * 商城商家流水表数据库连接层
 */
public interface YdShopFlowAccountMapper {
    int insert(YdShopFlowAccount record);

    List<YdShopFlowAccount> selectAll();
}