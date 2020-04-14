package com.qianmo.config;

import com.qianmo.commpent.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: QianMo
 * @Date: Create in 17:33 2020/4/4
 */
@Configuration
public class OvSpringMvc implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/register").setViewName("login/register");
        registry.addViewController("/login").setViewName("login/login");
        registry.addViewController("/forgetPwd").setViewName("login/forgetPwd");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }
    //自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/search","/searchlist","/setmsg","/message","/mlist","/register","/login","/getUser","/forgetPwd","/user/login","/asserts/**","/about","/error","/user/register","/user/sendSms","/list");
    }
}