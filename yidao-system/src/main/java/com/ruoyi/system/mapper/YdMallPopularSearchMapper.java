package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdMallPopularSearch;
import java.util.List;

/**
 * 商城热门搜索数据库连接层
 */
public interface YdMallPopularSearchMapper {
    int insert(YdMallPopularSearch record);

    List<YdMallPopularSearch> selectAll();
}