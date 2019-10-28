package com.ruoyi.MobileApi.personal.homepage;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.service.YdUserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("申诉相关api")
@RestController
@RequestMapping("/yd/app/appeal")
public class YdUserAppealController {

    @Autowired
    private YdUserLoginService ydUserLoginService;

    @ApiOperation("申诉")
    @PostMapping("/addAppeal")
    @ApiImplicitParams({
            @ApiImplicitParam(name="identifier",value="标识（手机号 用户名或第三方应用的唯一标识）",required=true,paramType="query"),
            @ApiImplicitParam(name="credential",value="密码凭证",required=true,paramType="query"),
            @ApiImplicitParam(name="identityType",value="登录类型 1：手机号验证码登录；2：账号密码登录；3：qq登录；4：微信登录；5：新浪微博登录",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="申诉内容",required=true,paramType="query")
    })
    public Body appeal(@RequestParam("identifier") String identifier, @RequestParam("credential") String credential,
                         @RequestParam("identityType") int identityType, @RequestParam("content") String content){
        int returnVal = ydUserLoginService.appeal(identifier, credential, identityType, content);
        if(returnVal == 1){
            return Body.newInstance();
        }else if(returnVal == -1){
            return Body.newInstance(201,"账号登录异常");
        }else if(returnVal == -2){
            return Body.newInstance(202,"申诉失败");
        }else{
            return Body.newInstance(203,"未知异常");
        }
    }

}
