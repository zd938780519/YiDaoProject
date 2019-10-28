package com.ruoyi.common.utils.shortMessageVerification;

import java.util.HashMap;

public class ThirdPartyUtil {
    public static final HashMap<Integer,String> loginModeMap ;
    static{
        loginModeMap = new HashMap<Integer,String>();
        loginModeMap.put(1,"手机验证码");
        loginModeMap.put(2,"账号密码登录");
        loginModeMap.put(3,"QQ登录");
        loginModeMap.put(4,"微信号登录");
        loginModeMap.put(5,"新浪微博登录");

    }
}
