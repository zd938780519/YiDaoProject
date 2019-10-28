package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdFoodDistributionInfo implements Serializable {
    private Integer id;
    /**
     *用户表外键
     */
    private Integer userId;
    /**
     *收货地址
     */
    private String receivingAddress;
    /**
     *收货人
     */
    private String consignee;
    /**
     *收货电话
     */
    private String receivingTelephone;
    /**
     *是否是默认地址(0:否 ，1：是)
     */
    private Integer isDefault;
    /**
     *备注
     */
    private String remark;
    /**
     *经度
     */
    private String longitude;
    /**
     *纬度
     */
    private String latitude;
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
    /**
     * 是否超出配送距离 0：否 1：是
     */
    private Integer isBeyond = 0;

    private String other1;

    private String other2;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress == null ? null : receivingAddress.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getReceivingTelephone() {
        return receivingTelephone;
    }

    public void setReceivingTelephone(String receivingTelephone) {
        this.receivingTelephone = receivingTelephone == null ? null : receivingTelephone.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
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

    public Integer getIsBeyond() {
        return isBeyond;
    }

    public void setIsBeyond(Integer isBeyond) {
        this.isBeyond = isBeyond;
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
        sb.append(", userId=").append(userId);
        sb.append(", receivingAddress=").append(receivingAddress);
        sb.append(", consignee=").append(consignee);
        sb.append(", receivingTelephone=").append(receivingTelephone);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", remark=").append(remark);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
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