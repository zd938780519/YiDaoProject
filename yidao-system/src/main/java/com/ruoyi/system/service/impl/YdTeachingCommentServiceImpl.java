package com.ruoyi.system.service.impl;


import com.ruoyi.system.domain.YdTeachingComment;
import com.ruoyi.system.domain.YdTeachingCommentUser;
import com.ruoyi.system.domain.YdTeachingContent;
import com.ruoyi.system.domain.YdVideoComment;
import com.ruoyi.system.mapper.YdTeachingCommentMapper;
import com.ruoyi.system.mapper.YdTeachingContentMapper;
import com.ruoyi.system.service.YdTeachingCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class YdTeachingCommentServiceImpl implements YdTeachingCommentService {
    @Autowired(required = false)
    private YdTeachingCommentMapper ydTeachingCommentMapper;

    @Autowired(required = false)
    private YdTeachingContentMapper ydTeachingContentMapper;


    @Override
    public int selectCommentNumById(long videoId) {
        return ydTeachingCommentMapper.selectCommentNumById(videoId);
    }

    @Override
    public List<YdTeachingComment> selectCommentInfoById(YdTeachingComment ydTeachingComment) {
        return ydTeachingCommentMapper.selectCommentInfoById(ydTeachingComment);
    }

    @Override
    public List<YdTeachingComment> selectCommentInfoByIdToDelete(long commentObject,int type) {
        return ydTeachingCommentMapper.selectCommentInfoByIdToDelete(commentObject,type);
    }

    @Override
    public YdTeachingComment selectCommentById(long id) {
        return ydTeachingCommentMapper.selectCommentById(id);
    }

    @Override
    public int insertComment(YdTeachingComment ydTeachingComment) {
        boolean flag = true;
        int insertComment = ydTeachingCommentMapper.insertComment(ydTeachingComment);
        if(insertComment != 1){
            flag = false;
        }
        this.updateCommentNum(ydTeachingComment,1,flag);
        return flag?1:-1;
    }

    @Override
    public int deleteComment(YdTeachingComment ydTeachingComment) {
        boolean flag = true;
        //sql 的where条件中的in 通过Java代码分批执行，一次500
        HashMap<Long,Long> ids = new HashMap();//评论和评论回复id
        long teachingCommentId = ydTeachingComment.getId();
        ids.put(teachingCommentId,teachingCommentId);
        getChildrenComment(ids,teachingCommentId);
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
        this.updateCommentNum(ydTeachingComment,-commentNum,flag);
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
    public int deleteCommentByTeaching(YdTeachingComment ydTeachingComment) {
        boolean flag = true;
        //sql 的where条件中的in 通过Java代码分批执行，一次500
        HashMap<Long,Long> ids = new HashMap();//评论和评论回复id
        long teachingCommentId = ydTeachingComment.getId();
        ids.put(teachingCommentId,teachingCommentId);
        getChildrenComment(ids,teachingCommentId);
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
        return ydTeachingCommentMapper.deleteCommentByIds(ids);
    }

    @Override
    public int updateCommentReplyNum(YdTeachingComment ydTeachingComment) {
        return ydTeachingCommentMapper.updateCommentReplyNum(ydTeachingComment);
    }

    @Override
    public int deleteCommentUserRelation(String ids) {
        return ydTeachingCommentMapper.deleteCommentUserRelation(ids);
    }

    @Override
    public int deleteAtUserInfo(String ids) {
        return ydTeachingCommentMapper.deleteAtUserInfo(ids);
    }

    @Override
    public int fabulousComment(YdTeachingCommentUser ydTeachingCommentUser) {
        return ydTeachingCommentMapper.fabulousComment(ydTeachingCommentUser);
    }

    @Override
    public boolean fabulous(YdTeachingCommentUser ydTeachingCommentUser) {
        boolean flag = true;
        int fabulousComment = this.fabulousComment(ydTeachingCommentUser);
        if(fabulousComment != 1){
            flag = false;
        }else{
            YdTeachingComment ydTeachingComment = new YdTeachingComment();
            ydTeachingComment.setId(ydTeachingCommentUser.getTcomId());
            ydTeachingComment.setHeatNum(1);
            int updateCommentHeatNum = this.updateCommentHeatNum(ydTeachingComment);
            if(updateCommentHeatNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int deleteFabulous(YdTeachingCommentUser ydTeachingCommentUser) {
        return ydTeachingCommentMapper.deleteFabulous(ydTeachingCommentUser);
    }

    @Override
    public boolean cancelFabulous(YdTeachingCommentUser ydTeachingCommentUser) {
        boolean flag = true;
        int deleteFabulous = this.deleteFabulous(ydTeachingCommentUser);
        if(deleteFabulous != 1){
            flag = false;
        }else{
            YdTeachingComment ydTeachingComment = new YdTeachingComment();
            ydTeachingComment.setId(ydTeachingCommentUser.getTcomId());
            ydTeachingComment.setHeatNum(-1);
            int updateCommentHeatNum = this.updateCommentHeatNum(ydTeachingComment);
            if(updateCommentHeatNum != 1){
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public int updateCommentHeatNum(YdTeachingComment ydTeachingComment) {
        return ydTeachingCommentMapper.updateCommentHeatNum(ydTeachingComment);
    }

    /**
     * 根据评论路径向上找，直到找到评论视频的那一条数据，将评论数加一
     * @param ydTeachingComment
     * @param num
     */
    private void updateCommentNum(YdTeachingComment ydTeachingComment,int num,boolean flag){
        int commentType = ydTeachingComment.getCommentType();
        if(commentType == 2){
            YdTeachingComment commentReply = new YdTeachingComment();
            long commentObject = ydTeachingComment.getCommentObject();
            commentReply.setId(commentObject);
            commentReply.setReplyNum(num);
            int updateCommentReplyNum = this.updateCommentReplyNum(commentReply);
            if(updateCommentReplyNum != 1){
                flag = false;
            }else{
                YdTeachingComment teachingComment = ydTeachingCommentMapper.selectCommentById(commentObject);
                this.updateCommentNum(teachingComment,num,flag);
            }
        }else{
            long commentObject = ydTeachingComment.getCommentObject();
            YdTeachingContent ydTeaching = new YdTeachingContent();
            ydTeaching.setId(commentObject);
            ydTeaching.setCommentNum(num);
            int updateCommentNum = ydTeachingContentMapper.updateCommentNum(ydTeaching);
            if(updateCommentNum != 1){
                flag = false;
            }
        }
    }

    /**
     * 获取子评论
     * @param ids
     * @param teachingCommentId
     */
    private void getChildrenComment(HashMap<Long,Long> ids,long teachingCommentId){
        List<YdTeachingComment> ydTeachingComments = this.selectCommentInfoByIdToDelete(teachingCommentId,2);
        if(ydTeachingComments.size()>0){
            for (YdTeachingComment comm:ydTeachingComments) {
                long commId = comm.getId();
                ids.put(commId,commId);
                getChildrenComment(ids,commId);
            }
        }
    }




}
