package com.ruoyi.common.utils;

import java.math.BigDecimal;

/**
 * 高精度计算工具类，适用于商业计算，避免由于浮点数近似值产生的误差
 */
public class ArithUtil {
    private static final int DEF_DIV_SCALE=10;

    private ArithUtil(){}

    /**
     * 加
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 减
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1,double d2){
        return div(d1,d2,DEF_DIV_SCALE);
    }

    /**
     * 除
     * @param d1
     * @param d2
     * @param scale 精度
     * @return
     */
    public static double div(double d1,double d2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("精度不能小于零");
        }
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
