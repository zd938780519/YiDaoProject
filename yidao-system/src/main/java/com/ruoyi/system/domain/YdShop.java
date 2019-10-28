package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdShop extends BaseEntity {
    private Long id;

    private String name;

    private String advertisingSlogans;

    private Integer browsingVolume;

    private Integer thumbsNum;

    private Integer comment;

    private Integer follow;

    private Integer rateOfAcclaim;

    private String shippingAddress;

    private String dynamic;

    private BigDecimal turnover;

    private BigDecimal postalQuota;

    private int integral;

    private BigDecimal balance;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String other1;

    private String other2;

    private List<YdShoppingCartGoods> scGoods;
    /** 是否包邮 */
    private boolean isFreeShipping;
    /** 差多少包邮 */
    private BigDecimal  postalBalance;
    /** 邮费 */
    private double postage;

    private static final long serialVersionUID = 1L;

    public YdShop() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAdvertisingSlogans() {
        return advertisingSlogans;
    }

    public void setAdvertisingSlogans(String advertisingSlogans) {
        this.advertisingSlogans = advertisingSlogans == null ? null : advertisingSlogans.trim();
    }

    public Integer getBrowsingVolume() {
        return browsingVolume;
    }

    public void setBrowsingVolume(Integer browsingVolume) {
        this.browsingVolume = browsingVolume;
    }

    public Integer getThumbsNum() {
        return thumbsNum;
    }

    public void setThumbsNum(Integer thumbsNum) {
        this.thumbsNum = thumbsNum;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getRateOfAcclaim() {
        return rateOfAcclaim;
    }

    public void setRateOfAcclaim(Integer rateOfAcclaim) {
        this.rateOfAcclaim = rateOfAcclaim;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress == null ? null : shippingAddress.trim();
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic == null ? null : dynamic.trim();
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        if(createTime == null){
            return new Date();
        }else{
            return createTime;
        }
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        if(updateTime == null){
            return new Date();
        }else{
            return updateTime;
        }
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

    public List<YdShoppingCartGoods> getScGoods() {
        return scGoods;
    }

    public void setScGoods(List<YdShoppingCartGoods> scGoods) {
        this.scGoods = scGoods;
    }

    public BigDecimal getPostalQuota() {
        return postalQuota;
    }

    public void setPostalQuota(BigDecimal postalQuota) {
        this.postalQuota = postalQuota;
    }

    public boolean isFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        isFreeShipping = freeShipping;
    }

    public BigDecimal getPostalBalance() {
        return postalBalance;
    }

    public void setPostalBalance(BigDecimal postalBalance) {
        this.postalBalance = postalBalance;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public double getPostage() {
        return postage;
    }

    public void setPostage(double postage) {
        this.postage = postage;
    }

    @Override
    public String toString() {
        return "YdShop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", advertisingSlogans='" + advertisingSlogans + '\'' +
                ", browsingVolume=" + browsingVolume +
                ", thumbsNum=" + thumbsNum +
                ", comment=" + comment +
                ", follow=" + follow +
                ", rateOfAcclaim=" + rateOfAcclaim +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", dynamic='" + dynamic + '\'' +
                ", turnover=" + turnover +
                ", postalQuota=" + postalQuota +
                ", integral=" + integral +
                ", balance=" + balance +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", scGoods=" + scGoods +
                ", isFreeShipping=" + isFreeShipping +
                ", postalBalance=" + postalBalance +
                ", postage=" + postage +
                '}';
    }
}