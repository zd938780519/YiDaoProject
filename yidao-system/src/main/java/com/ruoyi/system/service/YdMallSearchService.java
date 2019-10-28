package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdMallPopularSearch;
import com.ruoyi.system.domain.YdMallSearchRecord;
import com.ruoyi.system.domain.YdShopGoods;

import java.util.List;

public interface YdMallSearchService {
    /**
     * 模糊搜索商城商品
     * @param userId
     * @param content
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdShopGoods> selectGoodsByName(long userId,String content, int currIndex, int pageSize);
    /**
     * 新增搜索历史
     * @param searchRecord
     * @return
     */
    public int insertSearchRecord(YdMallSearchRecord searchRecord);
    /**
     * 根据用户id查询搜索记录
     * @param userId
     * @return
     */
    public List<YdMallSearchRecord> selectSearchRecord(long userId);

    /**
     * 根据用户id删除搜索记录
     * @param userId
     * @return
     */
    public int deleteSearchRecordByUserId(long userId);

    /**
     * 根据内容删除个人的搜索记录
     * @param userId
     * @param content
     * @return
     */
    public boolean deleteSearchRecordByContent(long userId,String content);

    /**
     * 获取热门搜索
     * @return
     */
    public List<YdMallPopularSearch> selectPopularSearch();

}
