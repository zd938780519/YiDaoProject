package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdUserFlowAccount;
import java.util.List;

/**
 * 用户流水表数据库连接层
 */
public interface YdUserFlowAccountMapper {
    int insert(YdUserFlowAccount record);

    List<YdUserFlowAccount> selectAll();
}