package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdSellerHotComment implements Serializable {
    private Integer id;
    /**
     *用户id
     */
    private Integer userId;
    /**
     * 卖家id
     */
    private Integer sellerId;
    /**
     * 订单编号
     */
    private Long orderNum;
    /**
     *商品id
     */
    private Integer hotGoodId;
    /**
     *评分
     */
    private Integer score;
    /**
     * 是否匿名 0：不匿名 1：匿名
     */
    private Integer isAnonymity;
    /**
     *是否有图（0：无，1：有）
     */
    private Integer isImage;
    /**
     *是否内容（0：无，1：有）
     */
    private Integer isContent;
    /**
     *评价内容
     */
    private String content;
    /**
     *评价图片
     */
    private String images;
    /**
     *评价视频
     */
    private String video;
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

    private static final long serialVersionUID = 1L;

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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderId) {
        this.orderNum = orderId;
    }

    public Integer getHotGoodId() {
        return hotGoodId;
    }

    public void setHotGoodId(Integer hotGoodId) {
        this.hotGoodId = hotGoodId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIsAnonymity() {
        return isAnonymity;
    }

    public void setIsAnonymity(Integer isAnonymity) {
        this.isAnonymity = isAnonymity;
    }

    public Integer getIsImage() {
        return isImage;
    }

    public void setIsImage(Integer isImage) {
        this.isImage = isImage;
    }

    public Integer getIsContent() {
        return isContent;
    }

    public void setIsContent(Integer isContent) {
        this.isContent = isContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
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
        return "YdSellerHotComment{" +
                "id=" + id +
                ", userId=" + userId +
                ", sellerId=" + sellerId +
                ", orderNum=" + orderNum +
                ", hotGoodId=" + hotGoodId +
                ", score=" + score +
                ", isAnonymity=" + isAnonymity +
                ", isImage=" + isImage +
                ", isContent=" + isContent +
                ", content='" + content + '\'' +
                ", images='" + images + '\'' +
                ", video='" + video + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}