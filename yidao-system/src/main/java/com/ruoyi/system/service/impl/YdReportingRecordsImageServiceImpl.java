package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdReportingRecordsImage;
import com.ruoyi.system.mapper.YdReportingRecordsImageMapper;
import com.ruoyi.system.service.YdReportingRecordsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdReportingRecordsImageServiceImpl implements YdReportingRecordsImageService {
    @Autowired(required = false)
    private YdReportingRecordsImageMapper ydReportingRecordsImageMapper;


    @Override
    public int insertReportingRecordsImages(List<YdReportingRecordsImage> ydReportingRecordsImages) {
        return ydReportingRecordsImageMapper.insertReportingRecordsImages(ydReportingRecordsImages);
    }

    @Override
    public int updateReportingRecordsImage(long rrId) {
        return ydReportingRecordsImageMapper.updateReportingRecordsImage(rrId);
    }
}
