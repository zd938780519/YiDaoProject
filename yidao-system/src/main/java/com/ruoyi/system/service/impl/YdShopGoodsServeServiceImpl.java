package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdShopGoodsServe;
import com.ruoyi.system.mapper.YdShopGoodsServeMapper;
import com.ruoyi.system.service.YdShopGoodsServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdShopGoodsServeServiceImpl implements YdShopGoodsServeService {
    @Autowired(required = false)
    YdShopGoodsServeMapper ydShopGoodsServeMapper;

    @Override
    public int insert(YdShopGoodsServe record) {
        return ydShopGoodsServeMapper.insert(record);
    }

    @Override
    public List<YdShopGoodsServe> selectAll() {
        return ydShopGoodsServeMapper.selectAll();
    }
}
