package com.ruoyi.system.service.impl;


import com.ruoyi.system.domain.YdUser;
import com.ruoyi.system.mapper.YdKanDianMapper;
import com.ruoyi.system.service.YdKanDianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 看点排行和特色
 *
 * @author ruoyi
 */
@Service
public class YdKanDianServiceImpl implements YdKanDianService {

    @Autowired(required = false)
    private YdKanDianMapper ydKanDianMapper;

    @Override
    public List<YdUser> searchYdUser(long userId, int currIndex, int pageSize) {
        return ydKanDianMapper.searchYdUser(userId, currIndex, pageSize);
    }

    @Override
    public List<YdUser> showYdUser(long userId, int currIndex, int pageSize) {
        return ydKanDianMapper.showYdUser(userId, currIndex, pageSize);
    }

    @Override
    public List searchForAllThree(int type, String searchWord, int currIndex, int pageSize) {

        if (type == 1) {
            return ydKanDianMapper.searchForTeaching(searchWord, currIndex, pageSize);
        } else if (type == 2) {
            return ydKanDianMapper.searchForFeature(searchWord, currIndex, pageSize);
        } else if (type == 3) {
            return ydKanDianMapper.searchForRuralFlavor(searchWord, currIndex, pageSize);
        }

        return null;
    }
}
