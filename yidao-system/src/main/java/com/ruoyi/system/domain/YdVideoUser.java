package com.ruoyi.system.domain;

import java.util.Date;

/**
 * 视频用户关联表实体
 */
public class YdVideoUser {
    /** id*/
    private	long id            ;
    /**  视频id */
    private	long videoId      ;
    /**  用户id */
    private	long userId       ;
    /**  是否已收藏 */
    private	int isCollection ;
    /**  是否想吃 */
    private	int isWantEat   ;
    /**  是否已转发 */
    private	int isForward    ;
    private Date createTime   ;
    private	Date updateTime   ;
    private	int status        ;
    private	String other1        ;
    private	String other2        ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public int getIsWantEat() {
        return isWantEat;
    }

    public void setIsWantEat(int isWantEat) {
        this.isWantEat = isWantEat;
    }

    public int getIsForward() {
        return isForward;
    }

    public void setIsForward(int isForward) {
        this.isForward = isForward;
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

    @Override
    public String toString() {
        return "YdVideoUser{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", userId=" + userId +
                ", isCollection=" + isCollection +
                ", isWantEat=" + isWantEat +
                ", isForward=" + isForward +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}
