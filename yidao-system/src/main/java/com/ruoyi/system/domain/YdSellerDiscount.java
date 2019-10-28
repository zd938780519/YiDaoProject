package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdSellerDiscount implements Serializable {
    /**
     * 优惠券id
     */
    private Integer id;
    /**
     * 店铺id
     */
    private Integer sellerShopId;
    /**
     * 优惠券名
     */
    private String disName;
    /**
     * 优惠券描述
     */
    private String describe;
    /**
     * 优惠券类型（0：满减券，1：通用券，2：打折券）
     */
    private Integer type;
    /**
     * 优惠额度
     */
    private BigDecimal discountMoney;
    /**
     * 优惠条件
     */
    private BigDecimal discountCondition;
    /**
     * 优惠折扣
     */
    private BigDecimal discountNum;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 到期时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后一次修改时间
     */
    private Date updateTime;
    /**
     * 状态 -1：逻辑删除 0：未上架 1：已上架 2：已到期
     */
    private Integer status;

    private String other1;

    private String other;
    /**
     *是否领取(0:未领取，1：已领取)
     */
    private Integer isDraw = 0;

    private static final long serialVersionUID = 1L;

    public YdSellerDiscount(Integer id, Integer sellerShopId, String disName, String describe, Integer type, BigDecimal discountMoney, BigDecimal discountCondition, BigDecimal discountNum, Integer amount, Date startTime, Date endTime, Date createTime, Date updateTime, Integer status, String other1, String other) {
        this.id = id;
        this.sellerShopId = sellerShopId;
        this.disName = disName;
        this.describe = describe;
        this.type = type;
        this.discountMoney = discountMoney;
        this.discountCondition = discountCondition;
        this.discountNum = discountNum;
        this.amount = amount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.other1 = other1;
        this.other = other;
    }

    public YdSellerDiscount() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerShopId() {
        return sellerShopId;
    }

    public void setSellerShopId(Integer sellerShopId) {
        this.sellerShopId = sellerShopId;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName == null ? null : disName.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public BigDecimal getDiscountCondition() {
        return discountCondition;
    }

    public void setDiscountCondition(BigDecimal discountCondition) {
        this.discountCondition = discountCondition;
    }

    public BigDecimal getDiscountNum() {
        return discountNum;
    }

    public void setDiscountNum(BigDecimal discountNum) {
        this.discountNum = discountNum;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public Integer getIsDraw() {
        return isDraw;
    }

    public void setIsDraw(Integer isDraw) {
        this.isDraw = isDraw;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sellerShopId=").append(sellerShopId);
        sb.append(", disName=").append(disName);
        sb.append(", describe=").append(describe);
        sb.append(", type=").append(type);
        sb.append(", discountMoney=").append(discountMoney);
        sb.append(", discountCondition=").append(discountCondition);
        sb.append(", discountNum=").append(discountNum);
        sb.append(", amount=").append(amount);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", other1=").append(other1);
        sb.append(", other=").append(other);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}