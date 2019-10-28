package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdReportingRecords;
import com.ruoyi.system.domain.YdUserToUser;
import com.ruoyi.system.mapper.YdUserToUserMapper;
import com.ruoyi.system.service.YdUserToUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdUserToUserServiceImpl implements YdUserToUserService {
    @Autowired(required = false)
    private YdUserToUserMapper ydUserToUserMapper;

    @Override
    public List<YdUserToUser> selectUserToUserInfoByTo(long id) {
        return ydUserToUserMapper.selectUserToUserInfoByTo(id);
    }

    @Override
    public List<YdUserToUser> selectUserInfoByFromAndNameOrRemark(YdUserToUser ydUserToUser) {
        return ydUserToUserMapper.selectUserInfoByFromAndNameOrRemark(ydUserToUser);
    }

    @Override
    public List<YdUserToUser> getFollowUserInfosByName(YdUserToUser ydUserToUser) {
        List<YdUserToUser> ydUserToUserByName = this.selectUserInfoByFromAndNameOrRemark(ydUserToUser);
        List<YdUserToUser> userToUsersByTo = this.selectUserToUserInfoByTo(ydUserToUser.getFromId());
        for(int i=0;i<ydUserToUserByName.size();i++){
            YdUserToUser userToUserByFrom = ydUserToUserByName.get(i);
            long toId = userToUserByFrom.getToId();
            for (YdUserToUser userToUserByTo:userToUsersByTo) {
                long fromId = userToUserByTo.getFromId();
                if(fromId == toId){
                    userToUserByFrom.setIsFriend(1);
                    ydUserToUserByName.remove(i);
                    ydUserToUserByName.add(i,userToUserByFrom);
                    break;
                }
            }
        }
        return ydUserToUserByName;
    }

    @Override
    public int selectUserToUserIsHas(YdUserToUser ydUserToUser) {
        return ydUserToUserMapper.selectUserToUserIsHas(ydUserToUser);
    }

    @Override
    public int insertUserToUserInfo(YdUserToUser ydUserToUser) {
        return ydUserToUserMapper.insertUserToUserInfo(ydUserToUser);
    }

    @Override
    public int updateUserToUserInfo(YdUserToUser ydUserToUser) {
        return ydUserToUserMapper.updateUserToUserInfo(ydUserToUser);
    }

    @Override
    public boolean updateRelationshipType(YdUserToUser ydUserToUser) {
        boolean flag = true;
        int selectUserToUserIsHas = this.selectUserToUserIsHas(ydUserToUser);
        if(selectUserToUserIsHas >0){
            int updateUserToUserInfo = this.updateUserToUserInfo(ydUserToUser);
            if(updateUserToUserInfo != 1){
                flag = false;
            }
        }else{
            int insertUserToUserInfo = this.insertUserToUserInfo(ydUserToUser);
            if(insertUserToUserInfo != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean getPullBlackType(YdUserToUser ydUserToUser) {
        boolean flag = false;
        int selectUserToUserIsHas = this.selectUserToUserIsHas(ydUserToUser);
        if(selectUserToUserIsHas>0){
            int pullBlackType = ydUserToUserMapper.getPullBlackType(ydUserToUser);
            flag = pullBlackType == 1?true:false;
        }
        return flag;
    }

}
