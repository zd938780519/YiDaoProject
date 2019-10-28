package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdUserLogin;

public interface YdUserLoginService {

    public int appeal(String identifier,String credential,int identityType, String content);
    /**+
     * 根据标识和密码凭证查询用户信息
     * @param identifier 标识
     * @param credential 密码凭证
     * @return
     */
    public YdUserLogin selectUserLogin(String identifier, String credential,int identityType);

    /**
     * 保存生成的token
     * @param id 登录表id
     * @param tokenValue token值
     * @return
     */
    public int updateToken(long id,String tokenValue);

    /**
     * 获取token值
     * @param id 登录表id
     * @return
     */
    public YdUserLogin selectToken(long id);

    /**
     * 保存生成的签名
     * @param id 登录表id
     * @param abstractValue 签名值
     * @return
     */
    public int updateAbstract(long id,String abstractValue);

    /**
     * 获取签名值
     * @param id 登录表id
     * @return
     */
    public YdUserLogin selectAbstract(long id);


    /**
     *  新增用户登录信息
     * @param userLogin
     * @return
     */
    public int insertUserLogin(YdUserLogin userLogin);


    /**
     * 修改密码
     * @param userLogin
     * @return
     */
    public int updatePassword(YdUserLogin userLogin);

    /**
     * 根据账号查询账号
     * @param identifier
     * @return
     */
    public int selectIdentifier(String identifier);


}
