package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdUserAppeal;
import com.ruoyi.system.mapper.YdUserAppealMapper;
import com.ruoyi.system.service.YdUserAppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdUserAppealServiceImpl implements YdUserAppealService {
    @Autowired(required = false)
    private YdUserAppealMapper ydUserAppealMapper;

    @Override
    public int insertUserAppeal(YdUserAppeal ydUserAppeal) {
        return ydUserAppealMapper.insertUserAppeal(ydUserAppeal);
    }

    @Override
    public int updateUserAppeal(YdUserAppeal ydUserAppeal) {
        return ydUserAppealMapper.updateUserAppeal(ydUserAppeal);
    }

    @Override
    public int selectUserAppeal(long userId) {
        return ydUserAppealMapper.selectUserAppeal(userId);
    }

}
