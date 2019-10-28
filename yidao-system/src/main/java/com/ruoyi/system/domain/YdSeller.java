package com.ruoyi.system.domain;

import java.util.Date;

/**
 * 卖家实体
 */
public class YdSeller {
    private long id               ;
    /** 类别 1：私厨；2：商家 */
    private int type             ;
    /** 私厨表外键 */
    private long pkId            ;
    /**  商家表外键*/
    private long businessId      ;
    /** 昵称 */
    private String name             ;
    /**  头像路径*/
    private String photoPath       ;
    /**  是否正在直播*/
    private int isLiveBroadcast;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getPkId() {
        return pkId;
    }

    public void setPkId(long pkId) {
        this.pkId = pkId;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
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

    public int getIsLiveBroadcast() {
        return isLiveBroadcast;
    }

    public void setIsLiveBroadcast(int isLiveBroadcast) {
        this.isLiveBroadcast = isLiveBroadcast;
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
        return "YdSeller{" +
                "id=" + id +
                ", type=" + type +
                ", pkId=" + pkId +
                ", businessId=" + businessId +
                ", name='" + name + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", isLiveBroadcast=" + isLiveBroadcast +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}
