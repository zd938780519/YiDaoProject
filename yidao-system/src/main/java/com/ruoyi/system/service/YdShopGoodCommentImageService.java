package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShopGoodCommentImage;

import java.util.List;

public interface YdShopGoodCommentImageService {
    int insert(YdShopGoodCommentImage record);

    List<YdShopGoodCommentImage> selectAll();
}
