package com.ruoyi.system.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.json.CustomStringDeserializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单商品表实体
 */
@JsonDeserialize(using = CustomStringDeserializer.class)
public class YdShopOrderGoods implements Serializable {
    private Long id;

    private Long goodsId;

    private Long sgpId;

    private Long sbgId;

    private Long soId;

    private Long shopId;

    private BigDecimal goodsUnitPrice;

    private Integer goodsCount;

    private int integralLimit;

    private int integralPrice;

    private Integer giveIntegralPrice;

    private int isAllowCoupon;

    private int isFixedMoneyAndIntegral;

    private int isPureIntegral;

    private int integralPaymentPrice;
    /** 是否全积分付款 0：不是；1：是 */
    private int isIntegralPayment;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String other1;

    private String other2;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getGoodsUnitPrice() {
        return goodsUnitPrice;
    }

    public void setGoodsUnitPrice(BigDecimal goodsUnitPrice) {
        this.goodsUnitPrice = goodsUnitPrice;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getGiveIntegralPrice() {
        return giveIntegralPrice;
    }

    public void setGiveIntegralPrice(Integer giveIntegralPrice) {
        this.giveIntegralPrice = giveIntegralPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1 == null ? null : other1.trim();
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2 == null ? null : other2.trim();
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

    public Long getSgpId() {
        return sgpId;
    }

    public void setSgpId(Long sgpId) {
        this.sgpId = sgpId;
    }

    public Long getSbgId() {
        return sbgId;
    }

    public void setSbgId(Long sbgId) {
        this.sbgId = sbgId;
    }

    public int getIsIntegralPayment() {
        return isIntegralPayment;
    }

    public void setIsIntegralPayment(int isIntegralPayment) {
        this.isIntegralPayment = isIntegralPayment;
    }

    @Override
    public String toString() {
        return "YdShopOrderGoods{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", sgpId=" + sgpId +
                ", sbgId=" + sbgId +
                ", soId=" + soId +
                ", shopId=" + shopId +
                ", goodsUnitPrice=" + goodsUnitPrice +
                ", goodsCount=" + goodsCount +
                ", integralLimit=" + integralLimit +
                ", integralPrice=" + integralPrice +
                ", giveIntegralPrice=" + giveIntegralPrice +
                ", isAllowCoupon=" + isAllowCoupon +
                ", isFixedMoneyAndIntegral=" + isFixedMoneyAndIntegral +
                ", isPureIntegral=" + isPureIntegral +
                ", integralPaymentPrice=" + integralPaymentPrice +
                ", isIntegralPayment=" + isIntegralPayment +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}