package com.ruoyi.system.provider;

import com.ruoyi.system.domain.YdVideoCommentAtUser;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class InsertVideoCommentAtUser {
    public String insertAll(Map map) {
        List<YdVideoCommentAtUser> urlBlack = (List<YdVideoCommentAtUser>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO yd_video_comment_at_user ");
        sb.append("(vc_id,user_id ) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].vcId},#'{'list[{0}].userId})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
