package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopGoodsParameter;
import java.util.List;

/**
 * 商城商品参数表数据库访问层
 */
public interface YdShopGoodsParameterMapper {
    int insert(YdShopGoodsParameter record);

    List<YdShopGoodsParameter> selectAll();
}