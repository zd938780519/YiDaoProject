package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdShopGoodComment;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.mapper.YdShopGoodsMapper;
import com.ruoyi.system.service.YdShopGoodCommentService;
import com.ruoyi.system.service.YdShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class YdShopGoodsServiceImpl implements YdShopGoodsService {
    @Autowired(required = false)
    private YdShopGoodsMapper ydShopGoodsMapper;
    @Autowired(required = false)
    private YdShopGoodCommentService ydShopGoodCommentService;

    @Override
    public List<YdShopGoods> getAll(YdShopGoods shopGoods) {
        return ydShopGoodsMapper.selectAll(shopGoods);
    }

    @Override
    public int insertGoods(YdShopGoods shopGoods) {
        return ydShopGoodsMapper.insertGoods(shopGoods);
    }

    @Override
    public int deleteGoods(long id) {
        return ydShopGoodsMapper.deleteGoods(id);
    }

    @Override
    public String selectPhotoPathById(long id) {
        return ydShopGoodsMapper.selectPhotoPathById(id);
    }

    @Override
    public int updateGoods(YdShopGoods shopGoods) {
        return ydShopGoodsMapper.updateGoods(shopGoods);
    }

    @Override
    public List<YdShopGoods> selectGoodsByOrderId(long orderId) {
        return ydShopGoodsMapper.selectGoodsByOrderId(orderId);
    }

    @Override
    public List<YdShopGoods> selectGoods(int currIndex, int pageSize) {
        return ydShopGoodsMapper.selectGoods(currIndex,pageSize);
    }

    @Override
    public HashMap<String,Object> selectGoodsById(long goodsId) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        YdShopGoods ydShopGoods = ydShopGoodsMapper.selectGoodsById(goodsId);
        map.put("goodsInfo",ydShopGoods);
        List<YdShopGoodComment> YdShopGoodCommentList = ydShopGoodCommentService.getFavorableRate(goodsId);
        int praiseNum=0;
        for (YdShopGoodComment shopGoodComment:YdShopGoodCommentList) {
            Integer commentScore = shopGoodComment.getCommentScore();
            Integer attitudeScore = shopGoodComment.getAttitudeScore();
            Integer logisticsScore = shopGoodComment.getLogisticsScore();
            if(commentScore>=4){
                praiseNum++;
            }
            if(attitudeScore>=4){
                praiseNum++;
            }
            if(logisticsScore>=4){
                praiseNum++;
            }
        }
        String favorableRate = String.format("%.1f", (praiseNum*100)/(YdShopGoodCommentList.size()*3.0));
        map.put("favorableRate",favorableRate);
        YdShopGoodComment commentByGoodsIdIsNew = ydShopGoodCommentService.getCommentByGoodsIdIsNew(goodsId);
        map.put("newComment",commentByGoodsIdIsNew);
        int commentNumByGoodsId = ydShopGoodCommentService.getCommentNumByGoodsId(goodsId);
        map.put("commentNum",commentNumByGoodsId);
        return map;
    }

    @Override
    public List<YdShopGoods> selectGoodsByName(String content, int currIndex, int pageSize) {
        return ydShopGoodsMapper.selectGoodsByName(content,currIndex,pageSize);
    }

    @Override
    public YdShopGoods selectGoodsParameters(long goodsId) {
        return ydShopGoodsMapper.selectGoodsParameters(goodsId);
    }
}
