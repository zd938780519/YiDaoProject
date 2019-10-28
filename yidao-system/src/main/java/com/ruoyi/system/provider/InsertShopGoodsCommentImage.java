package com.ruoyi.system.provider;

import com.ruoyi.system.domain.YdShopGoodCommentImage;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class InsertShopGoodsCommentImage {
    public String insertAll(Map map) {
        List<YdShopGoodCommentImage> urlBlack = (List<YdShopGoodCommentImage>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO yd_shop_good_comment_image ");
        sb.append("(sgc_id,photo_path ) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].sgcId},#'{'list[{0}].photoPath})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
