package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdTeachingCommentAtUser;
import com.ruoyi.system.provider.InsertTeachingCommentAtUser;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * 评论@人员数据访问层
 */
public interface YdTeachingCommentAtUserMapper {
    /**
     * 新增@人员信息
     * @param ydTeachingCommentAtUsers
     * @return
     */
    @InsertProvider(type = InsertTeachingCommentAtUser.class, method = "insertAll")
    public int insertAtUserInfo(List<YdTeachingCommentAtUser> ydTeachingCommentAtUsers);


}
