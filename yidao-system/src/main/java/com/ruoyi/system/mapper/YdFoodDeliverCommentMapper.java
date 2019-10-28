package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdFoodDeliverComment;
import java.util.List;

public interface YdFoodDeliverCommentMapper {
    int insert(YdFoodDeliverComment record);

    List<YdFoodDeliverComment> selectAll();
}