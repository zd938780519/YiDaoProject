package com.ruoyi.MobileApi;


import com.ruoyi.common.json.Body;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api("视频信息相关api")
@RestController
@RequestMapping("/yd/app/video")
public class YdVideoController {
    @Autowired
    private YdVideoService ydVideoService;
    @Autowired
    private YdVideoCommentService ydVideoCommentService;
    @Autowired
    private YdVideoUserService ydVideoUserService;
    @Autowired
    private YdVideoCommentAtUserService ydVideoCommentAtUserService;
    @Autowired
    private YdUserToUserService ydUserToUserService;


    @ApiOperation("新增视频信息")
    @PostMapping("/addVideo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录者id",required=true,paramType="query"),
            @ApiImplicitParam(name="thumbnail",value="缩略图",required=true,paramType="query"),
            @ApiImplicitParam(name="video",value="视频",required=true,paramType="query"),
            @ApiImplicitParam(name="score",value="评分",required=true,paramType="query"),
            @ApiImplicitParam(name="explanatoryText",value="说明文字",required=true,paramType="query"),
            @ApiImplicitParam(name="bgmName",value="bgm名称",required=true,paramType="query"),
            @ApiImplicitParam(name="bgm",value="bgm",required=true,paramType="query"),
            @ApiImplicitParam(name="supplementaryNotes",value="补充说明",required=true,paramType="query")
    })
    public Body addVideo(@RequestParam("userId") long userId, @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
                         @RequestParam(value = "video", required = false) MultipartFile video, @RequestParam("score") int score,
                         @RequestParam("explanatoryText") String explanatoryText,@RequestParam("bgmName") String bgmName,
                         @RequestParam(value = "bgm", required = false) MultipartFile bgm,@RequestParam("supplementaryNotes") String supplementaryNotes){
        boolean insertVideo = ydVideoService.insertVideo(userId, thumbnail, video, score, explanatoryText, bgmName, bgm, supplementaryNotes);
        if(insertVideo){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"新增失败");
        }
    }

    @ApiOperation("删除视频信息")
    @PostMapping("/removeVideo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="视频id",required=true,paramType="query")
    })
    public Body removeVideo(@RequestParam("id") long id){
        int deleteVideo = ydVideoService.deleteRelevantInfo(id);
        if(deleteVideo == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    @ApiOperation("输出视频信息列表")
    @PostMapping("/showVideosInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录者id",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getVideoList(@RequestParam("userId") long userId,@RequestParam("currIndex") int currIndex,
                               @RequestParam("pageSize") int pageSize){
        List<YdVideo> videoList = ydVideoService.selectAllVideo(userId, currIndex, pageSize);
        if(videoList.size()>0){
            return Body.newInstance(videoList);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }
    @ApiOperation("获取作品信息")
    @PostMapping("/getVideoSimpleInfoByUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录者id",required=true,paramType="query"),
            @ApiImplicitParam(name="sellerId",value="卖家表外键（普通用户或私厨或商家）",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getVideoSimpleInfoByUserId(@RequestParam("userId") long userId, @RequestParam("sellerId") long sellerId,@RequestParam("currIndex") int currIndex,
                                             @RequestParam("pageSize") int pageSize){
        List<YdVideo> ydVideos = ydVideoService.selectVideoByUserId(userId,sellerId,currIndex,pageSize);
        if(ydVideos.size()>0){
            return Body.newInstance(ydVideos);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     *  输出视频信息
     * @param videoId 视频id
     * @return
     */
    @ApiOperation("输出视频信息")
    @PostMapping("/showVideoInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="videoId",value="视频id",required=true,paramType="query")
    })
    public Body getVideoInfoById(@RequestParam("videoId") long videoId){
        YdVideo ydVideo = ydVideoService.selectVideoById(videoId);
        if(ydVideo != null){
            return Body.newInstance(ydVideo);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    /**
     *  获取视频评论数量
     * @param videoId 视频id
     * @return
     */
    @ApiOperation("获取视频评论数量")
    @PostMapping("/comment/getCommentNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name="videoId",value="视频id",required=true,paramType="query")
    })
    public Body getCommentNum(@RequestParam("videoId") long videoId){
        JSONObject jsonObject = new JSONObject();
        int commentNum = ydVideoCommentService.selectCommentNumById(videoId);
        jsonObject.put("commentNum",commentNum);
        return Body.newInstance(jsonObject);
    }

    /**
     * 获取视频评论信息
     * @param userId 用户id
     * @param commentObject 评论对象id 视频id或评论id
     * @param commentType 评论类别 1：评论视频；2：回复评论
     * @return
     */
    @ApiOperation("获取视频评论")
    @PostMapping("/comment/getCommentInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentObject",value="评论对象id 视频id或评论id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentType",value="评论类别 1：评论视频；2：回复评论",required=true,paramType="query")
    })
    public Body getCommentInfo(@RequestParam("userId") long userId,@RequestParam("commentObject") long commentObject,@RequestParam("commentType") int commentType){
        YdVideoComment videoComment = new YdVideoComment();
        videoComment.setCommentType(commentType);
        videoComment.setCommentObject(commentObject);
        videoComment.setUserId(userId);
        List<YdVideoComment> ydVideoComments = ydVideoCommentService.selectCommentInfoById(videoComment);
        if(ydVideoComments.size()>0){
            return Body.newInstance(ydVideoComments);
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
            @ApiImplicitParam(name="commentObject",value="评论对象id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentType",value="评论类型 1：评论视频；2：回复评论",required=true,paramType="query"),
            @ApiImplicitParam(name="name",value="用户昵称",required=true,paramType="query"),
            @ApiImplicitParam(name="photoPath",value="用户头像路径",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="评论内容",required=true,paramType="query"),
            @ApiImplicitParam(name="atUserIds[]",value="@的用户id数组",required=true,paramType="query")
    })
    public Body addComment(@RequestParam("userId") long userId,@RequestParam("commentObject") long commentObject,
                             @RequestParam("commentType") int commentType,@RequestParam("name") String name,
                             @RequestParam("photoPath") String photoPath,@RequestParam("content") String content,
                             @RequestParam(value = "atUserIds[]", required = false) List<Long> atUserIds){
        YdVideoComment videoComment = new YdVideoComment();
        videoComment.setCommentType(commentType);
        videoComment.setCommentObject(commentObject);
        videoComment.setUserId(userId);
        videoComment.setName(name);
        videoComment.setPhotoPath(photoPath);
        videoComment.setContent(content);
        boolean addComment = ydVideoCommentService.addComment(videoComment, atUserIds);
        if(addComment){
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
            @ApiImplicitParam(name="commentType",value="评论类型 1：评论视频；2：回复评论",required=true,paramType="query")
    })
    public Body removeComment(@RequestParam("commentId") long commentId,@RequestParam("commentObject") long commentObject,
                                @RequestParam("commentType") int commentType){
        YdVideoComment ydVideoComment = new YdVideoComment();
        ydVideoComment.setId(commentId);
        ydVideoComment.setCommentObject(commentObject);
        ydVideoComment.setCommentType(commentType);
        int deleteComment = ydVideoCommentService.deleteComment(ydVideoComment);
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
            @ApiImplicitParam(name="commentId",value="评论id",required=true,paramType="query")
    })
    public Body fabulous(@RequestParam("userId") long userId,@RequestParam("commentId") long commentId){
        YdVideoCommentUser ydVideoCommentUser = new YdVideoCommentUser();
        ydVideoCommentUser.setCommentId(commentId);
        ydVideoCommentUser.setUserId(userId);
        boolean fabulous = ydVideoCommentService.fabulous(ydVideoCommentUser);
        if(fabulous){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"点赞失败");
        }
    }

    @ApiOperation("取消点赞")
    @PostMapping("/comment/cancelFabulous")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="commentId",value="评论id",required=true,paramType="query")
    })
    public Body cancelFabulous(@RequestParam("userId") long userId,@RequestParam("commentId") long commentId){
        YdVideoCommentUser ydVideoCommentUser = new YdVideoCommentUser();
        ydVideoCommentUser.setCommentId(commentId);
        ydVideoCommentUser.setUserId(userId);
        boolean cancelFabulous = ydVideoCommentService.cancelFabulous(ydVideoCommentUser);
        if(cancelFabulous){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"取消失败");
        }
    }

    /**
     * 修改是否想吃的状态
     * @param userId 用户id
     * @param videoId 视频id
     * @param type 状态 0：不想吃；1：想吃
     * @return
     */
    @ApiOperation("想吃")
    @PostMapping("/wantEat")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="videoId",value="视频id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="状态 0：不想吃；1：想吃",required=true,paramType="query")
    })
    public Body wantEat(@RequestParam("userId") long userId,@RequestParam("videoId") long videoId,@RequestParam("type") int type){
        YdVideoUser ydVideoUser = new YdVideoUser();
        ydVideoUser.setVideoId(videoId);
        ydVideoUser.setUserId(userId);
        ydVideoUser.setIsWantEat(type);
        boolean wantEat = ydVideoService.wantEat(ydVideoUser);
        if(wantEat){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("增加转发数")
    @PostMapping("/addForwardNum")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="videoId",value="视频id",required=true,paramType="query")
    })
    public Body addForwardNum(@RequestParam("userId") long userId,@RequestParam("videoId") long videoId){
        YdVideoUser ydVideoUser = new YdVideoUser();
        ydVideoUser.setVideoId(videoId);
        ydVideoUser.setUserId(userId);
        ydVideoUser.setIsForward(1);
        boolean addForwardNum = ydVideoService.addForwardNum(ydVideoUser);
        if(addForwardNum){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"增加失败");
        }
    }

    @ApiOperation("收藏")
    @PostMapping("/collection")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="videoId",value="视频id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="状态 0：收藏；1：取消收藏",required=true,paramType="query")
    })
    public Body collection(@RequestParam("userId") long userId,@RequestParam("videoId") long videoId,@RequestParam("type") int type){
        YdVideoUser ydVideoUser = new YdVideoUser();
        ydVideoUser.setVideoId(videoId);
        ydVideoUser.setUserId(userId);
        ydVideoUser.setIsCollection(type);
        boolean collection = ydVideoUserService.collection(ydVideoUser);
        if(collection){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"收藏失败");
        }
    }

    @ApiOperation("根据用户备注或名称获取我关注的人信息列表")
    @PostMapping("/getFollowUserInfosByName")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="登录用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="name",value="用户备注或名称",required=true,paramType="query")
    })
    public Body getFollowUserInfosByName(@RequestParam("userId") long userId,@RequestParam(value="name",required = false) String name){
        YdUserToUser ydUserToUser = new YdUserToUser();
        ydUserToUser.setFromId(userId);
        ydUserToUser.setName(name);
        List<YdUserToUser> followUserInfosByName = ydUserToUserService.getFollowUserInfosByName(ydUserToUser);
        if(followUserInfosByName.size()>0){
            return Body.newInstance(followUserInfosByName);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

}
