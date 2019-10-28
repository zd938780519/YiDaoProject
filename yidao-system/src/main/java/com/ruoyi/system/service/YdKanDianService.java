package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdUser;

import java.util.List;

public interface YdKanDianService {
    /**
     * 排行版块
     *
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdUser> searchYdUser(long userId, int currIndex, int pageSize);
    /**
     * 特色版块
     *
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdUser> showYdUser(long userId, int currIndex, int pageSize);
    /**
     * 看点搜索(教学，特色，乡味)
     *
     * @param type 根据类型用不同的mapper接口
     * @param searchWord
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<Object> searchForAllThree(int type,String searchWord,int currIndex, int pageSize);
}
