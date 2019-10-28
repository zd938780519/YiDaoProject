package com.ruoyi.MobileApi.ZhoubianApi;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdSellerBasket;
import com.ruoyi.system.service.YdSellerBaskerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api("周边购物车Api")
@RestController
@RequestMapping("/yd/app/ZBBasker")
public class YdSellerBasketController {

    @Autowired
    private YdSellerBaskerService baskerService;

    @ApiOperation("根据用户和店铺id查询购物车信息")
    @PostMapping("/getBasketByUserAndShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query")
    })
    public Body getBasketByUserAndShop(@RequestParam("userId")Integer userId, @RequestParam("shopId")Integer shopId){
        YdSellerBasket basket = baskerService.getBasketByUserAndShop(userId,shopId);
        if(basket != null) {
            return Body.newInstance(basket);
        }
        return Body.newInstance(204,"无数据");
    }

    @ApiOperation("点击订餐按钮创建购物车")
    @PostMapping("/insertBasket")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "goodId",value = "商品id",required = true,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body insertBasket(@RequestParam("userId")Integer userId,@RequestParam("goodId")Integer goodId){
        YdSellerBasket basket = baskerService.insertBasket(userId,goodId);
        if(basket != null) {
            return Body.newInstance(basket);
        }
        return Body.newInstance(204,"无数据");
    }

    @ApiOperation("修改商品数量")
    @PostMapping("/updateGoodNumOfBasket")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "goodId",value = "商品id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "operateType",value = "数量+1 or 数量-1（1 or -1）",required = true,paramType = "query")
    })
    public Body updateGoodNumOfBasket(@RequestParam("userId")Integer userId,@RequestParam("goodId")Integer goodId,@RequestParam("operateType")Integer operateType){
        if(operateType != 1 && operateType != -1)return Body.newInstance(400,"operateType只能为1或-1");
        YdSellerBasket basket = baskerService.updateGoodNumOfBasket(userId,goodId,operateType);
        if(basket != null) {
            return Body.newInstance(basket);
        }
        return Body.newInstance(204,"无数据");
    }

    @ApiOperation("清空购物车")
    @PostMapping("/emptyBasket")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query")
    })
    public Body emptyBasket(@RequestParam("userId")Integer userId,@RequestParam("shopId")Integer shopId){
        //查询购物车信息并处理返回数据
        YdSellerBasket basket = baskerService.emptyBasket(userId,shopId);
        if(basket != null) {
            return Body.newInstance(basket);
        }
        return Body.newInstance(204,"无数据");
    }


}
