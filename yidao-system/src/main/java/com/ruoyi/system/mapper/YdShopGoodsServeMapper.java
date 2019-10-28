package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopGoodsServe;
import java.util.List;

/**
 * 商品服务数据库访问层
 */
public interface YdShopGoodsServeMapper {
    int insert(YdShopGoodsServe record);

    List<YdShopGoodsServe> selectAll();
}