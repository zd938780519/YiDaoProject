package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 视频表
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdVideo {
    /**  视频id  */
    private	long id	                  ;
    /**  卖家id  */
    private	long sellerId	          ;
    /**  卖家类型（私厨或商家）  */
    private	int type	                ;
    /**  缩略图路径  */
    private	String thumbnailPath	    ;
    /**  视频路径  */
    private String videoPath	        ;
    /**  评分  */
    private	int score	                ;
    /**  想吃的人数  */
    private	int wantEatNum	        ;
    /**  评论数  */
    private	int commentNum	          ;
    /**  转发数  */
    private	int forwardNum	          ;
    /**  说明文字  */
    private	String explanatoryText 	  ;
    /**  bgm名称  */
    private	String bgmName	          ;
    /**  bgm路径 */
    private String bgmPath;
    /**  总销量  */
    private	int totalSaleNum	      ;
    /**  月销量  */
    private	int monthlySaleNum	    ;
    /**  限购数量  */
    private	int quotaNum	            ;
    /**  原价  */
    private	double originalPrice 	  ;
    /**  售价  */
    private	double actualPrice	      ;
    /**  折扣  */
    private	double discount	          ;
    /**  是否售罄  */
    private	int isSellOut 	        ;
    /**  补充说明  */
    private	String supplementaryNotes;
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
    /**  卖家名称  */
    private String sellerName;
    /**  卖家头像  */
    private String sellerPhotoPath;
    /**  用户是否想吃 */
    private int isWantEat;
    /**  起始位置偏移量  */
    private int currIndex;
    /**  页大小    */
    private int pageSize;
    /**  是否特色         */
    private int feature;

    public int getFeature() {
        return feature;
    }

    public void setFeature(int feature) {
        this.feature = feature;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWantEatNum() {
        return wantEatNum;
    }

    public void setWantEatNum(int wantEatNum) {
        this.wantEatNum = wantEatNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(int forwardNum) {
        this.forwardNum = forwardNum;
    }

    public String getExplanatoryText() {
        return explanatoryText;
    }

    public void setExplanatoryText(String explanatoryText) {
        this.explanatoryText = explanatoryText;
    }

    public String getBgmName() {
        return bgmName;
    }

    public void setBgmName(String bgmName) {
        this.bgmName = bgmName;
    }

    public int getTotalSaleNum() {
        return totalSaleNum;
    }

    public void setTotalSaleNum(int totalSaleNum) {
        this.totalSaleNum = totalSaleNum;
    }

    public int getMonthlySaleNum() {
        return monthlySaleNum;
    }

    public void setMonthlySaleNum(int monthlySaleNum) {
        this.monthlySaleNum = monthlySaleNum;
    }

    public int getQuotaNum() {
        return quotaNum;
    }

    public void setQuotaNum(int quotaNum) {
        this.quotaNum = quotaNum;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getIsSellOut() {
        return isSellOut;
    }

    public void setIsSellOut(int isSellOut) {
        this.isSellOut = isSellOut;
    }

    public String getSupplementaryNotes() {
        return supplementaryNotes;
    }

    public void setSupplementaryNotes(String supplementaryNotes) {
        this.supplementaryNotes = supplementaryNotes;
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerPhotoPath() {
        return sellerPhotoPath;
    }

    public void setSellerPhotoPath(String sellerPhotoPath) {
        this.sellerPhotoPath = sellerPhotoPath;
    }

    public int getIsWantEat() {
        return isWantEat;
    }

    public void setIsWantEat(int isWantEat) {
        this.isWantEat = isWantEat;
    }

    public int getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(int currIndex) {
        this.currIndex = currIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getBgmPath() {
        return bgmPath;
    }

    public void setBgmPath(String bgmPath) {
        this.bgmPath = bgmPath;
    }

    @Override
    public String toString() {
        return "YdVideo{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", type=" + type +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", score=" + score +
                ", wantEatNum=" + wantEatNum +
                ", commentNum=" + commentNum +
                ", forwardNum=" + forwardNum +
                ", explanatoryText='" + explanatoryText + '\'' +
                ", bgmName='" + bgmName + '\'' +
                ", bgmPath='" + bgmPath + '\'' +
                ", totalSaleNum=" + totalSaleNum +
                ", monthlySaleNum=" + monthlySaleNum +
                ", quotaNum=" + quotaNum +
                ", originalPrice=" + originalPrice +
                ", actualPrice=" + actualPrice +
                ", discount=" + discount +
                ", isSellOut=" + isSellOut +
                ", supplementaryNotes='" + supplementaryNotes + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerPhotoPath='" + sellerPhotoPath + '\'' +
                ", isWantEat=" + isWantEat +
                ", currIndex=" + currIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
