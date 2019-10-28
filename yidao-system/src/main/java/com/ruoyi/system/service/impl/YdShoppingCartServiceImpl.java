package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdShoppingCart;
import com.ruoyi.system.mapper.YdShoppingCartMapper;
import com.ruoyi.system.service.YdShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdShoppingCartServiceImpl implements YdShoppingCartService {
    @Autowired(required = false)
    private YdShoppingCartMapper ydShoppingCartMapper;

    @Override
    public YdShoppingCart selectShoppingCart(long userId) {
        return ydShoppingCartMapper.selectShoppingCart(userId);
    }

    @Override
    public int insertShoppingCart(YdShoppingCart ydShoppingCart) {
        return ydShoppingCartMapper.insertShoppingCart(ydShoppingCart);
    }
}
