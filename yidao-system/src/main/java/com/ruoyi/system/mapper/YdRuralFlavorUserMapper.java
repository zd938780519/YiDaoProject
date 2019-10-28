package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdRuralFlavorUser;

public interface YdRuralFlavorUserMapper {
    /**
     * 查询关联数据
     * @param ydRuralFlavorUser
     * @return
     */
    public YdRuralFlavorUser selectInfoById(YdRuralFlavorUser ydRuralFlavorUser);

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

}
