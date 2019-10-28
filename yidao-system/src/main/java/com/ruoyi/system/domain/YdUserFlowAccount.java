package com.ruoyi.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 用户流水表实体
 */
public class YdUserFlowAccount implements Serializable {
    private Long id;

    private Long userId;

    private Integer type;

    private String typeShow;

    private BigDecimal renminbi;

    private Integer integral;

    private Integer giftIntegral;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String other1;

    private String other2;

    private static final long serialVersionUID = 1L;

    public YdUserFlowAccount(Long id, Long userId, Integer type, String typeShow, BigDecimal renminbi, Integer integral, Integer giftIntegral, Date createTime, Date updateTime, Integer status, String other1, String other2) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.typeShow = typeShow;
        this.renminbi = renminbi;
        this.integral = integral;
        this.giftIntegral = giftIntegral;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.other1 = other1;
        this.other2 = other2;
    }

    public YdUserFlowAccount() {
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

    public BigDecimal getRenminbi() {
        return renminbi;
    }

    public void setRenminbi(BigDecimal renminbi) {
        this.renminbi = renminbi;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getGiftIntegral() {
        return giftIntegral;
    }

    public void setGiftIntegral(Integer giftIntegral) {
        this.giftIntegral = giftIntegral;
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
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", typeShow=").append(typeShow);
        sb.append(", renminbi=").append(renminbi);
        sb.append(", integral=").append(integral);
        sb.append(", giftIntegral=").append(giftIntegral);
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