package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdUserToUserLetter;

import java.util.List;

public interface YdUserToUserLetterService {
    /**
     * 新增私信信息
     * @param ydUserToUserLetter
     * @return
     */
    public int insertLetter(YdUserToUserLetter ydUserToUserLetter);

    /**
     * 查询聊天记录
     * @param ydUserToUserLetter
     * @return
     */
    public List<YdUserToUserLetter> selectLetters(YdUserToUserLetter ydUserToUserLetter);
}
