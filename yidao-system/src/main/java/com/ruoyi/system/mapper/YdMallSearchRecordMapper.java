package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdMallSearchRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商城搜索记录数据库连接层
 */
public interface YdMallSearchRecordMapper {
    /**
     * 新增搜索历史
     * @param searchRecord
     * @return
     */
    int insertSearchRecord(YdMallSearchRecord searchRecord);

    /**
     * 根据用户id查询搜索记录
     * @param userId
     * @return
     */
    List<YdMallSearchRecord> selectSearchRecord(long userId);

    /**
     * 根据content删除搜索记录
     * @param userId
     * @param content
     * @return
     */
    int deleteSearchRecordByContent(@Param("userId") long userId,@Param("content") String content);

    /**
     * 根据用户id删除搜索记录
     * @param userId
     * @return
     */
    int deleteSearchRecordByUserId(long userId);


}