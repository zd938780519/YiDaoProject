package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * 商品实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdShopGoods {
    /**  主键  */
    private long id              ;
    /**   店铺id */
    private long shopId         ;
    /**  商品名称  */
    private String name            ;
    /**  商品类型id  */
    private int typeId          ;
    /**   简介 */
    private String brief           ;
    /**   原价 */
    private double originalPrice  ;
    /**   折扣 */
    private double discount        ;
    /**   销售价格 */
    private double salePrice      ;
    /**  积分限额  */
    private int integralLimit  ;
    /** 积分价 */
    private int integralPrice;
    /**  展示图片 */
    private String photoPath;
    /** 是否可用券 0:不可以；1:可以  */
    private int isAllowCoupon;
    /**  是否固定金额加固定积分 0:不是；1:是 */
    private int isFixedMoneyAndIntegral;
    /**  是否纯积分 0:不是；1:是 */
    private int isPureIntegral;
    /**  喜欢此商品人数 */
    private int likeNum;
    /** 促销内容 */
    private String promotionalContent;
    /** 详情图片路径 */
    private String detailsPhotoPath;
    /** 全积分付款金额  */
    private int integralPaymentPrice;
    /** 返积分金额 */
    private int giveIntegralPrice;

    /**   进货价格 */
    private double storePrice     ;
    /**   浏览次数 */
    private int readCount      ;
    /**   销售数量 */
    private int sellCount      ;
    /**   月销量 */
    private int sellCountMonth;
    /**   是否包邮 */
    private int isFreeShipping;
    /** 首件邮费 */
    private double firstPostage;
    /** 加件邮费 */
    private double additionalPostage;

    /**   上架时间 */
    private Date shelfTime      ;
    /**   库存量 */
    private int storedCount    ;
    /**   商品序列号 */
    private String anNumber       ;
    /**   生产厂家 */
    private String producer        ;
    /** 参数类型 */
    private String parameterType;
    /**   包装类型 */
    private String packageType         ;
    /**  创建时间  */
    private Date createTime	        ;
    /**  最后一次修改时间  */
    private	Date updateTime	        ;
    /**  状态 -1：逻辑删除  */
    private	int status	              ;
    /**  备用字段1  */
    private	String other1	            ;
    /**  备用字段2  */
    private	String other2	            ;
    /**  店铺名*/
    private String shopName;
    /**  商品类型 */
    private String typeShow;
    /** 商品单价-->订单里的商品价格 */
    private double goodsUnitPrice  ;
    /** 商品数量 */
    private int goodsCount;
    /** 提供的服务 */
    private List<YdShopGoodsServe> serve;
    /** 商品参数 */
    private List<YdShopGoodsParameter> goodsParameters;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getIntegralLimit() {
        return integralLimit;
    }

    public void setIntegralLimit(int integralLimit) {
        this.integralLimit = integralLimit;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public double getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(double storePrice) {
        this.storePrice = storePrice;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public int getSellCountMonth() {
        return sellCountMonth;
    }

    public void setSellCountMonth(int sellCountMonth) {
        this.sellCountMonth = sellCountMonth;
    }

    public int getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(int isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Date getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(Date shelfTime) {
        this.shelfTime = shelfTime;
    }

    public int getStoredCount() {
        return storedCount;
    }

    public void setStoredCount(int storedCount) {
        this.storedCount = storedCount;
    }

    public String getAnNumber() {
        return anNumber;
    }

    public void setAnNumber(String anNumber) {
        this.anNumber = anNumber;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTypeShow() {
        return typeShow;
    }

    public void setTypeShow(String typeShow) {
        this.typeShow = typeShow;
    }

    public double getGoodsUnitPrice() {
        return goodsUnitPrice;
    }

    public void setGoodsUnitPrice(double goodsUnitPrice) {
        this.goodsUnitPrice = goodsUnitPrice;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(int integralPrice) {
        this.integralPrice = integralPrice;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public int getIsAllowCoupon() {
        return isAllowCoupon;
    }

    public void setIsAllowCoupon(int isAllowCoupon) {
        this.isAllowCoupon = isAllowCoupon;
    }

    public int getIsFixedMoneyAndIntegral() {
        return isFixedMoneyAndIntegral;
    }

    public void setIsFixedMoneyAndIntegral(int isFixedMoneyAndIntegral) {
        this.isFixedMoneyAndIntegral = isFixedMoneyAndIntegral;
    }

    public int getIsPureIntegral() {
        return isPureIntegral;
    }

    public void setIsPureIntegral(int isPureIntegral) {
        this.isPureIntegral = isPureIntegral;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getPromotionalContent() {
        return promotionalContent;
    }

    public void setPromotionalContent(String promotionalContent) {
        this.promotionalContent = promotionalContent;
    }

    public List<YdShopGoodsServe> getServe() {
        return serve;
    }

    public void setServe(List<YdShopGoodsServe> serve) {
        this.serve = serve;
    }

    public String getDetailsPhotoPath() {
        return detailsPhotoPath;
    }

    public void setDetailsPhotoPath(String detailsPhotoPath) {
        this.detailsPhotoPath = detailsPhotoPath;
    }

    public List<YdShopGoodsParameter> getGoodsParameters() {
        return goodsParameters;
    }

    public void setGoodsParameters(List<YdShopGoodsParameter> goodsParameters) {
        this.goodsParameters = goodsParameters;
    }

    public int getIntegralPaymentPrice() {
        return integralPaymentPrice;
    }

    public void setIntegralPaymentPrice(int integralPaymentPrice) {
        this.integralPaymentPrice = integralPaymentPrice;
    }

    public int getGiveIntegralPrice() {
        return giveIntegralPrice;
    }

    public void setGiveIntegralPrice(int giveIntegralPrice) {
        this.giveIntegralPrice = giveIntegralPrice;
    }

    public double getFirstPostage() {
        return firstPostage;
    }

    public void setFirstPostage(double firstPostage) {
        this.firstPostage = firstPostage;
    }

    public double getAdditionalPostage() {
        return additionalPostage;
    }

    public void setAdditionalPostage(double additionalPostage) {
        this.additionalPostage = additionalPostage;
    }

    @Override
    public String toString() {
        return "YdShopGoods{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", brief='" + brief + '\'' +
                ", originalPrice=" + originalPrice +
                ", discount=" + discount +
                ", salePrice=" + salePrice +
                ", integralLimit=" + integralLimit +
                ", integralPrice=" + integralPrice +
                ", photoPath='" + photoPath + '\'' +
                ", isAllowCoupon=" + isAllowCoupon +
                ", isFixedMoneyAndIntegral=" + isFixedMoneyAndIntegral +
                ", isPureIntegral=" + isPureIntegral +
                ", likeNum=" + likeNum +
                ", promotionalContent='" + promotionalContent + '\'' +
                ", detailsPhotoPath='" + detailsPhotoPath + '\'' +
                ", integralPaymentPrice=" + integralPaymentPrice +
                ", giveIntegralPrice=" + giveIntegralPrice +
                ", storePrice=" + storePrice +
                ", readCount=" + readCount +
                ", sellCount=" + sellCount +
                ", sellCountMonth=" + sellCountMonth +
                ", isFreeShipping=" + isFreeShipping +
                ", firstPostage=" + firstPostage +
                ", additionalPostage=" + additionalPostage +
                ", shelfTime=" + shelfTime +
                ", storedCount=" + storedCount +
                ", anNumber='" + anNumber + '\'' +
                ", producer='" + producer + '\'' +
                ", parameterType='" + parameterType + '\'' +
                ", packageType='" + packageType + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", shopName='" + shopName + '\'' +
                ", typeShow='" + typeShow + '\'' +
                ", goodsUnitPrice=" + goodsUnitPrice +
                ", goodsCount=" + goodsCount +
                ", serve=" + serve +
                ", goodsParameters=" + goodsParameters +
                '}';
    }
}
