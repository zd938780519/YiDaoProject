package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerCollect;
import java.util.List;
import java.util.Map;

public interface YdSellerCollectMapper {
    int insert(YdSellerCollect record);

    int delete(Map<String,Object> param);

    int deleteOfBatch(Map<String,Object> param);

    int selectCount(YdSellerCollect record);

    List<YdSellerCollect> selectAll();
}