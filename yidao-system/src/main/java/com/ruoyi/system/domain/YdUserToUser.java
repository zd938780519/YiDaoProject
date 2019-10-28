package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 用户与用户关联表实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdUserToUser {
    private	long id                ;
    /**  主动方id  */
    private	long fromId           ;
    /**  被动方id  */
    private	long toId             ;
    /**  是否已关注  */
    private	int isFollow         ;
    /**  是否已拜师  */
    private	int isApprentice     ;
    /**  是否已拉黑  */
    private	int isPullBlack     ;
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
    /**  昵称 */
    private String name;
    /**  头像路径 */
    private String photoPath;
    /** 是否是好友（互关） */
    private int isFriend;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getIsApprentice() {
        return isApprentice;
    }

    public void setIsApprentice(int isApprentice) {
        this.isApprentice = isApprentice;
    }

    public int getIsPullBlack() {
        return isPullBlack;
    }

    public void setIsPullBlack(int isPullBlack) {
        this.isPullBlack = isPullBlack;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    @Override
    public String toString() {
        return "YdUserToUser{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", isFollow=" + isFollow +
                ", isApprentice=" + isApprentice +
                ", isPullBlack=" + isPullBlack +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", name='" + name + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", isFriend=" + isFriend +
                '}';
    }
}
