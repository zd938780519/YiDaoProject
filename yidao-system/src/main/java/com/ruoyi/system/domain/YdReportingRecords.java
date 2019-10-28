package com.ruoyi.system.domain;

import java.util.Date;
import java.util.List;

/**
 * 举报记录实体
 */
public class YdReportingRecords {
    /**  主键  */
    private long id                     ;
    /**  举报者id  */
    private long fromId                ;
    /**  被被举报者id  */
    private long toId                  ;
    /**  举报类型  */
    private int reportType            ;
    /**  举报类型显示值  */
    private String reportShow            ;
    /**  举报理由  */
    private String reportReason          ;
    /**   处理状态 */
    private int processingStatus      ;
    /**   处理状态显示值 */
    private String processingStatusShow ;
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
    /**  被举报者名称 */
    private String name;
    /**  已警告次数 */
    private int num;
    /**  申诉内容 */
    private String content;
    /**  账号状态*/
    private int accountStatus;
    /**  举报图片*/
    private List<YdReportingRecordsImage> images;
    /** 举报者名称 */
    private String fname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public String getReportShow() {
        return reportShow;
    }

    public void setReportShow(String reportShow) {
        this.reportShow = reportShow;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public int getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(int processingStatus) {
        this.processingStatus = processingStatus;
    }

    public String getProcessingStatusShow() {
        return processingStatusShow;
    }

    public void setProcessingStatusShow(String processingStatusShow) {
        this.processingStatusShow = processingStatusShow;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<YdReportingRecordsImage> getImages() {
        return images;
    }

    public void setImages(List<YdReportingRecordsImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "YdReportingRecords{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", reportType=" + reportType +
                ", reportShow='" + reportShow + '\'' +
                ", reportReason='" + reportReason + '\'' +
                ", processingStatus=" + processingStatus +
                ", processingStatusShow='" + processingStatusShow + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", content='" + content + '\'' +
                ", accountStatus=" + accountStatus +
                ", images=" + images +
                ", fname='" + fname + '\'' +
                '}';
    }
}
