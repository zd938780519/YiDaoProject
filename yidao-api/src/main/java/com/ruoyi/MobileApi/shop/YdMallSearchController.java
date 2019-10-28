package com.ruoyi.MobileApi.shop;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdMallPopularSearch;
import com.ruoyi.system.domain.YdMallSearchRecord;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.service.YdMallSearchService;
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

@Api("商城搜索相关api")
@RestController
@RequestMapping("/yd/app/shop/mallSearch")
public class YdMallSearchController {
    @Autowired
    private YdMallSearchService ydMallSearchService;

    @ApiOperation("模糊查询商品-->根据总销量、月销量、浏览量排序")
    @PostMapping("/getGoodsByName")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="搜索内容",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getGoodsByName(@RequestParam("userId") long userId, @RequestParam("content") String content,
                               @RequestParam("currIndex") int currIndex, @RequestParam("pageSize")  int pageSize){
        List<YdShopGoods> ydShopGoods = ydMallSearchService.selectGoodsByName(userId, content, currIndex, pageSize);
        if(ydShopGoods.size()>0){
            return Body.newInstance(ydShopGoods);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("获取个人的搜索历史")
    @PostMapping("/getSearchRecordByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query")
    })
    public Body getSearchRecordByUserId(@RequestParam("userId") long userId){
        List<YdMallSearchRecord> ydMallSearchRecords = ydMallSearchService.selectSearchRecord(userId);
        if(ydMallSearchRecords.size()>0){
            return Body.newInstance(ydMallSearchRecords);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("删除个人的搜索历史")
    @PostMapping("/removeSearchRecordByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query")
    })
    public Body removeSearchRecordByUserId(@RequestParam("userId") long userId){
        int deleteSearchRecordByUserId = ydMallSearchService.deleteSearchRecordByUserId(userId);
        if(deleteSearchRecordByUserId>0){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    @ApiOperation("获取热门搜索")
    @PostMapping("/selectPopularSearch")
    public Body selectPopularSearch(){
        List<YdMallPopularSearch> ydMallPopularSearches = ydMallSearchService.selectPopularSearch();
        if(ydMallPopularSearches.size()>0){
            return Body.newInstance(ydMallPopularSearches);
        }else{
            return Body.newInstance(201,"获取失败");
        }
    }



}
