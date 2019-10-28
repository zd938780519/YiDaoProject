package com.ruoyi.system.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.json.CustomStringDeserializer;

import java.util.Date;

/**
 * 配料实体
 */
@JsonDeserialize(using = CustomStringDeserializer.class)
public class YdTeachingMaterials {
    /**  主键  */
    private long id                     ;
    /**  用户表外键  */
    private long tcId                ;
    /**  类型 1：主料；2：辅料 */
    private int type;
    /** 原料  */
    private String material;
    /**  用量 */
    private String number;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        return "YdTeachingMaterials{" +
                "id=" + id +
                ", tcId=" + tcId +
                ", type=" + type +
                ", material='" + material + '\'' +
                ", number='" + number + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}
