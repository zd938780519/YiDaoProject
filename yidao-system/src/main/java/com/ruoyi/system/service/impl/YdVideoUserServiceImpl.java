package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdVideoUser;
import com.ruoyi.system.mapper.YdVideoUserMapper;
import com.ruoyi.system.service.YdVideoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class YdVideoUserServiceImpl implements YdVideoUserService {
    @Autowired(required = false)
    private YdVideoUserMapper ydVideoUserMapper;

    @Override
    public YdVideoUser selectInfoById(YdVideoUser ydVideoUser) {
        return ydVideoUserMapper.selectInfoById(ydVideoUser);
    }

    @Override
    public int insertInfo(YdVideoUser ydVideoUser) {
        return ydVideoUserMapper.insertInfo(ydVideoUser);
    }

    @Override
    public int deleteInfo(YdVideoUser ydVideoUser) {
        return ydVideoUserMapper.deleteInfo(ydVideoUser);
    }

    @Override
    public int deleteInfoByVideoId(long videoId) {
        return ydVideoUserMapper.deleteInfoByVideoId(videoId);
    }

    @Override
    public int deleteInfoByUserId(long userId) {
        return ydVideoUserMapper.deleteInfoByUserId(userId);
    }

    @Override
    public int updateInfo(YdVideoUser ydVideoUser) {
        return ydVideoUserMapper.updateInfo(ydVideoUser);
    }

    @Override
    public boolean collection(YdVideoUser ydVideoUser) {
        YdVideoUser ydVideoUser1 = this.selectInfoById(ydVideoUser);
        boolean flag = true;
        if(ydVideoUser1 == null){
            int insertInfo = this.insertInfo(ydVideoUser);
            if(insertInfo != 1){
                flag = false;
            }
        }else{
            int updateInfo = this.updateInfo(ydVideoUser);
            if(updateInfo != 1){
                flag = false;
            }
        }
        return flag;
    }
}
