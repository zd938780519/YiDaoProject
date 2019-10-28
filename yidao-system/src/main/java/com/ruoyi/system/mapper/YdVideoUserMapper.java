package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdVideoUser;

import java.util.List;

/**
 * 视频用户关联信息数据访问层
 */
public interface YdVideoUserMapper {
    /**
     * 查询关联数据
     * @param ydVideoUser
     * @return
     */
    public YdVideoUser selectInfoById(YdVideoUser ydVideoUser);

    /**
     * 新增关联信息
     * @param ydVideoUser
     * @return
     */
    public int insertInfo(YdVideoUser ydVideoUser);

    /**
     * 删除关联信息
     * @param ydVideoUser
     * @return
     */
    public int deleteInfo(YdVideoUser ydVideoUser);

    /**
     * 根据视频id删除关联关系
     * @param videoId
     * @return
     */
    public int deleteInfoByVideoId(long videoId);

    /**
     * 根据用户id删除关联关系
     * @param userId
     * @return
     */
    public int deleteInfoByUserId(long userId);

    /**
     * 修改关联关系表的数据
     * @param ydVideoUser
     * @return
     */
    public int updateInfo(YdVideoUser ydVideoUser);

}
