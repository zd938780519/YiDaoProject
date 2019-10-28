package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 到店吃菜品信息实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdSellerOrderItem implements Serializable {
    private Integer id;
    /**
     *卖家表外键
     */
    private Integer sellerId;
    /**
     *订单表外键
     */
    private Integer orderId;
    /**
     * 菜品id
     */
    private Integer dishId;
    /**
     *菜名
     */
    private String dishName;
    /**
     *菜品缩略图路径
     */
    private String thumbnailPath;
    /**
     *菜品数量
     */
    private Integer foodNum;
    /**
     *原价
     */
    private BigDecimal originalPrice;
    /**
     *售价
     */
    private BigDecimal actualPrice;
    /**
     *折扣
     */
    private BigDecimal discount;
    /**
     *套餐包含商品
     */
    private String comboGoods;
    /**
     *套餐开始日期
     */
    private Date comboStartTime;
    /**
     *套餐结束日期
     */
    private Date comboEndTime;
    /**
     *套餐使用开始时间
     */
    private Date comboTimeOn;
    /**
     *套餐使用结束时间
     */
    private Date comboTimeOff;
    /**
     *套餐使用规则
     */
    private String comboRemark;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public Integer getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(Integer foodNum) {
        this.foodNum = foodNum;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getComboGoods() {
        return comboGoods;
    }

    public void setComboGoods(String comboGoods) {
        this.comboGoods = comboGoods;
    }

    public Date getComboStartTime() {
        return comboStartTime;
    }

    public void setComboStartTime(Date comboStartTime) {
        this.comboStartTime = comboStartTime;
    }

    public Date getComboEndTime() {
        return comboEndTime;
    }

    public void setComboEndTime(Date comboEndTime) {
        this.comboEndTime = comboEndTime;
    }

    public Date getComboTimeOn() {
        return comboTimeOn;
    }

    public void setComboTimeOn(Date comboTimeOn) {
        this.comboTimeOn = comboTimeOn;
    }

    public Date getComboTimeOff() {
        return comboTimeOff;
    }

    public void setComboTimeOff(Date comboTimeOff) {
        this.comboTimeOff = comboTimeOff;
    }

    public String getComboRemark() {
        return comboRemark;
    }

    public void setComboRemark(String comboRemark) {
        this.comboRemark = comboRemark;
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
        return "YdSellerOrderItem{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", orderId=" + orderId +
                ", dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", foodNum=" + foodNum +
                ", originalPrice=" + originalPrice +
                ", actualPrice=" + actualPrice +
                ", discount=" + discount +
                ", comboGoods='" + comboGoods + '\'' +
                ", comboStartTime=" + comboStartTime +
                ", comboEndTime=" + comboEndTime +
                ", comboTimeOn=" + comboTimeOn +
                ", comboTimeOff=" + comboTimeOff +
                ", comboRemark='" + comboRemark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}