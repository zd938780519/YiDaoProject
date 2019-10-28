package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdUser;
import com.ruoyi.system.domain.YdUserToUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户相关数据访问层
 */
public interface YdUserMapper {

    /**
     *
     * @return 用户信息列表
     */
    public List<YdUser> selectAllUser();

    /**
     *
     * @param user 用户实体
     * @return 新增用户
     */
    public int insertUser(YdUser user);

    /**
     *
     * @param user 用户实体
     * @return 修改用户信息
     */
    public int updateUser(@Param("user")YdUser user);

    /**
     *
     * @param id 用户id
     * @param credential 用户密码凭证
     * @return 逻辑删除用户 - 销号
     */
    public int deleteUser(@Param("id")long id,@Param("credential")String credential);

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
     * 根据账号查询账号
     * @param phoneNum 手机号
     * @return
     */
    public int selectPhoneNum(@Param("phoneNum") String phoneNum);

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

    /**
     * 根据id查询用户信息（订单）
     * @param id
     * @return
     */
    YdUser selectByIdOfOrder(Long id);

}
