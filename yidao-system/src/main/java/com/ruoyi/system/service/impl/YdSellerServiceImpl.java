package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdSeller;
import com.ruoyi.system.mapper.YdSellerMapper;
import com.ruoyi.system.service.YdSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdSellerServiceImpl implements YdSellerService {
    @Autowired(required = false)
    private YdSellerMapper ydSellerMapper;

    @Override
    public int insertSeller(YdSeller ydSeller) {
        return ydSellerMapper.insertSeller(ydSeller);
    }
}
