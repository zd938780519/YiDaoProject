package com.ruoyi.MobileApi.KanDianApi;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.service.YdKanDianService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("看点api")
@RestController
@RequestMapping("/yd/app/KanDian")
public class YdKanDianController {
    @Autowired
    private YdKanDianService ydKanDianService;

    @ApiOperation("排行展示（附近商家）")
    @RequestMapping(value = "/searchYdUser")
    public Body showPaiHang(@RequestParam("userId") long userId, @RequestParam("currIndex") int currIndex,
                            @RequestParam("pageSize") int pageSize) {
        return Body.newInstance(ydKanDianService.searchYdUser(userId, currIndex, pageSize));
    }

    @ApiOperation("特色展示（附近商家和网红）")
    @RequestMapping(value = "/showYdUser")
    public Body showTeSe(@RequestParam("userId") long userId, @RequestParam("currIndex") int currIndex,
                         @RequestParam("pageSize") int pageSize) {
        return Body.newInstance(ydKanDianService.showYdUser(userId, currIndex, pageSize));
    }

    @ApiOperation("看点搜索功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "搜索类型（1，2，3）", required = false, paramType = "form"),
            @ApiImplicitParam(name = "currIndex", value = "当前页", required = true, paramType = "form"),
            @ApiImplicitParam(name = "pageSize", value = "显示几条", required = true, paramType = "form"),
            @ApiImplicitParam(name = "searchWord", value = "搜索关键词", required = true, paramType = "form")
    })
    @RequestMapping(value = "/searchWord")
    public Body searchWord(@RequestParam("type") Integer type, @RequestParam("currIndex") int currIndex,
                           @RequestParam("pageSize") int pageSize, @RequestParam("searchWord") String searchWord) {
        if (type == null) {
            type = 1;//默认展示教学
        }
        return Body.newInstance(ydKanDianService.searchForAllThree(type, searchWord, currIndex, pageSize));
    }
}
