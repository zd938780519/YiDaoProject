package com.ruoyi.MobileApi.shop;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdShop;
import com.ruoyi.system.domain.YdShoppingCartGoods;
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

import java.util.List;

@Api("购物车相关api")
@RestController
@RequestMapping("/yd/app/shop/shoppingCart")
public class YdShoppingCartController {
    @Autowired
    private YdShoppingCartGoodsService YdShoppingCartGoodsService;

    /**
     * 根据用户查询购物车
     * @param userId 登陆人id
     * @return
     */
    @ApiOperation("根据用户查询购物车")
    @PostMapping("/getShoppingCartByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登陆人id",required=true,paramType="query")
    })
    public Body getShoppingCartByUserId(@RequestParam("userId") long userId){
        List<YdShop> shoppingCartGoods = YdShoppingCartGoodsService.getShoppingCartGoods(userId);
        if(shoppingCartGoods.size()>0){
            return Body.newInstance(shoppingCartGoods);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     * 加入购物车
     * @param userId 登陆人id
     * @param shopId 店铺id
     * @param goodsId 商品id
     * @param count 商品数量
     * @return
     */
    @ApiOperation("加入购物车")
    @PostMapping("/addShoppingCartGoods")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登陆人id",required=true,paramType="query"),
            @ApiImplicitParam(name="shopId",value="店铺id",required=true,paramType="query"),
            @ApiImplicitParam(name="goodsId",value="商品id",required=true,paramType="query"),
            @ApiImplicitParam(name="sgpId",value="商品参数id",required=true,paramType="query"),
            @ApiImplicitParam(name="count",value="商品数量",required=true,paramType="query")
    })
    public Body addShoppingCartGoods(@RequestParam("userId") long userId,@RequestParam("shopId") long shopId,
                                     @RequestParam("goodsId") long goodsId, @RequestParam("sgpId") long sgpId,
                                     @RequestParam("count") int count){
        int insertShoppingCartGoods = YdShoppingCartGoodsService.insertShoppingCartGoods(userId,shopId,goodsId,sgpId,count);
        if(insertShoppingCartGoods == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"加入失败");
        }
    }

    /**
     * 修改购物车中商品的数量
     * @param id 物车中商品id
     * @param count 商品数量变化 1 or -1
     * @return
     */
    @ApiOperation("修改购物车中商品的数量")
    @PostMapping("/updateShoppingCartGoodsOrderCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="物车中商品id",required=true,paramType="query"),
            @ApiImplicitParam(name="count",value="商品数量变化 1 or -1",required=true,paramType="query")
    })
    public Body updateShoppingCartGoodsOrderCount(@RequestParam("id") long id, @RequestParam("count") int count){
        YdShoppingCartGoods goods = new YdShoppingCartGoods();
        goods.setId(id);
        goods.setOrderCount(count);
        int updateShoppingCartGoods = YdShoppingCartGoodsService.updateShoppingCartGoodsOrderCount(goods);
        if(updateShoppingCartGoods == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    /**
     * 覆盖购物车中商品的数量
     * @param id 物车中商品id
     * @param count 商品数量
     * @return
     */
    @ApiOperation("覆盖购物车中商品的数量")
    @PostMapping("/updateShoppingCartGoodsCoverOrderCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="物车中商品id",required=true,paramType="query"),
            @ApiImplicitParam(name="count",value="商品数量",required=true,paramType="query")
    })
    public Body updateShoppingCartGoodsCoverOrderCount(@RequestParam("id") long id, @RequestParam("count") int count){
        YdShoppingCartGoods goods = new YdShoppingCartGoods();
        goods.setId(id);
        goods.setOrderCount(count);
        int updateShoppingCartGoods = YdShoppingCartGoodsService.updateShoppingCartGoodsCoverOrderCount(goods);
        if(updateShoppingCartGoods == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    /**
     * 删除购物车中商品
     * @param id 物车中商品id
     * @return
     */
    @ApiOperation("删除购物车中商品")
    @PostMapping("/deleteShoppingCartGoods")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="物车中商品id",required=true,paramType="query")
    })
    public Body deleteShoppingCartGoods(@RequestParam("id") long id){
        int deleteShoppingCartGoods = YdShoppingCartGoodsService.deleteShoppingCartGoods(id);
        if(deleteShoppingCartGoods == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    /**
     * 批量删除购物车中商品
     * @param ids
     * @return
     */
    @ApiOperation("批量删除购物车中商品")
    @PostMapping("/deleteSomeShoppingCartGoods")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="物车中商品id",required=true,paramType="query")
    })
    public Body deleteSomeShoppingCartGoods(@RequestParam("ids[]") List<Long> ids){
        int deleteShoppingCartGoods = YdShoppingCartGoodsService.deleteSomeShoppingCartGoods(ids);
        if(deleteShoppingCartGoods >0){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    /**
     * 修改商品参数
     * @param id
     * @param basketId
     * @param goodsId
     * @param sgpId
     * @param count
     * @return
     */
    @ApiOperation("修改商品参数")
    @PostMapping("/updateShoppingCartGoodsParameter")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="购物车中商品id",required=true,paramType="query"),
            @ApiImplicitParam(name="basketId",value="购物车id",required=true,paramType="query"),
            @ApiImplicitParam(name="goodsId",value="商品id",required=true,paramType="query"),
            @ApiImplicitParam(name="sgpId",value="新的商品参数id",required=true,paramType="query")
    })
    public Body updateShoppingCartGoodsParameter(@RequestParam("id") long id,@RequestParam("basketId") long basketId,
                                                 @RequestParam("goodsId") long goodsId,@RequestParam("sgpId") long sgpId,
                                                 @RequestParam("count") int count){
        YdShoppingCartGoods ydShoppingCartGoods = new YdShoppingCartGoods();
        ydShoppingCartGoods.setId(id);
        ydShoppingCartGoods.setBasketId(basketId);
        ydShoppingCartGoods.setGoodsId(goodsId);
        ydShoppingCartGoods.setSgpId(sgpId);
        ydShoppingCartGoods.setOrderCount(count);
        boolean updateShoppingCartGoodsParameter = YdShoppingCartGoodsService.updateShoppingCartGoodsParameter(ydShoppingCartGoods);
        if(updateShoppingCartGoodsParameter){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

}
