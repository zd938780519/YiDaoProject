package com.ruoyi.framework.config.tokenInterceptor;

import com.ruoyi.framework.config.tokenInterceptor.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register","/appeal") 表示除了登陆与注册申诉之外，因为登陆注册申诉不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor).addPathPatterns("/yd/app/**").excludePathPatterns("/yd/app/login/**", "/yd/app/register/**","/yd/app/appeal/**","/yd/app/inspection/**");
    }




}
