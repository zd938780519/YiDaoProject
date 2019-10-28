package com.ruoyi.framework.config.listener;

import com.ruoyi.common.utils.ThreadPool;
import com.ruoyi.common.utils.YdParameterUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.system.domain.YdSellerOrderAutoEntity;
import com.ruoyi.system.domain.YdShopOrderAutoEntity;
import com.ruoyi.system.service.YdSellerOrderAutoCancelService;
import com.ruoyi.system.service.YdShopOrderAutoCancelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * 自动取消订单监听器
 */
@Service
public class YdOrderAutoCancelListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(YdOrderAutoCancelListener.class);
    @Autowired(required = false)
    private YdShopOrderAutoCancelService ydShopOrderAutoCancelService;
    @Autowired
    private YdSellerOrderAutoCancelService sellerOrderAutoCancelService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        log.info(">>>>>>>>>>>>系统启动完成，开始加载订单自动取消功能onApplicationEvent()<<<<<<<<<<<<<<<");
        //自动取消
        ydShopOrderAutoCancelService.start();
        sellerOrderAutoCancelService.start();
        //查找需要入队的订单
        ThreadPool.getThreadPool(10).execute(()->{
            log.info(">>>>>>>>>>>>查找需要入队的订单：商城<<<<<<<<<<<<<<<<<<<<");
            Set<String> hashAllKey = RedisUtils.getHashAllKey(YdParameterUtils.RedisKey.ORDER_PREFIX);
            if(CollectionUtils.isEmpty(hashAllKey)){
                log.info(">>>>>>>>>>没有查找到待取消订单：商城<<<<<<<<<<<<<<");
            }else{
                for (String key:hashAllKey) {
                    String hashKV = RedisUtils.getHashKV(YdParameterUtils.RedisKey.ORDER_PREFIX, key);
                    YdShopOrderAutoEntity order = new YdShopOrderAutoEntity(Long.parseLong(key),Long.parseLong(hashKV));
                    if(order == null){
                        break;
                    }
                    ydShopOrderAutoCancelService.add(order);
                }
                log.info(">>>>>>>>>>待取消订单入队完成：商城<<<<<<<<<<<<<<<<<<<<<<");
            }

            log.info(">>>>>>>>>>>>查找需要入队的订单：周边<<<<<<<<<<<<<<<<<<<<");
            Set<String> sellerHashAllKey = RedisUtils.getHashAllKey(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX);
            if(CollectionUtils.isEmpty(sellerHashAllKey)){
                log.info(">>>>>>>>>>没有查找到待取消订单：周边<<<<<<<<<<<<<<");
            }else{
                for (String key:sellerHashAllKey) {
                    String hashKV = RedisUtils.getHashKV(YdParameterUtils.RedisKey.SELLER_ORDER_PREFIX, key);
                    YdSellerOrderAutoEntity order = new YdSellerOrderAutoEntity(Long.parseLong(key),Long.parseLong(hashKV));
                    if(order == null){
                        break;
                    }
                    sellerOrderAutoCancelService.add(order);
                }
                log.info(">>>>>>>>>>待取消订单入队完成：周边<<<<<<<<<<<<<<<<<<<<<<");
            }
        });
    }
}
