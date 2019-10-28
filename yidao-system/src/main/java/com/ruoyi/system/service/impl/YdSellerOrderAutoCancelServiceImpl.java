package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.ThreadPool;
import com.ruoyi.common.utils.YdParameterUtils;
import com.ruoyi.common.utils.formatUtil.FoodOrderUtil;
import com.ruoyi.common.utils.formatUtil.SellerOrderUtil;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.YdFoodOrder;
import com.ruoyi.system.domain.YdSellerOrder;
import com.ruoyi.system.domain.YdSellerOrderAutoEntity;
import com.ruoyi.system.mapper.YdFoodOrderMapper;
import com.ruoyi.system.mapper.YdSellerOrderMapper;
import com.ruoyi.system.service.YdFoodOrderService;
import com.ruoyi.system.service.YdSellerOrderAutoCancelService;
import com.ruoyi.system.service.YdSellerOrderRefundService;
import com.ruoyi.system.service.YdSellerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.DelayQueue;

/**
 * @description: 周边订单未支付自动取消
 **/
@Service
public class YdSellerOrderAutoCancelServiceImpl implements YdSellerOrderAutoCancelService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired(required = false)
    private YdSellerOrderMapper sellerOrderMapper;
    @Autowired
    private YdSellerOrderService sellerOrderService;
    @Autowired(required = false)
    private YdFoodOrderMapper foodOrderMapper;
    @Autowired
    private YdFoodOrderService foodOrderService;
    @Autowired
    private YdSellerOrderRefundService sellerOrderRefundService;

    //用于放入需要自动取消的订单
    private final static DelayQueue<YdSellerOrderAutoEntity> delayQueue = new DelayQueue<>();

    private boolean start;

    /**
     * 取消订单
     */
    @Override
    public void start(){
        log.debug("YdSellerOrderAutoCancelServiceImpl 启动成功");
        if(start)return;
        start = true;
        new Thread(()->{
            try {
                while(true){
                    YdSellerOrderAutoEntity order = delayQueue.take();
                    ThreadPool.getThreadPool(10).execute(()->{
                        cancelOrder(order);
                    });
                }
            } catch (InterruptedException e) {
                log.error("InterruptedException error:",e);
            }
        }).start();
    }

    private void cancelOrder(YdSellerOrderAutoEntity order){
        long orderNum = order.getOrderNum();
        //String lockKey = String.format("%s%s", YdParameterUtils.RedisKey.ORDER_PREFIX,orderId);
        try {
            //if(redisLockManager.tryLock(lockKey)){//不加锁
                String hashKV = RedisUtils.getHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, orderNum + "");

                if(hashKV == null){
                    log.info(">>>>>>>>>>>>redis中不存在该订单，订单：{}已经被取消，不处理<<<<<<<<<<<<",orderNum);
                    return;
                }
                deleteOrder(orderNum);
                //删除redis数据
                log.info("取消订单。。。。开始删除redis数据 order：{}",orderNum);
                RedisUtils.delHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX,orderNum+"");
                log.info("取消订单。。。。删除redis数据成功 order：{}",orderNum);
           // }
        } catch (Exception e) {
            log.error(">>>>>>>>>订单：{}取消发生异常，",e);
        } finally {
          //  redisLockManager.unlock(lockKey);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(long orderNum){
        YdSellerOrder sellerOrder = sellerOrderMapper.selectByOrderNum(orderNum);
        YdFoodOrder foodOrder = foodOrderMapper.selectByOrderNum(orderNum);
        if(sellerOrder == null && foodOrder == null){
            log.info(">>>>>>>>>>>>订单：{}不存在<<<<<<<<<<<<",orderNum);
            return;
        }
        if(sellerOrder != null && sellerOrder.getStatus() == SellerOrderUtil.UNPAID){
            log.info("自动取消订单 ------ 开始取消到店吃订单：order={}",orderNum);
            sellerOrderService.cancelOrder(orderNum,"支付超时，自动取消订单");
            log.info("自动取消订单 ------ 取消到店吃订单成功：order={}",orderNum);
        }else if(sellerOrder != null && sellerOrder.getStatus() == SellerOrderUtil.UNUSE){
            log.info("到店吃订单自动退款 ------ 超时未使用：开始处理：order={}",orderNum);
            sellerOrderRefundService.refundOrder(orderNum,"超时未使用，自动退款");
            log.info("到店吃订单自动退款 ------ 超时未使用：执行成功：order={}",orderNum);
        }else if(foodOrder != null && foodOrder.getStatus() == FoodOrderUtil.UNPAID){
            log.info("自动取消订单 ------ 开始取消外卖订单：order={}",orderNum);
            foodOrderService.cancelOrder(orderNum,"支付超时，自动取消订单");
            log.info("自动取消订单 ------ 取消外卖订单成功：order={}",orderNum);
        }else if(foodOrder != null && foodOrder.getStatus() == FoodOrderUtil.UNORDER){
            //退款
            log.info("外卖订单自动退款 ------ 商家未接单：开始处理：order={}",orderNum);
            log.info("外卖订单自动退款 ------ 商家未接单：执行成功：order={}",orderNum);
        }else{
            log.error("自动取消订单 ------ 异常订单：order={}",orderNum);
        }
    }

    /**
     * 添加待取消订单
     * @param entity
     */
    @Override
    public void add(YdSellerOrderAutoEntity entity){
        delayQueue.put(entity);
        RedisUtils.setHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, entity.getOrderNum()+"",entity.getTime()+"");
    }

    /**
     * 添加待取消订单
     * @param orderNum 订单号
     * @param timeout 过期时间
     */
    @Override
    public void add(long orderNum,long timeout){
        YdSellerOrderAutoEntity entity = new YdSellerOrderAutoEntity(orderNum, timeout);
        delayQueue.put(entity);
        RedisUtils.setHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, orderNum+"",timeout+"");
    }

    /**
     * 移除
     * @param entity
     */
    @Override
    public void remove(YdSellerOrderAutoEntity entity){
        delayQueue.remove(entity);
        RedisUtils.delHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, entity.getOrderNum()+"");
    }

    /**
     * 移除
     * @param orderNum 订单号
     * @param timeout
     */
    @Override
    public void remove(long orderNum,long timeout){
        delayQueue.remove(new YdSellerOrderAutoEntity(orderNum,timeout));
        RedisUtils.delHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, orderNum+"");
    }

    /**
     * 移除
     * @param orderNum 订单号
     */
    @Override
    public void remove(long orderNum){
        String hashKV = RedisUtils.getHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, orderNum + "");
        if(hashKV != null){
            delayQueue.remove(new YdSellerOrderAutoEntity(orderNum,Long.parseLong(RedisUtils.getHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, orderNum + ""))));
            RedisUtils.delHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, orderNum+"");
        }
    }
}