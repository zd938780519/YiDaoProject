package com.ruoyi.system.provider;

import com.ruoyi.system.domain.YdTeachingCommentAtUser;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class InsertTeachingCommentAtUser {
    public String insertAll(Map map) {
        List<YdTeachingCommentAtUser> urlBlack = (List<YdTeachingCommentAtUser>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO yd_teaching_comment_at_user ");
        sb.append("(tcom_id,user_id ) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].tcomId},#'{'list[{0}].userId})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
