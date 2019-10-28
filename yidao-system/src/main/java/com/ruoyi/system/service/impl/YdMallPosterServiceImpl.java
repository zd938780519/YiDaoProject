package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdMallPoster;
import com.ruoyi.system.mapper.YdMallPosterMapper;
import com.ruoyi.system.service.YdMallPosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class YdMallPosterServiceImpl implements YdMallPosterService {
    @Autowired(required = false)
    YdMallPosterMapper YdMallPosterMapper;
    @Override
    public int insert(YdMallPoster poster) {
        return YdMallPosterMapper.insert(poster);
    }

    @Override
    public List<YdMallPoster> selectAll() {
        return YdMallPosterMapper.selectAll();
    }


}
