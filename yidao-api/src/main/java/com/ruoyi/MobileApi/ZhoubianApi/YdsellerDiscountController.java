package com.ruoyi.MobileApi.ZhoubianApi;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdSellerDiscountDraw;
import com.ruoyi.system.service.YdSellerDiscountDrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("周边优惠券Api")
@RestController
@RequestMapping("/yd/app/ZBDiscount")
public class YdsellerDiscountController {

    @Autowired
    private YdSellerDiscountDrawService discountDrawService;

    @ApiOperation("查询店铺所有优惠券")
    @PostMapping("/getDiscountOfShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query")
    })
    public Body getDiscountOfShop(@RequestParam(value = "shopId")Integer shopId, @RequestParam(value = "userId")Integer userId){
        List<YdSellerDiscountDraw> discountDrawList = discountDrawService.getDiscountOfShop(shopId,userId);
        if(discountDrawList != null && !discountDrawList.isEmpty()){
            return Body.newInstance(discountDrawList);
        }
        return Body.newInstance(204,"无数据");
    }

    @ApiOperation("用户领取优惠券")
    @PostMapping("/insertDiscountDraw")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "discountId",value = "优惠券id",required = true,paramType = "query"),
    })
    public Body insertDiscountDraw(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "discountId")Integer discountId){
        return discountDrawService.insert(userId,discountId);
    }
}
