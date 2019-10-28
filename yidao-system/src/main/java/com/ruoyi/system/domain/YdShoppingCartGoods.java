package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.json.CustomStringDeserializer;


import java.util.Date;

/**
 *购物车商品实体
 */
@JsonDeserialize(using = CustomStringDeserializer.class)
public class YdShoppingCartGoods extends BaseEntity {
    private long id          ;
    /**  购物车id  */
    private long basketId   ;
    /**  店铺id  */
    private long shopId     ;
    /**  商品id  */
    private long goodsId    ;
    /**  商品参数id */
    private long sgpId;
    /**  订购时间  */
    private Date orderTime  ;
    /**  订购数量  */
    private int orderCount ;
    /**  创建时间  */
    private Date createTime	        ;
    /**  最后一次修改时间  */
    private	Date updateTime	        ;
    /**  状态 -1：逻辑删除  */
    private	int status	              ;
    /**  备用字段1  */
    private	String other1	            ;
    /**  备用字段2  */
    private	String other2	            ;
    /** 店铺名称 */
    private String sname;
    /** 商品名称 */
    private String gname;
    /**   原价 */
    private double originalPrice  ;
    /**   折扣 */
    private double discount        ;
    /**   销售价格 */
    private double salePrice      ;
    /**  积分限额  */
    private int integralLimit  ;
    /** 积分价 */
    private int integralPrice;
    /** 是否可用券 0:不可以；1:可以  */
    private int isAllowCoupon;
    /**  是否固定金额加固定积分 0:不是；1:是 */
    private int isFixedMoneyAndIntegral;
    /**  是否纯积分 0:不是；1:是 */
    private int isPureIntegral;
    /**  展示图片 */
    private String photoPath;
    /** 参数类型 */
    private String parameterType;
    /**   参数 */
    private String parameter       ;
    /**  库存 */
    private int storedCount;
    /** 是否包邮 */
    private int isFreeShipping;
    /** 首件邮费 */
    private double firstPostage;
    /** 加件邮费 */
    private double additionalPostage;

    /** 全积分付款金额  */
    private int integralPaymentPrice;
    /** 返积分金额 */
    private int giveIntegralPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getSgpId() {
        return sgpId;
    }

    public void setSgpId(long sgpId) {
        this.sgpId = sgpId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getOrderTime() {
        if(orderTime == null){
            return new Date();
        }else{
            return orderTime;
        }
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        if(createTime == null){
            return new Date();
        }else{
            return createTime;
        }
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        if(updateTime == null){
            return new Date();
        }else{
            return updateTime;
        }
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getIntegralLimit() {
        return integralLimit;
    }

    public void setIntegralLimit(int integralLimit) {
        this.integralLimit = integralLimit;
    }

    public int getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(int integralPrice) {
        this.integralPrice = integralPrice;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
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

    public int getIntegralPaymentPrice() {
        return integralPaymentPrice;
    }

    public void setIntegralPaymentPrice(int integralPaymentPrice) {
        this.integralPaymentPrice = integralPaymentPrice;
    }

    public int getGiveIntegralPrice() {
        return giveIntegralPrice;
    }

    public void setGiveIntegralPrice(int giveIntegralPrice) {
        this.giveIntegralPrice = giveIntegralPrice;
    }

    public int getStoredCount() {
        return storedCount;
    }

    public void setStoredCount(int storedCount) {
        this.storedCount = storedCount;
    }

    public int getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(int isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public double getFirstPostage() {
        return firstPostage;
    }

    public void setFirstPostage(double firstPostage) {
        this.firstPostage = firstPostage;
    }

    public double getAdditionalPostage() {
        return additionalPostage;
    }

    public void setAdditionalPostage(double additionalPostage) {
        this.additionalPostage = additionalPostage;
    }

    @Override
    public String toString() {
        return "YdShoppingCartGoods{" +
                "id=" + id +
                ", basketId=" + basketId +
                ", shopId=" + shopId +
                ", goodsId=" + goodsId +
                ", sgpId=" + sgpId +
                ", orderTime=" + orderTime +
                ", orderCount=" + orderCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", sname='" + sname + '\'' +
                ", gname='" + gname + '\'' +
                ", originalPrice=" + originalPrice +
                ", discount=" + discount +
                ", salePrice=" + salePrice +
                ", integralLimit=" + integralLimit +
                ", integralPrice=" + integralPrice +
                ", isAllowCoupon=" + isAllowCoupon +
                ", isFixedMoneyAndIntegral=" + isFixedMoneyAndIntegral +
                ", isPureIntegral=" + isPureIntegral +
                ", photoPath='" + photoPath + '\'' +
                ", parameterType='" + parameterType + '\'' +
                ", parameter='" + parameter + '\'' +
                ", storedCount=" + storedCount +
                ", isFreeShipping=" + isFreeShipping +
                ", firstPostage=" + firstPostage +
                ", additionalPostage=" + additionalPostage +
                ", integralPaymentPrice=" + integralPaymentPrice +
                ", giveIntegralPrice=" + giveIntegralPrice +
                '}';
    }
}
