package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.ThreadPool;
import com.ruoyi.common.utils.YdParameterUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.YdShopOrderAutoEntity;
import com.ruoyi.system.domain.YdShopOrders;
import com.ruoyi.system.mapper.YdShopOrdersMapper;
import com.ruoyi.system.service.YdShopOrderAutoCancelService;
import com.ruoyi.system.service.YdShopOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.DelayQueue;

/**
 * @description: 订单未支付自动取消
 **/
@Service
public class YdShopOrderAutoCancelServiceImpl implements YdShopOrderAutoCancelService {
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired(required = false)
    private YdShopOrdersMapper ydShopOrdersMapper;
    @Autowired
    private YdShopOrdersService ydShopOrdersService;

    //用于放入需要自动取消的订单
    private final static DelayQueue<YdShopOrderAutoEntity> delayQueue = new DelayQueue<>();

    private boolean start;

    /**
     * 取消订单
     */
    @Override
    public void start(){
        if(start){
            log.debug("YdShopOrderAutoCancelServiceImpl 已启动");
            return;
        }
        start = true;
        log.debug("YdShopOrderAutoCancelServiceImpl 启动成功");
        new Thread(()->{
            try {
                while(true){
                    YdShopOrderAutoEntity order = delayQueue.take();
                    ThreadPool.getThreadPool(10).execute(()->{
                        cancelOrder(order);
                    });
                }
            } catch (InterruptedException e) {
                log.error("InterruptedException error:",e);
            }
        }).start();
    }

    private void cancelOrder(YdShopOrderAutoEntity order){
        long orderId = order.getOrderId();
        //String lockKey = String.format("%s%s", YdParameterUtils.RedisKey.ORDER_PREFIX,orderId);
        try {
            //if(redisLockManager.tryLock(lockKey)){//不加锁
                String hashKV = RedisUtils.getHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, orderId + "");

                if(hashKV == null){
                    log.info(">>>>>>>>>>>>redis中不存在该订单，订单：{}已经被取消，不处理<<<<<<<<<<<<",orderId);
                    return;
                }
                deleteOrder(orderId);
                //删除redis数据
                log.info("取消订单。。。。开始删除redis数据 order：{}",orderId);
                RedisUtils.delHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX,orderId+"");
                log.info("取消订单。。。。删除redis数据成功 order：{}",orderId);
           // }
        } catch (Exception e) {
            log.error(">>>>>>>>>订单：{}取消发生异常，",e);
        } finally {
          //  redisLockManager.unlock(lockKey);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(long orderId){
        YdShopOrders ydShopOrders = ydShopOrdersMapper.selectShopOrderById(orderId);
        if(ydShopOrders == null){
            log.info(">>>>>>>>>>>>订单：{}不存在<<<<<<<<<<<<",orderId);
            return;
        }
        log.info("自动取消订单 ------ 开始取消：order={}",orderId);
        ydShopOrdersService.removeOrders(orderId,-1,"超时自动取消");
        log.info("自动取消订单 ------ 订单取消成功：order={}",orderId);
    }

    /**
     * 添加待取消订单
     * @param entity
     */
    @Override
    public void add(YdShopOrderAutoEntity entity){
        delayQueue.put(entity);
        RedisUtils.setHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, entity.getOrderId()+"",entity.getTime()+"");
    }

    /**
     * 添加待取消订单
     * @param orderId 订单号
     * @param timeout 过期时间
     */
    @Override
    public void add(long orderId,long timeout){
        YdShopOrderAutoEntity entity = new YdShopOrderAutoEntity(orderId, timeout);
        delayQueue.put(entity);
        RedisUtils.setHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, orderId+"",timeout+"");
    }

    /**
     * 移除
     * @param entity
     */
    @Override
    public void remove(YdShopOrderAutoEntity entity){
        delayQueue.remove(entity);
        RedisUtils.delHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, entity.getOrderId()+"");
    }

    /**
     * 移除
     * @param orderId 订单号
     * @param timeout
     */
    @Override
    public void remove(long orderId,long timeout){
        delayQueue.remove(new YdShopOrderAutoEntity(orderId,timeout));
        RedisUtils.delHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, orderId+"");
    }

    /**
     * 移除
     * @param orderId 订单号
     */
    @Override
    public void remove(long orderId){
        String hashKV = RedisUtils.getHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, orderId + "");
        if(hashKV != null){
            delayQueue.remove(new YdShopOrderAutoEntity(orderId,Long.parseLong(RedisUtils.getHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, orderId + ""))));
            RedisUtils.delHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, orderId+"");
        }
    }
}