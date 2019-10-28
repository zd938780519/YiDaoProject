package com.ruoyi.MobileApi;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.Md5Utils;
import com.ruoyi.system.domain.User;
import com.ruoyi.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 学生 接口
 *
 * @author
 * @date 2019-01-28
 */
@Api("学生用户管理")
@Controller
@CrossOrigin
@RequestMapping("/app/user")
public class UserAppController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(UserAppController.class);
    @Autowired
    private IUserService userService;


    /**
     * 用户账号密码登录
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("用户账号密码登录")
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Body login(String username, String password){
        if (StringUtils.isEmpty(username)){return Body.BODY_400;};
        if (StringUtils.isEmpty(password)){return Body.BODY_400;};

        User student = userService.selectStudentByName(username);
        System.out.print("Md5Utils===="+ Md5Utils.hash(password));

        if (StringUtils.isNull(student)) return Body.newInstance(401,"用户不存在或密码错误");
        if (!student.getPassword().equals(Md5Utils.hash(password))) return Body.newInstance(401,"用户不存在或密码错误");
        return Body.newInstance(student);
    }




    /**
     * 获取个人信息
     */
    @GetMapping("/my")
    @ResponseBody
    public Body info(Integer sid)
    {
        if (StringUtils.isNull(sid)){return Body.BODY_400;};

        User my = userService.selectStudentById(sid);
        return Body.newInstance(my);
    }




}
