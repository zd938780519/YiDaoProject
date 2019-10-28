package com.ruoyi.MobileApi.KanDianApi;


import com.ruoyi.common.json.Body;
import com.ruoyi.common.json.JSON;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.YdTeachingCommentAtUserService;
import com.ruoyi.system.service.YdTeachingCommentService;
import com.ruoyi.system.service.YdTeachingContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Api("教学相关api")
@RestController
@RequestMapping("/yd/app/teaching")
public class YdTeachingController {
    @Autowired
    private YdTeachingContentService ydTeachingContentService;
    @Autowired
    private YdTeachingCommentService ydTeachingCommentService;
    @Autowired
    private YdTeachingCommentAtUserService ydTeachingCommentAtUserService;

    /**
     * 获取单个用户的教学简单信息
     * @param lookUserId 被查看的用户id
     * @param type 教学信息状态类型 1：草稿；2：已发布
     * @param currIndex 分页偏移量
     * @param pageSize 分页大小
     * @return
     */
    @ApiOperation("获取教学简单信息")
    @PostMapping("/getTeachingSimpleInfoByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="lookUserId",value="被查看的用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="教学信息状态类型 1：草稿；2：已发布",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="分页大小",required=true,paramType="query")
    })
    public Body getTeachingSimpleInfoByUserId(@RequestParam("lookUserId") long lookUserId,@RequestParam("type") int type,
                                                @RequestParam("currIndex") int currIndex,@RequestParam("pageSize") int pageSize){
        List<YdTeachingContent> ydTeachingContents;
        if(type == 1){
            ydTeachingContents = ydTeachingContentService.selectDraftTeachingSimpleInfoByUserId(lookUserId,currIndex,pageSize);
        }else{
            ydTeachingContents = ydTeachingContentService.selectTeachingSimpleInfoByUserId(lookUserId, currIndex, pageSize);
        }
        if(ydTeachingContents.size()>0){
            return Body.newInstance(ydTeachingContents);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     *
     * 获取所有人的教学简单信息
     * @param userId
     * @param currIndex
     * @param pageSize
     * @return
     */
    @ApiOperation("获取所有人的教学简单信息")
    @PostMapping("/getAllTeachingSimpleInfoByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="分页大小",required=true,paramType="query")
    })
    public Body getAllTeachingSimpleInfoByUserId(@RequestParam("userId") long userId,@RequestParam("currIndex") int currIndex,
                                                   @RequestParam("pageSize") int pageSize){
        List<YdTeachingContent> ydTeachingContents = ydTeachingContentService.selectAllTeachingSimpleInfoByUserId(userId, currIndex, pageSize);
        if(ydTeachingContents.size()>0){
            return Body.newInstance(ydTeachingContents);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     * 获取教学详细信息
     * @param id 教学id
     * @param userId 登录者id
     * @return
     */
    @ApiOperation("获取教学详细信息")
    @PostMapping("/getTeachingInfoByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="教学id",required=true,paramType="query"),
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query")
    })
    public Body getTeachingInfoByUserId(@RequestParam("id") long id,@RequestParam("userId") long userId){
        YdTeachingContent ydTeachingContent = ydTeachingContentService.selectTeachingDetailedInfoByUserId(id, userId);
        if(ydTeachingContent != null){
            return Body.newInstance(ydTeachingContent);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     * 新增教学内容
     * @param userId 用户id
     * @param dishName 菜名
     * @param resultVideo 效果视频路径
     * @param thumbnail 缩略图路径
     * @param resultPicture 效果图路径
     * @param experience 说明文字（心得）
     * @param skill 技巧
     * @param cookingTime 烹饪时间
     * @param cookingTimeShow 烹饪时间显示值
     * @param flavor 口味
     * @param flavorShow 口味显示值
     * @param difficulty 难度
     * @param difficultyShow 难度显示值
     * @param stateType 状态类型 1：草稿 2：发布
     * @param json 主料、辅料、步骤的数据
     * @return
     */
    @ApiOperation("新增教学内容")
    @PostMapping("/addTeachingInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="dishName",value="菜名",required=true,paramType="query"),
            @ApiImplicitParam(name="resultVideo",value="效果视频路径",required=true,paramType="query"),
            @ApiImplicitParam(name="thumbnail",value="缩略图路径",required=true,paramType="query"),
            @ApiImplicitParam(name="resultPicture",value="效果图路径",required=true,paramType="query"),
            @ApiImplicitParam(name="experience",value="说明文字（心得）",required=true,paramType="query"),
            @ApiImplicitParam(name="skill",value="技巧",required=true,paramType="query"),
            @ApiImplicitParam(name="cookingTime",value="烹饪时间",required=true,paramType="query"),
            @ApiImplicitParam(name="cookingTimeShow",value="烹饪时间显示值",required=true,paramType="query"),
            @ApiImplicitParam(name="flavor",value="口味",required=true,paramType="query"),
            @ApiImplicitParam(name="flavorShow",value="口味显示值",required=true,paramType="query"),
            @ApiImplicitParam(name="difficulty",value="难度",required=true,paramType="query"),
            @ApiImplicitParam(name="difficultyShow",value="难度显示值",required=true,paramType="query"),
            @ApiImplicitParam(name="stateType",value="状态类型 1：草稿 2：发布",required=true,paramType="query"),
            @ApiImplicitParam(name="model",value="主料、辅料、步骤",required=true,paramType="query"),
            @ApiImplicitParam(name="files",value="步骤图片",required=true,paramType="query")
    })
    public Body addTeachingInfo(@RequestParam("userId") long  userId  , @RequestParam("dishName") String  dishName ,
                                  @RequestParam(value = "resultVideo", required = false) MultipartFile  resultVideo ,
                                  @RequestParam("thumbnail") MultipartFile  thumbnail , @RequestParam(value = "resultPicture", required = false) MultipartFile  resultPicture,
                                  @RequestParam(value = "experience", required = false) String  experience, @RequestParam(value = "skill", required = false) String  skill ,
                                  @RequestParam("cookingTime") int  cookingTime, @RequestParam("cookingTimeShow") String  cookingTimeShow  ,
                                  @RequestParam("flavor") int  flavor, @RequestParam("flavorShow") String  flavorShow, @RequestParam("difficulty") int  difficulty ,
                                  @RequestParam("difficultyShow") String  difficultyShow , @RequestParam("stateType") int  stateType ,
                                  @RequestParam(value = "files", required = false) MultipartFile[] files,@RequestParam(value = "json", required = false) String  json) {
        TeachingActivityModel model = new TeachingActivityModel();
        try {
            model = JSON.unmarshal(json, TeachingActivityModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean addTeachingInfo = ydTeachingContentService.addTeachingInfo(userId, dishName, resultVideo, thumbnail, resultPicture, experience, skill,
                cookingTime, cookingTimeShow, flavor, flavorShow, difficulty, difficultyShow, stateType, model, files);
       if(addTeachingInfo){
           return Body.newInstance();
       }else{
           return Body.newInstance(201,"新增失败");
       }
    }

    @ApiOperation("删除教学信息")
    @PostMapping("/removeTeaching")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="教学id",required=true,paramType="query")
    })
    public Body removeTeaching(@RequestParam("id") long  id ){
        int deleteRelevantInfo = ydTeachingContentService.deleteRelevantInfo(id);
        if(deleteRelevantInfo == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    @ApiOperation("修改教学内容发布状态")
    @PostMapping("/updateReleaseType")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="教学id",required=true,paramType="query")
    })
    public Body updateReleaseType(@RequestParam("id") long  id ){
        int updateReleaseType = ydTeachingContentService.updateReleaseType(id);
        if(updateReleaseType == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("增加浏览量")
    @PostMapping("/addBrowseNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="教学id",required=true,paramType="query")
    })
    public Body addBrowseNum(@RequestParam("id") long  id){
        int addBrowseNum = ydTeachingContentService.insertBrowseNum(id);
        if(addBrowseNum == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"增加失败");
        }
    }

    /**
     * 修改教学点赞的状态
     * @param userId 用户id
     * @param tcId 教学id
     * @param type 状态 0：不赞；1：赞
     * @return
     */
    @ApiOperation("修改教学点赞的状态")
    @PostMapping("/fabulous")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="tcId",value="教学id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="状态 0：不赞；1：赞",required=true,paramType="query")
    })
    public Body wantEat(@RequestParam("userId") long userId,@RequestParam("tcId") long tcId,@RequestParam("type") int type){
        YdTeachingUser ydTeachingUser = new YdTeachingUser();
        ydTeachingUser.setTcId(tcId);
        ydTeachingUser.setUserId(userId);
        ydTeachingUser.setIsFabulous(type);
        boolean wantEat = ydTeachingContentService.wantEat(ydTeachingUser);
        if(wantEat){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("增加转发数")
    @PostMapping("/addForwardNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="tcId",value="教学id",required=true,paramType="query")
    })
    public Body addForwardNum(@RequestParam("userId") long userId,@RequestParam("tcId") long tcId){
        YdTeachingUser ydTeachingUser = new YdTeachingUser();
        ydTeachingUser.setTcId(tcId);
        ydTeachingUser.setUserId(userId);
        ydTeachingUser.setIsForward(1);
        boolean addForwardNum = ydTeachingContentService.addForwardNum(ydTeachingUser);
        if(addForwardNum){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"增加失败");
        }
    }

    /**
     * 修改收藏状态
     * @param userId 用户id
     * @param tcId 教学id
     * @param type 收藏状态 -1：取消收藏；1：收藏
     * @return
     */
    @ApiOperation("收藏")
    @PostMapping("/collection")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="tcId",value="教学id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="收藏状态 -1：取消收藏；1：收藏",required=true,paramType="query")
    })
    public Body collection(@RequestParam("userId") long userId,@RequestParam("tcId") long tcId,@RequestParam("type") int type){
        YdTeachingUser ydTeachingUser = new YdTeachingUser();
        ydTeachingUser.setTcId(tcId);
        ydTeachingUser.setUserId(userId);
        ydTeachingUser.setIsCollection(type);
        boolean collection = ydTeachingContentService.collection(ydTeachingUser);
        if(collection){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    /**
     *  获取教学评论数量
     * @param tcId 教学id
     * @return
     */
    @ApiOperation("获取教学评论数量")
    @PostMapping("/comment/getCommentNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name="tcId",value="教学id",required=true,paramType="query")
    })
    public Body getCommentNum(@RequestParam("tcId") long tcId){
        JSONObject jsonObject = new JSONObject();
        int commentNum = ydTeachingCommentService.selectCommentNumById(tcId);
        jsonObject.put("commentNum",commentNum);
        return Body.newInstance(jsonObject);
    }

    /**
     * 获取教学评论信息
     * @param userId 用户id
     * @param commentObject 评论对象id 视频id或评论id
     * @param commentType 评论类别 1：评论教学；2：回复评论
     * @return
     */
    @ApiOperation("获取教学评论")
    @PostMapping("/comment/getCommentInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentObject",value="评论对象id 视频id或评论id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentType",value="评论类别 1：评论教学；2：回复评论",required=true,paramType="query")
    })
    public Body getCommentInfo(@RequestParam("userId") long userId,@RequestParam("commentObject") long commentObject,@RequestParam("commentType") int commentType){
        YdTeachingComment teachingComment = new YdTeachingComment();
        teachingComment.setCommentType(commentType);
        teachingComment.setCommentObject(commentObject);
        teachingComment.setUserId(userId);
        List<YdTeachingComment> ydTeachingComments = ydTeachingCommentService.selectCommentInfoById(teachingComment);
        if(ydTeachingComments.size()>0){
            return Body.newInstance(ydTeachingComments);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     * 新增评论
     * @param userId 用户id
     * @param commentObject 评论对象id
     * @param commentType 评论类型 1：评论视频；2：回复评论
     * @param name 用户昵称
     * @param photoPath 用户头像路径
     * @param content 评论内容
     * @return
     */
    @ApiOperation("新增评论")
    @PostMapping("/comment/addComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentObject",value="评论对象id 视频id或评论id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentType",value="评论类别 1：评论教学；2：回复评论",required=true,paramType="query"),
            @ApiImplicitParam(name="name",value="用户昵称",required=true,paramType="query"),
            @ApiImplicitParam(name="photoPath",value="用户头像路径",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="评论内容",required=true,paramType="query")
    })
    public Body addComment(@RequestParam("userId") long userId,@RequestParam("commentObject") long commentObject,
                             @RequestParam("commentType") int commentType,@RequestParam("name") String name,
                             @RequestParam("photoPath") String photoPath,@RequestParam("content") String content,
                             @RequestParam(value = "atUserIds[]", required = false) List<Long> atUserIds){
        boolean flag=true;
        YdTeachingComment teachingComment = new YdTeachingComment();
        teachingComment.setCommentType(commentType);
        teachingComment.setCommentObject(commentObject);
        teachingComment.setUserId(userId);
        teachingComment.setName(name);
        teachingComment.setPhotoPath(photoPath);
        teachingComment.setContent(content);
        int insertComment = ydTeachingCommentService.insertComment(teachingComment);
        if(insertComment!=1){
            flag=false;
        }
        if(atUserIds != null && atUserIds.size()>0 && insertComment == 1){
            List<YdTeachingCommentAtUser> YdTeachingCommentAtUsers = new ArrayList();
            for (int i=0;i<atUserIds.size();i++){
                YdTeachingCommentAtUser ydTeachingCommentAtUser = new YdTeachingCommentAtUser();
                ydTeachingCommentAtUser.setUserId(atUserIds.get(i));
                ydTeachingCommentAtUser.setTcomId(teachingComment.getId());
                YdTeachingCommentAtUsers.add(ydTeachingCommentAtUser);
            }
            int insertAtUserInfo = ydTeachingCommentAtUserService.insertAtUserInfo(YdTeachingCommentAtUsers);
            if(insertAtUserInfo!=1){
                flag=false;
            }
        }
        if(flag){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"新增失败");
        }
    }

    @ApiOperation("删除评论")
    @PostMapping("/comment/removeComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name="commentId",value="评论id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentObject",value="评论对象id 视频id或评论id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentType",value="评论类别 1：评论教学；2：回复评论",required=true,paramType="query")
    })
    public Body removeComment(@RequestParam("commentId") long commentId,@RequestParam("commentObject") long commentObject,
                                @RequestParam("commentType") int commentType){
        YdTeachingComment ydTeachingComment = new YdTeachingComment();
        ydTeachingComment.setId(commentId);
        ydTeachingComment.setCommentObject(commentObject);
        ydTeachingComment.setCommentType(commentType);
        int deleteComment = ydTeachingCommentService.deleteComment(ydTeachingComment);
        if(deleteComment>0){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    @ApiOperation("评论点赞")
    @PostMapping("/comment/fabulous")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentId",value="评论id",required=true,paramType="query")
    })
    public Body fabulous(@RequestParam("userId") long userId,@RequestParam("commentId") long commentId){
        YdTeachingCommentUser ydTeachingCommentUser = new YdTeachingCommentUser();
        ydTeachingCommentUser.setTcomId(commentId);
        ydTeachingCommentUser.setUserId(userId);
        boolean fabulous = ydTeachingCommentService.fabulous(ydTeachingCommentUser);
        if(fabulous){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"点赞失败");
        }
    }

    @ApiOperation("取消评论点赞")
    @PostMapping("/comment/cancelFabulous")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentId",value="评论id",required=true,paramType="query")
    })
    public Body cancelFabulous(@RequestParam("userId") long userId,@RequestParam("commentId") long commentId){
        YdTeachingCommentUser ydTeachingCommentUser = new YdTeachingCommentUser();
        ydTeachingCommentUser.setTcomId(commentId);
        ydTeachingCommentUser.setUserId(userId);
        boolean cancelFabulous = ydTeachingCommentService.cancelFabulous(ydTeachingCommentUser);
        if(cancelFabulous){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"取消失败");
        }
    }

}
