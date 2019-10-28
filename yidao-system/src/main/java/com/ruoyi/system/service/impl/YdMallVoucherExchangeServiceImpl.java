package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdMallVoucherExchange;
import com.ruoyi.system.domain.YdShop;
import com.ruoyi.system.domain.YdShoppingCartGoods;
import com.ruoyi.system.mapper.YdMallVoucherExchangeMapper;
import com.ruoyi.system.mapper.YdMallVoucherMapper;
import com.ruoyi.system.mapper.YdShoppingCartGoodsMapper;
import com.ruoyi.system.service.YdMallVoucherExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class YdMallVoucherExchangeServiceImpl implements YdMallVoucherExchangeService {
    @Autowired(required = false)
    private YdMallVoucherExchangeMapper ydMallVoucherExchangeMapper;
    @Autowired(required = false)
    private YdMallVoucherMapper ydMallVoucherMapper;
    @Autowired(required = false)
    private YdShoppingCartGoodsMapper ydShoppingCartGoodsMapper;

    @Override
    public int insert(YdMallVoucherExchange record) {
        int insert = ydMallVoucherExchangeMapper.insert(record);
        int stockReduceOne = ydMallVoucherMapper.stockReduceOne(record.getMvId());
        return (insert == 1 && stockReduceOne == 1)?1:-1;
    }

    @Override
    public List<YdMallVoucherExchange> selectAllByUser(long userId, int currIndex, int pageSize,int status) {
        return ydMallVoucherExchangeMapper.selectAllByUser(userId,currIndex,pageSize,status);
    }

    @Override
    public int getVoucherNum(long userId,List<Long> ids) {
        List<YdMallVoucherExchange> ydMallVoucherExchanges = this.getAvailableVoucherList(userId, 0, 2100000000,ids);
        return ydMallVoucherExchanges.size();
    }

    @Override
    public List<YdMallVoucherExchange> getAvailableVoucherList(long userId, int currIndex, int pageSize, List<Long> list) {
        List<YdMallVoucherExchange> ydMallVoucherExchanges = ydMallVoucherExchangeMapper.selectAllByUser(userId, currIndex, pageSize, 0);
        List<YdShop> ydShops = ydShoppingCartGoodsMapper.selectGoodsByIds(list);
        BigDecimal allSalePrice = new BigDecimal("0").setScale(10);
        for (YdShop shop:ydShops) {
            List<YdShoppingCartGoods> scGoods = shop.getScGoods();
            for (int i=0;i<scGoods.size();i++){
                YdShoppingCartGoods ydShoppingCartGoods = scGoods.get(i);
                int orderCount = ydShoppingCartGoods.getOrderCount();//订购数量
                double salePrice1 = ydShoppingCartGoods.getSalePrice();//销售价格
                int isAllowCoupon = ydShoppingCartGoods.getIsAllowCoupon();//是否可用券
                if(isAllowCoupon == 1){
                    BigDecimal thisSalePrice = new BigDecimal(orderCount+"").setScale(10).multiply(new BigDecimal(salePrice1+"").setScale(10));
                    allSalePrice.add(thisSalePrice.setScale(10));
                }
            }
        }

        for (int i=0;i<ydMallVoucherExchanges.size();i++){
            YdMallVoucherExchange ydMallVoucherExchange = ydMallVoucherExchanges.get(i);
            BigDecimal limitedAmount = ydMallVoucherExchange.getLimitedAmount();
            int compareTo = limitedAmount.setScale(10).compareTo(allSalePrice.setScale(10));
            if(compareTo == -1 || compareTo == 0){
                ydMallVoucherExchange.setIsAvailable(true);
            }else {
                ydMallVoucherExchange.setIsAvailable(false);
                ydMallVoucherExchange.setRemark("消费金额不足，无法使用此优惠券！");
            }
            ydMallVoucherExchanges.remove(i);
            ydMallVoucherExchanges.add(i,ydMallVoucherExchange);
        }
        return ydMallVoucherExchanges;
    }

    @Override
    public int updateStatus(long id, int status) {
        return ydMallVoucherExchangeMapper.updateStatus(id,status);
    }
}
