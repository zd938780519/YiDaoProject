package com.ruoyi.MobileApi.shop;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdMallPoster;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.service.YdMallPosterService;
import com.ruoyi.system.service.YdShopGoodsService;
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

@Api("商城主页相关api")
@RestController
@RequestMapping("/yd/app/shop/mallHomepage")
public class YdMallHomepageController {
    @Autowired
    private YdMallPosterService ydMallPosterService;
    @Autowired
    private YdShopGoodsService ydShopGoodsService;

    @ApiOperation("获取商城顶部广告")
    @PostMapping("/getPoster")
    public Body getPoster(){
        List<YdMallPoster> ydMallPosters = ydMallPosterService.selectAll();
        if(ydMallPosters.size()>0){
            return Body.newInstance(ydMallPosters);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("获取首页商品-->根据总销量、月销量、浏览量排序")
    @PostMapping("/getGoods")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getGoods(@RequestParam("currIndex") int currIndex,@RequestParam("pageSize")  int pageSize){
        List<YdShopGoods> ydShopGoods = ydShopGoodsService.selectGoods(currIndex, pageSize);
        if(ydShopGoods.size()>0){
            return Body.newInstance(ydShopGoods);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("获取单个商品详情")
    @PostMapping("/getGoodsDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsId",value="商品id",required=true,paramType="query")
    })
    public Body getGoodsDetails(@RequestParam("goodsId")  long goodsId){
        HashMap<String, Object> stringObjectHashMap = ydShopGoodsService.selectGoodsById(goodsId);
        if(stringObjectHashMap.size()>0){
            return Body.newInstance(stringObjectHashMap);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("获取商品参数详情")
    @PostMapping("/getGoodsParameters")
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsId",value="商品id",required=true,paramType="query")
    })
    public Body getGoodsParameters(@RequestParam("goodsId")  long goodsId){
        YdShopGoods ydShopGoods = ydShopGoodsService.selectGoodsParameters(goodsId);
        if(ydShopGoods != null){
            return Body.newInstance(ydShopGoods);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }


}
