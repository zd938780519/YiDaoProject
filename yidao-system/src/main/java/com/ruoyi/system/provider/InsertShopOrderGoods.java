package com.ruoyi.system.provider;

import com.ruoyi.system.domain.YdShopOrderGoods;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class InsertShopOrderGoods {
    public String insertAll(Map map) {
        List<YdShopOrderGoods> urlBlack = (List<YdShopOrderGoods>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO yd_shop_order_goods ");
        sb.append("(goods_id,sgp_id,sbg_id,so_id,shop_id,goods_unit_price,goods_count,integral_limit,integral_price," +
                "give_integral_price,is_allow_coupon,is_fixed_money_and_integral,is_pure_integral ,integral_payment_price,is_integral_payment) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].goodsId},#'{'list[{0}].sgpId},#'{'list[{0}].sbgId},#'{'list[{0}].soId},#'{'list[{0}].shopId},#'{'list[{0}].goodsUnitPrice}," +
                "#'{'list[{0}].goodsCount},#'{'list[{0}].integralLimit},#'{'list[{0}].integralPrice},#'{'list[{0}].giveIntegralPrice}," +
                "#'{'list[{0}].isAllowCoupon},#'{'list[{0}].isFixedMoneyAndIntegral},#'{'list[{0}].isPureIntegral},#'{'list[{0}].integralPaymentPrice},#'{'list[{0}].isIntegralPayment})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
