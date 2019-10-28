package com.ruoyi.MobileApi.personal;


import com.ruoyi.common.json.Body;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.shortMessageVerification.PhoneCode;
import com.ruoyi.common.utils.shortMessageVerification.ThirdPartyUtil;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.IYdCodeService;
import com.ruoyi.system.service.YdSellerService;
import com.ruoyi.system.service.YdUserLoginService;
import com.ruoyi.system.service.YdUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Api("用户管理")
@RestController
@RequestMapping("/yd/app")
@Transactional(rollbackFor=Exception.class)
public class YdUserAppLoginController {
    @Autowired
    private YdUserLoginService ydUserLoginService;
    @Autowired
    private YdUserService ydUserService;
    @Autowired
    private YdSellerService ydSellerService;
    @Autowired
    private IYdCodeService ydCodeService;

    /**
     * @param phonenum 登录手机号
     * @param password 密码凭证 或者短信验证码凭证
//     * @param identityType 登录类型 1：手机号验证码登录；2：账号密码登录；3：qq登录；4：微信登录；5：新浪微博登录
     * @return
     */
    @ApiOperation("登录并返回token值和abstract值")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="phonenum",value="手机号",required=true,paramType="query"),
            @ApiImplicitParam(name="password",value="密码",required=true,paramType="query"),
            @ApiImplicitParam(name="identityType",value="登录类型 1：手机号验证码登录；2：账号密码登录；3：qq登录；4：微信登录；5：新浪微博登录",required=true,paramType="query"),
            @ApiImplicitParam(name="name",value="第三方昵称",required=true,paramType="query"),
            @ApiImplicitParam(name="photoPath",value="第三方头像路径",required=true,paramType="query")
    })
    @ResponseBody
    public Body getToken(@RequestParam("phonenum") String phonenum, @RequestParam("password") String password, @RequestParam("identityType") int identityType,
                         @RequestParam(value = "name", required = false) String name, @RequestParam(value = "photoPath", required = false) String photoPath,HttpServletRequest request){
        String credentialMD5 = "";
        if(password != null && password.length()!=0 && !password.equals("null") && !password.equals("unidfined")){
            credentialMD5= DigestUtils.md5Hex(password);//TODO MD5加密
        }
        YdUserLogin ydUserLogin ;
        ydUserLogin = ydUserLoginService.selectUserLogin(phonenum, DigestUtils.md5Hex(password),identityType);
        if(identityType == 2){
            if(ydUserLogin != null){
                int prohibition = ydUserService.isProhibition(ydUserLogin.getUserId());
                if(prohibition == 1){
                    return Body.newInstance(201,"账号涉嫌违规");
                }else{
                    return setTokenAndAbstract(ydUserLogin,request);
                }
            }else{
                return Body.newInstance(202,"用户名或密码有误，登录失败");
            }
        }else{
            boolean flag = false;
            if(ydUserLogin == null){//第一次使用验证码登录或第三方登录，需要自动创建用户
                YdUser ydUserNew = new YdUser();
                ydUserNew.setUserName("yd_"+System.currentTimeMillis());
                if(identityType == 1){
                        ydUserNew.setPhoneNum(phonenum);
                }
                int insertUser = ydUserService.insertUser(ydUserNew);
                YdUserLogin ydUserLoginNew = new YdUserLogin();
                ydUserLoginNew.setUserId(ydUserNew.getId());
                ydUserLoginNew.setIdentityType(identityType);
                ydUserLoginNew.setIdentityTest(ThirdPartyUtil.loginModeMap.get(identityType));
                ydUserLoginNew.setIdentifier(phonenum);
                ydUserLoginNew.setCredential(credentialMD5);
                int insertUserLogin = ydUserLoginService.insertUserLogin(ydUserLoginNew);
                YdSeller ydSeller= new YdSeller();
                ydSeller.setType(1);
                ydSeller.setPkId(ydUserNew.getId());
                ydSeller.setName(name);
                ydSeller.setPhotoPath(photoPath);
                ydSeller.setIsLiveBroadcast(0);
                int insertSeller = ydSellerService.insertSeller(ydSeller);
                if(insertUser == 1 && insertUserLogin == 1 && insertSeller == 1){
                    flag = true;
                    ydUserLogin = ydUserLoginNew;
                }
            }else{
                flag = true;
            }
            if(flag){
                int prohibition = ydUserService.isProhibition(ydUserLogin.getUserId());
                if(prohibition == 1){
                    return Body.newInstance(201,"账号涉嫌违规");
                }else{
                    if (identityType == 1){
                        String mCode =  ydCodeService.selectYdCode(phonenum);
                        if (password.equals(mCode)){
                            return setTokenAndAbstract(ydUserLogin,request);
                        }else {
                            return Body.newInstance(203,"验证码错误");
                        }
                    }else {
                        return setTokenAndAbstract(ydUserLogin,request);
                    }
                }
            }else{
                return Body.newInstance(203,"第三方登录失败");
            }
        }
    }


    @ApiOperation("验证验证码")
    @RequestMapping(value = "/inspection/code",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="phonenum",value="手机号",required=true,paramType="query"),
            @ApiImplicitParam(name="mCode",value="验证码",required=true,paramType="query")
    })
    @ResponseBody
    public Body checkCode(@RequestParam("phonenum") String phonenum,@RequestParam("mCode") String mCode){
        if (StringUtils.isEmpty(phonenum)){return Body.BODY_400;};
        if (StringUtils.isEmpty(mCode)){return Body.BODY_400;}
        String mcheckCode =  ydCodeService.selectYdCode(phonenum);
        if (mCode.equals(mcheckCode)){
            return Body.newInstance(200,"验证成功！");
        }else {
            return Body.newInstance(202,"验证错误！");
        }
    }



    /**
     * 登录成功后设置token相关信息
     * @param ydUserLogin 登录状态信息
     */
    private Body setTokenAndAbstract(YdUserLogin ydUserLogin, HttpServletRequest request){
        long userId = ydUserLogin.getUserId();
        JSONObject json = new JSONObject();
        long time = System.currentTimeMillis();
        String tokenValue = UUID.randomUUID().toString().replaceAll("-", "") + "-" + time ;
        String abstractValue = UUID.randomUUID().toString().replaceAll("-", "") + "-" + time ;
        int updateToken = ydUserLoginService.updateToken(userId, tokenValue);
        int updateAbstract = ydUserLoginService.updateAbstract(userId, abstractValue);
        if(updateToken == 1 && updateAbstract == 1){
            json.put("tokenValue",tokenValue);
            json.put("abstractValue",abstractValue);
            YdUser ydUser = ydUserService.selectUserById(userId);
            json.put("userId",userId);
            json.put("userName",ydUser.getUserName());
            json.put("photoPath",ydUser.getPhotoPath());
            json.put("isPeripheryRestaurant",ydUser.getIsPeripheryRestaurant());
            HttpSession session = request.getSession();
            session.setAttribute("userId",userId);
            return Body.newInstance(json);
        }else{
            return Body.newInstance(204,"登录失败");
        }
    }

    /**
     * 短信验证
     * @param mobile 手机号
     * @return
     */
    @ApiOperation("获取短信验证码")
    @PostMapping("/login/phoneMsg")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mobile",value="手机号",required=true,paramType="query")
    })
    public Body getVerificationCode (@RequestParam("mobile") String mobile){
        YdCode ydCode =  ydCodeService.selectYdCodeById(mobile);
        YdCode newYdCode = new YdCode();
        String code = PhoneCode.vcode();
        newYdCode.setPhone(mobile);
        newYdCode.setMCode(code);
        if (StringUtils.isNull(ydCode)){
            ydCodeService.insertYdCode(newYdCode);
        }else {
            ydCodeService.updateYdCode(newYdCode);
        }

        JSONObject json = new JSONObject();
        PhoneCode.getPhonemsg(mobile,code,json);
        json.put("code",code);
        return Body.newInstance(json);
    }

    /**
     * 注册
     * @param credential 密码
     * @param phoneNum 手机号
     * @param identityType 登录类型 2：账号密码登录
     * @return
     */
    @ApiOperation("注册账号")
    @PostMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name="credential",value="密码",required=true,paramType="query"),
            @ApiImplicitParam(name="phoneNum",value="手机号",required=true,paramType="query"),
            @ApiImplicitParam(name="identityType",value="登录类型",required=true,paramType="query")
    })
    public Body register(@RequestParam("credential") String credential,
                           @RequestParam("phoneNum") String phoneNum,@RequestParam("identityType") int identityType){
        JSONObject json = new JSONObject();
        int selectPhoneNum = ydUserService.selectPhoneNum(phoneNum);
        if(selectPhoneNum>0){
            return Body.newInstance(201,"注册失败,手机号已存在");
        }else{
            String credentialMD5= DigestUtils.md5Hex(credential);//TODO MD5加密
            YdUser ydUser = new YdUser();
            ydUser.setUserName("yd_"+System.currentTimeMillis());
            ydUser.setPhoneNum(phoneNum);
            int insertUser = ydUserService.insertUser(ydUser);
            YdUserLogin ydUserLogin = new YdUserLogin();
            ydUserLogin.setUserId(ydUser.getId());
            ydUserLogin.setIdentityType(identityType);
            ydUserLogin.setIdentityTest(ThirdPartyUtil.loginModeMap.get(identityType));
            ydUserLogin.setCredential(credentialMD5);
            ydUserLogin.setIdentifier(phoneNum);
            int insertUserLogin = ydUserLoginService.insertUserLogin(ydUserLogin);
            YdSeller ydSeller= new YdSeller();
            ydSeller.setType(1);
            ydSeller.setPkId(ydUser.getId());
            ydSeller.setName(ydUser.getUserName());
            ydSeller.setPhotoPath("default");
            ydSeller.setIsLiveBroadcast(0);
            int insertSeller = ydSellerService.insertSeller(ydSeller);
            if(insertUser == 1 && insertUserLogin == 1 && insertSeller == 1){
                return Body.newInstance(200,"注册成功");
            }else{
                return Body.newInstance(202,"注册失败");
            }
        }
    }



    /**
    * 修改密码
    * @param phonenum
    * @param password
    * @return
     */
    @ApiOperation("修改密码")
    @RequestMapping(value = "/login/updatePassword",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="phonenum",value="手机号",required=true,paramType="query"),
            @ApiImplicitParam(name="password",value="密码",required=true,paramType="query")
    })
    @ResponseBody
    public Body updatePassword(@RequestParam("phonenum") String phonenum,@RequestParam("password") String password){
        String passwordMD5 = "";
        if(password != null && password.length()!=0 && !password.equals("null") && !password.equals("unidfined")){
            passwordMD5= DigestUtils.md5Hex(password);//TODO MD5加密
        }
        YdUserLogin ydUserLogin = new YdUserLogin();
        ydUserLogin.setIdentifier(phonenum);
        ydUserLogin.setCredential(passwordMD5);
        ydUserLogin.setIdentityType(2);
        int updatePassword = ydUserLoginService.updatePassword(ydUserLogin);
        if(updatePassword == 1){
            return Body.newInstance(200,"密码修改成功，请重新登录！");
        }else{
            return Body.newInstance(201,"密码修改失败！");
        }
    }

}
