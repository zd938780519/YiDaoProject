package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdUserAppeal;
import com.ruoyi.system.domain.YdUserLogin;
import com.ruoyi.system.mapper.YdUserLoginMapper;
import com.ruoyi.system.service.YdUserAppealService;
import com.ruoyi.system.service.YdUserLoginService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdUserLoginServiceImpl implements YdUserLoginService {
    @Autowired(required = false)
    private YdUserLoginMapper ydUserLoginMapper;
    @Autowired
    private YdUserAppealService ydUserAppealService;

    @Override
    public int appeal(String identifier, String credential, int identityType, String content) {
        String credentialMD5 = "";
        if(credential != null && credential.length()!=0 && !credential.equals("null") && !credential.equals("unidfined")){
            credentialMD5= DigestUtils.md5Hex(credential);//TODO MD5加密
        }
        YdUserLogin ydUserLogin = this.selectUserLogin(identifier, credentialMD5, identityType);
        if(ydUserLogin != null){
            long userId = ydUserLogin.getUserId();
            int selectUserAppeal = ydUserAppealService.selectUserAppeal(userId);
            YdUserAppeal ydUserAppeal = new YdUserAppeal();
            ydUserAppeal.setUserId(userId);
            ydUserAppeal.setContent(content);
            int appeal = 0;
            if(selectUserAppeal == 1){
                appeal = ydUserAppealService.updateUserAppeal(ydUserAppeal);
            }else{
                appeal = ydUserAppealService.insertUserAppeal(ydUserAppeal);
            }
            if(appeal == 1){
                return 1;
            }else{
                return -2;
            }
        }else{
            return -1;
        }
    }

    @Override
    public YdUserLogin selectUserLogin(String identifier, String credential,int identityType) {
        return ydUserLoginMapper.selectUserLogin(identifier,credential,identityType);
    }

    @Override
    public int updateToken(long id, String tokenValue) {
        return ydUserLoginMapper.updateToken(id,tokenValue);
    }

    @Override
    public YdUserLogin selectToken(long id) {
        return ydUserLoginMapper.selectToken(id);
    }

    @Override
    public int updateAbstract(long id, String abstractValue) {
        return ydUserLoginMapper.updateAbstract(id,abstractValue);
    }

    @Override
    public YdUserLogin selectAbstract(long id) {
        return ydUserLoginMapper.selectAbstract(id);
    }

    @Override
    public int insertUserLogin(YdUserLogin userLogin) {
        return ydUserLoginMapper.insertUserLogin(userLogin);
    }

    @Override
    public int updatePassword(YdUserLogin userLogin) {
        return ydUserLoginMapper.updatePassword(userLogin);
    }

    @Override
    public int selectIdentifier(String identifier) {
        return ydUserLoginMapper.selectIdentifier(identifier);
    }
}
