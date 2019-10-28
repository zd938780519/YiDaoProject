package com.ruoyi.framework.config.tokenInterceptor.interceptor;

import com.ruoyi.common.json.JSONObject;

import com.ruoyi.system.domain.YdUserLogin;
import com.ruoyi.system.service.YdUserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.UUID;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private YdUserLoginService ydUserLoginService;

    /**
     * token 过期时间,单位为分钟
     */
    private static final int TOKEN_EXPIRATION_TIME = 43200;//15;测试阶段，先改为30天过期

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
//        session.setAttribute("userId",userId);
        //这里的User是登陆时放入session的
        String userId = String.valueOf(session.getAttribute("userId"));
        log.info("userId==="+userId);
        if (userId.equals("null") || userId.equals("")){
            log.info("------------不进入拦截器---------------");
            return true;
        }else {
            log.info("------------进入拦截器---------------");

            String tokenValue = request.getHeader("tokenValue");
            String abstractValue = request.getHeader("abstractValue");
            String userIdStr = request.getParameter("userId");
            if(userIdStr == null || userIdStr.equals("null")){
                return false;
            }else{
            long userIdC = Long.parseLong(userIdStr);
                if(tokenValue != null && !tokenValue.equals("null")){
                    YdUserLogin ydUserLogin = ydUserLoginService.selectToken(userIdC);
                    if(ydUserLogin == null){
                        return false;
                    }else{
                        String oldToken = ydUserLogin.getTokenValue();
                        if(oldToken == null || oldToken.length() == 0 || oldToken.equals("null")){
                            return false;
                        }else{
                            if(abstractValue == null || abstractValue.equals("null")){
                                String[] oldTokenSplit = oldToken.split("-");
                                if(oldToken.equals(tokenValue)){
                                    if((System.currentTimeMillis() - Long.parseLong(oldTokenSplit[1])) / 60000>=TOKEN_EXPIRATION_TIME){
                                        PrintWriter out = response.getWriter();
                                        JSONObject json = new JSONObject();
                                        json.put("status","2");
                                        json.put("error","tokenOverdue");
                                        out.print(json.toString());
                                        out.close();
                                        return false;
                                    }else{
                                        return true;
                                    }
                                }else{
                                    return false;
                                }
                            }else{
                                YdUserLogin ydUserLogin1 = ydUserLoginService.selectAbstract(userIdC);
                                String oldAbstractValue = ydUserLogin1.getAbstractValue();
                                if(oldAbstractValue != null && !oldAbstractValue.equals("null") && oldAbstractValue.equals(abstractValue)){
                                    long time = System.currentTimeMillis();
                                    String newTokenValue = UUID.randomUUID().toString().replaceAll("-","") + "-" + time;
                                    String newAbstractValue = UUID.randomUUID().toString().replaceAll("-", "") + "-" + time ;
                                    ydUserLoginService.updateToken(userIdC,newTokenValue);
                                    ydUserLoginService.updateAbstract(userIdC,newAbstractValue);
                                    PrintWriter out = response.getWriter();
                                    JSONObject json = new JSONObject();
                                    json.put("status","1");
                                    json.put("token",newTokenValue);
                                    json.put("abstract",newAbstractValue);
                                    out.print(json.toString());
                                    out.close();
                                }
                                return false;
                            }
                        }
                    }
                }else{
                    return false;
                }
            }
        }

       /* User user = (User) session.getAttribute("user");
        //如果session中没有user，表示没登陆
        if (user == null){
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            return false;
        }else {
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }*/


    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
