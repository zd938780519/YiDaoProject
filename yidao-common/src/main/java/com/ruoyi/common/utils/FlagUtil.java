package com.ruoyi.common.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

/**
 * 生成订单编号工具
 */
public class FlagUtil {
    /** 商城订单，常量值：1*/
    public static final int MALL_ORDER = 1;
    /** 到店吃订单，常量值：2*/
    public static final int SELL_ORDER = 2;
    /** 外卖订单，常量值：3*/
    public static final int FOOD_ORDER = 3;
    /**
     * 生成订单编号：模块id + 年月日各取两位 + 当天秒数取五位 + flag字段标识码
     * @param title
     * @param tail
     * @return
     */
    public static Long createOrderNum(int title,int tail){
        Calendar time = Calendar.getInstance();
        //获取年
        String year = String.valueOf(time.get(Calendar.YEAR)).substring(2);
        //获取月
        String month = String.format("%02d",time.get(Calendar.MONTH)+1);
        //获取日
        String day = String.format("%02d",time.get(Calendar.DATE));
        //获取当天秒数
        String duration = String.format("%05d",(int)Duration.between(LocalDateTime.of(LocalDate.now(),LocalTime.MIN),LocalDateTime.now()).toMillis()/1000);

        Long orderNum = Long.parseLong(title + year + month + day + duration + String.format("%04d",tail));
        //System.out.println(orderNum);
        return orderNum;
    }
}
