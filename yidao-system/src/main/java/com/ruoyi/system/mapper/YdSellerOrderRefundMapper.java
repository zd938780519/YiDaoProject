package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerOrderRefund;
import java.util.List;

public interface YdSellerOrderRefundMapper {
    int insert(YdSellerOrderRefund record);

    List<YdSellerOrderRefund> selectAll();
}