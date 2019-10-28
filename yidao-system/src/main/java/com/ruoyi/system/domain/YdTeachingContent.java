package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

/**
 * 教学内容实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YdTeachingContent {
    /**  主键  */
    private long id                     ;
    /**  用户表外键  */
    private long userId                ;
    /**  菜名  */
    private String dishName              ;
    /**   是否精选 */
    private int isSelected;
    /**  浏览量  */
    private int browseNum             ;
    /**  收藏量  */
    private int collectionNum         ;
    /**  热度  */
    private int heatNum               ;
    /**  评论数  */
    private int commentNum            ;
    /**  转发数 */
    private int forwardNum;
    /**  效果视频路径  */
    private String resultVideoPath      ;
    /**  缩略图路径  */
    private String thumbnailPath         ;
    /**  效果图路径  */
    private String resultPicturePath    ;
    /**  说明文字（心得）  */
    private String experience             ;
    /**  技巧  */
    private String skill                  ;
    /**  烹饪时间  */
    private int cookingTime           ;
    /**  烹饪时间显示值 */
    private String cookingTimeShow      ;
    /**  口味   */
    private int flavor                 ;
    /**  口味显示值 */
    private String flavorShow            ;
    /**  难度  */
    private int difficulty             ;
    /**  难度显示值  */
    private String difficultyShow        ;
    /**  状态类型 1：草稿 2：发布  */
    private int stateType             ;
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
    /**  起始位置偏移量  */
    private int currIndex;
    /**  页大小    */
    private int pageSize;
    /**  是否点赞   */
    private int isFabulous;
    /**   材料     */
    private List<YdTeachingMaterials> materials;

    public List<YdTeachingMaterials> getMaterials() {
        return materials;
    }

    public void setMaterials(List<YdTeachingMaterials> materials) {
        this.materials = materials;
    }

    /** 主料 */
    private List<YdTeachingMaterials> mainMaterial ;
    /** 辅料 */
    private List<YdTeachingMaterials> accessories;
    /** 步骤 */
    private List<YdTeachingStep> steps;

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

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(int browseNum) {
        this.browseNum = browseNum;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getHeatNum() {
        return heatNum;
    }

    public void setHeatNum(int heatNum) {
        this.heatNum = heatNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getResultVideoPath() {
        return resultVideoPath;
    }

    public void setResultVideoPath(String resultVideoPath) {
        this.resultVideoPath = resultVideoPath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getResultPicturePath() {
        return resultPicturePath;
    }

    public void setResultPicturePath(String resultPicturePath) {
        this.resultPicturePath = resultPicturePath;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getCookingTimeShow() {
        return cookingTimeShow;
    }

    public void setCookingTimeShow(String cookingTimeShow) {
        this.cookingTimeShow = cookingTimeShow;
    }

    public int getFlavor() {
        return flavor;
    }

    public void setFlavor(int flavor) {
        this.flavor = flavor;
    }

    public String getFlavorShow() {
        return flavorShow;
    }

    public void setFlavorShow(String flavorShow) {
        this.flavorShow = flavorShow;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficultyShow() {
        return difficultyShow;
    }

    public void setDifficultyShow(String difficultyShow) {
        this.difficultyShow = difficultyShow;
    }

    public int getStateType() {
        return stateType;
    }

    public void setStateType(int stateType) {
        this.stateType = stateType;
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

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public int getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(int currIndex) {
        this.currIndex = currIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIsFabulous() {
        return isFabulous==1?1:-1;
    }

    public void setIsFabulous(int isFabulous) {
        this.isFabulous = isFabulous;
    }

    public int getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(int forwardNum) {
        this.forwardNum = forwardNum;
    }

    public List<YdTeachingMaterials> getMainMaterial() {
        return mainMaterial;
    }

    public void setMainMaterial(List<YdTeachingMaterials> mainMaterial) {
        this.mainMaterial = mainMaterial;
    }

    public List<YdTeachingMaterials> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<YdTeachingMaterials> accessories) {
        this.accessories = accessories;
    }

    public List<YdTeachingStep> getSteps() {
        return steps;
    }

    public void setSteps(List<YdTeachingStep> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "YdTeachingContent{" +
                "id=" + id +
                ", userId=" + userId +
                ", dishName='" + dishName + '\'' +
                ", isSelected=" + isSelected +
                ", browseNum=" + browseNum +
                ", collectionNum=" + collectionNum +
                ", heatNum=" + heatNum +
                ", commentNum=" + commentNum +
                ", forwardNum=" + forwardNum +
                ", resultVideoPath='" + resultVideoPath + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", resultPicturePath='" + resultPicturePath + '\'' +
                ", experience='" + experience + '\'' +
                ", skill='" + skill + '\'' +
                ", cookingTime=" + cookingTime +
                ", cookingTimeShow='" + cookingTimeShow + '\'' +
                ", flavor=" + flavor +
                ", flavorShow='" + flavorShow + '\'' +
                ", difficulty=" + difficulty +
                ", difficultyShow='" + difficultyShow + '\'' +
                ", stateType=" + stateType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", currIndex=" + currIndex +
                ", pageSize=" + pageSize +
                ", isFabulous=" + isFabulous +
                ", mainMaterial=" + mainMaterial +
                ", accessories=" + accessories +
                ", steps=" + steps +
                '}';
    }
}
