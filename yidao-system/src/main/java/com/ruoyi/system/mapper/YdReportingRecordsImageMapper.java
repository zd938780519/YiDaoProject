package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.YdReportingRecordsImage;
import com.ruoyi.system.provider.InsertReportingRecordsImage;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * 举报图片数据库访问层
 */
public interface YdReportingRecordsImageMapper {
    /**
     * 新增举报图片数据
     * @param ydReportingRecordsImages
     * @return
     */
    @InsertProvider(type = InsertReportingRecordsImage.class, method = "insertAll")
    public int insertReportingRecordsImages(List<YdReportingRecordsImage> ydReportingRecordsImages);

    /**
     * 逻辑删除举报图片
     * @param rrId
     * @return
     */
    public int updateReportingRecordsImage(long rrId);

}
