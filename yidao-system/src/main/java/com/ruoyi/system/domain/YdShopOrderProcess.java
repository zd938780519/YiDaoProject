package com.ruoyi.system.domain;

import java.util.Date;

/**
 * 订单进度表实体
 */
public class YdShopOrderProcess {
    private long id ;
    /** 订单ID*/
    private long soId;
    /** 进度备注内容 */
    private String content;
    /** 理由 */
    private String reason;
    /** 进度状态 */
    private int orderStatus;
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

    public long getSoId() {
        return soId;
    }

    public void setSoId(long soId) {
        this.soId = soId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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
        return "YdShopOrderProcess{" +
                "id=" + id +
                ", soId=" + soId +
                ", content='" + content + '\'' +
                ", reason='" + reason + '\'' +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}
