package com.ruoyi.system.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.json.CustomStringDeserializer;

import java.util.Date;

/**
 * 教学步骤实体
 */
@JsonDeserialize(using = CustomStringDeserializer.class)
public class YdTeachingStep {
    /**  主键  */
    private long id                     ;
    /**  用户表外键  */
    private long tcId                ;
    /** 步骤顺序  */
    private int step;
    /** 图片路径  */
    private	String picturePath;
    /** 说明文字  */
    private	String explanatoryText;
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

    public long getTcId() {
        return tcId;
    }

    public void setTcId(long tcId) {
        this.tcId = tcId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getExplanatoryText() {
        return explanatoryText;
    }

    public void setExplanatoryText(String explanatoryText) {
        this.explanatoryText = explanatoryText;
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
        return "YdTeachingStep{" +
                "id=" + id +
                ", tcId=" + tcId +
                ", step=" + step +
                ", picturePath='" + picturePath + '\'' +
                ", explanatoryText='" + explanatoryText + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}
