package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdMallPoster;
import java.util.List;

/**
 *
 * 商城广告数据库访问层
 */
public interface YdMallPosterMapper {
    int insert(YdMallPoster record);

    List<YdMallPoster> selectAll();
}