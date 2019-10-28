package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdUser;
import com.ruoyi.system.domain.YdUserToUser;

import java.util.List;

public interface YdUserService {

    /**
     * 获取用户信息
     * @return
     */
    public List<YdUser> getUsers();

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int insertUser(YdUser user);

    /**
     * 修改用户信息
     * @param user 用户实体
     * @return
     */
    public int updateUser(YdUser user);


    /**
     *
     * @param id 用户id
     * @param credential 用户密码凭证
     * @return 逻辑删除用户 - 销号
     */
    public int deleteUser(long id,String credential);
    /**
     * 是否被封号
     * @param id
     * @return
     */
    public int isProhibition(long id);
    /**
     * 封号
     * @param id
     * @return
     */
    public int prohibition(long id);

    /**
     *
     * 解封
     * @param id
     * @return
     */
    public int relieveProhibition(long id);

    /**
     * 根据账号查询账号是否存在
     * @param phoneNum 手机号
     * @return
     */
    public int selectPhoneNum(String phoneNum);

    /**
     * 根据id查询账号信息
     * @param id
     * @return
     */
    public YdUser selectUserById(long id);

    /**
     * 根据id查询用户信息
     * @param ydUserToUser 用户关联关系实体
     * @return
     */
    public YdUser selectById(YdUserToUser ydUserToUser);



}
