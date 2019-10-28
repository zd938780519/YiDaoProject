package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商城抵用券和用户关联实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdMallVoucherExchange implements Serializable {
    private Long id;

    private Long userId;

    private Long mvId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beOverdueTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date useTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer status;

    private String other1;

    private String other2;

    private static final long serialVersionUID = 1L;
    /** 抵扣金额 */
    private BigDecimal amount;
    /** 限制金额  */
    private BigDecimal limitedAmount;

    /** 备注说明 */
    private String remark;
    /** 是否可用 */
    private boolean isAvailable;

    public YdMallVoucherExchange(Long id, Long userId, Long mvId, Date beOverdueTime, Date useTime, Date createTime, Date updateTime, Integer status, String other1, String other2, BigDecimal amount, BigDecimal limitedAmount, String remark, boolean isAvailable) {
        this.id = id;
        this.userId = userId;
        this.mvId = mvId;
        this.beOverdueTime = beOverdueTime;
        this.useTime = useTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.other1 = other1;
        this.other2 = other2;
        this.amount = amount;
        this.limitedAmount = limitedAmount;
        this.remark = remark;
        this.isAvailable = isAvailable;
    }

    public YdMallVoucherExchange() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMvId() {
        return mvId;
    }

    public void setMvId(Long mvId) {
        this.mvId = mvId;
    }

    public Date getBeOverdueTime() {
        return beOverdueTime;
    }

    public void setBeOverdueTime(Date beOverdueTime) {
        this.beOverdueTime = beOverdueTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getLimitedAmount() {
        return limitedAmount;
    }

    public void setLimitedAmount(BigDecimal limitedAmount) {
        this.limitedAmount = limitedAmount;
    }

    @Override
    public String toString() {
        return "YdMallVoucherExchange{" +
                "id=" + id +
                ", userId=" + userId +
                ", mvId=" + mvId +
                ", beOverdueTime=" + beOverdueTime +
                ", useTime=" + useTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", amount=" + amount +
                ", limitedAmount=" + limitedAmount +
                ", remark='" + remark + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}