package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdTeachingCommentAtUser;
import com.ruoyi.system.domain.YdVideoCommentAtUser;
import com.ruoyi.system.mapper.YdTeachingCommentAtUserMapper;
import com.ruoyi.system.mapper.YdVideoCommentAtUserMapper;
import com.ruoyi.system.service.YdTeachingCommentAtUserService;
import com.ruoyi.system.service.YdVideoCommentAtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdTeachingCommentAtUserServiceImpl implements YdTeachingCommentAtUserService {

    @Autowired(required = false)
    private YdTeachingCommentAtUserMapper ydTeachingCommentAtUserMapper;

    @Override
    public int insertAtUserInfo(List<YdTeachingCommentAtUser> ydTeachingCommentAtUsers) {
        return ydTeachingCommentAtUserMapper.insertAtUserInfo(ydTeachingCommentAtUsers);
    }
}
