package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdReportingRecords;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface YdReportService {

    /**
     * 新增举报信息
     * @param ydReportingRecords
     * @param files
     * @return
     */
    public boolean insertReportInfo(YdReportingRecords ydReportingRecords, MultipartFile[] files);
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
