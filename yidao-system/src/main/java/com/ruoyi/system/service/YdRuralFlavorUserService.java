package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdRuralFlavorUser;

public interface YdRuralFlavorUserService {
    /**
     * 查询关联数据
     * @param ydRuralFlavorUser
     * @return
     */
    public YdRuralFlavorUser selectInfoById(YdRuralFlavorUser ydRuralFlavorUser);

    /**
     * 增加转发数
     * @param userId
     * @param rfId
     * @return
     */
    public boolean addForwardNum(long userId,long rfId);

    /**
     * 新增关联信息
     * @param ydRuralFlavorUser
     * @return
     */
    public int insertInfo(YdRuralFlavorUser ydRuralFlavorUser);

    /**
     * 删除关联信息
     * @param ydRuralFlavorUser
     * @return
     */
    public int deleteInfo(YdRuralFlavorUser ydRuralFlavorUser);

    /**
     * 根据乡味id删除关联关系
     * @param rfId
     * @return
     */
    public int deleteInfoByRfId(long rfId);

    /**
     * 根据用户id删除关联关系
     * @param userId
     * @return
     */
    public int deleteInfoByUserId(long userId);

    /**
     * 修改关联关系表的数据
     * @param ydRuralFlavorUser
     * @return
     */
    public int updateInfo(YdRuralFlavorUser ydRuralFlavorUser);

    /**
     * 收藏
     * @param userId
     * @param rfId
     * @param type
     * @return
     */
    public boolean collection(long userId,long rfId,int type);

    /**
     * 赞或踩
     * @param userId
     * @param rfId
     * @param type
     * @return
     */
    public boolean fabulousOrStep(long  userId,long  rfId,int  type);

}
