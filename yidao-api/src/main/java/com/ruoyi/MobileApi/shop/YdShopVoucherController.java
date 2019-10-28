package com.ruoyi.MobileApi.shop;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdMallVoucher;
import com.ruoyi.system.domain.YdMallVoucherExchange;
import com.ruoyi.system.service.YdMallVoucherExchangeService;
import com.ruoyi.system.service.YdMallVoucherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api("优惠券相关api")
@RestController
@RequestMapping("/yd/app/shop/voucher")
public class YdShopVoucherController {
    @Autowired
    private YdMallVoucherService ydMallVoucherService;
    @Autowired
    private YdMallVoucherExchangeService ydMallVoucherExchangeService;

    @ApiOperation("获取所有优惠券数据")
    @PostMapping("/getShopVouchers")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getShopVouchers(@RequestParam("currIndex") int currIndex,@RequestParam("pageSize") int pageSize) {
        List<YdMallVoucher> ydMallVouchers = ydMallVoucherService.selectAll(currIndex,pageSize);
        if(ydMallVouchers != null && ydMallVouchers.size()>0){
            return Body.newInstance(ydMallVouchers);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("获取优惠券详细信息")
    @PostMapping("/getShopVoucherById")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="优惠券id",required=true,paramType="query")
    })
    public Body getShopVoucherById(@RequestParam("id") long id) {
        YdMallVoucher ydMallVoucher = ydMallVoucherService.selectById(id);
        if(ydMallVoucher != null){
            return Body.newInstance(ydMallVoucher);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("兑换优惠券")
    @PostMapping("/exchangeVoucher")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="优惠券id",required=true,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query")
    })
    public Body exchangeVoucher(@RequestParam("id") long id,@RequestParam("userId") long userId) {
        YdMallVoucherExchange ydMallVoucherExchange = new YdMallVoucherExchange();
        ydMallVoucherExchange.setMvId(id);
        ydMallVoucherExchange.setUserId(userId);
        //过期时间处理，使用数据库定时，精确到天
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());//设置起时间
        cal.add(Calendar.YEAR, 1);//增加一年
        ydMallVoucherExchange.setBeOverdueTime(cal.getTime());
        int insert = ydMallVoucherExchangeService.insert(ydMallVoucherExchange);
        if(insert == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"兑换失败");
        }
    }

    @ApiOperation("根据用户id获取不同状态的优惠券列表")
    @PostMapping("/getVoucherList")
    @ApiImplicitParams({
            @ApiImplicitParam(name="type",value="优惠券状态 0：未使用；1:已使用；2：已过期",required=true,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getVoucherList(@RequestParam("type") int type,@RequestParam("userId") long userId,
                               @RequestParam("currIndex") int currIndex,@RequestParam("pageSize") int pageSize) {
        List<YdMallVoucherExchange> ydMallVoucherExchanges = ydMallVoucherExchangeService.selectAllByUser(userId, currIndex, pageSize, type);
        if(ydMallVoucherExchanges != null && ydMallVoucherExchanges.size()>0){
            return Body.newInstance(ydMallVoucherExchanges);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }



}
