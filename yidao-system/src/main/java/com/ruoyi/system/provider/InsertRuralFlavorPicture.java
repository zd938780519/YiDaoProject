package com.ruoyi.system.provider;

import com.ruoyi.system.domain.YdRuralFlavorPicture;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class InsertRuralFlavorPicture {
    public String insertAll(Map map) {
        List<YdRuralFlavorPicture> urlBlack = (List<YdRuralFlavorPicture>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO yd_rural_flavor_picture ");
        sb.append("(rf_id,picture_path ) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].rfId},#'{'list[{0}].picturePath})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
