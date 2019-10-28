package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdFoodDeliver;
import java.util.List;

public interface YdFoodDeliverMapper {
    /**
     * 按id查询配送员信息
     * @param id
     * @return
     */
    YdFoodDeliver selectById(Integer id);

    int insert(YdFoodDeliver record);

    List<YdFoodDeliver> selectAll();
}