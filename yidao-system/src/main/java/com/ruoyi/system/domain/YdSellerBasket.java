package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 购物车实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdSellerBasket implements Serializable {
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     *总价
     */
    private BigDecimal totalPrice;
    /**
     *优惠后价格
     */
    private BigDecimal salePrice;
    /**
     *数量
     */
    private Integer goodsNum;
    /**
     *包装费
     */
    private BigDecimal packingExpense;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *最后一次修改时间
     */
    private Date updateTime;

    private Integer status;

    private String other1;

    private String other2;

    /**
     * 购物车商品集合
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<YdSellerBasketGoods> basketGoodsList;

    private static final long serialVersionUID = 1L;

    public YdSellerBasket(Integer id, Integer userId, BigDecimal totalPrice, BigDecimal salePrice, Integer goodsNum, BigDecimal packingExpense, Date createTime, Date updateTime, Integer status, String other1, String other2, List<YdSellerBasketGoods> basketGoodsList) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.salePrice = salePrice;
        this.goodsNum = goodsNum;
        this.packingExpense = packingExpense;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.other1 = other1;
        this.other2 = other2;
        this.basketGoodsList = basketGoodsList;
    }

    public YdSellerBasket(){super();}

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getPackingExpense() {
        return packingExpense;
    }

    public void setPackingExpense(BigDecimal packingExpense) {
        this.packingExpense = packingExpense;
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

    public List<YdSellerBasketGoods> getBasketGoodsList() {
        return basketGoodsList;
    }

    public void setBasketGoodsList(List<YdSellerBasketGoods> basketGoodsList) {
        this.basketGoodsList = basketGoodsList;
    }

    @Override
    public String toString() {
        return "YdSellerBasket{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", salePrice=" + salePrice +
                ", goodsNum=" + goodsNum +
                ", packingExpense=" + packingExpense +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", basketGoodsList=" + basketGoodsList +
                '}';
    }
}