package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdMallPopularSearch;
import com.ruoyi.system.domain.YdMallSearchRecord;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.mapper.YdMallPopularSearchMapper;
import com.ruoyi.system.mapper.YdMallSearchRecordMapper;
import com.ruoyi.system.service.YdMallSearchService;
import com.ruoyi.system.service.YdShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdMallSearchServiceImpl implements YdMallSearchService {
    @Autowired(required = false)
    YdMallSearchRecordMapper ydMallSearchRecordMapper;
    @Autowired(required = false)
    YdMallPopularSearchMapper ydMallPopularSearchMapper;
    @Autowired(required = false)
    private YdShopGoodsService ydShopGoodsService;

    @Override
    public List<YdShopGoods> selectGoodsByName(long userId, String content, int currIndex, int pageSize) {
        List<YdShopGoods> ydShopGoods = ydShopGoodsService.selectGoodsByName(content,currIndex,pageSize);
        //保存搜索历史
        YdMallSearchRecord ydMallSearchRecord = new YdMallSearchRecord();
        ydMallSearchRecord.setContent(content);
        ydMallSearchRecord.setUserId(userId);
        this.deleteSearchRecordByContent(userId,content);
        this.insertSearchRecord(ydMallSearchRecord);
        return ydShopGoods;
    }

    @Override
    public int insertSearchRecord(YdMallSearchRecord searchRecord) {
        return ydMallSearchRecordMapper.insertSearchRecord(searchRecord);
    }

    @Override
    public List<YdMallSearchRecord> selectSearchRecord(long userId) {
        return ydMallSearchRecordMapper.selectSearchRecord(userId);
    }

    @Override
    public int deleteSearchRecordByUserId(long userId) {
        return ydMallSearchRecordMapper.deleteSearchRecordByUserId(userId);
    }

    @Override
    public boolean deleteSearchRecordByContent(long userId,String content) {
        int deleteSearchRecordById = ydMallSearchRecordMapper.deleteSearchRecordByContent(userId,content);
        return deleteSearchRecordById == 1?true:false;
    }

    @Override
    public List<YdMallPopularSearch> selectPopularSearch() {
        return ydMallPopularSearchMapper.selectAll();
    }
}
