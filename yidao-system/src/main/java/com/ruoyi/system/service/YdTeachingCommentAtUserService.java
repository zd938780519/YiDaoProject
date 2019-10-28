package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdTeachingCommentAtUser;

import java.util.List;

public interface YdTeachingCommentAtUserService {
    /**
     * 新增@人员信息
     * @param ydTeachingCommentAtUsers
     * @return
     */
    public int insertAtUserInfo(List<YdTeachingCommentAtUser> ydTeachingCommentAtUsers);
}
