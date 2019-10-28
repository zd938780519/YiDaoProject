package com.ruoyi.system.scheduler;

import com.ruoyi.system.mapper.YdSellerDiscountDrawMapper;
import com.ruoyi.system.mapper.YdSellerDiscountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定时任务
 */
@Component
public class SchedulerTask {
    private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);
    @Autowired(required = false)
    private YdSellerDiscountMapper discountMapper;
    @Autowired(required = false)
    private YdSellerDiscountDrawMapper discountDrawMapper;
    /**
     * 清理过期优惠券
     */
    @Scheduled(cron = "0 58 16 ? * *")
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public void retrievalDiscount(){
        log.debug("定时任务：开始检索过期优惠券");
        //查询过期优惠券
        List<Integer> ids = discountMapper.getPastDue();
        if(ids != null && !ids.isEmpty()){
            //修改优惠券表过期优惠券状态为已过期
            int num = discountMapper.updateToPastDue(ids);
            //修改优惠券领取表未使用的过期优惠券状态为已过期
            int drawNum = discountDrawMapper.updateOfUnuserToPastDue(ids);
            log.debug("定时任务：更新了{}条优惠券信息和{}条优惠券领取信息",num,drawNum);
        }else {
            log.debug("定时任务：未检索到过期优惠券");
        }
    }

    /**
     * 套餐超过使用时间，通知商家下架
     */
    @Scheduled(cron = "0 00 00 ? * *")
    public void comboPastDue(){
        log.info("定时任务：开始检索过期套餐");
        //TODO 通知商家过期的套餐
    }
}
