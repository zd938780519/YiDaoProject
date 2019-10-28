package com.ruoyi.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class YdSellerFlowAccount implements Serializable {
    private Integer id;
    /**
     *卖家表外键
     */
    private Integer sellerId;
    /**
     *买家外键
     */
    private Integer userId;
    /**
     * 是否外卖店 0：否 1：是
     */
    private Integer isTakeOut;
    /**
     *类型 0：卖出 1：退款
     */
    private Integer type;
    /**
     *类型描述
     */
    private String typeShow;
    /**
     *金额
     */
    private BigDecimal money;
    /**
     *积分
     */
    private Integer integral;
    /**
     *订单编号
     */
    private Long orderNum;
    /**
     *付款方式 1：支付宝，2：微信
     */
    private Integer paymentMethod;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *最后一次修改时间
     */
    private Date updateTime;
    /**
     *状态 -1:逻辑删除
     */
    private Integer status;

    private String other1;

    private String other2;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsTakeOut() {
        return isTakeOut;
    }

    public void setIsTakeOut(Integer isTakeOut) {
        this.isTakeOut = isTakeOut;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeShow() {
        return typeShow;
    }

    public void setTypeShow(String typeShow) {
        this.typeShow = typeShow == null ? null : typeShow.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    @Override
    public String toString() {
        return "YdSellerFlowAccount{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", userId=" + userId +
                ", isTakeOut=" + isTakeOut +
                ", type=" + type +
                ", typeShow='" + typeShow + '\'' +
                ", money=" + money +
                ", integral=" + integral +
                ", orderNum=" + orderNum +
                ", paymentMethod=" + paymentMethod +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}