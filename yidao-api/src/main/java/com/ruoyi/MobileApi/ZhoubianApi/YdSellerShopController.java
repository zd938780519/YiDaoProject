package com.ruoyi.MobileApi.ZhoubianApi;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api("周边店铺、商品Api")
@RestController
@RequestMapping("/yd/app/ZhouBian")
public class YdSellerShopController {
    @Autowired
    private YdSellerHotGoodsService hotGoodsService;
    @Autowired
    private YdSellerShopService shopService;


    @ApiOperation("视频点入周边主页")
    @PostMapping("/getShopAndGoodByGoodId")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "热卖商品id",required = true,paramType = "query"),
        @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
        @ApiImplicitParam(name = "lng",value = "经度",required = false,paramType = "query"),
        @ApiImplicitParam(name = "lat",value = "纬度",required = false,paramType = "query")
    })
    public Body getShopAndGoodByGoodId(@RequestParam("id")int id, @RequestParam("userId")Integer userId,
                                       @RequestParam(value = "lng",required = false)Double lng, @RequestParam(value = "lat",required = false)Double lat,
                                       HttpServletRequest request){
        return Body.newInstance(hotGoodsService.getShopAndGoodByGoodId(id,userId,lng,lat,request));
    }

    @ApiOperation("周边主页附近店铺")
    @PostMapping("/getShopByDistance")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "lng",value = "经度",required = false,paramType = "query"),
        @ApiImplicitParam(name = "lat",value = "纬度",required = false,paramType = "query"),
        @ApiImplicitParam(name = "currIndex",value = "当前页",required = true,paramType = "query"),
        @ApiImplicitParam(name = "pageSize",value = "显示几条",required = true,paramType = "query")
    })
    public Body getShopByDistance(@RequestParam(value = "lng",required = false)Double lng,@RequestParam(value = "lat",required = false)Double lat,
                                  @RequestParam("currIndex") int currIndex,@RequestParam("pageSize") int pageSize){
        List<YdSellerShop> sellerShopList = shopService.getShopByDistance(lng,lat,currIndex,pageSize);
        if(sellerShopList != null && !sellerShopList.isEmpty())
            return Body.newInstance(sellerShopList);
        return Body.newInstance(204,"暂无数据");
    }

    @ApiOperation("点击进入店铺")
    @PostMapping("/getShowShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "lng",value = "经度",required = false,paramType = "query"),
            @ApiImplicitParam(name = "lat",value = "纬度",required = false,paramType = "query"),
            @ApiImplicitParam(name = "currIndex",value = "当前页",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "显示几条",required = true,paramType = "query")
    })
    public Body getShowShop(@RequestParam("userId")Integer userId,@RequestParam("shopId")Integer shopId,
                            @RequestParam(value = "lng",required = false)Double lng, @RequestParam(value = "lat",required = false)Double lat,
                            @RequestParam("currIndex") int currIndex,@RequestParam("pageSize") int pageSize,HttpServletRequest request){
        YdSellerShop shop = shopService.getShowShop(userId,shopId,lng,lat,currIndex,pageSize,request);
        if(shop != null){
            return Body.newInstance(shop);
        }
        return Body.newInstance(204,"暂无数据");
    }

    @ApiOperation("搜索框检索商品")
    @PostMapping("/searchGoods")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "searchName",value = "关键词",required = true,paramType = "query"),
            @ApiImplicitParam(name = "currIndex",value = "当前页",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "显示几条",required = true,paramType = "query")
    })
    public Body searchGoods(@RequestParam("shopId")Integer shopId,@RequestParam("searchName")String searchName,
                            @RequestParam("currIndex")Integer currIndex,@RequestParam("pageSize")Integer pageSize){
        List<YdSellerHotGoods> hotGoodsList = hotGoodsService.searchGoods(shopId,searchName,currIndex,pageSize);
        if(hotGoodsList != null && !hotGoodsList.isEmpty()){
            return Body.newInstance(hotGoodsList);
        }
        return Body.newInstance(204,"未查询到关键词“" + searchName + "”的商品");
    }

    @ApiOperation("查询商品详情")
    @PostMapping("/getGoodDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodId",value = "商品id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "lng",value = "经度",required = false,paramType = "query"),
            @ApiImplicitParam(name = "lat",value = "纬度",required = false,paramType = "query"),
            @ApiImplicitParam(name = "currIndex",value = "当前页",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "显示几条",required = true,paramType = "query")
    })
    public Body getGoodDetails(@RequestParam("goodId")Integer goodId,
                               @RequestParam(value = "lng",required = false)Double lng,
                               @RequestParam(value = "lat",required = false)Double lat){
        return hotGoodsService.getGoodDetails(goodId,lng,lat);
    }

    @ApiOperation("查询商品评论")
    @PostMapping("/getGoodComments")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodId",value = "商品id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "type",value = "评价类型（0：全部；1：最新；2：有图；3：差评；4：缩略）",required = true,paramType = "query"),
            @ApiImplicitParam(name = "currIndex",value = "当前页",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "显示几条",required = true,paramType = "query")
    })
    public Body getGoodComments(@RequestParam("goodId")Integer goodId,@RequestParam("type")Integer type,
                                @RequestParam("currIndex")Integer currIndex,@RequestParam("pageSize")Integer pageSize){
        return hotGoodsService.getGoodComments(goodId,type,currIndex,pageSize);
    }

    @ApiOperation("周边店铺收藏")
    @PostMapping("/giveCollect")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopId",value = "店铺id",required = true,paramType = "query"),
            @ApiImplicitParam(name = "shopName",value = "店铺名",required = true,paramType = "query")
    })
    public Body giveCollect(@RequestParam("userId") Integer userId,@RequestParam("shopId")Integer shopId,@RequestParam("shopName")String shopName){
        return shopService.giveCollect(userId,shopId,shopName);
    }

    @ApiOperation("周边店铺取消收藏")
    @PostMapping("/cancelCollect")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "收藏id", required = true, paramType = "query")
    })
    public Body cancelCollect(@RequestParam("userId")Integer userId,@RequestParam("id")Integer id){
        return shopService.cancelCollect(userId,id);
    }

    @ApiOperation("周边店铺取消收藏(批量)")
    @PostMapping("/cancelCollectOfBatch")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "ids[]", value = "收藏id数组", required = false, paramType = "query")
    })
    public Body cancelCollectOfBatch(@RequestParam(value = "userId",required = false)Integer userId,
                              @RequestParam(value = "ids[]",required = false) List<String> ids){
        return shopService.cancelCollectOfBatch(userId,ids);
    }
}