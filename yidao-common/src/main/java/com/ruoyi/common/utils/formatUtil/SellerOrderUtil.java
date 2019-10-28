package com.ruoyi.common.utils.formatUtil;

/**
 * 到店吃订单常量
 */
public class SellerOrderUtil {
    /**订单状态:未付款*/
    public static final int UNPAID = 0;
    /**订单状态:未使用*/
    public static final int UNUSE = 1;
    /**订单状态:未评价*/
    public static final int UNEVAL = 2;
    /**订单状态:未退款*/
    public static final int UNREFUND = 3;
    /**订单状态：卖家未确认*/
    public static final int UNAFFIRM = 4;
    /**订单状态:已退款*/
    public static final int REFUND = 5;

    /**订单类型:私厨订单*/
    public static final int KITCHEN = 1;
    /**订单类型:商家订单*/
    public static final int SELLER = 2;
    /**订单类型:网红订单*/
    public static final int ONLINE = 3;

    /**是否外卖店订单：否0*/
    public static final int UNTAKE_OUT = 0;

    /**订单类型：卖出*/
    public static final int SELLER_ORDER = 0;
    /**订单类型：卖出描述*/
    public static final String SELLER_ORDER_TEXT = "卖出";
    /**订单类型：退款*/
    public static final int REFUND_ORDER = 1;
    /**订单类型：退款描述*/
    public static final String REFUND_ORDER_TEXT = "退款";
}
