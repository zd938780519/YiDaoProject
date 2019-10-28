package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车商品实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdSellerBasketGoods implements Serializable {
    private Integer id;
    /**
     *购物车id
     */
    private Integer basketId;
    /**
     *店铺id
     */
    private Integer shopId;
    /**
     *商品id
     */
    private Integer goodsId;
    /**
     *订购时间
     */
    private Date orderTime;
    /**
     *订购数量
     */
    private Integer orderCount;
    /**
     *订购原价
     */
    private BigDecimal orderPrice;
    /**
     *订购优惠价
     */
    private BigDecimal orderSalePrice;
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
     * 热卖商品对象
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private YdSellerHotGoods hotGoods;

    private static final long serialVersionUID = 1L;

    public YdSellerBasketGoods(Integer id, Integer basketId, Integer shopId, Integer goodsId, Date orderTime, Integer orderCount, BigDecimal orderPrice, BigDecimal orderSalePrice, Date createTime, Date updateTime, Integer status, String other1, String other2) {
        this.id = id;
        this.basketId = basketId;
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.orderTime = orderTime;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
        this.orderSalePrice = orderSalePrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.other1 = other1;
        this.other2 = other2;
    }

    public YdSellerBasketGoods() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBasketId() {
        return basketId;
    }

    public void setBasketId(Integer basketId) {
        this.basketId = basketId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderSalePrice() {
        return orderSalePrice;
    }

    public void setOrderSalePrice(BigDecimal orderSalePrice) {
        this.orderSalePrice = orderSalePrice;
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

    public YdSellerHotGoods getHotGoods() {
        return hotGoods;
    }

    public void setHotGoods(YdSellerHotGoods hotGoods) {
        this.hotGoods = hotGoods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", basketId=").append(basketId);
        sb.append(", shopId=").append(shopId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", orderSalePrice=").append(orderSalePrice);
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