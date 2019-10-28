package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品评论表实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdShopGoodComment implements Serializable {
    private Long id;

    private Long soId;

    private Long goodsId;

    private Long userId;

    private String content;

    private String videoPath;

    private Integer commentScore;

    private Integer logisticsScore;

    private Integer attitudeScore;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    private Integer status;

    private String other1;

    private String other2;

    private String userName;

    private String userPhotoPath;

    private List<YdShopGoodCommentImage> imagesPath;

    private static final long serialVersionUID = 1L;



    public YdShopGoodComment() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath == null ? null : videoPath.trim();
    }

    public Integer getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Integer commentScore) {
        this.commentScore = commentScore;
    }

    public Integer getLogisticsScore() {
        return logisticsScore;
    }

    public void setLogisticsScore(Integer logisticsScore) {
        this.logisticsScore = logisticsScore;
    }

    public Integer getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(Integer attitudeScore) {
        this.attitudeScore = attitudeScore;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhotoPath() {
        return userPhotoPath;
    }

    public void setUserPhotoPath(String userPhotoPath) {
        this.userPhotoPath = userPhotoPath;
    }

    public List<YdShopGoodCommentImage> getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(List<YdShopGoodCommentImage> imagesPath) {
        this.imagesPath = imagesPath;
    }

    public YdShopGoodComment(Long id, Long soId, Long goodsId, Long userId, String content, String videoPath,
                             Integer commentScore, Integer logisticsScore, Integer attitudeScore, Date createTime,
                             Date updateTime, Integer status, String other1, String other2, String userName,
                             String userPhotoPath, List<YdShopGoodCommentImage> imagesPath) {
        this.id = id;
        this.soId = soId;
        this.goodsId = goodsId;
        this.userId = userId;
        this.content = content;
        this.videoPath = videoPath;
        this.commentScore = commentScore;
        this.logisticsScore = logisticsScore;
        this.attitudeScore = attitudeScore;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.other1 = other1;
        this.other2 = other2;
        this.userName = userName;
        this.userPhotoPath = userPhotoPath;
        this.imagesPath = imagesPath;
    }

    @Override
    public String toString() {
        return "YdShopGoodComment{" +
                "id=" + id +
                ", soId=" + soId +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", commentScore=" + commentScore +
                ", logisticsScore=" + logisticsScore +
                ", attitudeScore=" + attitudeScore +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhotoPath='" + userPhotoPath + '\'' +
                ", imagesPath=" + imagesPath +
                '}';
    }
}