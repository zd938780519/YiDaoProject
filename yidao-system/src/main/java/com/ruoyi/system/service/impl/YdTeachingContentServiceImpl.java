package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.oss.OSSFileUtil;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.YdTeachingContentMapper;
import com.ruoyi.system.service.YdTeachingCommentService;
import com.ruoyi.system.service.YdTeachingContentService;
import com.ruoyi.system.service.YdTeachingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class YdTeachingContentServiceImpl implements YdTeachingContentService {
    @Autowired(required = false)
    private YdTeachingContentMapper ydTeachingContentMapper;
    @Autowired(required = false)
    private YdTeachingCommentService ydTeachingCommentService;
    @Autowired(required = false)
    private YdTeachingUserService ydTeachingUserService;

    @Override
    public List<YdTeachingContent> selectTeachingSimpleInfoByUserId(long userId, int currIndex, int pageSize) {
        return ydTeachingContentMapper.selectTeachingSimpleInfoByUserId(userId,currIndex,pageSize);
    }

    @Override
    public List<YdTeachingContent> selectAllTeachingSimpleInfoByUserId(long userId, int currIndex, int pageSize) {
        return ydTeachingContentMapper.selectAllTeachingSimpleInfoByUserId(userId,currIndex,pageSize);
    }

    @Override
    public List<YdTeachingContent> selectDraftTeachingSimpleInfoByUserId(long userId, int currIndex, int pageSize) {
        return ydTeachingContentMapper.selectDraftTeachingSimpleInfoByUserId(userId,currIndex,pageSize);
    }

    @Override
    public YdTeachingContent selectTeachingDetailedInfoByUserId(long id,long userId) {
        return ydTeachingContentMapper.selectTeachingDetailedInfoByUserId(id,userId);
    }

    @Override
    public int insertTeachingContent(YdTeachingContent ydTeachingContent) {
        return ydTeachingContentMapper.insertTeachingContent(ydTeachingContent);
    }

    @Override
    public boolean addTeachingInfo(long userId, String dishName, MultipartFile resultVideo, MultipartFile thumbnail,
                                   MultipartFile resultPicture, String experience, String skill, int cookingTime,
                                   String cookingTimeShow, int flavor, String flavorShow, int difficulty, String difficultyShow,
                                   int stateType, TeachingActivityModel model, MultipartFile[] files) {
        boolean falg = true;
        YdTeachingContent ydTeachingContent = new YdTeachingContent();
       //将图片视频放到文件服务器上
        if(thumbnail != null ){
            String fileKey = "image/teaching/thumbnail/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(thumbnail);
            String path = OSSFileUtil.upload(fileKey, thumbnail);
            ydTeachingContent.setThumbnailPath(path);
        }
        if(resultPicture != null ){
            String fileKey = "image/teaching/resultPicture/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(resultPicture);
            String path = OSSFileUtil.upload(fileKey, resultPicture);
            ydTeachingContent.setResultPicturePath(path);
        }
        if(resultVideo != null ){
            String fileKey = "video/teaching/resultVideo/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(resultVideo);
            String path = OSSFileUtil.upload(fileKey, resultVideo);
            ydTeachingContent.setResultVideoPath(path);
        }
        ydTeachingContent.setDishName(dishName);
        if(experience != null){
            ydTeachingContent.setExperience(experience);
        }
        if(skill != null){
            ydTeachingContent.setSkill(skill);
        }
        ydTeachingContent.setUserId(userId);
        ydTeachingContent.setCookingTime(cookingTime);
        ydTeachingContent.setCookingTimeShow(cookingTimeShow);
        ydTeachingContent.setFlavor(flavor);
        ydTeachingContent.setFlavorShow(flavorShow);
        ydTeachingContent.setDifficulty(difficulty);
        ydTeachingContent.setDifficultyShow(difficultyShow);
        ydTeachingContent.setStateType(stateType);
        int insertTeachingContent = this.insertTeachingContent(ydTeachingContent);//保存教学内容
        long id = ydTeachingContent.getId();
        if(insertTeachingContent != 0){
            List<YdTeachingMaterials> tms = new ArrayList();
            for (Map map:model.getMainMaterials()) {
                YdTeachingMaterials ydTeachingMaterial = new YdTeachingMaterials();
                String material = (String) map.get("material");
                String number = (String) map.get("number");
                ydTeachingMaterial.setTcId(id);
                ydTeachingMaterial.setMaterial(material);
                ydTeachingMaterial.setNumber(number);
                ydTeachingMaterial.setType(1);
                tms.add(ydTeachingMaterial);
            }
            int insertTeachingMaterials = this.insertTeachingMaterials(tms);//保存主料
            if(insertTeachingMaterials != 0){
                List<YdTeachingMaterials> tmsa = new ArrayList();
                for (Map map: model.getAssistMaterials()) {
                    YdTeachingMaterials ydTeachingMaterial = new YdTeachingMaterials();
                    String material = (String) map.get("material");
                    String number = (String) map.get("number");
                    ydTeachingMaterial.setTcId(id);
                    ydTeachingMaterial.setMaterial(material);
                    ydTeachingMaterial.setNumber(number);
                    ydTeachingMaterial.setType(2);
                    tmsa.add(ydTeachingMaterial);
                }
                int insertTeachingMaterials1 = this.insertTeachingMaterials(tmsa);//保存辅料
                if(insertTeachingMaterials1 != 0){
                    List<YdTeachingStep> tss = new ArrayList();
                    for (Map map:model.getSteps()) {
                        YdTeachingStep ydTeachingStep = new YdTeachingStep();
                        int step = Integer.parseInt(map.get("step").toString());
                        String explanatoryText = (String) map.get("explanatoryText");
                        ydTeachingStep.setTcId(id);
                        ydTeachingStep.setStep(step);
                        //将图片放到文件服务器上
                        if(files[step] != null ){
                            String fileKey = "image/teaching/step/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(files[step]);
                            String path = OSSFileUtil.upload(fileKey, files[step]);
                            ydTeachingStep.setPicturePath(path);
                        }
                        ydTeachingStep.setExplanatoryText(explanatoryText);
                        tss.add(ydTeachingStep);
                    }
                    int insertTeachingSteps = this.insertTeachingSteps(tss);//保存步骤
                    if(insertTeachingSteps != 0){
                        if(insertTeachingContent == 1){
                            falg = true;
                        }else{
                            falg = false;
                        }
                    }else{
                        falg = false;
                    }
                }else{
                    falg = false;
                }
            }else{
                falg = false;
            }
        }else{
            falg = false;
        }
        return falg;
    }

    @Override
    public int insertTeachingMaterials(List<YdTeachingMaterials> tms) {
        return ydTeachingContentMapper.insertTeachingMaterials(tms);
    }

    @Override
    public List<YdTeachingMaterials> selectTeachingMaterials(long id, int type) {
        return ydTeachingContentMapper.selectTeachingMaterials(id,type);
    }

    @Override
    public int insertTeachingSteps(List<YdTeachingStep> tss) {
        return ydTeachingContentMapper.insertTeachingSteps(tss);
    }

    @Override
    public List<YdTeachingStep> selectTeachingStep(long id) {
        return ydTeachingContentMapper.selectTeachingStep(id);
    }

    @Override
    public int deleteTeachingInfo(long id) {
        return ydTeachingContentMapper.deleteTeachingInfo(id);
    }

    @Override
    public int deleteTeachingMaterialsInfo(long id) {
        return ydTeachingContentMapper.deleteTeachingMaterialsInfo(id);
    }

    @Override
    public int deleteTeachingStepsInfo(long id) {
        return ydTeachingContentMapper.deleteTeachingStepsInfo(id);
    }

    @Override
    public int updateReleaseType(long id) {
        return ydTeachingContentMapper.updateReleaseType(id);
    }

    @Override
    public int updateForwardNum(long id) {
        return ydTeachingContentMapper.updateForwardNum(id);
    }

    @Override
    public boolean addForwardNum(YdTeachingUser ydTeachingUser) {
        YdTeachingUser ydTeachingUser1 = ydTeachingUserService.selectInfoById(ydTeachingUser);
        boolean flag = true;
        if(ydTeachingUser1 == null){
            int insertInfo = ydTeachingUserService.insertInfo(ydTeachingUser);
            if(insertInfo != 1){
                flag = false;
            }
        }else{
            int updateInfo = ydTeachingUserService.updateInfo(ydTeachingUser);
            if(updateInfo != 1){
                flag = false;
            }
        }
        if(flag){
            int addForwardNum = this.updateForwardNum(ydTeachingUser.getTcId());
            if(addForwardNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int insertBrowseNum(long id) {
        return ydTeachingContentMapper.insertBrowseNum(id);
    }

    @Override
    public int updateCollectionNum(YdTeachingContent ydTeachingContent) {
        return ydTeachingContentMapper.updateCollectionNum(ydTeachingContent);
    }

    @Override
    public boolean collection(YdTeachingUser ydTeachingUser) {
        YdTeachingUser ydTeachingUser1 = ydTeachingUserService.selectInfoById(ydTeachingUser);
        boolean flag = true;
        if(ydTeachingUser1 == null){
            int insertInfo = ydTeachingUserService.insertInfo(ydTeachingUser);
            if(insertInfo != 1){
                flag = false;
            }
        }else{
            int updateInfo = ydTeachingUserService.updateInfo(ydTeachingUser);
            if(updateInfo != 1){
                flag = false;
            }
        }
        YdTeachingContent ydTeachingContent = new YdTeachingContent();
        ydTeachingContent.setId(ydTeachingUser.getTcId());
        ydTeachingContent.setCollectionNum(ydTeachingUser.getIsCollection());
        int updateCollectionNum = this.updateCollectionNum(ydTeachingContent);
        if(updateCollectionNum != 1){
            flag = false;
        }
        return flag;
    }

    @Override
    public int updateHeatNum(YdTeachingContent ydTeachingContent) {
        return ydTeachingContentMapper.updateHeatNum(ydTeachingContent);
    }

    @Override
    public boolean wantEat(YdTeachingUser ydTeachingUser) {
        boolean flag = true;
        YdTeachingUser ydTeachingUser1 = ydTeachingUserService.selectInfoById(ydTeachingUser);
        if(ydTeachingUser1 == null){
            int insertInfo = ydTeachingUserService.insertInfo(ydTeachingUser);
            if(insertInfo != 1){
                flag = false;
            }
        }else{
            int updateInfo = ydTeachingUserService.updateInfo(ydTeachingUser);
            if(updateInfo != 1){
                flag = false;
            }
        }
        if(flag){
            YdTeachingContent ydTeaching = new YdTeachingContent();
            ydTeaching.setId(ydTeachingUser.getTcId());
            ydTeaching.setHeatNum(ydTeachingUser.getIsFabulous() == 1?1:-1);
            int updateInfo = this.updateHeatNum(ydTeaching);
            if(updateInfo != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int updateCommentNum(YdTeachingContent ydTeachingContent) {
        return ydTeachingContentMapper.updateCommentNum(ydTeachingContent);
    }

    @Override
    public int deleteRelevantInfo(long id) {
        // 删除教学信息，连锁删除相关信息
        boolean flag = true;
        int deleteTeachingInfo = this.deleteTeachingInfo(id);
        if(deleteTeachingInfo != 1){
            flag = false;
        }
        int deleteTeachingMaterialsInfo = this.deleteTeachingMaterialsInfo(id);
        if(deleteTeachingMaterialsInfo <= 0){
            flag = false;
        }
        int deleteTeachingStepsInfo = this.deleteTeachingStepsInfo(id);
        if(deleteTeachingStepsInfo <= 0){
            flag = false;
        }
        ydTeachingUserService.deleteInfoByTeachingId(id);
        List<YdTeachingComment> ydTeachingComments = ydTeachingCommentService.selectCommentInfoByIdToDelete(id,1);
        for (YdTeachingComment ydTeachingComment:ydTeachingComments) {
            ydTeachingCommentService.deleteCommentByTeaching(ydTeachingComment);
        }
        return flag?1:-1;
    }


}
