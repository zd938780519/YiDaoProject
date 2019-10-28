package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * 乡味内容实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdRuralFlavor {
    /** id  */
    private long id          ;
    /** 用户id  */
    private long userId      ;
    /** 视频路径 */
    private String videoPath   ;
    /** 乡味内容 */
    private String content     ;
    /** 点赞人数  */
    private int fabulousNum ;
    /** 踩的人数 */
    private int stepNum     ;
    /** 评论数  */
    private int commentNum  ;
    /** 转发数  */
    private int forwardNum  ;
    /** 收藏数 */
    private int collectionNum;
    /**  状态类型 1：草稿 2：发布 */
    private int stateType;
    /**  创建时间  */
    private Date createTime	        ;
    /**  最后一次修改时间  */
    private	Date updateTime	        ;
    /**  状态 -1：逻辑删除  */
    private	int status	              ;
    /**  用户名  */
    private	YdUser userNameTT	            ;
    /**  备用字段1  */
    private	String other1;
    /**  备用字段2  */
    private	String other2	            ;
    /** 是否已收藏 */
    private int isCollection;
    /** 是否已转发 */
    private int isForward;
    /**  赞Or踩 */
    private int fabulousOrStep;
    /**  起始位置偏移量  */
    private int currIndex;
    /**  页大小    */
    private int pageSize;
    /** 乡味图片集合 */
    private List<YdRuralFlavorPicture> images;

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public YdUser getUserNameTT() {
        return userNameTT;
    }

    public void setUserNameTT(YdUser userNameTT) {
        this.userNameTT = userNameTT;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public int getIsForward() {
        return isForward;
    }

    public void setIsForward(int isForward) {
        this.isForward = isForward;
    }

    public int getFabulousOrStep() {
        return fabulousOrStep;
    }

    public void setFabulousOrStep(int fabulousOrStep) {
        this.fabulousOrStep = fabulousOrStep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFabulousNum() {
        return fabulousNum;
    }

    public void setFabulousNum(int fabulousNum) {
        this.fabulousNum = fabulousNum;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
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

    public int getStateType() {
        return stateType;
    }

    public void setStateType(int stateType) {
        this.stateType = stateType;
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



    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
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

    public List<YdRuralFlavorPicture> getImages() {
        return images;
    }

    public void setImages(List<YdRuralFlavorPicture> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "YdRuralFlavor{" +
                "id=" + id +
                ", userId=" + userId +
                ", videoPath='" + videoPath + '\'' +
                ", content='" + content + '\'' +
                ", fabulousNum=" + fabulousNum +
                ", stepNum=" + stepNum +
                ", commentNum=" + commentNum +
                ", forwardNum=" + forwardNum +
                ", collectionNum=" + collectionNum +
                ", stateType=" + stateType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", userNameTT='" + userNameTT + '\'' +
                ", other2='" + other2 + '\'' +
                ", isCollection=" + isCollection +
                ", isForward=" + isForward +
                ", fabulousOrStep=" + fabulousOrStep +
                ", currIndex=" + currIndex +
                ", pageSize=" + pageSize +
                ", images=" + images +
                '}';
    }
}
