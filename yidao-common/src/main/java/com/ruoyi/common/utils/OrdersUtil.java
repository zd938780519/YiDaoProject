package com.ruoyi.common.utils;

import java.util.Calendar;

/**
 *
 * 订单相关工具
 */
public class OrdersUtil {
    public static int NUM = 0;
    public static int oldYear=0;
    public static int oldMonth=0;
    public static int oldDay=0;

    public static String getOrderFormNum(){
        String orderFormNum="";
        Calendar cal=Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DATE);
        if(oldYear != year || oldMonth != month || oldDay != day){
            oldYear = year;
            oldMonth = month;
            oldDay = day;
            NUM = 0;
        }
        orderFormNum=oldYear+addZero(oldMonth,2)+addZero(oldDay,2)+addZero(NUM++,7);
        return orderFormNum;
    }


    public static String addZero(int item, int len){
        String itemS=item+"";
        if(itemS.length()<len){
            for (int i=0;i<len-itemS.length();i++){
                itemS="0"+itemS;
            }
        }
        return itemS;
    }


}
