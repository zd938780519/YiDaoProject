package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdVideoCommentAtUser;
import com.ruoyi.system.mapper.YdVideoCommentAtUserMapper;
import com.ruoyi.system.service.YdVideoCommentAtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdVideoCommentAtUserServiceImpl implements YdVideoCommentAtUserService {

    @Autowired(required = false)
    private YdVideoCommentAtUserMapper ydVideoCommentAtUserMapper;

    @Override
    public int insertAtUserInfo(List<YdVideoCommentAtUser> ydVideoCommentAtUsers) {
        return ydVideoCommentAtUserMapper.insertAtUserInfo(ydVideoCommentAtUsers);
    }
}
