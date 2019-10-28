package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品简单信息实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdShopOrderGoodsIsSimple implements Serializable {
    /** 商品id */
    private long goodsId;
    /** 店铺id */
    private long shopId;
    /** 店铺名称 */
    private String shopName;
    /** 店铺头像路径 */
    private String shopPhotoPath;
    /** 商品名称 */
    private String goodsName;
    /** 商品参数分类 */
    private String goodsParameterType;
    /** 商品参数 */
    private String goodsParameter;
    /** 商品图片路径 */
    private String goodsPhotoPath;
    /** 商品数量 */
    private int goodsCount;
    /** 商品价格 */
    private BigDecimal goodsMoney;
    /** 商品积分价 */
    private int goodsIntegral;
    /** 是否可用券 0:不可以；1:可以*/
    private int isAllowCoupon;
    /** 是否固定金额加固定积分 0:不是；1:是 */
    private int isFixedMoneyAndIntegral;
    /** 是否纯积分 0:不是；1:是 */
    private int isPureIntegral;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhotoPath() {
        return shopPhotoPath;
    }

    public void setShopPhotoPath(String shopPhotoPath) {
        this.shopPhotoPath = shopPhotoPath;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsParameterType() {
        return goodsParameterType;
    }

    public void setGoodsParameterType(String goodsParameterType) {
        this.goodsParameterType = goodsParameterType;
    }

    public String getGoodsParameter() {
        return goodsParameter;
    }

    public void setGoodsParameter(String goodsParameter) {
        this.goodsParameter = goodsParameter;
    }

    public String getGoodsPhotoPath() {
        return goodsPhotoPath;
    }

    public void setGoodsPhotoPath(String goodsPhotoPath) {
        this.goodsPhotoPath = goodsPhotoPath;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(BigDecimal goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public int getGoodsIntegral() {
        return goodsIntegral;
    }

    public void setGoodsIntegral(int goodsIntegral) {
        this.goodsIntegral = goodsIntegral;
    }

    public int getIsAllowCoupon() {
        return isAllowCoupon;
    }

    public void setIsAllowCoupon(int isAllowCoupon) {
        this.isAllowCoupon = isAllowCoupon;
    }

    public int getIsFixedMoneyAndIntegral() {
        return isFixedMoneyAndIntegral;
    }

    public void setIsFixedMoneyAndIntegral(int isFixedMoneyAndIntegral) {
        this.isFixedMoneyAndIntegral = isFixedMoneyAndIntegral;
    }

    public int getIsPureIntegral() {
        return isPureIntegral;
    }

    public void setIsPureIntegral(int isPureIntegral) {
        this.isPureIntegral = isPureIntegral;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "YdShopOrderGoodsIsSimple{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopPhotoPath='" + shopPhotoPath + '\'' +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsParameterType='" + goodsParameterType + '\'' +
                ", goodsParameter='" + goodsParameter + '\'' +
                ", goodsPhotoPath='" + goodsPhotoPath + '\'' +
                ", goodsCount=" + goodsCount +
                ", goodsMoney=" + goodsMoney +
                ", goodsIntegral=" + goodsIntegral +
                ", isAllowCoupon=" + isAllowCoupon +
                ", isFixedMoneyAndIntegral=" + isFixedMoneyAndIntegral +
                ", isPureIntegral=" + isPureIntegral +
                '}';
    }
}
