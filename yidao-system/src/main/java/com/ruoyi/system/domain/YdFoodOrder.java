package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdFoodOrder implements Serializable {
    private Integer id;
    /**
     *用户表外键
     */
    private Integer userId;
    /**
     *卖家表外键
     */
    private Integer sellerId;
    /**
     *订单编号
     */
    private Long orderNum;
    /**
     *预计送达时间
     */
    private Date estimatedServiceTime;
    /**
     *实际送达时间
     */
    private Date actualServiceTime;
    /**
     *包装费
     */
    private BigDecimal packingFee;
    /**
     *配送费
     */
    private BigDecimal distributionFee;
    /**
     *备注
     */
    private String remark;
    /**
     *餐具数量
     */
    private Integer tablewareNum;
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
     *理由
     */
    private String reason;
    /**
     *支付方式 1:支付宝 2：微信
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
     *状态 -1:逻辑删除;0：未付款；1：未接单；2：未配送；3：未送达；4：未评价；5：未退款；6：卖家未确认；7：已退款；
     */
    private Integer status;

    private String other1;

    private String other2;

    /**
     * 菜品信息列表
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<YdFoodInfo> foodInfoList;

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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Date getEstimatedServiceTime() {
        return estimatedServiceTime;
    }

    public void setEstimatedServiceTime(Date estimatedServiceTime) {
        this.estimatedServiceTime = estimatedServiceTime;
    }

    public Date getActualServiceTime() {
        return actualServiceTime;
    }

    public void setActualServiceTime(Date actualServiceTime) {
        this.actualServiceTime = actualServiceTime;
    }

    public BigDecimal getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(BigDecimal packingFee) {
        this.packingFee = packingFee;
    }

    public BigDecimal getDistributionFee() {
        return distributionFee;
    }

    public void setDistributionFee(BigDecimal distributionFee) {
        this.distributionFee = distributionFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTablewareNum() {
        return tablewareNum;
    }

    public void setTablewareNum(Integer tablewareNum) {
        this.tablewareNum = tablewareNum;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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
        this.payWayShow = payWayShow == null ? null : payWayShow.trim();
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
        this.other1 = other1 == null ? null : other1.trim();
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2 == null ? null : other2.trim();
    }

    public List<YdFoodInfo> getFoodInfoList() {
        return foodInfoList;
    }

    public void setFoodInfoList(List<YdFoodInfo> foodInfoList) {
        this.foodInfoList = foodInfoList;
    }

    @Override
    public String toString() {
        return "YdFoodOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", sellerId=" + sellerId +
                ", orderNum=" + orderNum +
                ", estimatedServiceTime=" + estimatedServiceTime +
                ", actualServiceTime=" + actualServiceTime +
                ", packingFee=" + packingFee +
                ", distributionFee=" + distributionFee +
                ", remark='" + remark + '\'' +
                ", tablewareNum=" + tablewareNum +
                ", couponMoney=" + couponMoney +
                ", activityMoney=" + activityMoney +
                ", integralMoney=" + integralMoney +
                ", totalIntegral=" + totalIntegral +
                ", amountPayable=" + amountPayable +
                ", totalAmount=" + totalAmount +
                ", type=" + type +
                ", reason='" + reason + '\'' +
                ", payWay=" + payWay +
                ", payWayShow='" + payWayShow + '\'' +
                ", payWayTime=" + payWayTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", foodInfoList=" + foodInfoList +
                '}';
    }
}