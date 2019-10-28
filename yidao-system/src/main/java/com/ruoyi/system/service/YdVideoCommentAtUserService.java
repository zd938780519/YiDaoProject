package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdVideoCommentAtUser;

import java.util.List;

public interface YdVideoCommentAtUserService {
    /**
     * 新增@人员信息
     * @param ydVideoCommentAtUsers
     * @return
     */
    public int insertAtUserInfo(List<YdVideoCommentAtUser> ydVideoCommentAtUsers);
}
