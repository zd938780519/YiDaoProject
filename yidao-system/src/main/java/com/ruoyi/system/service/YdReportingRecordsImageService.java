package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdReportingRecordsImage;

import java.util.List;

public interface YdReportingRecordsImageService {
    /**
     * 新增举报图片数据
     * @param ydReportingRecordsImages
     * @return
     */
    public int insertReportingRecordsImages(List<YdReportingRecordsImage> ydReportingRecordsImages);

    /**
     * 逻辑删除举报图片
     * @param rrId
     * @return
     */
    public int updateReportingRecordsImage(long rrId);
}
