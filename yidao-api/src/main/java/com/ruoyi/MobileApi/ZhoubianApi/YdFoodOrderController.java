package com.ruoyi.MobileApi.ZhoubianApi;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdFoodDistributionInfo;
import com.ruoyi.system.domain.YdFoodOrderRemark;
import com.ruoyi.system.service.YdFoodDistributionInfoService;
import com.ruoyi.system.service.YdFoodOrderRemarkService;
import com.ruoyi.system.service.YdFoodOrderService;
import com.ruoyi.system.service.YdSellerHotCommentService;
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

import java.util.List;


@Api("外卖订单Api")
@RestController
@RequestMapping("/yd/app/foodOrder")
public class YdFoodOrderController {
    @Autowired
    private YdFoodDistributionInfoService foodDistributionInfoService;
    @Autowired
    private YdFoodOrderService foodOrderService;
    @Autowired
    private YdFoodOrderRemarkService foodOrderRemarkService;
    @Autowired
    private YdSellerHotCommentService hotCommentService;

    @ApiOperation("新增外卖地址信息")
    @PostMapping("/insertDistribution")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "receivingAddress",value = "收货地址",required = true,paramType = "query"),
            @ApiImplicitParam(name = "consignee",value = "收货人",required = true,paramType = "query"),
            @ApiImplicitParam(name = "receivingTelephone",value = "收货电话",required = true,paramType = "query"),
            @ApiImplicitParam(name = "isDefault",value = "是否是默认地址(0:否 ，1：是)",required = true,paramType = "query"),
            @ApiImplicitParam(name = "remark",value = "备注",required = true,paramType = "query"),
            @ApiImplicitParam(name = "longitude",value = "经度",required = true,paramType = "query"),
            @ApiImplicitParam(name = "latitude",value = "纬度",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query")
    })
    public Body insertDistribution(YdFoodDistributionInfo foodDistributionInfo,@RequestParam(value = "shopId")Integer shopId){
        return foodDistributionInfoService.insert(foodDistributionInfo,shopId);
    }

    @ApiOperation("查询用户收货地址列表")
    @PostMapping("/selectDistributionList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query")
    })
    public Body selectDistributionList(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "shopId")Integer shopId){
        return foodDistributionInfoService.selectDistributionList(userId,shopId);
    }

    @ApiOperation("修改收货地址")
    @PostMapping("/updateDistribution")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "id",value = "收货地址id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "receivingAddress",value = "收货地址",required = true,paramType = "query"),
            @ApiImplicitParam(name = "consignee",value = "收货人",required = true,paramType = "query"),
            @ApiImplicitParam(name = "receivingTelephone",value = "收货电话",required = true,paramType = "query"),
            @ApiImplicitParam(name = "isDefault",value = "是否是默认地址(0:否 ，1：是)",required = true,paramType = "query"),
            @ApiImplicitParam(name = "remark",value = "备注",required = true,paramType = "query"),
            @ApiImplicitParam(name = "longitude",value = "经度",required = true,paramType = "query"),
            @ApiImplicitParam(name = "latitude",value = "纬度",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query")
    })
    public Body updateDistribution(YdFoodDistributionInfo foodDistributionInfo,@RequestParam(value = "shopId")Integer shopId){
        return foodDistributionInfoService.update(foodDistributionInfo,shopId);
    }

    @ApiOperation("删除收货地址")
    @PostMapping("/deleteDistribution")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "收货地址id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query")
    })
    public Body deleteDistribution(@RequestParam(value = "id")Integer id,@RequestParam(value = "userId")Integer userId,
                                   @RequestParam(value = "shopId")Integer shopId){
        return foodDistributionInfoService.delete(id,userId,shopId);
    }

    @ApiOperation("外卖模块确认订单")
    @PostMapping("/foodAffirmOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query")
    })
    public Body foodAffirmOrder(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "shopId")Integer shopId){
        return foodOrderService.foodAffirmOrder(userId,shopId);
    }

    @ApiOperation("查询用户备注")
    @PostMapping("/selectRemarkList")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query")
    public Body selectRemarkList(@RequestParam(value = "userId")Integer userId){
        return foodOrderRemarkService.selectAll(userId);
    }

    @ApiOperation("用户备注新增")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "content",value = "备注内容",required = true,paramType = "query")
    })
    public Body insert(YdFoodOrderRemark record){
        return foodOrderRemarkService.insert(record);
    }

    @ApiOperation("用户备注删除")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "备注id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query")
    })
    public Body delete(@RequestParam(value = "id")Integer id,@RequestParam(value = "userId")Integer userId){
        return foodOrderRemarkService.delete(id,userId);
    }

    @ApiOperation("创建订单")
    @PostMapping("/foodCreateOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "basketId",value = "购物车id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "discountDrawId",value = "优惠券id",required = false,paramType = "query"),
            @ApiImplicitParam(name = "distributionId",value = "收货地址id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "remark",value = "订单备注",required = false,paramType = "query"),
            @ApiImplicitParam(name = "tableWareNum",value = "餐具份数",required = true,paramType = "query"),
            @ApiImplicitParam(name = "payWay",value = "支付方式 1：支付宝 2：微信",required = true,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body createOrder(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "shopId")Integer shopId,
                            @RequestParam(value = "basketId")Integer basketId,@RequestParam(value = "discountDrawId",required = false)Integer discountDrawId,
                            @RequestParam(value = "distributionId")Integer distributionId, @RequestParam(value = "remark",required = false)String remark,
                            @RequestParam(value = "tableWareNum")Integer tableWareNum,@RequestParam(value = "payWay")Integer payWay){
        return foodOrderService.foodCreateOrder(userId,shopId,basketId,discountDrawId,distributionId,remark,tableWareNum,payWay);
    }

    @ApiOperation("卖家接单")
    @PostMapping("/sellerTakeOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderId",value = "订单id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderNum",value = "订单编号",required = true,paramType = "query")
    })
    public Body sellerTakeOrder(@RequestParam(value = "shopId")Integer shopId,@RequestParam(value = "orderId")Integer orderId,
                                @RequestParam(value = "orderNum")Long orderNum){
        return foodOrderService.sellerTakeOrder(shopId,orderId,orderNum);
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancelOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNum",value = "订单编号",required = true,paramType = "query"),
            @ApiImplicitParam(name = "reason",value = "理由",required = false,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body sellerTakeOrder(@RequestParam(value = "orderNum")Long orderNum,@RequestParam(value = "reason",required = false)String reason){
        return foodOrderService.cancelOrder(orderNum,reason);
    }

    @ApiOperation("外卖订单评论")
    @PostMapping("/foodComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderId",value = "订单id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "score",value = "评分",required = true,paramType = "query"),
            @ApiImplicitParam(name = "content",value = "评论内容",required = false,paramType = "query"),
            @ApiImplicitParam(name = "isAnonymity",value = "是否匿名（0：不匿名 1：匿名）",required = true,paramType = "query"),
            @ApiImplicitParam(name = "images",value = "图片",required = false,paramType = "query"),
            @ApiImplicitParam(name = "video",value = "视频",required = false,paramType = "query"),
            @ApiImplicitParam(name = "deliverId",value = "骑手id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "isGood",value = "是否满意（0：满意 1：不满意）",required = true,paramType = "query"),
            @ApiImplicitParam(name = "deliverTitle",value = "骑手评价标签",required = false,paramType = "query"),
            @ApiImplicitParam(name = "deliverContent",value = "骑手评价内容",required = false,paramType = "query"),
            @ApiImplicitParam(name = "timeCalibration",value = "校准时间",required = false,paramType = "query"),
            @ApiImplicitParam(name = "likeIds[]",value = "点赞的商品id数组",required = false,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body foodComment(@RequestParam("userId")Integer userId,@RequestParam("shopId")Integer shopId,
                            @RequestParam("orderId")Integer orderId,@RequestParam("score")Integer score,
                            @RequestParam(value = "content",required = false)String content,
                            @RequestParam(value = "isAnonymity")Integer isAnonymity,
                            @RequestParam(value = "images",required = false)String images,
                            @RequestParam(value = "video",required = false)String video,
                            @RequestParam("deliverId")Integer deliverId,@RequestParam("isGood")Integer isGood,
                            @RequestParam(value = "deliverTitle",required = false)String deliverTitle,
                            @RequestParam(value = "deliverContent",required = false)String deliverContent,
                            @RequestParam(value = "timeCalibration",required = false)String timeCalibration,
                            @RequestParam(value = "likeIds[]",required = false)List<Integer> likeIds){
        return hotCommentService.foodComment(userId,shopId,orderId,score,content,isAnonymity,images,video,deliverId,isGood,deliverTitle,deliverContent,timeCalibration,likeIds);
    }
}