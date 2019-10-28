package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdVideoCommentAtUser;
import com.ruoyi.system.provider.InsertVideoCommentAtUser;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * 评论@人员数据访问层
 */
public interface YdVideoCommentAtUserMapper {
    /**
     * 新增@人员信息
     * @param ydVideoCommentAtUsers
     * @return
     */
    @InsertProvider(type = InsertVideoCommentAtUser.class, method = "insertAll")
    public int insertAtUserInfo(List<YdVideoCommentAtUser> ydVideoCommentAtUsers);


}
