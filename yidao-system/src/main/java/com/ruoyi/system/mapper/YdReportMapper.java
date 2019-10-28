package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdReportingRecords;

import java.util.List;

/**
 * 举报数据层
 */
public interface YdReportMapper {


    /**
     * 新增举报信息
     * @param ydReportingRecords
     * @return
     */
    public int insertReportInfo(YdReportingRecords ydReportingRecords);

    /**
     * 查询所有被举报的信息
     * @return
     */
    public List<YdReportingRecords> selectAllReportInfo();

    /**
     * 修改举报信息内容
     * @param ydReportingRecords
     * @return
     */
    public int updateReportInfo(YdReportingRecords ydReportingRecords);

}
