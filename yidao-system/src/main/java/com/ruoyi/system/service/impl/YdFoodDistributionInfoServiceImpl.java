package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.formatUtil.DistributionUtil;
import com.ruoyi.system.domain.YdFoodDistributionInfo;
import com.ruoyi.system.domain.YdSellerShop;
import com.ruoyi.system.mapper.YdFoodDistributionInfoMapper;
import com.ruoyi.system.mapper.YdSellerShopMapper;
import com.ruoyi.system.service.YdFoodDistributionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YdFoodDistributionInfoServiceImpl implements YdFoodDistributionInfoService {
    @Autowired(required = false)
    private YdFoodDistributionInfoMapper foodDistributionInfoMapper;
    @Autowired(required = false)
    private YdSellerShopMapper shopMapper;

    /**
     * 新增用户收货地址
     * @param record
     * @return
     */
    @Override
    public Body insert(YdFoodDistributionInfo record,Integer shopId) {
        //判断是否是默认地址
        if(record.getIsDefault() == 1){
            //更改原默认地址
            foodDistributionInfoMapper.updateDefault(record.getUserId());
        }
        foodDistributionInfoMapper.insert(record);
        //返回更新后的数据
        return List(record.getUserId(),shopId);
    }

    /**
     * 查询用户收货地址列表
     * @param userId
     * @return
     */
    @Override
    public Body selectDistributionList(Integer userId,Integer shopId) {
        return List(userId,shopId);
    }
    /**
     * 修改用户收货地址信息
     * @param record
     * @return
     */
    @Override
    public Body update(YdFoodDistributionInfo record,Integer shopId) {
        //判断是否是默认地址
        if(record.getIsDefault() == 1){
            //更改原默认地址
            foodDistributionInfoMapper.updateDefault(record.getUserId());
        }
        int answer = foodDistributionInfoMapper.update(record);
        if(answer == 0)return Body.newInstance(400,"用户id或地址id错误");
        //返回更新后的数据
        return List(record.getUserId(),shopId);
    }
    /**
     * 删除收货地址
     * @param id
     * @param userId
     * @return
     */
    @Override
    public Body delete(Integer id, Integer userId,Integer shopId) {
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        param.put("userId",userId);
        int answer = foodDistributionInfoMapper.delete(param);
        if(answer == 0)return Body.newInstance(400,"用户id或地址id错误");
        //返回更新后的数据
        return List(userId,shopId);
    }

    private Body List(Integer userId,Integer shopId){
        //查询店铺信息
        YdSellerShop shop = shopMapper.getById(shopId);
        if(shop == null)return Body.newInstance(400,"店铺信息有误");
        Map<String,Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("lng",shop.getLongitude());
        param.put("lat",shop.getLatitude());
        param.put("minDistance",DistributionUtil.MAX_DISTANCE);
        List<YdFoodDistributionInfo> distributionInfoList = foodDistributionInfoMapper.selectAllByDistance(param);
        if(distributionInfoList != null && !distributionInfoList.isEmpty()){
            return Body.newInstance(distributionInfoList);
        }
        return Body.newInstance(204,"无数据");
    }
}
