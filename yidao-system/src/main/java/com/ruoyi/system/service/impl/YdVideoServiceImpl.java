package com.ruoyi.system.service.impl;

import com.ruoyi.common.oss.OSSFileUtil;
import com.ruoyi.system.domain.YdVideo;
import com.ruoyi.system.domain.YdVideoComment;
import com.ruoyi.system.domain.YdVideoUser;
import com.ruoyi.system.mapper.YdVideoMapper;
import com.ruoyi.system.service.YdVideoCommentService;
import com.ruoyi.system.service.YdVideoService;
import com.ruoyi.system.service.YdVideoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class YdVideoServiceImpl implements YdVideoService {
    @Autowired(required = false)
    private YdVideoMapper ydVideoMapper;
    @Autowired(required = false)
    private YdVideoCommentService ydVideoCommentService;
    @Autowired(required = false)
    private YdVideoUserService ydVideoUserService;

    @Override
    public int insertVideo(YdVideo ydVideo) {
        return ydVideoMapper.insertVideo(ydVideo);
    }

    @Override
    public boolean insertVideo(long userId, MultipartFile thumbnail, MultipartFile video, int score, String explanatoryText, String bgmName, MultipartFile bgm, String supplementaryNotes) {
        boolean flag = false;
        YdVideo ydVideo = new YdVideo();
        ydVideo.setSellerId(userId);
        //将图片视频音乐等文件放到文件服务器上
        if(thumbnail != null ){
            String fileKey = "image/video/thumbnail/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(thumbnail);
            String path = OSSFileUtil.upload(fileKey, thumbnail);
            ydVideo.setThumbnailPath(path);
        }
        if(video != null ){
            String fileKey = "video/video/video/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(video);
            String path = OSSFileUtil.upload(fileKey, video);
            ydVideo.setVideoPath(path);
        }
        ydVideo.setScore(score);
        ydVideo.setExplanatoryText(explanatoryText);
        ydVideo.setBgmName(bgmName);
        if(bgm != null ){
            String fileKey = "music/video/bgm/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(bgm);
            String path = OSSFileUtil.upload(fileKey, bgm);
            ydVideo.setBgmPath(path);
        }
        ydVideo.setSupplementaryNotes(supplementaryNotes);
        int insertVideo = this.insertVideo(ydVideo);
        if(insertVideo == 1){
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }

    @Override
    public int deleteVideo(long id) {
        return ydVideoMapper.deleteVideo(id);
    }

    @Override
    public List<YdVideo> selectAllVideo(long userId,int currIndex,int pageSize) {
        return ydVideoMapper.selectAllVideo(userId, currIndex, pageSize);
    }

    @Override
    public List<YdVideo> selectVideoByUserId(long userId,long sellerId,int currIndex,int pageSize) {
        return ydVideoMapper.selectVideoByUserId(userId,sellerId,currIndex,pageSize);
    }

    @Override
    public YdVideo selectVideoById(long videoId) {
        return ydVideoMapper.selectVideoById(videoId);
    }

    @Override
    public int updateCommentNum(YdVideo ydVideo) {
        return ydVideoMapper.updateCommentNum(ydVideo);
    }

    @Override
    public int updateInfo(YdVideo ydVideo) {
        return ydVideoMapper.updateInfo(ydVideo);
    }

    @Override
    public int updateWantEatNum(YdVideo ydVideo) {
        return ydVideoMapper.updateWantEatNum(ydVideo);
    }

    @Override
    public boolean wantEat(YdVideoUser ydVideoUser) {
        YdVideoUser ydVideoUser1 = ydVideoUserService.selectInfoById(ydVideoUser);
        boolean flag = true;
        if(ydVideoUser1 == null){
            int insertInfo = ydVideoUserService.insertInfo(ydVideoUser);
            if(insertInfo != 1){
                flag = false;
            }
        }else{
            int updateInfo = ydVideoUserService.updateInfo(ydVideoUser);
            if(updateInfo != 1){
                flag = false;
            }
        }
        if(flag){
            YdVideo ydVideo = new YdVideo();
            ydVideo.setId(ydVideoUser.getVideoId());
            ydVideo.setWantEatNum(ydVideoUser.getIsWantEat() == 1?1:-1);
            int updateInfo = this.updateWantEatNum(ydVideo);
            if(updateInfo != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int addForwardNum(long id) {
        return ydVideoMapper.addForwardNum(id);
    }

    @Override
    public boolean addForwardNum(YdVideoUser ydVideoUser) {
        YdVideoUser ydVideoUser1 = ydVideoUserService.selectInfoById(ydVideoUser);
        boolean flag = true;
        if(ydVideoUser1 == null){
            int insertInfo = ydVideoUserService.insertInfo(ydVideoUser);
            if(insertInfo != 1){
                flag = false;
            }
        }else{
            int updateInfo = ydVideoUserService.updateInfo(ydVideoUser);
            if(updateInfo != 1){
                flag = false;
            }
        }
        if(flag){
            int addForwardNum = this.addForwardNum(ydVideoUser.getVideoId());
            if(addForwardNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int deleteRelevantInfo(long id) {
        boolean flag = true;
        int deleteVideo = this.deleteVideo(id);
        if(deleteVideo != 1){
            flag = false;
        }
        ydVideoUserService.deleteInfoByVideoId(id);
        List<YdVideoComment> ydVideoComments = ydVideoCommentService.selectCommentInfoByIdToDelete(id,1);
        for (YdVideoComment ydVideoComment:ydVideoComments) {
            ydVideoCommentService.deleteCommentByVideo(ydVideoComment);
        }
        return flag?1:-1;
    }


}
