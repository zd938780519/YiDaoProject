package com.ruoyi.MobileApi.personal.homepage;


import com.ruoyi.common.json.Body;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api("个人主页信息相关")
@RestController
@RequestMapping("/yd/app/personalHomePage")
@Transactional(rollbackFor=Exception.class)
public class YdUserInfoController {
    @Autowired
    private YdUserService ydUserService;
    @Autowired
    private YdUserToUserService ydUserToUserService;
    @Autowired
    private YdReportService ydReportService;
    @Autowired
    YdReportingRecordsImageService ydReportingRecordsImageService;
    @Autowired
    private YdUserToUserLetterService ydUserToUserLetterService;


    @ApiOperation("获取用户信息")
    @PostMapping("/getUserInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fromId",value="查看者id",required=true,paramType="query"),
            @ApiImplicitParam(name="toId",value="被查看者id",required=true,paramType="query")
    })
    public Body getUserInfo(@RequestParam("fromId") long fromId,@RequestParam("toId") long toId){
        YdUserToUser ydUserToUser = new YdUserToUser();
        ydUserToUser.setFromId(fromId);
        ydUserToUser.setToId(toId);
        YdUser ydUser = ydUserService.selectById(ydUserToUser);
        return Body.newInstance(ydUser);
    }

    @ApiOperation("修改用户数据")
    @PostMapping("/updateUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="用户id",required=true,paramType="query"),
            @ApiImplicitParam(name="userName",value="用户名",required=true,paramType="query"),
            @ApiImplicitParam(name="photoPath",value="头像路径",required=true,paramType="query"),
            @ApiImplicitParam(name="briefIntroduction",value="简介",required=true,paramType="query"),
            @ApiImplicitParam(name="sex",value="性别",required=true,paramType="query"),
            @ApiImplicitParam(name="sexShow",value="性别显示值",required=true,paramType="query"),
            @ApiImplicitParam(name="birthday",value="生日",required=true,paramType="query"),
            @ApiImplicitParam(name="follow",value="关注数",required=true,paramType="query"),
            @ApiImplicitParam(name="apprentice",value="拜师数",required=true,paramType="query"),
            @ApiImplicitParam(name="coverPhotoPath",value="封面图片路径",required=true,paramType="query"),
            @ApiImplicitParam(name="fansNum",value="粉丝数",required=true,paramType="query"),
            @ApiImplicitParam(name="foodInterestNum",value="食趣号",required=true,paramType="query"),
            @ApiImplicitParam(name="slogan",value="标语（排行）",required=true,paramType="query"),
            @ApiImplicitParam(name="worksNum",value="作品数",required=true,paramType="query"),
            @ApiImplicitParam(name="teachingNum",value="教学数",required=true,paramType="query"),
            @ApiImplicitParam(name="ruralFlavorNum",value="乡味数",required=true,paramType="query"),
            @ApiImplicitParam(name="currentAddress",value="当前地址",required=true,paramType="query"),
            @ApiImplicitParam(name="hometown",value="家乡地址",required=true,paramType="query")
    })
    public Body updateUser(@RequestParam("id") long id, @RequestParam(value = "userName", required = false) String userName,
                          @RequestParam(value = "photoPath", required = false) String photoPath,
                          @RequestParam(value = "briefIntroduction", required = false) String briefIntroduction, @RequestParam(value = "sex", required = false) String sex,
                          @RequestParam(value = "sexShow", required = false) String sexShow, @RequestParam(value = "birthday", required = false) String birthday,
                          @RequestParam(value = "follow", required = false) String follow, @RequestParam(value = "apprentice", required = false) String apprentice,
                          @RequestParam(value = "coverPhotoPath", required = false) String coverPhotoPath, @RequestParam(value = "fansNum", required = false) String fansNum,
                          @RequestParam(value = "foodInterestNum", required = false) String foodInterestNum, @RequestParam(value = "slogan", required = false) String slogan,
                          @RequestParam(value = "worksNum", required = false) String worksNum, @RequestParam(value = "teachingNum", required = false) String teachingNum,
                          @RequestParam(value = "ruralFlavorNum", required = false) String ruralFlavorNum, @RequestParam(value = "currentAddress", required = false) String currentAddress,
                          @RequestParam(value = "hometown", required = false) String hometown
    ){
        YdUser user = new YdUser();
        user.setId(id);
        user.setUserName(userName);
        user.setPhotoPath(photoPath);
        user.setBriefIntroduction(briefIntroduction);
        user.setSex(sex);
        user.setSexShow(sexShow);
        user.setBirthday(birthday);
        user.setFollow(follow);
        user.setApprentice(apprentice);
        user.setCoverPhotoPath(coverPhotoPath);
        user.setFansNum(fansNum);
        user.setFoodInterestNum(foodInterestNum);
        user.setSlogan(slogan);
        user.setWorksNum(worksNum);
        user.setTeachingNum(teachingNum);
        user.setRuralFlavorNum(ruralFlavorNum);
        user.setCurrentAddress(currentAddress);
        user.setHometown(hometown);
        int updateUser = ydUserService.updateUser(user);
        if(updateUser == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }


    @ApiOperation("修改关注状态")
    @PostMapping("/updateFollowType")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fromId",value="关注者id",required=true,paramType="query"),
            @ApiImplicitParam(name="toId",value="被关注者id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="修改关注状态 0：取消关注；1：关注",required=true,paramType="query")
    })
    public Body updateFollowType(@RequestParam("fromId") long fromId,@RequestParam("toId") long toId,@RequestParam("type") int type){
        YdUserToUser ydUserToUser = new YdUserToUser();
        ydUserToUser.setFromId(fromId);
        ydUserToUser.setToId(toId);
        ydUserToUser.setIsFollow(type);
        boolean updateRelationshipType = ydUserToUserService.updateRelationshipType(ydUserToUser);
        if(updateRelationshipType){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("修改拜师状态")
    @PostMapping("/updateApprenticeType")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fromId",value="拜师者id",required=true,paramType="query"),
            @ApiImplicitParam(name="toId",value="被拜师者id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="修改拜师状态 0：取消拜师；1：拜师",required=true,paramType="query")
    })
    public Body updateApprenticeType(@RequestParam("fromId") long fromId,@RequestParam("toId") long toId,@RequestParam("type") int type){
        YdUserToUser ydUserToUser = new YdUserToUser();
        ydUserToUser.setFromId(fromId);
        ydUserToUser.setToId(toId);
        ydUserToUser.setIsApprentice(type);
        boolean updateRelationshipType = ydUserToUserService.updateRelationshipType(ydUserToUser);
        if(updateRelationshipType){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("修改拉黑状态")
    @PostMapping("/updatePullBlackType")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fromId",value="拉黑者id",required=true,paramType="query"),
            @ApiImplicitParam(name="toId",value="被拉黑者id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="修改拉黑状态 0：取消拉黑；1：拉黑",required=true,paramType="query")
    })
    public Body updatePullBlackType(@RequestParam("fromId") long fromId,@RequestParam("toId") long toId,@RequestParam("type") int type){
        YdUserToUser ydUserToUser = new YdUserToUser();
        ydUserToUser.setFromId(fromId);
        ydUserToUser.setToId(toId);
        ydUserToUser.setIsPullBlack(type);
        boolean updateRelationshipType = ydUserToUserService.updateRelationshipType(ydUserToUser);
        if(updateRelationshipType){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"修改失败");
        }
    }

    @ApiOperation("获取拉黑状态")
    @PostMapping("/getPullBlackType")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fromId",value="登录者id",required=true,paramType="query"),
            @ApiImplicitParam(name="toId",value="需判断者id",required=true,paramType="query")
    })
    public Body getPullBlackType(@RequestParam("fromId") long fromId,@RequestParam("toId") long toId){
        JSONObject json =new JSONObject();
        YdUserToUser ydUserToUser = new YdUserToUser();
        ydUserToUser.setFromId(fromId);
        ydUserToUser.setToId(toId);
        boolean pullBlackType = ydUserToUserService.getPullBlackType(ydUserToUser);
        json.put("status",pullBlackType ?1:-1);
        return Body.newInstance(json);
    }

    @ApiOperation("新增举报信息")
    @PostMapping("/addReportUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fromId",value="登录者id",required=true,paramType="query"),
            @ApiImplicitParam(name="toId",value="被举报者id",required=true,paramType="query"),
            @ApiImplicitParam(name="reportType",value="举报类型",required=true,paramType="query"),
            @ApiImplicitParam(name="reportShow",value="举报类型显示值",required=true,paramType="query"),
            @ApiImplicitParam(name="reportReason",value="举报理由",required=true,paramType="query"),
            @ApiImplicitParam(name="processingStatus",value="处理状态 1：未处理；2：已警告；3：已封号",required=true,paramType="query"),
            @ApiImplicitParam(name="processingStatusShow",value="处理状态显示值",required=true,paramType="query"),
            @ApiImplicitParam(name="files",value="举报图片路径数组",required=true,paramType="query"),
    })
    public Body addReportUser(@RequestParam("fromId") long fromId,@RequestParam("toId") long toId,@RequestParam("reportType") int reportType,
                                @RequestParam("reportShow") String reportShow,@RequestParam("reportReason") String reportReason,
                                @RequestParam("processingStatus") int processingStatus,@RequestParam("processingStatusShow") String processingStatusShow,
                                @RequestParam(value = "files", required = false) MultipartFile[] files
                                ){
        YdReportingRecords ydReportingRecords = new YdReportingRecords();
        ydReportingRecords.setFromId(fromId);
        ydReportingRecords.setToId(toId);
        ydReportingRecords.setReportType(reportType);
        ydReportingRecords.setReportShow(reportShow);
        ydReportingRecords.setReportReason(reportReason);
        ydReportingRecords.setProcessingStatus(processingStatus);
        ydReportingRecords.setProcessingStatusShow(processingStatusShow);
        boolean insertReportInfo = ydReportService.insertReportInfo(ydReportingRecords, files);
        if(insertReportInfo){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"举报失败");
        }
    }

    @ApiOperation("新增私信信息")
    @PostMapping("/addLetter")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fromId",value="发信人id",required=true,paramType="query"),
            @ApiImplicitParam(name="toId",value="收信人id",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="私信内容",required=true,paramType="query")
    })
    public Body addLetter(@RequestParam("fromId") long fromId,@RequestParam("toId") long toId,@RequestParam("content") String content){
        YdUserToUserLetter ydUserToUserLetter = new YdUserToUserLetter();
        ydUserToUserLetter.setFromId(fromId);
        ydUserToUserLetter.setToId(toId);
        ydUserToUserLetter.setContent(content);
        int insertLetter = ydUserToUserLetterService.insertLetter(ydUserToUserLetter);
        if(insertLetter == 1){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"发送失败");
        }
    }

    @ApiOperation("获取私信信息")
    @PostMapping("/getLetters")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fromId",value="发信人id",required=true,paramType="query"),
            @ApiImplicitParam(name="toId",value="收信人id",required=true,paramType="query"),
            @ApiImplicitParam(name="currIndex",value="分页偏移量",required=true,paramType="query"),
            @ApiImplicitParam(name="pageSize",value="页大小",required=true,paramType="query")
    })
    public Body getLetters(@RequestParam("fromId") long fromId,@RequestParam("toId") long toId,
                             @RequestParam("currIndex") int currIndex,@RequestParam("pageSize") int pageSize){
        YdUserToUserLetter ydUserToUserLetter = new YdUserToUserLetter();
        ydUserToUserLetter.setFromId(fromId);
        ydUserToUserLetter.setToId(toId);
        ydUserToUserLetter.setCurrIndex(currIndex);
        ydUserToUserLetter.setPageSize(pageSize);
        List<YdUserToUserLetter> ydUserToUserLetters = ydUserToUserLetterService.selectLetters(ydUserToUserLetter);
        if(ydUserToUserLetters.size()>0){
            return Body.newInstance(ydUserToUserLetters);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

}
