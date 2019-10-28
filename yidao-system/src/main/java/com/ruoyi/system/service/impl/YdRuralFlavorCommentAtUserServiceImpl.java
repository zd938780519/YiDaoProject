package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdRuralFlavorCommentAtUser;
import com.ruoyi.system.mapper.YdRuralFlavorCommentAtUserMapper;
import com.ruoyi.system.service.YdRuralFlavorCommentAtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdRuralFlavorCommentAtUserServiceImpl implements YdRuralFlavorCommentAtUserService {
    @Autowired(required = false)
    YdRuralFlavorCommentAtUserMapper ydRuralFlavorCommentAtUserMapper;
    @Override
    public int insertAtUserInfo(List<YdRuralFlavorCommentAtUser> ydRuralFlavorCommentAtUsers) {
        return ydRuralFlavorCommentAtUserMapper.insertAtUserInfo(ydRuralFlavorCommentAtUsers);
    }
}
