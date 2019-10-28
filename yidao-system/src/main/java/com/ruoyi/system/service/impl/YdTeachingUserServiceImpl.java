package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdTeachingUser;
import com.ruoyi.system.domain.YdVideoUser;
import com.ruoyi.system.mapper.YdTeachingUserMapper;
import com.ruoyi.system.mapper.YdVideoUserMapper;
import com.ruoyi.system.service.YdTeachingUserService;
import com.ruoyi.system.service.YdVideoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class YdTeachingUserServiceImpl implements YdTeachingUserService {
    @Autowired(required = false)
    private YdTeachingUserMapper ydTeachingUserMapper;

    @Override
    public YdTeachingUser selectInfoById(YdTeachingUser ydTeachingUser) {
        return ydTeachingUserMapper.selectInfoById(ydTeachingUser);
    }

    @Override
    public int insertInfo(YdTeachingUser ydTeachingUser) {
        return ydTeachingUserMapper.insertInfo(ydTeachingUser);
    }

    @Override
    public int deleteInfo(YdTeachingUser ydTeachingUser) {
        return ydTeachingUserMapper.deleteInfo(ydTeachingUser);
    }

    @Override
    public int deleteInfoByTeachingId(long TeachingId) {
        return ydTeachingUserMapper.deleteInfoByTeachingId(TeachingId);
    }

    @Override
    public int deleteInfoByUserId(long userId) {
        return ydTeachingUserMapper.deleteInfoByUserId(userId);
    }

    @Override
    public int updateInfo(YdTeachingUser ydTeachingUser) {
        return ydTeachingUserMapper.updateInfo(ydTeachingUser);
    }
}
