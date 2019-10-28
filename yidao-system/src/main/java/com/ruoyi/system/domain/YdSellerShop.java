package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 周边店铺实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdSellerShop implements Serializable {
    /**
     *店铺id
     */
    private Integer id;
    /**
     *店铺名称
     */
    private String shopName;
    /**
     *店铺logo
     */
    private String logo;
    /**
     *其他logo*
     */
    private String otherLogo;
    /**
     *店铺地址
     */
    private String address;
    /**
     *经度
     */
    private String longitude;
    /**
     *纬度
     */
    private String latitude;
    /**
     *是否外卖店（0：否，1：是）
     */
    private Integer isTakeOut;
    /**
     *起送金额
     */
    private BigDecimal startMoney;
    /**
     *电话/预约电话
     */
    private String phone;
    /**
     *公告/广告
     */
    private String advertisingSlogans;
    /**
     *营业时间（开始时间）
     */
    private Date timeOn;
    /**
     *营业时间（结束时间）
     */
    private Date timeOff;
    /**
     *是否有活动 0：否 1：是
     */
    private Integer isActivity;
    /**
     *随时退（0：否，1：是）
     */
    private Integer lableAnyTime;
    /**
     *免预约（0：否，1：是）
     */
    private Integer lableFreeAppiontment;
    /**
     *健康认证（0：否，1：是）
     */
    private Integer lableHealth;
    /**
     *实名认证（0：否，1：是）
     */
    private Integer lableRealName;
    /**
     *月售数量
     */
    private Integer sellCountMonth;
    /**
     *总售数量
     */
    private Integer sellCount;
    /**
     *关注数
     */
    private Integer follow;
    /**
     *好评率/评分
     */
    private BigDecimal rateOfAcclaim;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *最后一次修改时间
     */
    private Date updateTime;
    /**
     *状态 -1：逻辑删除
     */
    private Integer status;

    private String other1;

    private String other2;

    /**
     * 与用户的距离
     */
    private String distance;

    /**
     * 商品集合
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<YdSellerHotGoods> hotGoodsList;

    SimpleDateFormat timeOnAndOff = new SimpleDateFormat("HH:mm");

    private static final long serialVersionUID = 1L;

    public YdSellerShop(Integer id, String shopName, String logo, String otherLogo, String address, String longitude, String latitude, Integer isTakeOut, BigDecimal startMoney, String phone, String advertisingSlogans, Date timeOn, Date timeOff, Integer isActivity, Integer lableAnyTime, Integer lableFreeAppiontment, Integer lableHealth, Integer lableRealName, Integer sellCountMonth, Integer sellCount, Integer follow, BigDecimal rateOfAcclaim, Date createTime, Date updateTime, Integer status, String other1, String other2) {
        this.id = id;
        this.shopName = shopName;
        this.logo = logo;
        this.otherLogo = otherLogo;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isTakeOut = isTakeOut;
        this.startMoney = startMoney;
        this.phone = phone;
        this.advertisingSlogans = advertisingSlogans;
        this.timeOn = timeOn;
        this.timeOff = timeOff;
        this.isActivity = isActivity;
        this.lableAnyTime = lableAnyTime;
        this.lableFreeAppiontment = lableFreeAppiontment;
        this.lableHealth = lableHealth;
        this.lableRealName = lableRealName;
        this.sellCountMonth = sellCountMonth;
        this.sellCount = sellCount;
        this.follow = follow;
        this.rateOfAcclaim = rateOfAcclaim;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.other1 = other1;
        this.other2 = other2;
    }

    public YdSellerShop() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getOtherLogo() {
        return otherLogo;
    }

    public void setOtherLogo(String otherLogo) {
        this.otherLogo = otherLogo == null ? null : otherLogo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public Integer getIsTakeOut() {
        return isTakeOut;
    }

    public void setIsTakeOut(Integer isTakeOut) {
        this.isTakeOut = isTakeOut;
    }

    public BigDecimal getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(BigDecimal startMoney) {
        this.startMoney = startMoney;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAdvertisingSlogans() {
        return advertisingSlogans;
    }

    public void setAdvertisingSlogans(String advertisingSlogans) {
        this.advertisingSlogans = advertisingSlogans == null ? null : advertisingSlogans.trim();
    }

    public String getTimeOn() {
        if(null !=timeOn && !"".equals(timeOn))
            return timeOnAndOff.format(timeOn);
        return null;
    }

    public void setTimeOn(Date timeOn) {
        this.timeOn = timeOn;
    }

    public String getTimeOff() {
        if(null !=timeOff && !"".equals(timeOff))
            return timeOnAndOff.format(timeOff);
        return null;
    }

    public void setTimeOff(Date timeOff) {
        this.timeOff = timeOff;
    }

    public Integer getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Integer isActivity) {
        this.isActivity = isActivity;
    }

    public Integer getLableAnyTime() {
        return lableAnyTime;
    }

    public void setLableAnyTime(Integer lableAnyTime) {
        this.lableAnyTime = lableAnyTime;
    }

    public Integer getLableFreeAppiontment() {
        return lableFreeAppiontment;
    }

    public void setLableFreeAppiontment(Integer lableFreeAppiontment) {
        this.lableFreeAppiontment = lableFreeAppiontment;
    }

    public Integer getLableHealth() {
        return lableHealth;
    }

    public void setLableHealth(Integer lableHealth) {
        this.lableHealth = lableHealth;
    }

    public Integer getLableRealName() {
        return lableRealName;
    }

    public void setLableRealName(Integer lableRealName) {
        this.lableRealName = lableRealName;
    }

    public Integer getSellCountMonth() {
        return sellCountMonth;
    }

    public void setSellCountMonth(Integer sellCountMonth) {
        this.sellCountMonth = sellCountMonth;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public BigDecimal getRateOfAcclaim() {
        return rateOfAcclaim;
    }

    public void setRateOfAcclaim(BigDecimal rateOfAcclaim) {
        this.rateOfAcclaim = rateOfAcclaim;
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

    public String getDistance() {
        if("".equals(this.distance) || null == this.distance)
            return "暂无数据";
        return Double.parseDouble(distance) >= 1000 ? String.format("%.2f",Double.parseDouble(distance)/1000) + "km" : String.format("%.2f",Double.parseDouble(distance)) + "m" ;
    }

    public void setDistance(Double distance) {
        this.distance = distance.toString();
    }

    public List<YdSellerHotGoods> getHotGoodsList() {
        return hotGoodsList;
    }

    public void setHotGoodsList(List<YdSellerHotGoods> hotGoodsList) {
        this.hotGoodsList = hotGoodsList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopName=").append(shopName);
        sb.append(", logo=").append(logo);
        sb.append(", otherLogo=").append(otherLogo);
        sb.append(", address=").append(address);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", isTakeOut=").append(isTakeOut);
        sb.append(", startMoney=").append(startMoney);
        sb.append(", phone=").append(phone);
        sb.append(", advertisingSlogans=").append(advertisingSlogans);
        sb.append(", timeOn=").append(timeOn);
        sb.append(", timeOff=").append(timeOff);
        sb.append(", isActivity=").append(isActivity);
        sb.append(", lableAnyTime=").append(lableAnyTime);
        sb.append(", lableFreeAppiontment=").append(lableFreeAppiontment);
        sb.append(", lableHealth=").append(lableHealth);
        sb.append(", lableRealName=").append(lableRealName);
        sb.append(", sellCountMonth=").append(sellCountMonth);
        sb.append(", sellCount=").append(sellCount);
        sb.append(", follow=").append(follow);
        sb.append(", rateOfAcclaim=").append(rateOfAcclaim);
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