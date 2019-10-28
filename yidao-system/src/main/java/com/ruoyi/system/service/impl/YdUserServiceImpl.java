package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdUser;
import com.ruoyi.system.domain.YdUserToUser;
import com.ruoyi.system.mapper.YdUserMapper;
import com.ruoyi.system.service.YdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class YdUserServiceImpl implements YdUserService {
    @Autowired(required = false)
    private YdUserMapper ydUserMapper;

    @Override
    public List<YdUser> getUsers() {
        return ydUserMapper.selectAllUser();
    }

    @Override
    public int insertUser(YdUser user) {
        return ydUserMapper.insertUser(user);
    }

    @Override
    public int updateUser(YdUser user) {
        return ydUserMapper.updateUser(user);
    }

    @Override
    public int deleteUser(long id, String credential) {
        return ydUserMapper.deleteUser(id,credential);
    }

    @Override
    public int isProhibition(long id) {
        return ydUserMapper.isProhibition(id);
    }

    @Override
    public int prohibition(long id) {
        return ydUserMapper.prohibition(id);
    }

    @Override
    public int relieveProhibition(long id) {
        return ydUserMapper.relieveProhibition(id);
    }

    @Override
    public int selectPhoneNum(String phoneNum) {
        return ydUserMapper.selectPhoneNum(phoneNum);
    }

    @Override
    public YdUser selectUserById(long id) {
        return ydUserMapper.selectUserById(id);
    }

    @Override
    public YdUser selectById(YdUserToUser ydUserToUser) {
        return ydUserMapper.selectById(ydUserToUser);
    }
}
