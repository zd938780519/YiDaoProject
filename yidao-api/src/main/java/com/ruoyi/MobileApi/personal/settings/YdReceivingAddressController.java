package com.ruoyi.MobileApi.personal.settings;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdShopReceivingAddress;
import com.ruoyi.system.service.YdShopReceivingAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Api("收货地址相关api")
@RestController
@RequestMapping("/yd/app/receivingAddress")
public class YdReceivingAddressController {
    @Autowired
    private YdShopReceivingAddressService ydShopReceivingAddressService;

    @ApiOperation("新增收货地址")
    @PostMapping("/addReceivingAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="locationAddress",value="定位地址",required=true,paramType="query"),
            @ApiImplicitParam(name="longitude",value="经度",required=true,paramType="query"),
            @ApiImplicitParam(name="latitude",value="纬度",required=true,paramType="query"),
            @ApiImplicitParam(name="detailedAddress",value="详细地址",required=true,paramType="query"),
            @ApiImplicitParam(name="consignee",value="收货人",required=true,paramType="query"),
            @ApiImplicitParam(name="receivingTelephone",value="收货电话",required=true,paramType="query"),
            @ApiImplicitParam(name="sex",value="收货人性别",required=true,paramType="query"),
            @ApiImplicitParam(name="isDefault",value="是否是默认地址",required=true,paramType="query"),
            @ApiImplicitParam(name="labelId",value="标签id",required=true,paramType="query")
    })
    public Body addReceivingAddress(@RequestParam("userId") long userId, @RequestParam("locationAddress") String locationAddress,
                                    @RequestParam("longitude") BigDecimal longitude,@RequestParam("latitude") BigDecimal latitude,
                                    @RequestParam("detailedAddress") String detailedAddress,@RequestParam("consignee") String consignee,
                                    @RequestParam("receivingTelephone") String receivingTelephone,@RequestParam("sex") int sex,
                                    @RequestParam("isDefault") int isDefault,@RequestParam("labelId") int labelId){
        YdShopReceivingAddress ydShopReceivingAddress = new YdShopReceivingAddress(userId,(locationAddress+" "+detailedAddress),locationAddress,
                longitude,latitude,detailedAddress,consignee, receivingTelephone, sex, isDefault, labelId);
        int insert = ydShopReceivingAddressService.insert(ydShopReceivingAddress);
        if(insert == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"新增失败");
        }
    }

    @ApiOperation("查询收货地址")
    @PostMapping("/getReceivingAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query")
    })
    public Body getReceivingAddress(@RequestParam("userId") long userId){
        List<YdShopReceivingAddress> ydShopReceivingAddresses = ydShopReceivingAddressService.selectAllByUserId(userId);
        if(ydShopReceivingAddresses.size()>0){
            return Body.newInstance(ydShopReceivingAddresses);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("修改收货地址")
    @PostMapping("/updateReceivingAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name="raId",value="收货地址id",required=true,paramType="query"),
            @ApiImplicitParam(name="locationAddress",value="定位地址",required=true,paramType="query"),
            @ApiImplicitParam(name="longitude",value="经度",required=true,paramType="query"),
            @ApiImplicitParam(name="latitude",value="纬度",required=true,paramType="query"),
            @ApiImplicitParam(name="detailedAddress",value="详细地址",required=true,paramType="query"),
            @ApiImplicitParam(name="consignee",value="收货人",required=true,paramType="query"),
            @ApiImplicitParam(name="receivingTelephone",value="收货电话",required=true,paramType="query"),
            @ApiImplicitParam(name="sex",value="收货人性别",required=true,paramType="query"),
            @ApiImplicitParam(name="isDefault",value="是否是默认地址",required=true,paramType="query"),
            @ApiImplicitParam(name="labelId",value="标签id",required=true,paramType="query")
    })
    public Body updateReceivingAddress(@RequestParam("raId") long raId, @RequestParam("locationAddress") String locationAddress,
                                    @RequestParam("longitude") BigDecimal longitude,@RequestParam("latitude") BigDecimal latitude,
                                    @RequestParam("detailedAddress") String detailedAddress,@RequestParam("consignee") String consignee,
                                    @RequestParam("receivingTelephone") String receivingTelephone,@RequestParam("sex") int sex,
                                    @RequestParam("isDefault") int isDefault,@RequestParam("labelId") int labelId){
        YdShopReceivingAddress ydShopReceivingAddress = new YdShopReceivingAddress();
        ydShopReceivingAddress.setId(raId);
        ydShopReceivingAddress.setReceivingAddress(locationAddress+" "+detailedAddress);
        ydShopReceivingAddress.setLocationAddress(locationAddress);
        ydShopReceivingAddress.setLongitude(longitude);
        ydShopReceivingAddress.setLatitude(latitude);
        ydShopReceivingAddress.setDetailedAddress(detailedAddress);
        ydShopReceivingAddress.setConsignee(consignee);
        ydShopReceivingAddress.setReceivingTelephone(receivingTelephone);
        ydShopReceivingAddress.setSex(sex);
        ydShopReceivingAddress.setIsDefault(isDefault);
        ydShopReceivingAddress.setLabelId(labelId);
        int updateReceivingAddress = ydShopReceivingAddressService.updateReceivingAddress(ydShopReceivingAddress);
        if(updateReceivingAddress == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("删除收货地址")
    @PostMapping("/removeReceivingAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name="raId",value="收货地址id",required=true,paramType="query")
    })
    public Body removeReceivingAddress(@RequestParam("raId") long raId){
        int deleteReceivingAddress = ydShopReceivingAddressService.deleteReceivingAddress(raId);
        if(deleteReceivingAddress == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    /**
     * 批量删除收货地址
     * @param ids
     * @return
     */
    @ApiOperation("批量删除收货地址")
    @PostMapping("/deleteSomeShoppingCartGoods")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="收货地址id",required=true,paramType="query")
    })
    public Body deleteSomeShoppingCartGoods(@RequestParam("ids[]") List<Long> ids){
        int deleteSomeReceivingAddress = ydShopReceivingAddressService.deleteSomeReceivingAddress(ids);
        if(deleteSomeReceivingAddress >0){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    @ApiOperation("获取默认收货地址")
    @PostMapping("/getDefaultReceivingAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query")
    })
    public Body getDefaultReceivingAddress(@RequestParam("userId") long userId){
        YdShopReceivingAddress ydShopReceivingAddress = ydShopReceivingAddressService.selectDefaultByUserId(userId);
        if(ydShopReceivingAddress != null){
            return Body.newInstance(ydShopReceivingAddress);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

}
