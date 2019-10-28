package com.ruoyi.MobileApi.shop;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdMallVoucherExchange;
import com.ruoyi.system.domain.YdShop;
import com.ruoyi.system.service.YdIntegralService;
import com.ruoyi.system.service.YdMallVoucherExchangeService;
import com.ruoyi.system.service.YdShopSettlementService;
import com.ruoyi.system.service.YdShoppingCartGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Api("结算相关api")
@RestController
@RequestMapping("/yd/app/shop/settlement")
public class YdShopSettlementController {
    @Autowired
    private YdShoppingCartGoodsService ydShoppingCartGoodsService;
    @Autowired
    private YdMallVoucherExchangeService ydMallVoucherExchangeService;
    @Autowired
    private YdIntegralService ydIntegralService;
    @Autowired
    private YdShopSettlementService ydShopSettlementService;

    /**
     * 根据商品ids查询结算商品信息
     * @param ids 购物车商品ids
     * @return
     */
    @ApiOperation("根据商品ids查询结算商品信息")
    @PostMapping("/getShoppingCartByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="购物车商品ids",required=true,paramType="query")
    })
    public Body getShoppingCartByUserId(@RequestParam(value = "ids[]", required = false) List<Long> ids){
        List<YdShop> ydShops = ydShoppingCartGoodsService.selectGoodsByIds(ids);
        if(ydShops != null && ydShops.size()>0){
            return Body.newInstance(ydShops);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     * 获取可用抵用券数量
     * @param userId 用户id
     * @return
     */
    @ApiOperation("获取可用抵用券数量")
    @PostMapping("/getVoucherNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="ids",value="购物车商品ids",required=true,paramType="query")
    })
    public Body getVoucherNum(@RequestParam(value = "userId", required = false) long userId,@RequestParam(value = "ids[]", required = false) List<Long> ids){
        int voucherNum = ydMallVoucherExchangeService.getVoucherNum(userId,ids);
        return Body.newInstance(voucherNum);
    }

    /**
     * 获取抵用券列表--》标记出是否可用，不可用原因
     * @param userId
     * @param currIndex
     * @param pageSize
     * @param ids
     * @return
     */
    @ApiOperation("获取抵用券列表--》标记出是否可用，不可用原因")
    @PostMapping("/getAvailableVoucherList")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query"),
            @ApiImplicitParam(name="ids",value="购物车商品ids",required=true,paramType="query")
    })
    public Body getAvailableVoucherList(@RequestParam("userId") long userId,@RequestParam("currIndex") int currIndex,
                                        @RequestParam("pageSize") int pageSize,@RequestParam(value = "ids[]", required = false) List<Long> ids) {
        List<YdMallVoucherExchange> availableVoucherList = ydMallVoucherExchangeService.getAvailableVoucherList(userId, currIndex, pageSize, ids);
        if(availableVoucherList != null && availableVoucherList.size()>0){
            return Body.newInstance(availableVoucherList);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     * 获取用户可用积分、全积分支付优惠、总金额、总积分
     * @param userId 用户id
     * @return
     */
    @ApiOperation("获取用户可用积分、全积分支付优惠、总金额、总积分")
    @PostMapping("/getAvailableIntegral")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="ids",value="购物车商品ids",required=true,paramType="query")
    })
    public Body getAvailableIntegral(@RequestParam("userId") long userId,@RequestParam(value = "ids[]", required = false) List<Long> ids){
        HashMap<String, Object> availableIntegral = ydIntegralService.getAvailableIntegral(userId, ids);
        return Body.newInstance(availableIntegral);
    }


    /**
     * 提交订单
     * @return
     */
    @ApiOperation("提交订单")
    @PostMapping("/placeOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="ids",value="购物车商品ids",required=true,paramType="query"),
            @ApiImplicitParam(name="receivingAddress",value="收货地址id",required=true,paramType="query"),
            @ApiImplicitParam(name="voucherId",value="优惠券id",required=true,paramType="query"),
            @ApiImplicitParam(name="integral",value="使用积分",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="支付方式",required=true,paramType="query"),
            @ApiImplicitParam(name="remark",value="备注",required=true,paramType="query")
    })
    public Body placeOrder(@RequestParam("userId") long userId,@RequestParam("ids[]") List<Long> ids,
                           @RequestParam("receivingAddress") long receivingAddress,@RequestParam(value = "voucherId", required = false) Long voucherId,
                           @RequestParam("integral") int integral,@RequestParam("type") int type,@RequestParam(value = "remark", required = false) String remark){
        return ydShopSettlementService.placeOrder(userId, ids, receivingAddress, voucherId, integral, type,remark,null);
    }


    /**
     * 支付订单
     * @return
     */
    @ApiOperation("支付订单")
    @PostMapping("/paymentOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="ids",value="购物车商品ids",required=true,paramType="query"),
            @ApiImplicitParam(name="receivingAddress",value="收货地址id",required=true,paramType="query"),
            @ApiImplicitParam(name="voucherId",value="优惠券id",required=true,paramType="query"),
            @ApiImplicitParam(name="integral",value="使用积分",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="支付方式",required=true,paramType="query"),
            @ApiImplicitParam(name="orderId",value="订单编号",required=true,paramType="query")
    })
    public Body paymentOrder(@RequestParam("userId") long userId,@RequestParam("ids[]") List<Long> ids,
                           @RequestParam("receivingAddress") long receivingAddress,@RequestParam(value = "voucherId", required = false) Long voucherId,
                           @RequestParam("integral") int integral,@RequestParam("type") int type,@RequestParam("orderId") long orderId){
        return ydShopSettlementService.placeOrder(userId, ids, receivingAddress, voucherId, integral, type,null,orderId);
    }











}
