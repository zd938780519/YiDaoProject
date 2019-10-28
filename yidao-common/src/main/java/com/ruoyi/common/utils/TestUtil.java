package com.ruoyi.common.utils;

import com.ruoyi.common.json.JSONObject;

import java.math.BigDecimal;

public class TestUtil {
//    public static void main(String[] args) {
//        BigDecimal price1  = new BigDecimal("100");
//        BigDecimal price2  = new BigDecimal("200");
//        BigDecimal price3  = new BigDecimal("300");
//
//        BigDecimal inte1  = new BigDecimal("1");
//        BigDecimal inte2  = new BigDecimal("1");
//        BigDecimal inte3  = new BigDecimal("300");
//
//        BigDecimal discount1 = inte1.divide(price1);
//        BigDecimal discount2 = inte2.divide(price2);//折扣比例
//        BigDecimal discount3 = inte3.divide(price3);//折扣比例
//
//        System.out.println(discount1.divide(new BigDecimal("1.015")).setScale(2,BigDecimal.ROUND_HALF_UP));
//
//        System.out.println("折扣比例1："+discount1+"\t折扣比例2："+discount2+"\t折扣比例3："+discount3);
//    }


 /*   public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","红油顺风");
        jsonObject.put("price","30");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","啤酒（一杯）");
        jsonObject1.put("price","10");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name","瓜子（一包）");
        jsonObject2.put("price","5");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name","柠檬（半只）");
        jsonObject3.put("price","10");
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("name","酱料（一份）");
        jsonObject4.put("price","5");

        JSONObject.JSONArray jsonArray = new JSONObject.JSONArray();
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject1);
//        jsonArray.add(jsonObject2);
//        jsonArray.add(jsonObject3);
//        jsonArray.add(jsonObject4);

        String a = jsonArray.toString().replaceAll("(\\\r\\\n|\\\r|\\\n|\\\n\\\r|\\s*)", "");

        System.out.println(a);
    }*/
}
