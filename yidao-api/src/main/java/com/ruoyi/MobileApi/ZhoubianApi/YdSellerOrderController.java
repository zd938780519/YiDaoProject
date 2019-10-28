package com.ruoyi.MobileApi.ZhoubianApi;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.service.YdSellerHotCommentService;
import com.ruoyi.system.service.YdSellerOrderRefundService;
import com.ruoyi.system.service.YdSellerOrderService;
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

import java.text.ParseException;
import java.util.List;

@Api("周边订单Api")
@RestController
@RequestMapping("/yd/app/ZBOrder")
public class YdSellerOrderController {

    @Autowired
    private YdSellerOrderService orderService;
    @Autowired
    private YdSellerHotCommentService hotCommentService;
    @Autowired
    private YdSellerOrderRefundService orderRefundService;

    @ApiOperation("确认订单")
    @PostMapping("/affirmOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query")
    })
    public Body affirmOrder(@RequestParam("userId")Integer userId, @RequestParam("shopId")Integer shopId)throws ParseException {
        return orderService.affirmOrder(userId,shopId);
    }

    @ApiOperation("创建订单")
    @PostMapping("/createOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "userName",value = "用户名",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "basketId",value = "购物车id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "discountDrawId",value = "优惠券id",required = false,paramType = "query"),
            @ApiImplicitParam(name = "payWay",value = "支付方式 1：支付宝 2：微信",required = true,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body createOrder(@RequestParam("userId")Integer userId,@RequestParam("userName")String userName,
                            @RequestParam("shopId")Integer shopId,@RequestParam("basketId")Integer basketId,
                            @RequestParam(value = "discountDrawId",required = false)Integer discountDrawId,
                            @RequestParam("payWay")Integer payWay) throws ParseException{
        return orderService.createOrder(userId,userName,shopId,basketId,discountDrawId,payWay);
    }

    @ApiOperation("使用订单")
    @PostMapping("/useOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderNum",value = "订单编号",required = true,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body useOrder(@RequestParam("shopId")Integer shopId,@RequestParam("orderNum")Long orderNum){
        return orderService.useOrder(shopId,orderNum);
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancelOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNum",value = "订单编号",required = true,paramType = "query"),
            @ApiImplicitParam(name = "remark",value = "备注",required = false,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body cancelOrder(@RequestParam("orderNum")Long orderNum,
                            @RequestParam(value = "remark",required = false)String remark){
        return orderService.cancelOrder(orderNum,remark);
    }

    @ApiOperation("订单退款")
    @PostMapping("/refundOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNum",value = "订单编号",required = true,paramType = "query"),
            @ApiImplicitParam(name = "dictIds[]",value = "理由（字典表id）",required = false,paramType = "query"),
            @ApiImplicitParam(name = "dictContent",value = "理由（自由填写）",required = false,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body refundOrder(@RequestParam("orderNum")Long orderNum,
                            @RequestParam(value = "dictIds[]",required = false)List<Integer> dictIds,@RequestParam(value = "dictContent",required = false)String dictContent){
        return orderRefundService.refundOrder(orderNum,dictIds,dictContent);
    }

    @ApiOperation("到店吃订单评论")
    @PostMapping("/sellerComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "orderId",value = "订单id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "score",value = "评分",required = true,paramType = "query"),
            @ApiImplicitParam(name = "content",value = "评论内容",required = false,paramType = "query"),
            @ApiImplicitParam(name = "isAnonymity",value = "是否匿名（0：不匿名 1：匿名）",required = true,paramType = "query"),
            @ApiImplicitParam(name = "images",value = "图片",required = false,paramType = "query"),
            @ApiImplicitParam(name = "video",value = "视频",required = false,paramType = "query"),
            @ApiImplicitParam(name = "likeIds[]",value = "点赞的商品id数组",required = false,paramType = "query")
    })
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public Body sellerComment(@RequestParam("userId")Integer userId,@RequestParam("shopId")Integer shopId,
                              @RequestParam("orderId")Integer orderId,@RequestParam("score")Integer score,
                              @RequestParam(value = "content",required = false)String content,
                              @RequestParam(value = "isAnonymity")Integer isAnonymity,
                              @RequestParam(value = "images",required = false)String images,
                              @RequestParam(value = "video",required = false)String video,
                              @RequestParam(value = "likeIds[]",required = false)List<Integer> likeIds){
        return hotCommentService.sellerComment(userId,shopId,orderId,score,content,isAnonymity,images,video,likeIds);
    }

//    @ApiOperation("周边商品点赞")
//    @PostMapping("/giveLike")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
//            @ApiImplicitParam(name = "goodId",value = "商品id",required = true,paramType = "query")
//    })
//    public Body giveLike(@RequestParam("userId")Integer userId,@RequestParam("goodId")Integer goodId){
//        return orderService.giveLike(userId,goodId);
//    }
//
//    @ApiOperation("周边商品取消赞")
//    @PostMapping("/cancelLike")
//    @ApiImplicitParam(name = "id",value = "点赞id",required = true,paramType = "query")
//    public Body cancelLike(@RequestParam("id")Integer id){
//        return orderService.cancelLike(id);
//    }
}