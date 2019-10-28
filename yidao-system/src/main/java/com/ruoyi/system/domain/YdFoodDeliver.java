package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdFoodDeliver implements Serializable {
    private Integer id;
    /**
     *名称
     */
    private String name;
    /**
     *联系电话
     */
    private String deliverMobile;
    /**
     *登录密码
     */
    private String password;
    /**
     *平均评分
     */
    private BigDecimal avgScore;
    /**
     *最近一个月完成的订单量
     */
    private Integer orderQuantityMonth;
    /**
     *总完成的订单量
     */
    private Integer orderQuantityTotal;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *最后一次修改时间
     */
    private Date updateTime;
    /**
     *状态 -1:逻辑删除
     */
    private Integer status;

    private String other1;

    private String other2;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDeliverMobile() {
        return deliverMobile;
    }

    public void setDeliverMobile(String deliverMobile) {
        this.deliverMobile = deliverMobile == null ? null : deliverMobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public BigDecimal getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(BigDecimal avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getOrderQuantityMonth() {
        return orderQuantityMonth;
    }

    public void setOrderQuantityMonth(Integer orderQuantityMonth) {
        this.orderQuantityMonth = orderQuantityMonth;
    }

    public Integer getOrderQuantityTotal() {
        return orderQuantityTotal;
    }

    public void setOrderQuantityTotal(Integer orderQuantityTotal) {
        this.orderQuantityTotal = orderQuantityTotal;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1 == null ? null : other1.trim();
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2 == null ? null : other2.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", deliverMobile=").append(deliverMobile);
        sb.append(", password=").append(password);
        sb.append(", avgScore=").append(avgScore);
        sb.append(", orderQuantityMonth=").append(orderQuantityMonth);
        sb.append(", orderQuantityTotal=").append(orderQuantityTotal);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", other1=").append(other1);
        sb.append(", other2=").append(other2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}