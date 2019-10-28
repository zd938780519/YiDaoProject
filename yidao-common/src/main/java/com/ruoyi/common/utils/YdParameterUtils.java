package com.ruoyi.common.utils;

/**
 * 公共变量
 */
public class YdParameterUtils {
    /**
     * 积分-->人民币汇率
     */
    public static int EXCHANGE_RATE = 100;

    public static class RedisKey{
        /**
         * 商城订单前缀
         */
        public static final String ORDER_PREFIX = "order";

        /**
         * 周边订单前缀
         */
        public static final String SELLER_ORDER_PREFIX = "seller_order";

    }

    /** 订单支付过期时间 */
    //public static long ORDER_OVERDUE_TIME=15*60*1000;
    public static long ORDER_OVERDUE_TIME=100*1000;
    /**订单使用超时时间*/
    //public static long ORDER_USE_TIME_OUT = 24 * 60 * 60 * 1000;
    public static long ORDER_USE_TIME_OUT = 30 * 1000;
}
