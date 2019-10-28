package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopOrderGoods;
import com.ruoyi.system.domain.YdShopOrderGoodsIsSimple;
import com.ruoyi.system.provider.InsertShopOrderGoods;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

/**
 * 订单商品表数据库连接层
 */
public interface YdShopOrderGoodsMapper {
    int insert(YdShopOrderGoods record);

    List<YdShopOrderGoods> selectAll();

    /**
     * 批量添加订单商品
     * @param ydShopOrderGoods
     * @return
     */
    @InsertProvider(type = InsertShopOrderGoods.class, method = "insertAll")
    int insertShopOrderGoods(List<YdShopOrderGoods> ydShopOrderGoods);

    /**
     * 删除指定订单商品
     * @param orderId
     * @return
     */
    int removeGoodsByOrderId(long orderId);

    /**
     * 获取订单商品简单信息
     * @param orderId
     * @return
     */
    List<YdShopOrderGoodsIsSimple> getOrderGoodsSimpleInfo(long orderId);

}