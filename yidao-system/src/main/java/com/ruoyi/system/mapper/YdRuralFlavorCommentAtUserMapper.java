package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdRuralFlavorCommentAtUser;
import com.ruoyi.system.provider.InsertRuralFlavorCommentAtUser;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * 评论@人员数据访问层
 */
public interface YdRuralFlavorCommentAtUserMapper {
    /**
     * 新增@人员信息
     * @param ydRuralFlavorCommentAtUsers
     * @return
     */
    @InsertProvider(type = InsertRuralFlavorCommentAtUser.class, method = "insertAll")
    public int insertAtUserInfo(List<YdRuralFlavorCommentAtUser> ydRuralFlavorCommentAtUsers);


}
