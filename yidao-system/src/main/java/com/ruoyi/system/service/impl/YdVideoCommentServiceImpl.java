package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdVideo;
import com.ruoyi.system.domain.YdVideoComment;
import com.ruoyi.system.domain.YdVideoCommentAtUser;
import com.ruoyi.system.domain.YdVideoCommentUser;
import com.ruoyi.system.mapper.YdVideoCommentMapper;
import com.ruoyi.system.mapper.YdVideoMapper;
import com.ruoyi.system.service.YdVideoCommentAtUserService;
import com.ruoyi.system.service.YdVideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class YdVideoCommentServiceImpl implements YdVideoCommentService {
    @Autowired(required = false)
    private YdVideoCommentMapper ydVideoCommentMapper;

    @Autowired(required = false)
    private YdVideoMapper ydVideoMapper;
    @Autowired
    private YdVideoCommentAtUserService ydVideoCommentAtUserService;


    @Override
    public int selectCommentNumById(long videoId) {
        return ydVideoCommentMapper.selectCommentNumById(videoId);
    }

    @Override
    public List<YdVideoComment> selectCommentInfoById(YdVideoComment ydVideoComment) {
        return ydVideoCommentMapper.selectCommentInfoById(ydVideoComment);
    }

    @Override
    public List<YdVideoComment> selectCommentInfoByIdToDelete(long commentObject,int type) {
        return ydVideoCommentMapper.selectCommentInfoByIdToDelete(commentObject,type);
    }

    @Override
    public YdVideoComment selectCommentById(long id) {
        return ydVideoCommentMapper.selectCommentById(id);
    }

    @Override
    public int insertComment(YdVideoComment ydVideoComment) {
        boolean flag = true;
        int insertComment = ydVideoCommentMapper.insertComment(ydVideoComment);
        if(insertComment != 1){
            flag = false;
        }
        this.updateCommentNum(ydVideoComment,1,flag);
        return flag?1:-1;
    }

    @Override
    public boolean addComment(YdVideoComment videoComment, List<Long> atUserIds) {
        boolean flag=true;
        int insertComment = this.insertComment(videoComment);
        if(insertComment!=1){
            flag=false;
        }
        if(atUserIds != null && atUserIds.size()>0 && insertComment == 1){
            List<YdVideoCommentAtUser> ydVideoCommentAtUsers = new ArrayList();
            for (int i=0;i<atUserIds.size();i++){
                YdVideoCommentAtUser ydVideoCommentAtUser = new YdVideoCommentAtUser();
                ydVideoCommentAtUser.setUserId(atUserIds.get(i));
                ydVideoCommentAtUser.setVcId(videoComment.getId());
                ydVideoCommentAtUsers.add(ydVideoCommentAtUser);
            }
            int insertAtUserInfo = ydVideoCommentAtUserService.insertAtUserInfo(ydVideoCommentAtUsers);
            if(insertAtUserInfo<1){
                flag=false;
            }
        }
        return flag;
    }

    @Override
    public int deleteComment(YdVideoComment ydVideoComment) {
        boolean flag = true;
        //sql 的where条件中的in 通过Java代码分批执行，一次500
        HashMap<Long,Long> ids = new HashMap();//评论和评论回复id
        long videoCommentId = ydVideoComment.getId();
        ids.put(videoCommentId,videoCommentId);
        getChildrenComment(ids,videoCommentId);
        int commentNum = ids.size();//需要减掉的评论数
        List<String> idsList = new ArrayList();
        Set<Long> idsKey = ids.keySet();
        int i=0;
        String idsVal="";
        for (long id:idsKey ) {
            i++;
            idsVal += id+",";
            if(i%500 == 0){
                idsVal=idsVal.substring(0,idsVal.length()-1);
                idsList.add(idsVal);
                idsVal = "";
            }
        }
        if(idsVal.length()>0){
            idsVal=idsVal.substring(0,idsVal.length()-1);
            idsList.add(idsVal);
            idsVal = "";
        }
        //删除评论与回复
        for (String idsValue: idsList) {
            if(idsValue.length()>0){
                int deleteComment = this.deleteCommentByIds("(" + idsValue + ")");
                if(deleteComment == 0){
                    flag = false;
                }
            }
        }
        //修改评论数、回复数
        this.updateCommentNum(ydVideoComment,-commentNum,flag);
        //删除评论与用户的关系表数据和删除@人员相关数据
        for (String idsValue: idsList) {
            if(idsValue.length()>0){
                this.deleteCommentUserRelation("(" + idsValue + ")");
                this.deleteAtUserInfo("(" + idsValue + ")");
            }
        }
        return flag?1:-1;
    }

    @Override
    public int deleteCommentByVideo(YdVideoComment ydVideoComment) {
        boolean flag = true;
        //sql 的where条件中的in 通过Java代码分批执行，一次500
        HashMap<Long,Long> ids = new HashMap();//评论和评论回复id
        long videoCommentId = ydVideoComment.getId();
        ids.put(videoCommentId,videoCommentId);
        getChildrenComment(ids,videoCommentId);
        int commentNum = ids.size();//需要减掉的评论数
        List<String> idsList = new ArrayList();
        Set<Long> idsKey = ids.keySet();
        int i=0;
        String idsVal="";
        for (long id:idsKey ) {
            i++;
            idsVal += id+",";
            if(i%500 == 0){
                idsVal=idsVal.substring(0,idsVal.length()-1);
                idsList.add(idsVal);
                idsVal = "";
            }
        }
        if(idsVal.length()>0){
            idsVal=idsVal.substring(0,idsVal.length()-1);
            idsList.add(idsVal);
            idsVal = "";
        }
        //删除评论与回复
        for (String idsValue: idsList) {
            if(idsValue.length()>0){
                int deleteComment = this.deleteCommentByIds("(" + idsValue + ")");
                if(deleteComment == 0){
                    flag = false;
                }
            }
        }
        //删除评论与用户的关系表数据和删除@人员相关数据
        for (String idsValue: idsList) {
            if(idsValue.length()>0){
                this.deleteCommentUserRelation("(" + idsValue + ")");
                this.deleteAtUserInfo("(" + idsValue + ")");
            }
        }
        return flag?1:-1;
    }

    @Override
    public int deleteCommentByIds(String ids) {
        return ydVideoCommentMapper.deleteCommentByIds(ids);
    }

    @Override
    public int updateCommentReplyNum(YdVideoComment ydVideoComment) {
        return ydVideoCommentMapper.updateCommentReplyNum(ydVideoComment);
    }

    @Override
    public int deleteCommentUserRelation(String ids) {
        return ydVideoCommentMapper.deleteCommentUserRelation(ids);
    }

    @Override
    public int deleteAtUserInfo(String ids) {
        return ydVideoCommentMapper.deleteAtUserInfo(ids);
    }

    @Override
    public int fabulousComment(YdVideoCommentUser ydVideoCommentUser) {
        return ydVideoCommentMapper.fabulousComment(ydVideoCommentUser);
    }

    @Override
    public boolean fabulous(YdVideoCommentUser ydVideoCommentUser) {
        boolean flag = true;
        int fabulousComment = this.fabulousComment(ydVideoCommentUser);
        if(fabulousComment != 1){
            flag = false;
        }else{
            YdVideoComment ydVideoComment = new YdVideoComment();
            ydVideoComment.setId(ydVideoCommentUser.getCommentId());
            ydVideoComment.setHeatNum(1);
            int updateCommentHeatNum = this.updateCommentHeatNum(ydVideoComment);
            if(updateCommentHeatNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int deleteFabulous(YdVideoCommentUser ydVideoCommentUser) {
        return ydVideoCommentMapper.deleteFabulous(ydVideoCommentUser);
    }

    @Override
    public boolean cancelFabulous(YdVideoCommentUser ydVideoCommentUser) {
        boolean flag = true;
        int deleteFabulous = this.deleteFabulous(ydVideoCommentUser);
        if(deleteFabulous != 1){
            flag = false;
        }else{
            YdVideoComment ydVideoComment = new YdVideoComment();
            ydVideoComment.setId(ydVideoCommentUser.getCommentId());
            ydVideoComment.setHeatNum(-1);
            int updateCommentHeatNum = this.updateCommentHeatNum(ydVideoComment);
            if(updateCommentHeatNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int updateCommentHeatNum(YdVideoComment ydVideoComment) {
        return ydVideoCommentMapper.updateCommentHeatNum(ydVideoComment);
    }

    /**
     * 根据评论路径向上找，直到找到评论视频的那一条数据，将评论数加一
     * @param ydVideoComment
     * @param num
     */
    private void updateCommentNum(YdVideoComment ydVideoComment,int num,boolean flag){
        int commentType = ydVideoComment.getCommentType();
        if(commentType == 2){
            YdVideoComment commentReply = new YdVideoComment();
            long commentObject = ydVideoComment.getCommentObject();
            commentReply.setId(commentObject);
            commentReply.setReplyNum(num);
            int updateCommentReplyNum = this.updateCommentReplyNum(commentReply);
            if(updateCommentReplyNum != 1){
                flag = false;
            }else{
                YdVideoComment videoComment = ydVideoCommentMapper.selectCommentById(commentObject);
                this.updateCommentNum(videoComment,num,flag);
            }
        }else{
            long commentObject = ydVideoComment.getCommentObject();
            YdVideo ydVideo = new YdVideo();
            ydVideo.setId(commentObject);
            ydVideo.setCommentNum(num);
            int updateCommentNum = ydVideoMapper.updateCommentNum(ydVideo);
            if(updateCommentNum != 1){
                flag = false;
            }
        }
    }

    /**
     * 获取子评论
     * @param ids
     * @param videoCommentId
     */
    private void getChildrenComment(HashMap<Long,Long> ids,long videoCommentId){
        List<YdVideoComment> ydVideoComments = this.selectCommentInfoByIdToDelete(videoCommentId,2);
        if(ydVideoComments.size()>0){
            for (YdVideoComment comm:ydVideoComments) {
                long commId = comm.getId();
                ids.put(commId,commId);
                getChildrenComment(ids,commId);
            }
        }
    }




}
