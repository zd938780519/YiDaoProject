package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerFlowAccount;
import java.util.List;

public interface YdSellerFlowAccountMapper {
    int insert(YdSellerFlowAccount record);

    List<YdSellerFlowAccount> selectAll();
}