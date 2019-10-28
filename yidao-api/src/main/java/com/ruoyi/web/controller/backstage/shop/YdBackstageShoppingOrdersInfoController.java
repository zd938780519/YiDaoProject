package com.ruoyi.web.controller.backstage.shop;


import com.ruoyi.common.utils.KdniaoTrackQueryAPI;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.domain.YdShopOrderProcess;
import com.ruoyi.system.domain.YdShopOrders;
import com.ruoyi.system.service.YdShopGoodsService;
import com.ruoyi.system.service.YdShopOrdersService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/yd/backstage/shopping/orders")
public class YdBackstageShoppingOrdersInfoController {
    @Autowired
    private YdShopOrdersService ydShopOrdersService;
    @Autowired
    private YdShopGoodsService ydShopGoodsService;

    /**
     * 关闭订单
     * @param id
     * @return
     */
    @PostMapping("closeOrders")
    public String closeOrders (@RequestParam("id") long id){
        JSONObject json =new JSONObject();
        int closeOrders = ydShopOrdersService.closeOrders(id);
        json.put("status", closeOrders == 1 ? 1 : -1);
        return json.toString();
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @PostMapping("removeOrders")
    public String removeOrders (@RequestParam("id") long id){
        JSONObject json =new JSONObject();
        int closeOrders = ydShopOrdersService.removeOrders(id,-2,"商家删除订单");
        json.put("status", closeOrders == 1 ? 1 : -1);
        return json.toString();
    }

    /**
     * 获取订单详细信息
     * @param orderId
     * @return
     */
    @PostMapping("getOrderInfo")
    public String getOrderInfo(@RequestParam("orderId") long orderId){
        JSONObject json =new JSONObject();
        YdShopOrders ydShopOrders = ydShopOrdersService.selectOrderInfo(orderId);
        List<YdShopGoods> ydShopGoods = ydShopGoodsService.selectGoodsByOrderId(orderId);
        if(ydShopOrders != null && ydShopGoods!=null && ydShopGoods.size()>0){
            JSONObject ydShopOrdersJson = JSONObject.fromObject(ydShopOrders);
            JSONArray ydShopGoodsJson = JSONArray.fromObject(ydShopGoods);
            json.put("status",1);
            json.put("order",ydShopOrdersJson);
            json.put("goods",ydShopGoodsJson);
        }else{
            json.put("status",-1);
        }
        return json.toString();
    }

    /**
     * 修改备注和应付金额
     * @param orderId 订单id
     * @param remark 备注
     * @param amountPayable 应付金额
     * @return
     */
    @PostMapping("updateRemarkAndMoney")
    public String updateRemarkAndMoney(@RequestParam("orderId") long orderId,@RequestParam(value = "remark", required = false) String remark,
                                       @RequestParam(value = "amountPayable", required = false) Double amountPayable){
        JSONObject json =new JSONObject();
        YdShopOrders ydShopOrders = new YdShopOrders();
        ydShopOrders.setId(orderId);
        ydShopOrders.setRemark(remark);
        ydShopOrders.setAmountPayable(amountPayable);
        int updateRemarkAndMoney = ydShopOrdersService.updateRemarkAndMoney(ydShopOrders);
        if(updateRemarkAndMoney == 1){
            json.put("status",1);
        }else{
            json.put("status",-1);
        }
        return json.toString();
    }

    /**
     * 获取发货信息
     * @param orderId
     * @return
     */
    @PostMapping("getDeliverGoodsInfo")
    public String getDeliverGoodsInfo(@RequestParam("orderId") long orderId){
        JSONObject json =new JSONObject();
        YdShopOrders ydShopOrders = ydShopOrdersService.selectDeliverGoodsInfo(orderId);
        if(ydShopOrders!=null){
            JSONObject ydShopOrdersJson = JSONObject.fromObject(ydShopOrders);
            json.put("info",ydShopOrdersJson);
            json.put("status",1);
        }else{
            json.put("status",-1);
        }
        return json.toString();
    }

    /**
     * 修改发货信息
     * @param orderId 订单id
     * @param deliveryType 发货类型
     * @param deliveryTypeShow 发货类型显示值
     * @param freightBillNo 运单号
     * @return
     */
    @PostMapping("updateOrderDelivery")
    public String updateOrderDelivery(@RequestParam("orderId") long orderId,@RequestParam("deliveryType") String deliveryType,
                                      @RequestParam("deliveryTypeShow") String deliveryTypeShow,@RequestParam("freightBillNo") String freightBillNo){
        JSONObject json =new JSONObject();
        YdShopOrders ydShopOrders = new YdShopOrders();
        ydShopOrders.setId(orderId);
        ydShopOrders.setDistributionModeType(deliveryType);
        ydShopOrders.setDistributionModeShow(deliveryTypeShow);
        ydShopOrders.setFreightBillNo(freightBillNo);
        int updateOrderDelivery = ydShopOrdersService.updateOrderDelivery(ydShopOrders);
        YdShopOrderProcess ydShopOrderProcess = new YdShopOrderProcess();
        ydShopOrderProcess.setSoId(orderId);
        ydShopOrderProcess.setContent("已发货");
        ydShopOrderProcess.setReason("未收货");
        ydShopOrderProcess.setOrderStatus(3);
        int insertShopOrderProcess = ydShopOrdersService.insertOrderProcess(ydShopOrderProcess);
        json.put("status",updateOrderDelivery == 1 && insertShopOrderProcess == 1?1:-1);
        return json.toString();
    }

    /**
     * 判断快递单号是否正确
     * @param freightBillNo
     * @param deliveryType
     * @return
     */
    @PostMapping("judgeFreightBillNo")
    public String judgeFreightBillNo(@RequestParam("freightBillNo") String freightBillNo,@RequestParam("deliveryType") String deliveryType){
        JSONObject json =new JSONObject();
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        int state = 0;
        try {
            String result = api.getOrderTracesByJson(deliveryType, freightBillNo);
            state = JSONObject.fromObject(result).getInt("State");
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put("status",state);
        return json.toString();
    }

    /**
     * 获取物流跟踪信息
     * @param orderId
     * @return
     */
    @PostMapping("getLogisticsTrackingInfo")
    public String getLogisticsTrackingInfo(@RequestParam("orderId") long orderId){
        JSONObject json =new JSONObject();
        YdShopOrders ydShopOrders = ydShopOrdersService.selectDeliverGoodsInfo(orderId);
        if(ydShopOrders != null){
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            try {
                String result = api.getOrderTracesByJson(ydShopOrders.getDistributionModeType(), ydShopOrders.getFreightBillNo());
                json.put("result",result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            json.put("status",1);
        }else{
            json.put("status",-1);
        }
        return json.toString();
    }

    /**
     * 退款
     * @param id
     * @return
     */
    @PostMapping("refund")
    public String refund (@RequestParam("id") long id){
        JSONObject json =new JSONObject();
        int refund = ydShopOrdersService.refund(id);
        json.put("status", refund == 1 ? 1 : -1);
        return json.toString();
    }

    /**
     * 拒绝退款
     * @param id
     * @return
     */
    @PostMapping("refusalRefund")
    public String refusalRefund (@RequestParam("id") long id){
        JSONObject json =new JSONObject();
        int refund = ydShopOrdersService.refusalRefund(id);
        json.put("status", refund == 1 ? 1 : -1);
        return json.toString();
    }
}
