package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.YdTeachingUser;

/**
 * 教学用户关联信息数据访问层
 */
public interface YdTeachingUserMapper {
    /**
     * 查询关联数据
     * @param ydTeachingUser
     * @return
     */
    public YdTeachingUser selectInfoById(YdTeachingUser ydTeachingUser);

    /**
     * 新增关联信息
     * @param ydTeachingUser
     * @return
     */
    public int insertInfo(YdTeachingUser ydTeachingUser);

    /**
     * 删除关联信息
     * @param ydTeachingUser
     * @return
     */
    public int deleteInfo(YdTeachingUser ydTeachingUser);

    /**
     * 根据教学id删除关联关系
     * @param teachingId
     * @return
     */
    public int deleteInfoByTeachingId(long teachingId);

    /**
     * 根据用户id删除关联关系
     * @param userId
     * @return
     */
    public int deleteInfoByUserId(long userId);

    /**
     * 修改关联关系表的数据
     * @param ydTeachingUser
     * @return
     */
    public int updateInfo(YdTeachingUser ydTeachingUser);

}
