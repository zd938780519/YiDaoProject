package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdShopGoodCommentImage;
import com.ruoyi.system.mapper.YdShopGoodCommentImageMapper;
import com.ruoyi.system.service.YdShopGoodCommentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class YdShopGoodCommentImageServiceImpl implements YdShopGoodCommentImageService {
    @Autowired(required = false)
    YdShopGoodCommentImageMapper ydShopGoodCommentImageMapper;
    @Override
    public int insert(YdShopGoodCommentImage record) {
        return ydShopGoodCommentImageMapper.insert(record);
    }

    @Override
    public List<YdShopGoodCommentImage> selectAll() {
        return ydShopGoodCommentImageMapper.selectAll();
    }
}
