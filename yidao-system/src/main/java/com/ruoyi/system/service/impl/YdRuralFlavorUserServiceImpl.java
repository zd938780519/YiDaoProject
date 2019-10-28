package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdRuralFlavor;
import com.ruoyi.system.domain.YdRuralFlavorUser;
import com.ruoyi.system.mapper.YdRuralFlavorUserMapper;
import com.ruoyi.system.service.YdRuralFlavorService;
import com.ruoyi.system.service.YdRuralFlavorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdRuralFlavorUserServiceImpl implements YdRuralFlavorUserService {
    @Autowired(required = false)
    private YdRuralFlavorUserMapper ydRuralFlavorUserMapper;
    @Autowired
    private YdRuralFlavorService ydRuralFlavorService;
    @Override
    public YdRuralFlavorUser selectInfoById(YdRuralFlavorUser ydRuralFlavorUser) {
        return ydRuralFlavorUserMapper.selectInfoById(ydRuralFlavorUser);
    }

    @Override
    public boolean addForwardNum(long userId, long rfId) {
        YdRuralFlavorUser ydRuralFlavorUser = new YdRuralFlavorUser();
        ydRuralFlavorUser.setRfId(rfId);
        ydRuralFlavorUser.setUserId(userId);
        ydRuralFlavorUser.setIsForward(1);
        YdRuralFlavorUser ydRuralFlavorUser1 = this.selectInfoById(ydRuralFlavorUser);
        boolean flag = true;
        if(ydRuralFlavorUser1 == null){
            int insertInfo = this.insertInfo(ydRuralFlavorUser);
            if(insertInfo != 1){
                flag = false;
            }
        }else{
            int updateInfo = this.updateInfo(ydRuralFlavorUser);
            if(updateInfo != 1){
                flag = false;
            }
        }
        if(flag){
            int addForwardNum = ydRuralFlavorService.updateForwardNum(rfId);
            if(addForwardNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int insertInfo(YdRuralFlavorUser ydRuralFlavorUser) {
        return ydRuralFlavorUserMapper.insertInfo(ydRuralFlavorUser);
    }

    @Override
    public int deleteInfo(YdRuralFlavorUser ydRuralFlavorUser) {
        return ydRuralFlavorUserMapper.deleteInfo(ydRuralFlavorUser);
    }

    @Override
    public int deleteInfoByRfId(long rfId) {
        return ydRuralFlavorUserMapper.deleteInfoByRfId(rfId);
    }

    @Override
    public int deleteInfoByUserId(long userId) {
        return ydRuralFlavorUserMapper.deleteInfoByUserId(userId);
    }

    @Override
    public int updateInfo(YdRuralFlavorUser ydRuralFlavorUser) {
        return ydRuralFlavorUserMapper.updateInfo(ydRuralFlavorUser);
    }

    @Override
    public boolean collection(long userId, long rfId, int type) {
        YdRuralFlavorUser ydRuralFlavorUser = new YdRuralFlavorUser();
        ydRuralFlavorUser.setRfId(rfId);
        ydRuralFlavorUser.setUserId(userId);
        ydRuralFlavorUser.setIsCollection(type);
        YdRuralFlavorUser ydRuralFlavorUser1 = this.selectInfoById(ydRuralFlavorUser);
        boolean flag = true;
        if(ydRuralFlavorUser1 == null){
            int insertInfo = this.insertInfo(ydRuralFlavorUser);
            if(insertInfo != 1){
                flag = false;
            }
        }else{
            int updateInfo = this.updateInfo(ydRuralFlavorUser);
            if(updateInfo != 1){
                flag = false;
            }
        }
        YdRuralFlavor ydRuralFlavor = new YdRuralFlavor();
        ydRuralFlavor.setId(rfId);
        ydRuralFlavor.setCollectionNum(type);
        int updateCollectionNum = ydRuralFlavorService.updateCollectionNum(ydRuralFlavor);
        if(updateCollectionNum != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean fabulousOrStep(long userId, long rfId, int type) {
        YdRuralFlavorUser ydRuralFlavorUser = new YdRuralFlavorUser();
        ydRuralFlavorUser.setRfId(rfId);
        ydRuralFlavorUser.setUserId(userId);
        ydRuralFlavorUser.setFabulousOrStep(type);
        YdRuralFlavorUser ydRuralFlavorUser1 = this.selectInfoById(ydRuralFlavorUser);
        boolean flag = true;
        if(ydRuralFlavorUser1 == null){
            int insertInfo = this.insertInfo(ydRuralFlavorUser);
            if(insertInfo != 1){
                flag = false;
            }else{
                YdRuralFlavor ydRuralFlavor = new YdRuralFlavor();
                ydRuralFlavor.setId(rfId);
                if(type == -1){
                    ydRuralFlavor.setStepNum(-type);
                    int updateStepNum = ydRuralFlavorService.updateStepNum(ydRuralFlavor);
                    if(updateStepNum != 1){
                        flag = false;
                    }
                }else{
                    ydRuralFlavor.setFabulousNum(type);
                    int updateFabulousNum = ydRuralFlavorService.updateFabulousNum(ydRuralFlavor);
                    if(updateFabulousNum != 1){
                        flag = false;
                    }
                }
            }
        }else{
            int updateInfo = this.updateInfo(ydRuralFlavorUser);
            if(updateInfo != 1){
                flag = false;
            }else{
                int fabulousOrStep = ydRuralFlavorUser1.getFabulousOrStep();
                if(fabulousOrStep!=0){
                    YdRuralFlavor ydRuralFlavor = new YdRuralFlavor();
                    ydRuralFlavor.setId(rfId);
                    ydRuralFlavor.setStepNum(-type);
                    ydRuralFlavor.setFabulousNum(type);
                    int updateStepNum = ydRuralFlavorService.updateStepNum(ydRuralFlavor);
                    if(updateStepNum != 1){
                        flag = false;
                    }
                    int updateFabulousNum = ydRuralFlavorService.updateFabulousNum(ydRuralFlavor);
                    if(updateFabulousNum != 1){
                        flag = false;
                    }
                }else{
                    YdRuralFlavor ydRuralFlavor = new YdRuralFlavor();
                    ydRuralFlavor.setId(rfId);
                    ydRuralFlavor.setStepNum(1);
                    ydRuralFlavor.setFabulousNum(1);
                    if(type==1){
                        int updateFabulousNum = ydRuralFlavorService.updateFabulousNum(ydRuralFlavor);
                        if(updateFabulousNum != 1){
                            flag = false;
                        }
                    }else if(type==-1){
                        int updateStepNum = ydRuralFlavorService.updateStepNum(ydRuralFlavor);
                        if(updateStepNum != 1){
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }
}
