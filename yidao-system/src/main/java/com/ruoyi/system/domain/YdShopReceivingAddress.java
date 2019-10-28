package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 购物车实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdShopReceivingAddress implements Serializable {
    private Long id;

    private Long userId;

    private String receivingAddress;

    private String locationAddress;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String detailedAddress;

    private Integer zipCode;

    private String consignee;

    private String receivingTelephone;

    private Integer sex;

    private Integer isDefault;

    private String remark;

    private Integer labelId;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String other1;

    private String other2;

    private static final long serialVersionUID = 1L;

    public YdShopReceivingAddress(Long id, Long userId, String receivingAddress, String locationAddress,
                                  BigDecimal longitude, BigDecimal latitude, String detailedAddress, Integer zipCode,
                                  String consignee, String receivingTelephone, Integer sex, Integer isDefault, String remark, Integer labelId) {
        this.id = id;
        this.userId = userId;
        this.receivingAddress = receivingAddress;
        this.locationAddress = locationAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.detailedAddress = detailedAddress;
        this.zipCode = zipCode;
        this.consignee = consignee;
        this.receivingTelephone = receivingTelephone;
        this.sex = sex;
        this.isDefault = isDefault;
        this.remark = remark;
        this.labelId = labelId;
    }

    public YdShopReceivingAddress(Long userId, String receivingAddress, String locationAddress,
                                  BigDecimal longitude, BigDecimal latitude, String detailedAddress,
                                  String consignee, String receivingTelephone, Integer sex, Integer isDefault, Integer labelId) {
        this.userId = userId;
        this.receivingAddress = receivingAddress;
        this.locationAddress = locationAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.detailedAddress = detailedAddress;
        this.consignee = consignee;
        this.receivingTelephone = receivingTelephone;
        this.sex = sex;
        this.isDefault = isDefault;
        this.labelId = labelId;
    }



    public YdShopReceivingAddress() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress == null ? null : receivingAddress.trim();
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress == null ? null : locationAddress.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress == null ? null : detailedAddress.trim();
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
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
        sb.append(", userId=").append(userId);
        sb.append(", receivingAddress=").append(receivingAddress);
        sb.append(", locationAddress=").append(locationAddress);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", detailedAddress=").append(detailedAddress);
        sb.append(", zipCode=").append(zipCode);
        sb.append(", consignee=").append(consignee);
        sb.append(", receivingTelephone=").append(receivingTelephone);
        sb.append(", sex=").append(sex);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", remark=").append(remark);
        sb.append(", labelId=").append(labelId);
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