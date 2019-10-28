package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 用户消息（私信）实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdUserToUserLetter {
    /**     主键   */
    private long id              ;
    /**    发送者id    */
    private long fromId         ;
    /**    接受者id    */
    private long toId           ;
    /**    内容    */
    private String content       ;
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
    /**  起始位置偏移量  */
    private int currIndex;
    /**  页大小    */
    private int pageSize;
    /**  发送者头像路径   */
    private String fromPhotoPath;
    /**  接收者头像路径  */
    private String toPhotoPath;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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



    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(int currIndex) {
        this.currIndex = currIndex;
    }

    public String getFromPhotoPath() {
        return fromPhotoPath;
    }

    public void setFromPhotoPath(String fromPhotoPath) {
        this.fromPhotoPath = fromPhotoPath;
    }

    public String getToPhotoPath() {
        return toPhotoPath;
    }

    public void setToPhotoPath(String toPhotoPath) {
        this.toPhotoPath = toPhotoPath;
    }

    @Override
    public String toString() {
        return "YdUserToUserLetter{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", currIndex=" + currIndex +
                ", pageSize=" + pageSize +
                ", fromPhotoPath='" + fromPhotoPath + '\'' +
                ", toPhotoPath='" + toPhotoPath + '\'' +
                '}';
    }
}
