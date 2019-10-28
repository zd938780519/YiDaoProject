package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdFoodOrderDetail implements Serializable {
    private Integer id;
    /**
     *订单ID
     */
    private Integer foId;
    /**
     *用户id
     */
    private Integer userId;
    /**
     *用户名
     */
    private String userName;
    /**
     *用户联系电话
     */
    private String userMobile;
    /**
     *配送信息ID（历史数据可能会因为配送地址的修改而匹配不到）
     */
    private Integer fdiId;
    /**
     *配送信息完整地址
     */
    private String distributionShow;
    /**
     * 收货人
     */
    private String consignee;
    /**
     *收货人联系方式
     */
    private String receivingTelephone;
    /**
     *卖方id
     */
    private Integer sellerId;
    /**
     *卖方名字
     */
    private String sellerShopname;
    /**
     *卖方联系电话
     */
    private String sellerMobile;
    /**
     *卖方详细地址
     */
    private String sellerAddress;
    /**
     *送餐员ID
     */
    private Integer deliverId;
    /**
     *送餐员姓名
     */
    private String deliverName;
    /**
     *送餐员联系电话
     */
    private String deliverMobile;
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

    public Integer getFoId() {
        return foId;
    }

    public void setFoId(Integer foId) {
        this.foId = foId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public Integer getFdiId() {
        return fdiId;
    }

    public void setFdiId(Integer fdiId) {
        this.fdiId = fdiId;
    }

    public String getDistributionShow() {
        return distributionShow;
    }

    public void setDistributionShow(String distributionShow) {
        this.distributionShow = distributionShow == null ? null : distributionShow.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getReceivingTelephone() {
        return receivingTelephone;
    }

    public void setReceivingTelephone(String receivingTelephone) {
        this.receivingTelephone = receivingTelephone;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerShopname() {
        return sellerShopname;
    }

    public void setSellerShopname(String sellerShopname) {
        this.sellerShopname = sellerShopname == null ? null : sellerShopname.trim();
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile == null ? null : sellerMobile.trim();
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress == null ? null : sellerAddress.trim();
    }

    public Integer getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName == null ? null : deliverName.trim();
    }

    public String getDeliverMobile() {
        return deliverMobile;
    }

    public void setDeliverMobile(String deliverMobile) {
        this.deliverMobile = deliverMobile == null ? null : deliverMobile.trim();
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
        return "YdFoodOrderDetail{" +
                "id=" + id +
                ", foId=" + foId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", fdiId=" + fdiId +
                ", distributionShow='" + distributionShow + '\'' +
                ", consignee='" + consignee + '\'' +
                ", receivingTelephone='" + receivingTelephone + '\'' +
                ", sellerId=" + sellerId +
                ", sellerShopname='" + sellerShopname + '\'' +
                ", sellerMobile='" + sellerMobile + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", deliverId=" + deliverId +
                ", deliverName='" + deliverName + '\'' +
                ", deliverMobile='" + deliverMobile + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                '}';
    }
}