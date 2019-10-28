package com.ruoyi.system.provider;

import com.ruoyi.system.domain.YdTeachingMaterials;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class InsertTeachingMaterials {
    public String insertAll(Map map) {
        List<YdTeachingMaterials> urlBlack = (List<YdTeachingMaterials>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO yd_teaching_materials ");
        sb.append("(tc_Id,type ,material,number) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].tcId},#'{'list[{0}].type},#'{'list[{0}].material},#'{'list[{0}].number})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
