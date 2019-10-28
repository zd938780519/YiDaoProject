package com.ruoyi.system.domain;

import java.util.Date;

/**
 * 乡味用户关联表实体
 */
public class YdRuralFlavorUser {
    /**  主键id  */
    private long id                 ;
    /**  乡味表外键 */
    private long rfId              ;
    /**  用户表外键  */
    private long userId            ;
    /**  是否已收藏  */
    private int isCollection      ;
    /**  赞还是踩 0：未评价；1：赞；2：踩 */
    private int fabulousOrStep   ;
    /**  是否已转发  */
    private int isForward         ;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRfId() {
        return rfId;
    }

    public void setRfId(long rfId) {
        this.rfId = rfId;
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

    public int getFabulousOrStep() {
        return fabulousOrStep;
    }

    public void setFabulousOrStep(int fabulousOrStep) {
        this.fabulousOrStep = fabulousOrStep;
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
        return "YdRuralFlavorUser{" +
                "id=" + id +
                ", rfId=" + rfId +
                ", userId=" + userId +
                ", isCollection=" + isCollection +
                ", fabulousOrStep=" + fabulousOrStep +
                ", isForward=" + isForward +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}
