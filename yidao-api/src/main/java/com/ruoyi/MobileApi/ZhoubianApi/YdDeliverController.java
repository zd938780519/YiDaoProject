package com.ruoyi.MobileApi.ZhoubianApi;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.service.YdFoodDeliverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("骑手Api")
@RestController
@RequestMapping("/yd/app/deilver")
public class YdDeliverController {
    @Autowired
    private YdFoodDeliverService deliverService;

    @ApiOperation("订单开始配送")
    @PostMapping("/sendOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderId",value = "订单id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "deliverId",value = "配送员id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderNum",value = "订单编号",required = true,paramType = "query")
    })
    public Body sendOrder(@RequestParam(value = "shopId")Integer shopId, @RequestParam(value = "orderId")Integer orderId,
                          @RequestParam(value = "deliverId")Integer deliverId, @RequestParam(value = "orderNum")Long orderNum){
        return deliverService.sendOrder(shopId,orderId,deliverId,orderNum);
    }

    @ApiOperation("订单已送达")
    @PostMapping("/sendOver")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderId",value = "订单id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "deliverId",value = "配送员id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderNum",value = "订单编号",required = true,paramType = "query")
    })
    public Body sendOver(@RequestParam(value = "shopId")Integer shopId, @RequestParam(value = "orderId")Integer orderId,
                         @RequestParam(value = "deliverId")Integer deliverId, @RequestParam(value = "orderNum")Long orderNum){
        return deliverService.sendOver(shopId,orderId,deliverId,orderNum);
    }
}
