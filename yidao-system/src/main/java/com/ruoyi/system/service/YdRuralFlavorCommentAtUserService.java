package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdRuralFlavorCommentAtUser;

import java.util.List;

public interface YdRuralFlavorCommentAtUserService {
    /**
     * 新增@人员信息
     * @param ydRuralFlavorCommentAtUsers
     * @return
     */
    public int insertAtUserInfo(List<YdRuralFlavorCommentAtUser> ydRuralFlavorCommentAtUsers);
}
