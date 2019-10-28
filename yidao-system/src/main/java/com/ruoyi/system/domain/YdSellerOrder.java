package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 到店吃订单实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdSellerOrder implements Serializable {
    private Integer id;
    /**
     *用户id
     */
    private Integer userId;
    /**
     *用户名
     */
    private String userName;
    /**
     *店铺id
     */
    private Integer sellerId;
    /**
     *店铺名
     */
    private String sellerName;
    /**
     * 店铺地址
     */
    private String sellerAddress;
    /**
     *店铺联系方式
     */
    private String sellerPhone;
    /**
     *订单编号
     */
    private Long orderNum;
    /**
     *订单二维码
     */
    private String orderCode;
    /**
     *备注
     */
    private String remark;
    /**
     *优惠券抵扣金额
     */
    private BigDecimal couponMoney;
    /**
     *活动抵扣金额
     */
    private BigDecimal activityMoney;
    /**
     *积分抵扣金额
     */
    private BigDecimal integralMoney;
    /**
     *合计积分
     */
    private Integer totalIntegral;
    /**
     *应付金额
     */
    private BigDecimal amountPayable;
    /**
     *合计金额
     */
    private BigDecimal totalAmount;
    /**
     *订单类型 1：私厨订单；2：商家订单；3：网红订单
     */
    private Integer type;
    /**
     *支付方式
     */
    private Integer payWay;
    /**
     *支付方式显示值
     */
    private String payWayShow;
    /**
     *支付时间
     */
    private Date payWayTime;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *最后一次修改时间
     */
    private Date updateTime;
    /**
     *状态 -1:逻辑删除 0：未付款；1:未使用；2：未评价；3：未退款；4：卖家未确认；5：已退款
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getActivityMoney() {
        return activityMoney;
    }

    public void setActivityMoney(BigDecimal activityMoney) {
        this.activityMoney = activityMoney;
    }

    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public BigDecimal getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(BigDecimal amountPayable) {
        this.amountPayable = amountPayable;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getPayWayShow() {
        return payWayShow;
    }

    public void setPayWayShow(String payWayShow) {
        this.payWayShow = payWayShow;
    }

    public Date getPayWayTime() {
        return payWayTime;
    }

    public void setPayWayTime(Date payWayTime) {
        this.payWayTime = payWayTime;
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
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    @Override
    public String toString() {
        return "YdSellerOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", sellerPhone='" + sellerPhone + '\'' +
                ", orderNum=" + orderNum +
                ", orderCode='" + orderCode + '\'' +
                ", remark='" + remark + '\'' +
                ", couponMoney=" + couponMoney +
                ", activityMoney=" + activityMoney +
                ", integralMoney=" + integralMoney +
                ", totalIntegral=" + totalIntegral +
                ", amountPayable=" + amountPayable +
                ", totalAmount=" + totalAmount +
                ", type=" + type +
                ", payWay=" + payWay +
                ", payWayShow='" + payWayShow + '\'' +
                ", payWayTime=" + payWayTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}