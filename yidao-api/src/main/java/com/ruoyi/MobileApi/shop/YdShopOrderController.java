package com.ruoyi.MobileApi.shop;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdShopOrders;
import com.ruoyi.system.service.YdShopOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("订单相关api")
@RestController
@RequestMapping("/yd/app/shop/order")
public class YdShopOrderController {
    @Autowired
    private YdShopOrdersService ydShopOrdersService;

    @ApiOperation("获取未付款订单数据")
    @PostMapping("/getOrderInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orderId",value="订单id",required=true,paramType="query")
    })
    public Body getOrderInfo(@RequestParam("orderId") long orderId){
        YdShopOrders ydShopOrders = ydShopOrdersService.selectOrderInfoOfUnpaid(orderId);
        if(ydShopOrders != null){
            return Body.newInstance(ydShopOrders);
        }else{
            return Body.newInstance(201,"获取失败");
        }
    }
    /**
     * 取消订单
     * @param orderId 订单id
     * @return
     */
    @ApiOperation("取消订单")
    @PostMapping("/cancellationOfOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name="orderId",value="订单id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="取消原因 不要用-1，因为-1是超时自动取消的type",required=true,paramType="query"),
            @ApiImplicitParam(name="typeShow",value="取消原因显示值",required=true,paramType="query")
    })
    public Body cancellationOfOrder(@RequestParam("orderId") long orderId,@RequestParam("type") int type,@RequestParam("typeShow") String typeShow){
        int removeOrders = ydShopOrdersService.removeOrders(orderId,type,typeShow);
        if(removeOrders == 1){
            return Body.newInstance("取消成功");
        }else{
            return Body.newInstance(201,"取消失败");
        }
    }

    //获取物流信息




    //获取全部订单或单个订单简单信息

    //催发货

    //确认收货

    //退款

    //


}
