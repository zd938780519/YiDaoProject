package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdMallVoucher;

import java.util.List;

public interface YdMallVoucherService {

    int insert(YdMallVoucher record);

    List<YdMallVoucher> selectAll(int currIndex,int pageSize);
    /**
     *
     * 查询抵用券详细信息
     * @param id
     * @return
     */
    YdMallVoucher selectById(long id);
}
