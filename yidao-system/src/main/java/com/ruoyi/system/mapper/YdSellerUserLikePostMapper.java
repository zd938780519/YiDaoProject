package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerUserLikePost;
import java.util.List;

public interface YdSellerUserLikePostMapper {
    int insert(YdSellerUserLikePost record);

    int insertOfBatch(List<YdSellerUserLikePost> record);

    int delete(Integer id);
}