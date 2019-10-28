package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdShopOrderCancelReason;
import com.ruoyi.system.domain.YdShopOrderGoodsIsSimple;
import com.ruoyi.system.domain.YdShopOrderProcess;
import com.ruoyi.system.domain.YdShopOrders;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.YdShopOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class YdShopOrdersServiceImpl implements YdShopOrdersService {
    @Autowired(required = false)
    private YdShopOrdersMapper ydShopOrdersMapper;
    @Autowired(required = false)
    private YdShopOrderGoodsMapper ydShopOrderGoodsMapper;
    @Autowired(required = false)
    private YdShoppingCartGoodsMapper ydShoppingCartGoodsMapper;
    @Autowired(required = false)
    private YdMallVoucherExchangeMapper ydMallVoucherExchangeMapper;
    @Autowired(required = false)
    private YdShopOrderCancelReasonMapper ydShopOrderCancelReasonMapper;

    @Override
    public List<YdShopOrders> selectALl(long shopId) {
        return ydShopOrdersMapper.selectALl(shopId);
    }

    @Override
    public List<YdShopOrders> selectByType(long shopId, long type) {
        return ydShopOrdersMapper.selectByType(shopId,type);
    }

    @Override
    public int closeOrders(long id) {
        //int closeOrders = ydShopOrdersMapper.closeOrders(id);
        YdShopOrderProcess ydShopOrderProcess = new YdShopOrderProcess();
        ydShopOrderProcess.setSoId(id);
        ydShopOrderProcess.setContent("已关闭");
        ydShopOrderProcess.setReason("已关闭");
        ydShopOrderProcess.setOrderStatus(7);
        int insertShopOrderProcess = this.insertOrderProcess(ydShopOrderProcess);
        return insertShopOrderProcess ==1?1:-1;
    }

    @Override
    public int insertOrderProcess(YdShopOrderProcess ydShopOrderProcess) {
        return ydShopOrdersMapper.insertOrderProcess(ydShopOrderProcess);
    }

    @Override
    public int removeOrders(long id,int type,String typeShow) {
        int deleteCartGoodsByOrderId = ydShoppingCartGoodsMapper.deleteCartGoodsByOrderId(id);
        int removeOrders = ydShopOrdersMapper.removeOrders(id);
        int removeOrdersProcess = ydShopOrdersMapper.removeOrdersProcess(id);
        int removeGoodsByOrderId = ydShopOrderGoodsMapper.removeGoodsByOrderId(id);
        Long voucherId = ydShopOrdersMapper.selectShopOrderById(id).getVoucherId();
        if(voucherId != null){
            ydMallVoucherExchangeMapper.updateStatus(voucherId,0);
        }
        int insertOrderCancelReason = ydShopOrderCancelReasonMapper.insert(new YdShopOrderCancelReason(id, type, typeShow));
        return (removeOrders != 0 && removeOrdersProcess != 0 && removeGoodsByOrderId != 0
                && deleteCartGoodsByOrderId != 0 && insertOrderCancelReason == 1)?1:-1;
    }

    @Override
    public YdShopOrders selectOrderInfo(long orderId) {
        return ydShopOrdersMapper.selectOrderInfo(orderId);
    }

    @Override
    public int updateRemarkAndMoney(YdShopOrders ydShopOrders) {
        return ydShopOrdersMapper.updateRemarkAndMoney(ydShopOrders);
    }

    @Override
    public YdShopOrders selectDeliverGoodsInfo(long orderId) {
        return ydShopOrdersMapper.selectDeliverGoodsInfo(orderId);
    }

    @Override
    public int updateOrderDelivery(YdShopOrders ydShopOrders) {
        return ydShopOrdersMapper.updateOrderDelivery(ydShopOrders);
    }

    @Override
    public int refund(long id) {
        YdShopOrderProcess ydShopOrderProcess = new YdShopOrderProcess();
        ydShopOrderProcess.setSoId(id);
        ydShopOrderProcess.setContent("已退款");
        ydShopOrderProcess.setReason("已退款");
        ydShopOrderProcess.setOrderStatus(5);
        int insertShopOrderProcess = this.insertOrderProcess(ydShopOrderProcess);
        return insertShopOrderProcess ==1?1:-1;
    }

    @Override
    public int refusalRefund(long id) {
        long sopId = ydShopOrdersMapper.selectSopMax(id);
        int refusalRefund = ydShopOrdersMapper.refusalRefund(sopId);
        return refusalRefund == 1?1:-1;
    }

    @Override
    public YdShopOrders selectOrderInfoOfUnpaid(long orderId) {
        YdShopOrders ydShopOrders = ydShopOrdersMapper.selectOrderInfo(orderId);
        List<HashMap<String,Object>> goods = new ArrayList<>();
        List<YdShopOrderGoodsIsSimple> orderGoodsSimpleInfo = ydShopOrderGoodsMapper.getOrderGoodsSimpleInfo(orderId);
        for (int i=0;i<orderGoodsSimpleInfo.size();i++){
            YdShopOrderGoodsIsSimple ydShopOrderGoodsIsSimple = orderGoodsSimpleInfo.get(i);
            long shopId = ydShopOrderGoodsIsSimple.getShopId();
            String shopName = ydShopOrderGoodsIsSimple.getShopName();
            String shopPhotoPath = ydShopOrderGoodsIsSimple.getShopPhotoPath();
            HashMap<String,Object> map = new HashMap<>();
            map.put("shopId",shopId);
            map.put("shopName",shopName);
            map.put("shopPhotoPath",shopPhotoPath);
            boolean isHas = false;
            int goodsIndex = 0;
            for(int j=0;j<goods.size();j++){
                long sId = (long)goods.get(j).get("shopId");
                if(sId == shopId){
                    isHas = true;
                    goodsIndex = j;
                    break;
                }
            }
            if(!isHas){
                List<YdShopOrderGoodsIsSimple> goodsList = new ArrayList<>();
                goodsList.add(ydShopOrderGoodsIsSimple);
                map.put("goodsList",goodsList);
                goods.add(map);
            }else{
                HashMap<String, Object> stringObjectHashMap = goods.get(goodsIndex);
                List<YdShopOrderGoodsIsSimple> goodsList = (List<YdShopOrderGoodsIsSimple>) stringObjectHashMap.get("goodsList");
                goodsList.add(ydShopOrderGoodsIsSimple);
                stringObjectHashMap.put("goodsList",goodsList);
                goods.remove(goodsIndex);
                goods.add(goodsIndex,stringObjectHashMap);
            }
        }
        ydShopOrders.setGoods(goods);
        return ydShopOrders;
    }
}
