package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdSellerBasket;
import com.ruoyi.system.domain.YdSellerBasketGoods;
import com.ruoyi.system.domain.YdSellerHotGoods;
import com.ruoyi.system.mapper.YdSellerBasketGoodsMapper;
import com.ruoyi.system.mapper.YdSellerBasketMapper;
import com.ruoyi.system.mapper.YdSellerHotGoodsMapper;
import com.ruoyi.system.service.YdSellerBaskerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class YdSellerBaskerServiceImpl implements YdSellerBaskerService {
    @Autowired(required = false)
    private YdSellerBasketMapper basketMapper;
    @Autowired(required = false)
    private YdSellerBasketGoodsMapper basketGoodsMapper;
    @Autowired(required = false)
    private YdSellerHotGoodsMapper hotGoodsMapper;

    /**
     * 周边主页查询购物车信息
     * @param userId
     * @param shopId
     * @return
     */
    @Override
    public YdSellerBasket getBasketByUserAndShop(Integer userId, Integer shopId) {
        //查询购物车信息并处理返回数据
        Map<String,Integer> param = new HashMap<>();
        param.put("userId",userId);
        param.put("shopId",shopId);
        return calculateBasketInfo(basketMapper.getBasketByUserAndShop(param));
    }

    /**
     * 点击订餐按钮创建购物车
     * @param userId
     * @param goodId
     * @return
     */
    @Override
    public YdSellerBasket insertBasket(Integer userId, Integer goodId) {
        //根据id查询商品信息
        YdSellerHotGoods hotGoods = hotGoodsMapper.getGoodById(goodId);
        if(hotGoods == null)return null;
        //检查用户是否有购物车主表数据
        YdSellerBasket sellerBasket = basketMapper.getBasketByUserId(userId);
        int basketId;
        //如果无，新增购物车主表数据并返回id;如果有，保存id到basketId
        if(sellerBasket == null){
            YdSellerBasket insertBasket = new YdSellerBasket();
            insertBasket.setUserId(userId);
            basketMapper.insertBasketReturnId(insertBasket);
            basketId = insertBasket.getId();
        }else{
            basketId = sellerBasket.getId();
        }
        //获取到id后，开始检查商品是否存在子表数据
        Map<String,Integer> param = new HashMap<>();
        param.put("basketId",basketId);
        param.put("goodId",goodId);
        YdSellerBasketGoods sellerBasketGoods = basketGoodsMapper.getBasketGoodByBasketIdAndGoodId(param);
        //如果无，新增一条子表记录;如果有，在该记录基础上添加数量
        if(sellerBasketGoods == null){
            YdSellerBasketGoods insertBasketGood = new YdSellerBasketGoods();
            insertBasketGood.setBasketId(basketId);
            insertBasketGood.setShopId(hotGoods.getSellerShopId());
            insertBasketGood.setGoodsId(goodId);
            insertBasketGood.setOrderTime(new Date());
            insertBasketGood.setOrderCount(1);
            basketGoodsMapper.insert(insertBasketGood);
        }else{
            YdSellerBasketGoods updateBasketGood = new YdSellerBasketGoods();
            updateBasketGood.setId(sellerBasketGoods.getId());
            updateBasketGood.setOrderTime(new Date());
            updateBasketGood.setOrderCount(sellerBasketGoods.getOrderCount() + 1);
            basketGoodsMapper.update(updateBasketGood);
        }
        //查询购物车信息并处理返回数据
        Map<String,Integer> param1 = new HashMap<>();
        param1.put("userId",userId);
        param1.put("shopId",hotGoods.getSellerShopId());
        YdSellerBasket basket = calculateBasketInfo(basketMapper.getBasketByUserAndShop(param1));
        return basket;
    }
    /**
     * 修改商品数量
     * @param userId
     * @param goodId
     * @param operateType
     * @return
     */
    @Override
    public YdSellerBasket updateGoodNumOfBasket(Integer userId, Integer goodId, Integer operateType) {
        //根据id查询商品信息
        YdSellerHotGoods hotGoods = hotGoodsMapper.getGoodById(goodId);
        if(hotGoods == null)return null;
        //查询购物车主表数据
        YdSellerBasket sellerBasket = basketMapper.getBasketByUserId(userId);
        //获取到id后，开始查询子表数据
        Map<String,Integer> param = new HashMap<>();
        param.put("basketId",sellerBasket.getId());
        param.put("goodId",goodId);
        YdSellerBasketGoods sellerBasketGoods = basketGoodsMapper.getBasketGoodByBasketIdAndGoodId(param);
        //修改前检查商品数量；如果为1并执行-1操作，则直接执行删除
        if(sellerBasketGoods.getOrderCount() == 1 && operateType == -1){
            //开始删除
            basketGoodsMapper.delete(sellerBasketGoods.getId());
        }else{
            //开始修改
            YdSellerBasketGoods updateBasketGood = new YdSellerBasketGoods();
            updateBasketGood.setId(sellerBasketGoods.getId());
            updateBasketGood.setOrderTime(new Date());
            updateBasketGood.setOrderCount(sellerBasketGoods.getOrderCount() + operateType);
            basketGoodsMapper.update(updateBasketGood);
        }

        //查询购物车信息并处理返回数据
        Map<String,Integer> param1 = new HashMap<>();
        param1.put("userId",userId);
        param1.put("shopId",hotGoods.getSellerShopId());
        return calculateBasketInfo(basketMapper.getBasketByUserAndShop(param1));
    }
    /**
     * 清空购物车
     * @param userId
     * @param shopId
     * @return
     */
    @Override
    public YdSellerBasket emptyBasket(Integer userId, Integer shopId) {
        YdSellerBasket basketD = basketMapper.getBasketByUserId(userId);
        if(basketD == null)return null;
        basketGoodsMapper.deleteAll(basketD.getId());

        //查询购物车信息并处理返回数据
        Map<String,Integer> param1 = new HashMap<>();
        param1.put("userId",userId);
        param1.put("shopId",shopId);
        return calculateBasketInfo(basketMapper.getBasketByUserAndShop(param1));
    }

    /**
     * 购物车价格信息计算
     */
    public YdSellerBasket calculateBasketInfo(YdSellerBasket basket){
        if(basket != null) {
            //循环计算商品价格
            BigDecimal priceTotal = new BigDecimal("0");
            BigDecimal salePriceTotal = new BigDecimal("0");
            BigDecimal packingTotal = new BigDecimal("0");
            int countTotal = 0;
            for (YdSellerBasketGoods bg : basket.getBasketGoodsList()) {
                //计算商品总价,存入实体
                bg.setOrderPrice(bg.getHotGoods().getOriginalPrice().multiply(new BigDecimal(bg.getOrderCount())));
                bg.setOrderSalePrice(bg.getHotGoods().getSalePrice().multiply(new BigDecimal(bg.getOrderCount())));
                //计算购物车总价
                priceTotal = priceTotal.add(bg.getOrderPrice());
                salePriceTotal = salePriceTotal.add(bg.getOrderSalePrice());
                packingTotal = packingTotal.add(bg.getHotGoods().getPackingExpense().multiply(new BigDecimal(bg.getOrderCount())));
                countTotal += bg.getOrderCount();
            }
            //购物车总价存入实体
            basket.setTotalPrice(priceTotal);
            basket.setSalePrice(salePriceTotal);
            basket.setPackingExpense(packingTotal);
            basket.setGoodsNum(countTotal);
            return basket;
        }
        return null;
    }
}
