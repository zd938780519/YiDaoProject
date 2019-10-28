package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerDict;
import java.util.List;
import java.util.Map;

public interface YdSellerDictMapper {
    /**
     * 根据id和类型查询字典信息
     * @param param
     * @return
     */
    List<String> getByIdAndType(Map<String,Object> param);

    int insert(YdSellerDict record);

    List<YdSellerDict> selectAll();
}