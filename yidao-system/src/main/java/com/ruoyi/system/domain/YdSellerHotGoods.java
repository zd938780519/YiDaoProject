package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 周边商品实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdSellerHotGoods implements Serializable {
    /**
     * 商品id
     */
    private Integer id;
    /**
     * 店铺id
     */
    private Integer sellerShopId;
    /**
     *是否热卖（0：否，1：是）
     */
    private Integer isPopular;
    /**
     *是否套餐（0：否，1：是）
     */
    private Integer isCombo;
    /**
     *是否可以外卖（0：否，1：是）
     */
    private Integer isTakeOut;
    /**
     *商品名称
     */
    private String hotName;
    /**
     *简介
     */
    private String brief;
    /**
     *详情标题
     */
    private String details;
    /**
     *原价
     */
    private BigDecimal originalPrice;
    /**
     *折扣
     */
    private BigDecimal discount;
    /**
     *积分限额
     */
    private BigDecimal integralLimit;
    /**
     *纯积分价
     */
    private Integer integralPrice;
    /**
     *展示图片
     */
    private String photoPath;
    /**
     *展示视频
     */
    private String videoPath;
    /**
     *销售价格
     */
    private BigDecimal salePrice;
    /**
     *浏览数量
     */
    private Integer readCount;
    /**
     *月销量
     */
    private Integer sellCountMonth;
    /**
     *总销量
     */
    private Integer sellCount;
    /**
     *点赞数
     */
    private Integer likeNum;
    /**
     *评论数
     */
    private Integer commentNum;
    /**
     *上架时间
     */
    private Date shelfTime;
    /**
     *主料
     */
    private String mainIngredient;
    /**
     *辅料
     */
    private String ingredient;
    /**
     *制作方法
     */
    private String creationMethod;
    /**
     *口味
     */
    private String flavor;
    /**
     *包装费
     */
    private BigDecimal packingExpense;
    /**
     *套餐包含商品
     */
    private String comboGoods;
    /**
     *套餐开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date comboStartTime;
    /**
     *套餐结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date comboEndTime;
    /**
     *套餐使用开始时间
     */
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date comboTimeOn;
    /**
     *套餐使用结束时间
     */
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date comboTimeOff;
    /**
     *套餐使用规则
     */
    private String comboRemark;
    /**
     *库存量/是否售完(0:未售完，1：已售完)
     */
    private Integer storedCount;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *最后一次修改时间
     */
    private Date updateTime;
    /**
     *状态 -1：逻辑删除
     */
    private Integer status;

    private String other1;

    private String other2;

    /**
     * 周边店铺对象
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private YdSellerShop ydSellerShop;

    private static final long serialVersionUID = 1L;

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

    public Integer getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(Integer isPopular) {
        this.isPopular = isPopular;
    }

    public Integer getIsCombo() {
        return isCombo;
    }

    public void setIsCombo(Integer isCombo) {
        this.isCombo = isCombo;
    }

    public Integer getIsTakeOut() {
        return isTakeOut;
    }

    public void setIsTakeOut(Integer isTakeOut) {
        this.isTakeOut = isTakeOut;
    }

    public String getHotName() {
        return hotName;
    }

    public void setHotName(String hotName) {
        this.hotName = hotName == null ? null : hotName.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getIntegralLimit() {
        return integralLimit;
    }

    public void setIntegralLimit(BigDecimal integralLimit) {
        this.integralLimit = integralLimit;
    }

    public Integer getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(Integer integralPrice) {
        this.integralPrice = integralPrice;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath == null ? null : photoPath.trim();
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath == null ? null : videoPath.trim();
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getSellCountMonth() {
        return sellCountMonth;
    }

    public void setSellCountMonth(Integer sellCountMonth) {
        this.sellCountMonth = sellCountMonth;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Date getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(Date shelfTime) {
        this.shelfTime = shelfTime;
    }

    public String getMainIngredient() {
        return mainIngredient;
    }

    public void setMainIngredient(String mainIngredient) {
        this.mainIngredient = mainIngredient == null ? null : mainIngredient.trim();
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient == null ? null : ingredient.trim();
    }

    public String getCreationMethod() {
        return creationMethod;
    }

    public void setCreationMethod(String creationMethod) {
        this.creationMethod = creationMethod == null ? null : creationMethod.trim();
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor == null ? null : flavor.trim();
    }

    public BigDecimal getPackingExpense() {
        return packingExpense;
    }

    public void setPackingExpense(BigDecimal packingExpense) {
        this.packingExpense = packingExpense;
    }

    public String getComboGoods() {
        return comboGoods;
    }

    public void setComboGoods(String comboGoods) {
        this.comboGoods = comboGoods == null ? null : comboGoods.trim();
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

    public Integer getStoredCount() {
        return storedCount;
    }

    public void setStoredCount(Integer storedCount) {
        this.storedCount = storedCount;
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

    public YdSellerShop getYdSellerShop() {
        return ydSellerShop;
    }

    public void setYdSellerShop(YdSellerShop ydSellerShop) {
        this.ydSellerShop = ydSellerShop;
    }

    @Override
    public String toString() {
        return "YdSellerHotGoods{" +
                "id=" + id +
                ", sellerShopId=" + sellerShopId +
                ", isPopular=" + isPopular +
                ", isCombo=" + isCombo +
                ", isTakeOut=" + isTakeOut +
                ", hotName='" + hotName + '\'' +
                ", brief='" + brief + '\'' +
                ", details='" + details + '\'' +
                ", originalPrice=" + originalPrice +
                ", discount=" + discount +
                ", integralLimit=" + integralLimit +
                ", integralPrice=" + integralPrice +
                ", photoPath='" + photoPath + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", salePrice=" + salePrice +
                ", readCount=" + readCount +
                ", sellCountMonth=" + sellCountMonth +
                ", sellCount=" + sellCount +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                ", shelfTime=" + shelfTime +
                ", mainIngredient='" + mainIngredient + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", creationMethod='" + creationMethod + '\'' +
                ", flavor='" + flavor + '\'' +
                ", packingExpense=" + packingExpense +
                ", comboGoods='" + comboGoods + '\'' +
                ", comboStartTime=" + comboStartTime +
                ", comboEndTime=" + comboEndTime +
                ", comboTimeOn=" + comboTimeOn +
                ", comboTimeOff=" + comboTimeOff +
                ", comboRemark='" + comboRemark + '\'' +
                ", storedCount=" + storedCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", ydSellerShop=" + ydSellerShop +
                '}';
    }
}