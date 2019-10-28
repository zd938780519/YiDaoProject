package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.YdParameterUtils;
import com.ruoyi.system.domain.YdShop;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.domain.YdShoppingCartGoods;
import com.ruoyi.system.domain.YdUser;
import com.ruoyi.system.mapper.YdIntegralMapper;
import com.ruoyi.system.mapper.YdShopGoodsMapper;
import com.ruoyi.system.mapper.YdShoppingCartGoodsMapper;
import com.ruoyi.system.service.YdIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class YdIntegralServiceImpl implements YdIntegralService {
    @Autowired(required = false)
    private YdIntegralMapper ydIntegralMapper;
    @Autowired(required = false)
    private YdShoppingCartGoodsMapper ydShoppingCartGoodsMapper;

    @Override
    public YdUser selectIntegralByUserId(long userId) {
        return ydIntegralMapper.selectIntegralByUserId(userId);
    }

    @Override
    public HashMap<String,Object> getAvailableIntegral(long userId, List<Long> list) {
        HashMap<String,Object> item = new HashMap<String,Object>();
        YdUser ydUser = ydIntegralMapper.selectIntegralByUserId(userId);
        int integral = ydUser.getIntegral();//积分
        int giftIntegral = ydUser.getGiftIntegral();//赠送的积分
        BigDecimal allIntegral = new BigDecimal(integral+"").setScale(10).add(new BigDecimal(giftIntegral+"").setScale(10));
        List<YdShop> ydShops = ydShoppingCartGoodsMapper.selectGoodsByIds(list);
        BigDecimal allSalePrice = new BigDecimal("0").setScale(10);//所有商品销售价格
        BigDecimal allSalePriceIntegral = new BigDecimal("0").setScale(10);//所有商品销售价格对应积分
        BigDecimal allIntegralLimit = new BigDecimal("0").setScale(10);//所有商品总积分限额
        BigDecimal allIntegralPrice = new BigDecimal("0").setScale(10);//所有商品总积分价格
        BigDecimal allIntegralPaymentPrice = new BigDecimal("0").setScale(10);//所有商品全积分付款金额
        BigDecimal allGiveIntegralPrice = new BigDecimal("0").setScale(10);//所有商品返积分金额
        for (YdShop shop:ydShops) {
            List<YdShoppingCartGoods> scGoods = shop.getScGoods();
            for (int i=0;i<scGoods.size();i++){
                YdShoppingCartGoods ydShoppingCartGoods = scGoods.get(i);
                int orderCount = ydShoppingCartGoods.getOrderCount();//订购数量
                double salePrice1 = ydShoppingCartGoods.getSalePrice();//销售价格
                int integralLimit = ydShoppingCartGoods.getIntegralLimit();//积分限额
                int integralPrice = ydShoppingCartGoods.getIntegralPrice();//积分价格
                int integralPaymentPrice = ydShoppingCartGoods.getIntegralPaymentPrice();//全积分付款金额
                int giveIntegralPrice = ydShoppingCartGoods.getGiveIntegralPrice();//返积分金额
                int exchangeRate = YdParameterUtils.EXCHANGE_RATE;//汇率
                BigDecimal thisSalePrice = new BigDecimal(orderCount+"").setScale(10).multiply(new BigDecimal(salePrice1+"").setScale(10));
                allSalePrice.add(thisSalePrice);
                allSalePriceIntegral.add(thisSalePrice.setScale(10).multiply(new BigDecimal(exchangeRate+"").setScale(10)));
                BigDecimal thisIntegralLimit = new BigDecimal(orderCount+"").setScale(10).multiply(new BigDecimal(integralLimit+"").setScale(10));
                allIntegralLimit.add(thisIntegralLimit.setScale(10));
                BigDecimal thisIntegralPaymentPrice = new BigDecimal(orderCount+"").setScale(10).multiply(new BigDecimal(integralPaymentPrice+"").setScale(10));
                allIntegralPaymentPrice.add(new BigDecimal(thisIntegralPaymentPrice+"").setScale(10));
                BigDecimal thisGiveIntegralPrice = new BigDecimal(orderCount+"").setScale(10).multiply(new BigDecimal(giveIntegralPrice+"").setScale(10));
                allGiveIntegralPrice.add(new BigDecimal(thisGiveIntegralPrice+"").setScale(10));
                BigDecimal thisIntegralPrice = new BigDecimal(orderCount+"").setScale(10).multiply(new BigDecimal(integralPrice+"").setScale(10));
                allIntegralPrice.add(new BigDecimal(thisIntegralPrice+"").setScale(10));
            }
        }
        //(我的积分-总积分价)>总积分限额？总积分限额:(我的积分-总积分价)
        int compareTo = allIntegral.subtract(allIntegralPrice).setScale(10).compareTo(allIntegralLimit.setScale(10));//我的积分-总积分价与总积分限额比较结果
        if(compareTo == 1 ){//>
            item.put("availableIntegral",Integer.parseInt(new BigDecimal(allIntegralLimit+"").setScale(0, BigDecimal.ROUND_UP)+""));
        }else {//<=
            item.put("availableIntegral",Integer.parseInt(allIntegral.subtract(allIntegralPrice).setScale(10).setScale(0, BigDecimal.ROUND_DOWN)+""));
        }
        //全积分支付优惠
        item.put("integralDiscount",Integer.parseInt(allSalePriceIntegral.subtract(allIntegralPaymentPrice).setScale(10).setScale(0, BigDecimal.ROUND_DOWN)+""));
        //总金额
        item.put("totalSum",allSalePrice);
        //总积分
        item.put("totalIntegral",allIntegralPrice);
        return item;
    }
}
