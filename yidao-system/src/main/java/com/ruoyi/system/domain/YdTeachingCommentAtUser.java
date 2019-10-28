package com.ruoyi.system.domain;

import java.util.Date;

/**
 * 教学评论@人员表实体
 */
public class YdTeachingCommentAtUser {
    private	long id                ;
    private	long tcomId             ;
    private	long userId           ;
    private Date createTime      ;
    private	Date updateTime      ;
    private	int status           ;
    private	String other1           ;
    private	String other2           ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTcomId() {
        return tcomId;
    }

    public void setTcomId(long tcomId) {
        this.tcomId = tcomId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
        return "YdTeachingCommentAtUser{" +
                "id=" + id +
                ", tcomId=" + tcomId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}
