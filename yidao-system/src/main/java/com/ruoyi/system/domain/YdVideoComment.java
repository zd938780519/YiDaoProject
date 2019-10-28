package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 视频评论表
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdVideoComment {
    /** 评论id */
    private	long id               ;
    /** 评论类别 1：评论视频；2：回复评论 */
    private	int commentType     ;
    /** 被评论对象id */
    private	long commentObject   ;
    /**  热度*/
    private	int heatNum         ;
    /** 用户表外键 */
    private	long userId          ;
    /** 昵称 */
    private	String name             ;
    /** 头像路径 */
    private	String photoPath       ;
    /** 评论内容 */
    private	String content          ;
    /** 评论时间 */
    private Date time             ;
    /** 评论回复数量 */
    private	int replyNum        ;
    private	Date createTime      ;
    private	Date updateTime      ;
    private	int status           ;
    private	String other1           ;
    private	String other2           ;
    /** 是否点赞 */
    private int isFabulous;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public long getCommentObject() {
        return commentObject;
    }

    public void setCommentObject(long commentObject) {
        this.commentObject = commentObject;
    }

    public int getHeatNum() {
        return heatNum;
    }

    public void setHeatNum(int heatNum) {
        this.heatNum = heatNum;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
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

    public int getIsFabulous() {
        return isFabulous;
    }

    public void setIsFabulous(int isFabulous) {
        this.isFabulous = isFabulous;
    }

    @Override
    public String toString() {
        return "YdVideoComment{" +
                "id=" + id +
                ", commentType=" + commentType +
                ", commentObject=" + commentObject +
                ", heatNum=" + heatNum +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", replyNum=" + replyNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", isFabulous=" + isFabulous +
                '}';
    }
}
