package com.ruoyi.system.provider;

import com.ruoyi.system.domain.YdRuralFlavorCommentAtUser;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class InsertRuralFlavorCommentAtUser {
    public String insertAll(Map map) {
        List<YdRuralFlavorCommentAtUser> urlBlack = (List<YdRuralFlavorCommentAtUser>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO yd_rural_flavor_comment_at_user ");
        sb.append("(rfc_id,user_id ) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].rfcId},#'{'list[{0}].userId})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
