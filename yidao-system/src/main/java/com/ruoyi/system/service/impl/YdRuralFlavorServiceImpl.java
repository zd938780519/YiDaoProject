package com.ruoyi.system.service.impl;

import com.ruoyi.common.oss.OSSFileUtil;
import com.ruoyi.system.domain.YdRuralFlavor;
import com.ruoyi.system.domain.YdRuralFlavorComment;
import com.ruoyi.system.domain.YdRuralFlavorPicture;
import com.ruoyi.system.mapper.YdRuralFlavorCommentMapper;
import com.ruoyi.system.mapper.YdRuralFlavorMapper;
import com.ruoyi.system.service.YdRuralFlavorCommentService;
import com.ruoyi.system.service.YdRuralFlavorService;
import com.ruoyi.system.service.YdRuralFlavorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class YdRuralFlavorServiceImpl implements YdRuralFlavorService {
    @Autowired(required = false)
    private YdRuralFlavorMapper ydRuralFlavorMapper;
    @Autowired(required = false)
    private YdRuralFlavorCommentMapper ydRuralFlavorCommentMapper;
    @Autowired(required = false)
    private YdRuralFlavorCommentService ydRuralFlavorCommentService;
    @Autowired(required = false)
    private YdRuralFlavorUserService ydRuralFlavorUserService;
    @Override
    public boolean insertRuralFlavor(long userId, String content,int stateType, MultipartFile[] pictures,MultipartFile video) {
        boolean flag = true;
        YdRuralFlavor ydRuralFlavor = new YdRuralFlavor();
        ydRuralFlavor.setUserId(userId);
        ydRuralFlavor.setContent(content);
        ydRuralFlavor.setStateType(stateType);
        if(video != null ){
            String fileKey = "video/ruralFlavor/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(video);
            String path = OSSFileUtil.upload(fileKey, video);
            ydRuralFlavor.setVideoPath(path);
        }
        int insertRuralFlavor = ydRuralFlavorMapper.insertRuralFlavor(ydRuralFlavor);
        if(insertRuralFlavor != 1){
            flag = false;
        }
        if(pictures != null && pictures.length>0){
            List<YdRuralFlavorPicture> ydRuralFlavorPictures = new ArrayList();
            for (int i=0;i<pictures.length;i++){
                MultipartFile file = pictures[i];
                String fileKey = "image/ruralFlavor/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(file);
                String path = OSSFileUtil.upload(fileKey, file);
                YdRuralFlavorPicture p = new YdRuralFlavorPicture();
                p.setRfId(ydRuralFlavor.getId());
                p.setPicturePath(path);
                ydRuralFlavorPictures.add(p);
            }
            int insertRuralFlavorPicture = this.insertRuralFlavorPicture(ydRuralFlavorPictures);
            if(insertRuralFlavorPicture != pictures.length){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean updateRuralFlavor(long userId, long rfId, String content, int stateType, MultipartFile[] pictures, MultipartFile video) {
        boolean flag = true;
        List<String> keys = new ArrayList<String>();
        YdRuralFlavor ruralFlavor = ydRuralFlavorMapper.selectRuralFlavorById(rfId);
        String videoPath = ruralFlavor.getVideoPath();
        if(videoPath != null && videoPath.length()>0 && videoPath.indexOf("?")>-1){
            String videoKey=videoPath.substring(0,videoPath.indexOf("?"));
            keys.add(videoKey);
        }
        List<YdRuralFlavorPicture> ydRuralFlavorPictures = ydRuralFlavorMapper.selectRuralFlavorPictureByRfid(rfId);
        for(int i=0;i<ydRuralFlavorPictures.size();i++){
            String picturePath = ydRuralFlavorPictures.get(i).getPicturePath();
            String pictureKey=picturePath.substring(0,picturePath.indexOf("?"));
            keys.add(pictureKey);
        }
        YdRuralFlavor ydRuralFlavor = new YdRuralFlavor();
        ydRuralFlavor.setId(rfId);
        ydRuralFlavor.setContent(content);
        ydRuralFlavor.setStateType(stateType);
        if(video != null ){
            String fileKey = "video/ruralFlavor/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(video);
            String path = OSSFileUtil.upload(fileKey, video);
            ydRuralFlavor.setVideoPath(path);
        }else{
            ydRuralFlavor.setVideoPath(null);
        }
        int updateRuralFlavor = ydRuralFlavorMapper.updateRuralFlavor(ydRuralFlavor);
        if(updateRuralFlavor != 1){
            flag = false;
        }
        ydRuralFlavorMapper.deleteRuralFlavorPicture(rfId);
        if(pictures != null && pictures.length>0){
            List<YdRuralFlavorPicture> ydRuralFlavorPicturesNew = new ArrayList();
            for (int i=0;i<pictures.length;i++){
                MultipartFile file = pictures[i];
                String fileKey = "image/ruralFlavor/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(file);
                String path = OSSFileUtil.upload(fileKey, file);
                YdRuralFlavorPicture p = new YdRuralFlavorPicture();
                p.setRfId(ydRuralFlavor.getId());
                p.setPicturePath(path);
                ydRuralFlavorPicturesNew.add(p);
            }
            int insertRuralFlavorPicture = this.insertRuralFlavorPicture(ydRuralFlavorPicturesNew);
            if(insertRuralFlavorPicture != pictures.length){
                flag = false;
            }
        }
        //删除旧的视频和图片
        if(flag){
            for(int i=0;i<keys.size();i++){
                OSSFileUtil.remove(keys.get(i));
            }
        }
        return flag;
    }

    @Override
    public int insertRuralFlavorPicture(List<YdRuralFlavorPicture> ydRuralFlavorPicture) {
        return ydRuralFlavorMapper.insertRuralFlavorPicture(ydRuralFlavorPicture);
    }

    @Override
    public int deleteRuralFlavor(long id) {
        return ydRuralFlavorMapper.deleteRuralFlavor(id);
    }

    @Override
    public int deleteRuralFlavorPicture(long id) {
        return ydRuralFlavorMapper.deleteRuralFlavorPicture(id);
    }

    @Override
    public boolean deleteRuralFlavorRelevantInfo(long id) {
        // 删除乡味，连锁删除相关信息
        boolean flag = true;
        int deleteRuralFlavor = this.deleteRuralFlavor(id);
        if(deleteRuralFlavor != 1){
            flag = false;
        }
        //删除乡味图片数据
        this.deleteRuralFlavorPicture(id);
        ydRuralFlavorUserService.deleteInfoByRfId(id);
        List<YdRuralFlavorComment> ydRuralFlavorComments = ydRuralFlavorCommentMapper.selectCommentInfoByIdToDelete(id,1);
        for (YdRuralFlavorComment ydRuralFlavorComment:ydRuralFlavorComments) {
            ydRuralFlavorCommentService.deleteCommentByRuralFlavor(ydRuralFlavorComment);
        }
        return flag;
    }

    @Override
    public int updateReleaseType(long id) {
        return ydRuralFlavorMapper.updateReleaseType(id);
    }

    @Override
    public YdRuralFlavor selectRuralFlavorById(long id) {
        return ydRuralFlavorMapper.selectRuralFlavorById(id);
    }

    @Override
    public List<YdRuralFlavor> selectRuralFlavorsByHometown(long userId,String hometown,int currIndex,int pageSize) {
        return ydRuralFlavorMapper.selectRuralFlavorsByHometown(userId,hometown,currIndex,pageSize);
    }

    @Override
    public List<YdRuralFlavor> selectRuralFlavorsByUserId( long userId,long lookUserId,int currIndex,int pageSize) {
        return ydRuralFlavorMapper.selectRuralFlavorsByUserId(userId,lookUserId,currIndex,pageSize);
    }

    @Override
    public List<YdRuralFlavor> selectDraftRuralFlavorsByUserId(long userId,int currIndex,int pageSize) {
        return ydRuralFlavorMapper.selectDraftRuralFlavorsByUserId( userId, currIndex, pageSize);
    }

    @Override
    public List<YdRuralFlavorPicture> selectRuralFlavorPictureByRfid(long rfId) {
        return ydRuralFlavorMapper.selectRuralFlavorPictureByRfid(rfId);
    }

    @Override
    public int updateForwardNum(long id) {
        return ydRuralFlavorMapper.updateForwardNum(id);
    }

    @Override
    public int updateCollectionNum(YdRuralFlavor ydRuralFlavor) {
        return ydRuralFlavorMapper.updateCollectionNum(ydRuralFlavor);
    }

    @Override
    public int updateCommentNum(YdRuralFlavor ydRuralFlavor) {
        return ydRuralFlavorMapper.updateCommentNum(ydRuralFlavor);
    }

    @Override
    public int updateFabulousNum(YdRuralFlavor ydRuralFlavor) {
        return ydRuralFlavorMapper.updateFabulousNum(ydRuralFlavor);
    }

    @Override
    public int updateStepNum(YdRuralFlavor ydRuralFlavor) {
        return ydRuralFlavorMapper.updateStepNum(ydRuralFlavor);
    }
}
