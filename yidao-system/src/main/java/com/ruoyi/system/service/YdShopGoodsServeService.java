package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShopGoodsServe;

import java.util.List;

public interface YdShopGoodsServeService {
    int insert(YdShopGoodsServe record);

    List<YdShopGoodsServe> selectAll();
}
