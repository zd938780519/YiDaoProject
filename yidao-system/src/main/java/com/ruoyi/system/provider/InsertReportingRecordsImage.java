package com.ruoyi.system.provider;

import com.ruoyi.system.domain.YdReportingRecordsImage;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class InsertReportingRecordsImage {
    public String insertAll(Map map) {
        List<YdReportingRecordsImage> urlBlack = (List<YdReportingRecordsImage>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO yd_reporting_records_image ");
        sb.append("(rr_id,path ) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].rrId},#'{'list[{0}].path})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
