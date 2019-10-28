package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 订单实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdShopOrders {
    /**  主键  */
    private long id                               ;
    /**  用户id  */
    private long userId                          ;
    /**  店铺id  */
    private long shopId                          ;
    /**  收货地址id  */
    private long raId                            ;
    /**   收货地址完整信息 */
    private String receivingAddressCompleteInfo  ;
    /**  订单号  */
    private String orderFormNum                   ;
    /**  配送方式  */
    private String distributionModeType           ;
    /**  配送方式显示值  */
    private String distributionModeShow           ;
    /**  期望配送日期  */
    private Date expectDistributionTime         ;
    /**   发票类型 */
    private int invoiceType                     ;
    /**  发票类型显示值  */
    private String invoiceTypeShow                ;
    /**  发票抬头类型 1：个人；2：单位  */
    private int invoicePayableType             ;
    /**   发票抬头类型显示值 */
    private String invoicePayableTypeShow        ;
    /**  收票人手机  */
    private String invoiceReceiverPhone           ;
    /**  收票人邮箱  */
    private String invoiceReceiverEmail           ;
    /**  纳税人  */
    private String taxpayer                         ;
    /**  纳税人识别号  */
    private String taxpayerIdentificationNumber   ;
    /**  发票内容类别 1：商品明细；2：商品类别  */
    private int invoiceContentType             ;
    /**  发票内容类别显示值  */
    private String invoiceContentTypeShow        ;
    /**  总价  */
    private double totalPrice                      ;
    /** 总积分 */
    private int totalIntegral;
    /**  优惠券抵扣金额  */
    private double couponMoney                     ;
    /** 优惠券id */
    private long voucherId;
    /**  活动抵扣金额  */
    private double activityMoney                   ;
    /**  积分抵扣金额  */
    private double integralMoney                   ;
    /**  运费  */
    private double freightMoney                    ;
    /**   应付款金额 */
    private double amountPayable                   ;
    /** 应付积分 */
    private int integralPayable;
    /**  下单时间  */
    private String placeOrderTime                 ;
    /**  支付方式  */
    private int payWay                          ;
    /**  支付方式显示值  */
    private String payWayShow                     ;
    /**  支付时间  */
    private Date payTime                         ;
    /**  运单号  */
    private String freightBillNo                  ;
    /**  备注 */
    private String remark;
    /** 用户流水号 */
    long ufaId;
    /**  创建时间  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime	        ;
    /**  最后一次修改时间  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private	Date updateTime	        ;
    /**  状态 -1：逻辑删除  */
    private	int status	              ;
    /**  备用字段1  */
    private	String other1	            ;
    /**  备用字段2  */
    private	String other2	            ;
    /**  用户名 */
    private String userName;
    /**  订单状态*/
    private int orderStatus;
    /** 邮编 */
    private int zipCode;
    /**  收货地址 */
    private String receivingAddress;
    /** 收货人 */
    private String consignee;
    /**  收货人手机号 */
    private String receivingTelephone;
    /** 订单状态 */
    private int type;
    /** 订单商品 */
    private List<HashMap<String,Object>> goods;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getRaId() {
        return raId;
    }

    public void setRaId(long raId) {
        this.raId = raId;
    }

    public String getReceivingAddressCompleteInfo() {
        return receivingAddressCompleteInfo;
    }

    public void setReceivingAddressCompleteInfo(String receivingAddressCompleteInfo) {
        this.receivingAddressCompleteInfo = receivingAddressCompleteInfo;
    }

    public String getOrderFormNum() {
        return orderFormNum;
    }

    public void setOrderFormNum(String orderFormNum) {
        this.orderFormNum = orderFormNum;
    }

    public String getDistributionModeType() {
        return distributionModeType;
    }

    public void setDistributionModeType(String distributionModeType) {
        this.distributionModeType = distributionModeType;
    }

    public String getDistributionModeShow() {
        return distributionModeShow;
    }

    public void setDistributionModeShow(String distributionModeShow) {
        this.distributionModeShow = distributionModeShow;
    }

    public Date getExpectDistributionTime() {
        return expectDistributionTime;
    }

    public void setExpectDistributionTime(Date expectDistributionTime) {
        this.expectDistributionTime = expectDistributionTime;
    }

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTypeShow() {
        return invoiceTypeShow;
    }

    public void setInvoiceTypeShow(String invoiceTypeShow) {
        this.invoiceTypeShow = invoiceTypeShow;
    }

    public int getInvoicePayableType() {
        return invoicePayableType;
    }

    public void setInvoicePayableType(int invoicePayableType) {
        this.invoicePayableType = invoicePayableType;
    }

    public String getInvoicePayableTypeShow() {
        return invoicePayableTypeShow;
    }

    public void setInvoicePayableTypeShow(String invoicePayableTypeShow) {
        this.invoicePayableTypeShow = invoicePayableTypeShow;
    }

    public String getInvoiceReceiverPhone() {
        return invoiceReceiverPhone;
    }

    public void setInvoiceReceiverPhone(String invoiceReceiverPhone) {
        this.invoiceReceiverPhone = invoiceReceiverPhone;
    }

    public String getInvoiceReceiverEmail() {
        return invoiceReceiverEmail;
    }

    public void setInvoiceReceiverEmail(String invoiceReceiverEmail) {
        this.invoiceReceiverEmail = invoiceReceiverEmail;
    }

    public String getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(String taxpayer) {
        this.taxpayer = taxpayer;
    }

    public String getTaxpayerIdentificationNumber() {
        return taxpayerIdentificationNumber;
    }

    public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
    }

    public int getInvoiceContentType() {
        return invoiceContentType;
    }

    public void setInvoiceContentType(int invoiceContentType) {
        this.invoiceContentType = invoiceContentType;
    }

    public String getInvoiceContentTypeShow() {
        return invoiceContentTypeShow;
    }

    public void setInvoiceContentTypeShow(String invoiceContentTypeShow) {
        this.invoiceContentTypeShow = invoiceContentTypeShow;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(double couponMoney) {
        this.couponMoney = couponMoney;
    }

    public double getActivityMoney() {
        return activityMoney;
    }

    public void setActivityMoney(double activityMoney) {
        this.activityMoney = activityMoney;
    }

    public double getIntegralMoney() {
        return integralMoney;
    }

    public void setIntegralMoney(double integralMoney) {
        this.integralMoney = integralMoney;
    }

    public double getFreightMoney() {
        return freightMoney;
    }

    public void setFreightMoney(double freightMoney) {
        this.freightMoney = freightMoney;
    }

    public double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public String getPlaceOrderTime() {
        return placeOrderTime;
    }

    public void setPlaceOrderTime(String placeOrderTime) {
        this.placeOrderTime = placeOrderTime;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getPayWayShow() {
        return payWayShow;
    }

    public void setPayWayShow(String payWayShow) {
        this.payWayShow = payWayShow;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getFreightBillNo() {
        return freightBillNo;
    }

    public void setFreightBillNo(String freightBillNo) {
        this.freightBillNo = freightBillNo;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUfaId() {
        return ufaId;
    }

    public void setUfaId(long ufaId) {
        this.ufaId = ufaId;
    }

    public long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(long voucherId) {
        this.voucherId = voucherId;
    }

    public int getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(int totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public int getIntegralPayable() {
        return integralPayable;
    }

    public void setIntegralPayable(int integralPayable) {
        this.integralPayable = integralPayable;
    }

    public List<HashMap<String, Object>> getGoods() {
        return goods;
    }

    public void setGoods(List<HashMap<String, Object>> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "YdShopOrders{" +
                "id=" + id +
                ", userId=" + userId +
                ", shopId=" + shopId +
                ", raId=" + raId +
                ", receivingAddressCompleteInfo='" + receivingAddressCompleteInfo + '\'' +
                ", orderFormNum='" + orderFormNum + '\'' +
                ", distributionModeType='" + distributionModeType + '\'' +
                ", distributionModeShow='" + distributionModeShow + '\'' +
                ", expectDistributionTime=" + expectDistributionTime +
                ", invoiceType=" + invoiceType +
                ", invoiceTypeShow='" + invoiceTypeShow + '\'' +
                ", invoicePayableType=" + invoicePayableType +
                ", invoicePayableTypeShow='" + invoicePayableTypeShow + '\'' +
                ", invoiceReceiverPhone='" + invoiceReceiverPhone + '\'' +
                ", invoiceReceiverEmail='" + invoiceReceiverEmail + '\'' +
                ", taxpayer='" + taxpayer + '\'' +
                ", taxpayerIdentificationNumber='" + taxpayerIdentificationNumber + '\'' +
                ", invoiceContentType=" + invoiceContentType +
                ", invoiceContentTypeShow='" + invoiceContentTypeShow + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalIntegral=" + totalIntegral +
                ", couponMoney=" + couponMoney +
                ", voucherId=" + voucherId +
                ", activityMoney=" + activityMoney +
                ", integralMoney=" + integralMoney +
                ", freightMoney=" + freightMoney +
                ", amountPayable=" + amountPayable +
                ", integralPayable=" + integralPayable +
                ", placeOrderTime='" + placeOrderTime + '\'' +
                ", payWay=" + payWay +
                ", payWayShow='" + payWayShow + '\'' +
                ", payTime=" + payTime +
                ", freightBillNo='" + freightBillNo + '\'' +
                ", remark='" + remark + '\'' +
                ", ufaId=" + ufaId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", userName='" + userName + '\'' +
                ", orderStatus=" + orderStatus +
                ", zipCode=" + zipCode +
                ", receivingAddress='" + receivingAddress + '\'' +
                ", consignee='" + consignee + '\'' +
                ", receivingTelephone='" + receivingTelephone + '\'' +
                ", type=" + type +
                ", goods=" + goods +
                '}';
    }
}
