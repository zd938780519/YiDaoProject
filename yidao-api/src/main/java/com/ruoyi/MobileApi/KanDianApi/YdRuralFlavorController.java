package com.ruoyi.MobileApi.KanDianApi;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.YdRuralFlavorCommentService;
import com.ruoyi.system.service.YdRuralFlavorService;
import com.ruoyi.system.service.YdRuralFlavorUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("乡味相关api")
@RestController
@RequestMapping("/yd/app/ruralFlavor")
public class YdRuralFlavorController {
    @Autowired
    private YdRuralFlavorService ydRuralFlavorService;
    @Autowired
    private YdRuralFlavorCommentService ydRuralFlavorCommentService;
    @Autowired
    private YdRuralFlavorUserService ydRuralFlavorUserService;

    /**
     * 获取个人发表的乡味信息
     * @param userId 登陆人id
     * @param lookUserId 被查看的人的id
     * @param type 乡味信息状态类型 1：草稿；2：已发布
     * @param currIndex 分页偏移量
     * @param pageSize 页大小
     * @return
     */
    @ApiOperation("获取个人发表的乡味信息")
    @PostMapping("/getRuralFlavorSimpleInfoByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登陆人id",required=true,paramType="query"),
            @ApiImplicitParam(name="lookUserId",value="被查看的人的id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="乡味信息状态类型 1：草稿；2：已发布",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getRuralFlavorInfoByUserId(@RequestParam("userId") long userId, @RequestParam("lookUserId") long lookUserId,
                                             @RequestParam("type") int type,@RequestParam("currIndex") int currIndex,
                                             @RequestParam("pageSize") int pageSize){
        List<YdRuralFlavor> ydRuralFlavors ;
        if(type == 1){
            ydRuralFlavors = ydRuralFlavorService.selectDraftRuralFlavorsByUserId(userId,currIndex,pageSize);
        }else{
            ydRuralFlavors = ydRuralFlavorService.selectRuralFlavorsByUserId(userId,lookUserId, currIndex,pageSize);
        }
        if(ydRuralFlavors.size()>0){
            return Body.newInstance(ydRuralFlavors);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     *根据家乡位置获取乡味信息
     * @param userId 登录人id
     * @param hometown 家乡位置
     * @param currIndex
     * @param pageSize
     * @return
     */
    @ApiOperation("根据家乡位置获取乡味信息")
    @PostMapping("/getRuralFlavorInfoByHometown")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登陆人id",required=true,paramType="query"),
            @ApiImplicitParam(name="hometown",value="家乡位置",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getRuralFlavorInfoByHometown(@RequestParam("userId") long userId,@RequestParam("hometown") String hometown, @RequestParam("currIndex") int currIndex,
                                             @RequestParam("pageSize") int pageSize) {
        List<YdRuralFlavor> ydRuralFlavors = ydRuralFlavorService.selectRuralFlavorsByHometown(userId,hometown,currIndex,pageSize);
        if(ydRuralFlavors.size()>0){
            return Body.newInstance(ydRuralFlavors);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("新增乡味信息")
    @PostMapping("/addRuralFlavor")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登陆人id",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="内容",required=true,paramType="query"),
            @ApiImplicitParam(name="video",value="视频",required=true,paramType="query"),
            @ApiImplicitParam(name="pictures",value="图片数组",required=true,paramType="query"),
            @ApiImplicitParam(name="stateType",value="状态类型 1：草稿 2：发布",required=true,paramType="query")
    })
    public Body addRuralFlavor(@RequestParam("userId") long userId,@RequestParam("content") String content,
                               @RequestParam("stateType") int stateType,@RequestParam(value = "pictures", required = false) MultipartFile[] pictures,
                               @RequestParam(value = "video", required = false) MultipartFile video){
        boolean flag =ydRuralFlavorService.insertRuralFlavor(userId,content,stateType, pictures,video);
        if(flag){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"新增失败");
        }
    }

    @ApiOperation("修改乡味信息")
    @PostMapping("/updateRuralFlavor")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登陆人id",required=true,paramType="query"),
            @ApiImplicitParam(name="rfId",value="乡味id",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="内容",required=true,paramType="query"),
            @ApiImplicitParam(name="video",value="视频",required=true,paramType="query"),
            @ApiImplicitParam(name="pictures",value="图片数组",required=true,paramType="query"),
            @ApiImplicitParam(name="stateType",value="状态类型 1：草稿 2：发布",required=true,paramType="query")
    })
    public Body updateRuralFlavor(@RequestParam("userId") long userId,@RequestParam("rfId") long rfId,@RequestParam("content") String content,
                               @RequestParam("stateType") int stateType,@RequestParam(value = "pictures", required = false) MultipartFile[] pictures,
                               @RequestParam(value = "video", required = false) MultipartFile video){
        boolean flag =ydRuralFlavorService.updateRuralFlavor(userId,rfId,content,stateType, pictures,video);
        if(flag){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }


    @ApiOperation("删除乡味信息")
    @PostMapping("/removeRuralFlavor")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="乡味id",required=true,paramType="query")
    })
    public Body removeRuralFlavor(@RequestParam("id") long id){
        boolean deleteRuralFlavorRelevantInfo = ydRuralFlavorService.deleteRuralFlavorRelevantInfo(id);
        if(deleteRuralFlavorRelevantInfo){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    @ApiOperation("修改乡味内容发布状态")
    @PostMapping("/updateReleaseType")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="乡味id",required=true,paramType="query")
    })
    public Body updateReleaseType(@RequestParam("id") long  id ){
        int updateReleaseType = ydRuralFlavorService.updateReleaseType(id);
        if(updateReleaseType == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("增加转发数")
    @PostMapping("/addForwardNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="rfId",value="乡味id",required=true,paramType="query")
    })
    public Body addForwardNum(@RequestParam("userId") long userId,@RequestParam("rfId") long rfId){
        boolean flag = ydRuralFlavorUserService.addForwardNum(userId,rfId);
        if(flag){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"增加失败");
        }
    }

    /**
     * 修改收藏状态
     * @param userId 用户id
     * @param rfId 乡味id
     * @param type 收藏状态 -1：取消收藏；1：收藏
     * @return
     */
    @ApiOperation("收藏")
    @PostMapping("/collection")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="rfId",value="乡味id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="收藏状态 -1：取消收藏；1：收藏",required=true,paramType="query")
    })
    public Body collection(@RequestParam("userId") long userId,@RequestParam("rfId") long rfId,@RequestParam("type") int type){
        boolean flag = ydRuralFlavorUserService.collection(userId,rfId,type);
        if(flag){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("赞或者踩")
    @PostMapping("/fabulousOrStep")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="rfId",value="乡味id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="赞或者踩状态 -1：取消收藏；1：收藏",required=true,paramType="query")
    })
    public Body fabulousOrStep(@RequestParam("userId") long  userId,@RequestParam("rfId") long  rfId,@RequestParam("type") int  type){
        boolean flag = ydRuralFlavorUserService.fabulousOrStep(userId,rfId,type);
        if(flag){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"赞或者踩失败");
        }
    }

    /**
     *  获取乡味评论数量
     * @param rfId 乡味id
     * @return
     */
    @ApiOperation("获取乡味评论数量")
    @PostMapping("/comment/getCommentNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name="rfId",value="乡味id",required=true,paramType="query")
    })
    public Body getCommentNum(@RequestParam("rfId") long rfId){
        JSONObject jsonObject = new JSONObject();
        int commentNum = ydRuralFlavorCommentService.selectCommentNumById(rfId);
        jsonObject.put("commentNum",commentNum);
        return Body.newInstance(jsonObject);
    }

    /**
     * 获取乡味评论信息
     * @param userId 用户id
     * @param commentObject 评论对象id 视频id或评论id
     * @param commentType 评论类别 1：评论乡味；2：回复评论
     * @return
     */
    @ApiOperation("获取乡味评论")
    @PostMapping("/comment/getCommentInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentObject",value="评论对象id 视频id或评论id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentType",value="评论类别 1：评论教学；2：回复评论",required=true,paramType="query")
    })
    public Body getCommentInfo(@RequestParam("userId") long userId,@RequestParam("commentObject") long commentObject,@RequestParam("commentType") int commentType){
        YdRuralFlavorComment ruralFlavorComment = new YdRuralFlavorComment();
        ruralFlavorComment.setCommentType(commentType);
        ruralFlavorComment.setCommentObject(commentObject);
        ruralFlavorComment.setUserId(userId);
        List<YdRuralFlavorComment> ydRuralFlavorComments = ydRuralFlavorCommentService.selectCommentInfoById(ruralFlavorComment);
        if(ydRuralFlavorComments.size()>0){
            return Body.newInstance(ydRuralFlavorComments);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     * 新增评论
     * @param userId 用户id
     * @param commentObject 评论对象id
     * @param commentType 评论类型 1：评论乡味；2：回复评论
     * @param name 用户昵称
     * @param photoPath 用户头像路径
     * @param content 评论内容
     * @return
     */
    @ApiOperation("新增评论")
    @PostMapping("/comment/addComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentObject",value="评论对象id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentType",value="评论类型 1：评论乡味；2：回复评论",required=true,paramType="query"),
            @ApiImplicitParam(name="name",value="用户昵称",required=true,paramType="query"),
            @ApiImplicitParam(name="photoPath",value="用户头像路径",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="评论内容",required=true,paramType="query"),
            @ApiImplicitParam(name="atUserIds[]",value="@的用户id数组",required=true,paramType="query")
    })
    public Body addComment(@RequestParam("userId") long userId,@RequestParam("commentObject") long commentObject,
                             @RequestParam("commentType") int commentType,@RequestParam("name") String name,
                             @RequestParam("photoPath") String photoPath,@RequestParam("content") String content,
                             @RequestParam(value = "atUserIds[]", required = false) List<Long> atUserIds){
        YdRuralFlavorComment ruralFlavorComment = new YdRuralFlavorComment();
        ruralFlavorComment.setCommentType(commentType);
        ruralFlavorComment.setCommentObject(commentObject);
        ruralFlavorComment.setUserId(userId);
        ruralFlavorComment.setName(name);
        ruralFlavorComment.setPhotoPath(photoPath);
        ruralFlavorComment.setContent(content);
        boolean flag = ydRuralFlavorCommentService.insertComment(ruralFlavorComment,atUserIds);
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
            @ApiImplicitParam(name="commentObject",value="评论对象id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentType",value="评论类型 1：评论乡味；2：回复评论",required=true,paramType="query"),
    })
    public Body removeComment(@RequestParam("commentId") long commentId,@RequestParam("commentObject") long commentObject,
                                @RequestParam("commentType") int commentType){
        YdRuralFlavorComment ydRuralFlavorComment = new YdRuralFlavorComment();
        ydRuralFlavorComment.setId(commentId);
        ydRuralFlavorComment.setCommentObject(commentObject);
        ydRuralFlavorComment.setCommentType(commentType);
        int deleteComment = ydRuralFlavorCommentService.deleteComment(ydRuralFlavorComment);
        if(deleteComment > 0){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    @ApiOperation("评论点赞")
    @PostMapping("/comment/fabulous")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="rfcId",value="评论id",required=true,paramType="query")
    })
    public Body fabulous(@RequestParam("userId") long userId,@RequestParam("rfcId") long rfcId){
        YdRuralFlavorCommentUser ydRuralFlavorCommentUser = new YdRuralFlavorCommentUser();
        ydRuralFlavorCommentUser.setRfcId(rfcId);
        ydRuralFlavorCommentUser.setUserId(userId);
        boolean flag = ydRuralFlavorCommentService.fabulousComment(ydRuralFlavorCommentUser);
        if(flag){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"点赞失败");
        }
    }

    @ApiOperation("取消点赞")
    @PostMapping("/comment/cancelFabulous")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="rfcId",value="评论id",required=true,paramType="query")
    })
    public Body cancelFabulous(@RequestParam("userId") long userId,@RequestParam("rfcId") long rfcId){
        YdRuralFlavorCommentUser ydRuralFlavorCommentUser = new YdRuralFlavorCommentUser();
        ydRuralFlavorCommentUser.setRfcId(rfcId);
        ydRuralFlavorCommentUser.setUserId(userId);
        boolean flag = ydRuralFlavorCommentService.deleteFabulous(ydRuralFlavorCommentUser);
        if(flag){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"取消点赞失败");
        }
    }

}
