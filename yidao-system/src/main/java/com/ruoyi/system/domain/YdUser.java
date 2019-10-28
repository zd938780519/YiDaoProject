package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.json.CustomStringDeserializer;

import java.util.List;

/**
 * 用户表
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdUser extends BaseEntity {

    /** id  */
    private long id;
    /**  用户名 */
    private String userName;
    /**  是否是会员 */
    private int isVip;
    /**  头像路径 */
    private String photoPath;
    /**  手机号 */
    private String phoneNum;
    /**  简介  */
    private String briefIntroduction    ;
    /**  性别  */
    private String sex                   ;
    /**   性别显示值 */
    private String sexShow              ;
    /**  生日  */
    private String birthday              ;
    /**   关注数 */
    private String follow                ;
    /**  拜师数  */
    private String apprentice            ;
    /**  封面图片路径  */
    private String coverPhotoPath      ;
    /**  粉丝数  */
    private String fansNum              ;
    /**  食趣号  */
    private String foodInterestNum     ;
    /**   标语（排行） */
    private String slogan                ;
    /**  作品数  */
    private String worksNum             ;
    /**  教学数  */
    private String teachingNum          ;
    /**   乡味数 */
    private String ruralFlavorNum      ;
    /**  当前地址  */
    private String currentAddress       ;
    /**  家乡地址  */
    private String hometown              ;
    /** 积分 */
    private int integral;
    /**  赠送的积分 */
    private int giftIntegral;
    /** 是否商家 0：不是 1：是 */
    private int isPeripheryRestaurant;

    /**  是否已关注 */
    private int isFollow;
    /**  是否已拜师 */
    private int isApprentice;
    /**  是否拉黑*/
    private int isPullBlack;
    /**  视频地址*/
    private List<YdVideo> phVideoPath;

    public List<YdVideo> getPhVideoPath() {
        return phVideoPath;
    }

    public void setPhVideoPath(List<YdVideo> phVideoPath) {
        this.phVideoPath = phVideoPath;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getIsVip() {
        return isVip;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexShow() {
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getApprentice() {
        return apprentice;
    }

    public void setApprentice(String apprentice) {
        this.apprentice = apprentice;
    }

    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    public void setCoverPhotoPath(String coverPhotoPath) {
        this.coverPhotoPath = coverPhotoPath;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getFoodInterestNum() {
        return foodInterestNum;
    }

    public void setFoodInterestNum(String foodInterestNum) {
        this.foodInterestNum = foodInterestNum;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getWorksNum() {
        return worksNum;
    }

    public void setWorksNum(String worksNum) {
        this.worksNum = worksNum;
    }

    public String getTeachingNum() {
        return teachingNum;
    }

    public void setTeachingNum(String teachingNum) {
        this.teachingNum = teachingNum;
    }

    public String getRuralFlavorNum() {
        return ruralFlavorNum;
    }

    public void setRuralFlavorNum(String ruralFlavorNum) {
        this.ruralFlavorNum = ruralFlavorNum;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getIsApprentice() {
        return isApprentice;
    }

    public void setIsApprentice(int isApprentice) {
        this.isApprentice = isApprentice;
    }

    public int getIsPullBlack() {
        return isPullBlack;
    }

    public void setIsPullBlack(int isPullBlack) {
        this.isPullBlack = isPullBlack;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getGiftIntegral() {
        return giftIntegral;
    }

    public void setGiftIntegral(int giftIntegral) {
        this.giftIntegral = giftIntegral;
    }

    public int getIsPeripheryRestaurant() {
        return isPeripheryRestaurant;
    }

    public void setIsPeripheryRestaurant(int isPeripheryRestaurant) {
        this.isPeripheryRestaurant = isPeripheryRestaurant;
    }

    @Override
    public String toString() {
        return "YdUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", isVip=" + isVip +
                ", photoPath='" + photoPath + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", sex='" + sex + '\'' +
                ", sexShow='" + sexShow + '\'' +
                ", birthday='" + birthday + '\'' +
                ", follow='" + follow + '\'' +
                ", apprentice='" + apprentice + '\'' +
                ", coverPhotoPath='" + coverPhotoPath + '\'' +
                ", fansNum='" + fansNum + '\'' +
                ", foodInterestNum='" + foodInterestNum + '\'' +
                ", slogan='" + slogan + '\'' +
                ", worksNum='" + worksNum + '\'' +
                ", teachingNum='" + teachingNum + '\'' +
                ", ruralFlavorNum='" + ruralFlavorNum + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                ", hometown='" + hometown + '\'' +
                ", integral=" + integral +
                ", giftIntegral=" + giftIntegral +
                ", isPeripheryRestaurant=" + isPeripheryRestaurant +
                ", isFollow=" + isFollow +
                ", isApprentice=" + isApprentice +
                ", isPullBlack=" + isPullBlack +
                ", phVideoPath=" + phVideoPath +
                '}';
    }
}
