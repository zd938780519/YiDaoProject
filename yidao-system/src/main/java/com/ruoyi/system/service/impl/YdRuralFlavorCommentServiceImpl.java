package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdRuralFlavor;
import com.ruoyi.system.domain.YdRuralFlavorComment;
import com.ruoyi.system.domain.YdRuralFlavorCommentAtUser;
import com.ruoyi.system.domain.YdRuralFlavorCommentUser;
import com.ruoyi.system.mapper.YdRuralFlavorCommentMapper;
import com.ruoyi.system.mapper.YdRuralFlavorMapper;
import com.ruoyi.system.service.YdRuralFlavorCommentAtUserService;
import com.ruoyi.system.service.YdRuralFlavorCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class YdRuralFlavorCommentServiceImpl implements YdRuralFlavorCommentService {
    @Autowired(required = false)
    YdRuralFlavorCommentMapper ydRuralFlavorCommentMapper;

    @Autowired(required = false)
    YdRuralFlavorMapper ydRuralFlavorMapper;
    @Autowired
    private YdRuralFlavorCommentAtUserService ydRuralFlavorCommentAtUserService;
    @Override
    public int selectCommentNumById(long videoId) {
        return ydRuralFlavorCommentMapper.selectCommentNumById(videoId);
    }

    @Override
    public List<YdRuralFlavorComment> selectCommentInfoById(YdRuralFlavorComment ydRuralFlavorComment) {
        return ydRuralFlavorCommentMapper.selectCommentInfoById(ydRuralFlavorComment);
    }

    @Override
    public List<YdRuralFlavorComment> selectCommentInfoByIdToDelete(long commentObject,int type) {
        return ydRuralFlavorCommentMapper.selectCommentInfoByIdToDelete(commentObject,type);
    }

    @Override
    public YdRuralFlavorComment selectCommentById(long id) {
        return ydRuralFlavorCommentMapper.selectCommentById(id);
    }

    @Override
    public boolean insertComment(YdRuralFlavorComment ydRuralFlavorComment,List<Long> atUserIds) {
        boolean flag = true;
        int insertComment = ydRuralFlavorCommentMapper.insertComment(ydRuralFlavorComment);
        if(insertComment != 1){
            flag = false;
        }
        this.updateCommentNum(ydRuralFlavorComment,1,flag);
        if(insertComment!=1){
            flag=false;
        }
        if(flag && atUserIds != null && atUserIds.size()>0 && insertComment == 1){
            List<YdRuralFlavorCommentAtUser> ydRuralFlavorCommentAtUsers = new ArrayList();
            for (int i=0;i<atUserIds.size();i++){
                YdRuralFlavorCommentAtUser ydRuralFlavorCommentAtUser = new YdRuralFlavorCommentAtUser();
                ydRuralFlavorCommentAtUser.setUserId(atUserIds.get(i));
                ydRuralFlavorCommentAtUser.setRfcId(ydRuralFlavorComment.getId());
                ydRuralFlavorCommentAtUsers.add(ydRuralFlavorCommentAtUser);
            }
            int insertAtUserInfo = ydRuralFlavorCommentAtUserService.insertAtUserInfo(ydRuralFlavorCommentAtUsers);
            if(insertAtUserInfo!=1){
                flag=false;
            }
        }
        return flag;
    }

    @Override
    public int deleteComment(YdRuralFlavorComment ydRuralFlavorComment) {
        boolean flag = true;
        //sql 的where条件中的in 通过Java代码分批执行，一次500
        HashMap<Long,Long> ids = new HashMap();//评论和评论回复id
        long ruralFlavorCommentId = ydRuralFlavorComment.getId();
        ids.put(ruralFlavorCommentId,ruralFlavorCommentId);
        getChildrenComment(ids,ruralFlavorCommentId);
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
        this.updateCommentNum(ydRuralFlavorComment,-commentNum,flag);
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
    public int deleteCommentByRuralFlavor(YdRuralFlavorComment ydRuralFlavorComment) {
        boolean flag = true;
        //sql 的where条件中的in 通过Java代码分批执行，一次500
        HashMap<Long,Long> ids = new HashMap();//评论和评论回复id
        long ruralFlavorCommentId = ydRuralFlavorComment.getId();
        ids.put(ruralFlavorCommentId,ruralFlavorCommentId);
        getChildrenComment(ids,ruralFlavorCommentId);
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
        return ydRuralFlavorCommentMapper.deleteCommentByIds(ids);
    }

    @Override
    public int updateCommentReplyNum(YdRuralFlavorComment ydRuralFlavorComment) {
        return ydRuralFlavorCommentMapper.updateCommentReplyNum(ydRuralFlavorComment);
    }

    @Override
    public int deleteCommentUserRelation(String ids) {
        return ydRuralFlavorCommentMapper.deleteCommentUserRelation(ids);
    }

    @Override
    public int deleteAtUserInfo(String ids) {
        return ydRuralFlavorCommentMapper.deleteAtUserInfo(ids);
    }

    @Override
    public boolean fabulousComment(YdRuralFlavorCommentUser ydRuralFlavorCommentUser) {
        boolean flag = true;
        int fabulousComment = ydRuralFlavorCommentMapper.fabulousComment(ydRuralFlavorCommentUser);
        if(fabulousComment != 1){
            flag = false;
        }else{
            YdRuralFlavorComment ydRuralFlavorComment = new YdRuralFlavorComment();
            ydRuralFlavorComment.setId(ydRuralFlavorCommentUser.getRfcId());
            ydRuralFlavorComment.setHeatNum(1);
            int updateCommentHeatNum = this.updateCommentHeatNum(ydRuralFlavorComment);
            if(updateCommentHeatNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean deleteFabulous(YdRuralFlavorCommentUser ydRuralFlavorCommentUser) {
        boolean flag = true;
        int deleteFabulous = ydRuralFlavorCommentMapper.deleteFabulous(ydRuralFlavorCommentUser);
        if(deleteFabulous != 1){
            flag = false;
        }else{
            YdRuralFlavorComment ydRuralFlavorComment = new YdRuralFlavorComment();
            ydRuralFlavorComment.setId(ydRuralFlavorCommentUser.getRfcId());
            ydRuralFlavorComment.setHeatNum(-1);
            int updateCommentHeatNum = this.updateCommentHeatNum(ydRuralFlavorComment);
            if(updateCommentHeatNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int updateCommentHeatNum(YdRuralFlavorComment ydRuralFlavorComment) {
        return ydRuralFlavorCommentMapper.updateCommentHeatNum(ydRuralFlavorComment);
    }

    /**
     * 根据评论路径向上找，直到找到评论视频的那一条数据，将评论数加一
     * @param ydRuralFlavorComment
     * @param num
     */
    private void updateCommentNum(YdRuralFlavorComment ydRuralFlavorComment,int num,boolean flag){
        int commentType = ydRuralFlavorComment.getCommentType();
        if(commentType == 2){
            YdRuralFlavorComment commentReply = new YdRuralFlavorComment();
            long commentObject = ydRuralFlavorComment.getCommentObject();
            commentReply.setId(commentObject);
            commentReply.setReplyNum(num);
            int updateCommentReplyNum = this.updateCommentReplyNum(commentReply);
            if(updateCommentReplyNum != 1){
                flag = false;
            }else{
                YdRuralFlavorComment ruralFlavorComment = ydRuralFlavorCommentMapper.selectCommentById(commentObject);
                this.updateCommentNum(ruralFlavorComment,num,flag);
            }
        }else{
            long commentObject = ydRuralFlavorComment.getCommentObject();
            YdRuralFlavor ydRuralFlavor = new YdRuralFlavor();
            ydRuralFlavor.setId(commentObject);
            ydRuralFlavor.setCommentNum(num);
            int updateCommentNum = ydRuralFlavorMapper.updateCommentNum(ydRuralFlavor);
            if(updateCommentNum != 1){
                flag = false;
            }
        }
    }

    /**
     * 获取子评论
     * @param ids
     * @param ruralFlavorCommentId
     */
    private void getChildrenComment(HashMap<Long,Long> ids,long ruralFlavorCommentId){
        List<YdRuralFlavorComment> ydRuralFlavorComments = this.selectCommentInfoByIdToDelete(ruralFlavorCommentId,2);
        if(ydRuralFlavorComments.size()>0){
            for (YdRuralFlavorComment comm:ydRuralFlavorComments) {
                long commId = comm.getId();
                ids.put(commId,commId);
                getChildrenComment(ids,commId);
            }
        }
    }


}
