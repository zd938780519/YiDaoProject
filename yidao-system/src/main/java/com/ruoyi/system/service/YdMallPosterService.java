package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdMallPoster;

import java.util.List;

public interface YdMallPosterService {
    int insert(YdMallPoster poster);

    List<YdMallPoster> selectAll();

}
