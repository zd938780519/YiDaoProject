package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdReportingRecords;
import com.ruoyi.system.domain.YdUserToUser;

import java.util.List;

/**
 * 用户与用户关系数据访问层
 */
public interface YdUserToUserMapper {

    /**
     * 获取关注我的人的数据
     * @param id
     * @return
     */
    public List<YdUserToUser> selectUserToUserInfoByTo(long id);

    /**
     * 搜索用户备注或名称
     * @param ydUserToUser
     * @return
     */
    public List<YdUserToUser> selectUserInfoByFromAndNameOrRemark(YdUserToUser ydUserToUser);

    /**
     * 查询用户关系是否已存在
     * @param ydUserToUser
     * @return
     */
    public int selectUserToUserIsHas(YdUserToUser ydUserToUser);

    /**
     * 新增用户关系
     * @param ydUserToUser
     * @return
     */
    public int insertUserToUserInfo(YdUserToUser ydUserToUser);

    /**
     * 修改用户关系
     * @param ydUserToUser
     * @return
     */
    public int updateUserToUserInfo(YdUserToUser ydUserToUser);

    /**
     * 获取拉黑状态
     * @param ydUserToUser
     * @return
     */
    public int getPullBlackType(YdUserToUser ydUserToUser);




}
