package com.ruoyi.system.service.impl;

import com.ruoyi.common.oss.OSSFileUtil;
import com.ruoyi.system.domain.YdReportingRecords;
import com.ruoyi.system.domain.YdReportingRecordsImage;
import com.ruoyi.system.mapper.YdReportMapper;
import com.ruoyi.system.service.YdReportService;
import com.ruoyi.system.service.YdReportingRecordsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class YdReportServiceImpl implements YdReportService {
    @Autowired(required = false)
    private YdReportMapper ydReportMapper;
    @Autowired
    YdReportingRecordsImageService ydReportingRecordsImageService;

    @Override
    public boolean insertReportInfo(YdReportingRecords ydReportingRecords, MultipartFile[] files) {
        boolean flag = true;
        int insertReportInfo = ydReportMapper.insertReportInfo(ydReportingRecords);
        if(insertReportInfo != 1){
            flag = false;
        }else{
            if(files != null && files.length>0){
                List<YdReportingRecordsImage> ydReportingRecordsImage = new ArrayList();
                for (int i=0;i<files.length;i++){
                    MultipartFile file = files[i];
                    String fileKey = "image/report/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(file);
                    String path = OSSFileUtil.upload(fileKey, file);
                    YdReportingRecordsImage item = new YdReportingRecordsImage();
                    item.setRrId(ydReportingRecords.getId());
                    item.setPath(path);
                    ydReportingRecordsImage.add(item);
                }
                int insertReportingRecordsImages = ydReportingRecordsImageService.insertReportingRecordsImages(ydReportingRecordsImage);
                if(insertReportingRecordsImages<1){
                    flag=false;
                }
            }
        }
        return flag;
    }

    @Override
    public List<YdReportingRecords> selectAllReportInfo() {
        return ydReportMapper.selectAllReportInfo();
    }

    @Override
    public int updateReportInfo(YdReportingRecords ydReportingRecords) {
        return ydReportMapper.updateReportInfo(ydReportingRecords);
    }
}
