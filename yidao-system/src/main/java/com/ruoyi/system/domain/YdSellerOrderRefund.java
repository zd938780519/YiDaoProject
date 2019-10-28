package com.ruoyi.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class YdSellerOrderRefund implements Serializable {
    private Integer id;
    /**
     *订单ID
     */
    private Integer orderId;
    /**
     *用户ID
     */
    private Integer userId;
    /**
     *用户姓名
     */
    private String userName;
    /**
     *需要退回的款项
     */
    private BigDecimal refundProject;
    /**
     *退款原因
     */
    private String reason;
    /**
     *申请时间
     */
    private Date applyTime;
    /**
     *协商的需要扣除的手续费
     */
    private BigDecimal serviceCharge;
    /**
     *卖家处理时间
     */
    private Date handleTime;
    /**
     *退款完成时间
     */
    private Date completeTime;
    /**
     *退款状态 1：已申请；2：已完成
     */
    private Integer orderRefundType;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
        this.userName = userName == null ? null : userName.trim();
    }

    public BigDecimal getRefundProject() {
        return refundProject;
    }

    public void setRefundProject(BigDecimal refundProject) {
        this.refundProject = refundProject;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getOrderRefundType() {
        return orderRefundType;
    }

    public void setOrderRefundType(Integer orderRefundType) {
        this.orderRefundType = orderRefundType;
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", refundProject=").append(refundProject);
        sb.append(", reason=").append(reason);
        sb.append(", applyTime=").append(applyTime);
        sb.append(", serviceCharge=").append(serviceCharge);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", orderRefundType=").append(orderRefundType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", other1=").append(other1);
        sb.append(", other2=").append(other2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}