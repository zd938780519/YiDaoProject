package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdUserToUserLetter;
import com.ruoyi.system.mapper.YdUserToUserLetterMapper;
import com.ruoyi.system.service.YdUserToUserLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdUserToUserLetterServiceImpl implements YdUserToUserLetterService {
    @Autowired(required = false)
    private YdUserToUserLetterMapper ydUserToUserLetterMapper;

    @Override
    public int insertLetter(YdUserToUserLetter ydUserToUserLetter) {
        return ydUserToUserLetterMapper.insertLetter(ydUserToUserLetter);
    }

    @Override
    public List<YdUserToUserLetter> selectLetters(YdUserToUserLetter ydUserToUserLetter) {
        return ydUserToUserLetterMapper.selectLetters(ydUserToUserLetter);
    }
}
