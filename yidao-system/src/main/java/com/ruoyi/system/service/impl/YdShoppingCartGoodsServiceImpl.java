package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.YdParameterUtils;
import com.ruoyi.system.domain.YdShop;
import com.ruoyi.system.domain.YdShoppingCart;
import com.ruoyi.system.domain.YdShoppingCartGoods;
import com.ruoyi.system.mapper.YdShoppingCartGoodsMapper;
import com.ruoyi.system.service.YdShoppingCartGoodsService;
import com.ruoyi.system.service.YdShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class YdShoppingCartGoodsServiceImpl implements YdShoppingCartGoodsService {
    @Autowired(required = false)
    private YdShoppingCartGoodsMapper ydShoppingCartGoodsMapper;
    @Autowired(required = false)
    private YdShoppingCartService ydShoppingCartService;

    @Override
    public List<YdShop> getShoppingCartGoods(long userId) {
        YdShoppingCart ydShoppingCart = ydShoppingCartService.selectShoppingCart(userId);
        if(ydShoppingCart == null){
            ydShoppingCart = new YdShoppingCart();
            ydShoppingCart.setUserId(userId);
            ydShoppingCartService.insertShoppingCart(ydShoppingCart);
        }
        long shoppingCartId = ydShoppingCart.getId();
        List<YdShop> selectShoppingCartGoods = ydShoppingCartGoodsMapper.selectShoppingCartGoods(shoppingCartId);
        if(selectShoppingCartGoods != null && selectShoppingCartGoods.size()>0){
            for (int i=0;i<selectShoppingCartGoods.size();i++){
                YdShop ydShop = selectShoppingCartGoods.get(i);
                List<YdShoppingCartGoods> scGoods = ydShop.getScGoods();
                if(scGoods != null && scGoods.size()>0){
                    BigDecimal postalQuota = ydShop.getPostalQuota();//包邮限额
                    BigDecimal goodsMoney = new BigDecimal("0").setScale(2);
                    BigDecimal postage = new BigDecimal("0").setScale(2);//这家店铺所有不包邮的商品的邮费
                    BigDecimal postageOfFreeShipping  = new BigDecimal("0").setScale(2);//这家店铺所有包邮的商品的邮费
                    for (int j=0;j<scGoods.size();j++){
                        YdShoppingCartGoods ydShoppingCartGoods = scGoods.get(j);
                        BigDecimal orderCount = new BigDecimal(ydShoppingCartGoods.getOrderCount()+"");//数量
                        BigDecimal salePrice = new BigDecimal(ydShoppingCartGoods.getSalePrice()+"");//销售价格
                        BigDecimal integralPrice = new BigDecimal(ydShoppingCartGoods.getIntegralPrice()+"");//积分价
                        int isFixedMoneyAndIntegral = ydShoppingCartGoods.getIsFixedMoneyAndIntegral();//是否固定金额加固定积分
                        int isPureIntegral = ydShoppingCartGoods.getIsPureIntegral();//是否纯积分
                        int isFreeShipping = ydShoppingCartGoods.getIsFreeShipping();//是否包邮
                        if(isFreeShipping == 1){
                            if(isFixedMoneyAndIntegral == 1 || isPureIntegral == 1){//将积分换算成人民币后累加进去
                                goodsMoney = goodsMoney.add(orderCount.multiply(integralPrice.divide(new BigDecimal(YdParameterUtils.EXCHANGE_RATE+""),2,BigDecimal.ROUND_HALF_UP)));
                            }
                            if(isPureIntegral != 1){
                                goodsMoney = goodsMoney.add(orderCount.multiply(salePrice));
                            }
                            if(j == 0){
                                postageOfFreeShipping = postageOfFreeShipping.add(new BigDecimal(ydShoppingCartGoods.getFirstPostage()+"").setScale(2));
                            }else{
                                postageOfFreeShipping = postageOfFreeShipping.add(new BigDecimal(ydShoppingCartGoods.getAdditionalPostage()+"").setScale(2));
                            }
                        }else{
                            if(j == 0){
                                postage = postage.add(new BigDecimal(ydShoppingCartGoods.getFirstPostage()+"").setScale(2));
                            }else{
                                postage = postage.add(new BigDecimal(ydShoppingCartGoods.getAdditionalPostage()+"").setScale(2));
                            }
                        }
                    }
                    int isFreeShipping = postalQuota.setScale(2).compareTo(goodsMoney.setScale(2));
                    if(isFreeShipping == -1 || isFreeShipping == 0){//包邮
                        ydShop.setFreeShipping(true);
                    }else{
                        ydShop.setFreeShipping(false);
                        ydShop.setPostalBalance(postalQuota.subtract(goodsMoney).setScale(2));
                        postage = postage.add(postageOfFreeShipping);
                    }
                    ydShop.setPostage(Double.parseDouble(postage+""));
                    selectShoppingCartGoods.remove(i);
                    selectShoppingCartGoods.add(i,ydShop);
                }


            }
        }
        return selectShoppingCartGoods;
    }

    @Override
    public int insertShoppingCartGoods(long userId,long shopId,long goodsId,long sgpId,int count) {
        YdShoppingCart ydShoppingCart = ydShoppingCartService.selectShoppingCart(userId);
        if(ydShoppingCart == null){
            ydShoppingCart = new YdShoppingCart();
            ydShoppingCart.setUserId(userId);
            ydShoppingCartService.insertShoppingCart(ydShoppingCart);
        }
        YdShoppingCartGoods ydShoppingCartGoods = ydShoppingCartGoodsMapper.selectShoppingCartGoodsByUserIdAndGoodsIdAndShopGoodsParameterId(userId, goodsId, sgpId);
        if(ydShoppingCartGoods != null){//如果有，数量增加
            ydShoppingCartGoods.setOrderCount(count);
            return ydShoppingCartGoodsMapper.updateShoppingCartGoodsOrderCount(ydShoppingCartGoods);
        }else{//否则新增
            YdShoppingCartGoods goods = new YdShoppingCartGoods();
            goods.setBasketId(ydShoppingCart.getId());
            goods.setShopId(shopId);
            goods.setGoodsId(goodsId);
            goods.setSgpId(sgpId);
            goods.setOrderCount(count);
            return ydShoppingCartGoodsMapper.insertShoppingCartGoods(goods);
        }
    }

    @Override
    public int updateShoppingCartGoodsOrderCount(YdShoppingCartGoods ydShoppingCartGoods) {
        return ydShoppingCartGoodsMapper.updateShoppingCartGoodsOrderCount(ydShoppingCartGoods);
    }

    @Override
    public int updateShoppingCartGoodsCoverOrderCount(YdShoppingCartGoods ydShoppingCartGoods) {
        return ydShoppingCartGoodsMapper.updateShoppingCartGoodsCoverOrderCount(ydShoppingCartGoods);
    }

    @Override
    public int deleteShoppingCartGoods(long id) {
        return ydShoppingCartGoodsMapper.deleteShoppingCartGoods(id);
    }

    @Override
    public int deleteSomeShoppingCartGoods(List<Long> list) {
        return ydShoppingCartGoodsMapper.deleteSomeShoppingCartGoods(list);
    }

    @Override
    public boolean updateShoppingCartGoodsParameter(YdShoppingCartGoods ydShoppingCartGoods) {
        boolean flag = false;
        long id = ydShoppingCartGoods.getId();
        long basketId = ydShoppingCartGoods.getBasketId();
        long goodsId = ydShoppingCartGoods.getGoodsId();
        long sgpId = ydShoppingCartGoods.getSgpId();
        int orderCount = ydShoppingCartGoods.getOrderCount();
        YdShoppingCartGoods shoppingCartGoods = ydShoppingCartGoodsMapper.selectShoppingCartGoodsByBasketIdAndGoodsIdAndShopGoodsParameterId(basketId, goodsId, sgpId);
        if(shoppingCartGoods != null){//如果有就增加
            shoppingCartGoods.setOrderCount(orderCount);
            int updateShoppingCartGoods = ydShoppingCartGoodsMapper.updateShoppingCartGoodsOrderCount(shoppingCartGoods);
            int deleteShoppingCartGoods = ydShoppingCartGoodsMapper.deleteShoppingCartGoods(id);
            if(updateShoppingCartGoods == 1 && deleteShoppingCartGoods == 1){
                flag = true;
            }
        }else{//否则就修改
            int updateShoppingCartGoodsParameter = ydShoppingCartGoodsMapper.updateShoppingCartGoodsParameter(ydShoppingCartGoods);
            if(updateShoppingCartGoodsParameter == 1){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<YdShop> selectGoodsByIds(List<Long> list) {
        return ydShoppingCartGoodsMapper.selectGoodsByIds(list);
    }
}
