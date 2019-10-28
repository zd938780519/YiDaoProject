package com.ruoyi.common.utils.formatUtil;

/**
 * 订单状态常量
 */
public class FoodOrderUtil {
    /**订单状态:未付款*/
    public static final int UNPAID = 0;
    /**订单状态:未接单*/
    public static final int UNORDER = 1;
    /**订单状态:未配送*/
    public static final int UNDISTR = 2;
    /**订单状态:未送达*/
    public static final int DISTR = 3;
    /**订单状态:未评价*/
    public static final int UNEVAL = 4;
    /**订单状态:未退款*/
    public static final int UNREFUND = 5;
    /**订单状态：卖家未确认*/
    public static final int UNAFFIRM = 6;
    /**订单状态:已退款*/
    public static final int REFUND = 7;

    /**订单类型:私厨订单*/
    public static final int KITCHEN = 1;
    /**订单类型:商家订单*/
    public static final int SELLER = 2;
    /**订单类型:网红订单*/
    public static final int ONLINE = 3;

    /**是否外卖店订单：是1*/
    public static final int TAKE_OUT = 1;

    /**订单类型：卖出*/
    public static final int FOOD_ORDER = 0;
    /**订单类型：卖出描述*/
    public static final String FOOD_ORDER_TEXT = "卖出";
    /**订单类型：退款*/
    public static final int REFUND_ORDER = 1;
    /**订单类型：退款描述*/
    public static final String REFUND_ORDER_TEXT = "退款";
}
