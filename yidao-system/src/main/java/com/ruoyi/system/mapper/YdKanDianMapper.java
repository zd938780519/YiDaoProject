package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdRuralFlavor;
import com.ruoyi.system.domain.YdTeachingContent;
import com.ruoyi.system.domain.YdUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 附近商铺排行
 */
public interface YdKanDianMapper {
    /**
     * 排行版块
     *
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdUser> searchYdUser(@Param("userId") long userId, @Param("currIndex") int currIndex,
                                     @Param("pageSize") int pageSize);

    /**
     * 特色版块
     *
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdUser> showYdUser(@Param("userId") long userId, @Param("currIndex") int currIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * 看点搜索1（教学搜索）
     *
     * @param searchWord
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdTeachingContent> searchForTeaching(@Param("searchWord") String searchWord, @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    /**
     * 看点搜索2（特色搜索）
     *
     * @param searchWord
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdUser> searchForFeature(@Param("searchWord") String searchWord, @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);

    /**
     * 看点搜索3（乡味搜索）
     *
     * @param searchWord
     * @param currIndex
     * @param pageSize
     * @return
     */
    public List<YdRuralFlavor> searchForRuralFlavor(@Param("searchWord") String searchWord, @Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
}
