package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.formatUtil.FoodOrderUtil;
import com.ruoyi.system.domain.YdFoodDeliver;
import com.ruoyi.system.domain.YdFoodOrder;
import com.ruoyi.system.domain.YdFoodOrderDetail;
import com.ruoyi.system.mapper.YdFoodDeliverMapper;
import com.ruoyi.system.mapper.YdFoodOrderDetailMapper;
import com.ruoyi.system.mapper.YdFoodOrderMapper;
import com.ruoyi.system.service.YdFoodDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YdFoodDeliverServiceImpl implements YdFoodDeliverService {
    @Autowired(required = false)
    private YdFoodDeliverMapper foodDeliverMapper;
    @Autowired(required = false)
    private YdFoodOrderMapper foodOrderMapper;
    @Autowired(required = false)
    private YdFoodOrderDetailMapper foodOrderDetailMapper;

    /**
     * 骑手开始配送
     * @param shopId
     * @param orderId
     * @param orderNum
     * @return
     */
    @Override
    public Body sendOrder(Integer shopId, Integer orderId,Integer deliverId, Long orderNum) {
        //验证订单是否存在
        YdFoodOrder foodOrder = new YdFoodOrder();
        foodOrder.setId(orderId);
        foodOrder.setSellerId(shopId);
        foodOrder.setOrderNum(orderNum);
        foodOrder = foodOrderMapper.selectById(foodOrder);
        if(foodOrder == null)return Body.newInstance(400,"检测到异常数据：订单不存在或已被删除");
        if(foodOrder.getStatus() != FoodOrderUtil.UNDISTR)return Body.newInstance(400,"检测到异常数据：订单状态异常");
        //验证骑手是否存在
        YdFoodDeliver foodDeliver = foodDeliverMapper.selectById(deliverId);
        if(foodDeliver == null)return Body.newInstance(400,"检测到异常数据：配送员不存在或已被删除");
        //订单详情表添加骑手信息
        YdFoodOrderDetail updateDetail = new YdFoodOrderDetail();
        updateDetail.setFoId(orderId);
        updateDetail.setDeliverId(deliverId);
        updateDetail.setDeliverName(foodDeliver.getName());
        updateDetail.setDeliverMobile(foodDeliver.getDeliverMobile());
        foodOrderDetailMapper.updateOfDeliver(updateDetail);
        //订单主表修改状态为开始配送
        YdFoodOrder updateFoodOrder = new YdFoodOrder();
        updateFoodOrder.setId(orderId);
        updateFoodOrder.setStatus(FoodOrderUtil.DISTR);
        foodOrderMapper.updateStatus(updateFoodOrder);
        return Body.BODY_200;
    }
    /**
     * 订单已送达
     * @param shopId
     * @param orderId
     * @param deliverId
     * @param orderNum
     * @return
     */
    @Override
    public Body sendOver(Integer shopId, Integer orderId,Integer deliverId, Long orderNum) {
        //验证订单是否存在
        YdFoodOrder foodOrder = new YdFoodOrder();
        foodOrder.setId(orderId);
        foodOrder.setSellerId(shopId);
        foodOrder.setOrderNum(orderNum);
        foodOrder = foodOrderMapper.selectById(foodOrder);
        if(foodOrder == null)return Body.newInstance(400,"检测到异常数据：订单不存在或已被删除");
        if(foodOrder.getStatus() != FoodOrderUtil.DISTR)return Body.newInstance(400,"检测到异常数据：订单状态异常");
        //验证骑手是否存在
        YdFoodDeliver foodDeliver = foodDeliverMapper.selectById(deliverId);
        if(foodDeliver == null)return Body.newInstance(400,"检测到异常数据：配送员不存在或已被删除");
        //TODO 验证送达时间，验证经纬度

        //订单主表修改状态为已送达未评价
        YdFoodOrder updateFoodOrder = new YdFoodOrder();
        updateFoodOrder.setId(orderId);
        updateFoodOrder.setStatus(FoodOrderUtil.UNEVAL);
        foodOrderMapper.updateStatus(updateFoodOrder);
        return Body.BODY_200;
    }
}
