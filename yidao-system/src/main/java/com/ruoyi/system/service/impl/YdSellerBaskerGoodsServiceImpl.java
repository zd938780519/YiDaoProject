package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdSellerBasketGoods;
import com.ruoyi.system.mapper.YdSellerBasketGoodsMapper;
import com.ruoyi.system.service.YdSellerBaskerGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class YdSellerBaskerGoodsServiceImpl implements YdSellerBaskerGoodsService {
    @Autowired(required = false)
    private YdSellerBasketGoodsMapper basketGoodsMapper;

    /**
     * 根据购物车id和商品id查询购物子表信息
     * @param basketId
     * @param goodId
     * @return
     */
    @Override
    public YdSellerBasketGoods getBasketGoodByBasketIdAndGoodId(Integer basketId, Integer goodId) {
        Map<String,Integer> param = new HashMap<>();
        param.put("basketId",basketId);
        param.put("goodId",goodId);
        return basketGoodsMapper.getBasketGoodByBasketIdAndGoodId(param);
    }

    @Override
    public int insert(YdSellerBasketGoods record) {
        return basketGoodsMapper.insert(record);
    }

    @Override
    public int update(YdSellerBasketGoods record) {
        return basketGoodsMapper.update(record);
    }

    @Override
    public int delete(Integer id) {
        return basketGoodsMapper.delete(id);
    }

    @Override
    public int deleteAll(Integer basketId) {
        return basketGoodsMapper.deleteAll(basketId);
    }
}
