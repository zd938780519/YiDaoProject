package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdMallVoucher;
import com.ruoyi.system.mapper.YdMallVoucherMapper;
import com.ruoyi.system.service.YdMallVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class YdMallVoucherServiceImpl implements YdMallVoucherService {
    @Autowired(required = false)
    private YdMallVoucherMapper ydMallVoucherMapper;

    @Override
    public int insert(YdMallVoucher record) {
        return ydMallVoucherMapper.insert(record);
    }

    @Override
    public List<YdMallVoucher> selectAll(int currIndex,int pageSize) {
        return ydMallVoucherMapper.selectAll(currIndex,pageSize);
    }

    @Override
    public YdMallVoucher selectById(long id) {
        return ydMallVoucherMapper.selectById(id);
    }
}
