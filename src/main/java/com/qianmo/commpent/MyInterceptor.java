package com.qianmo.commpent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: QianMo
 * @Date: Create in 15:22 2020/4/3
 */
public class MyInterceptor implements HandlerInterceptor {
    private final Logger logger= LoggerFactory.getLogger(getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("经过了拦截器");
        Object username = request.getSession().getAttribute("username");
        if(username==null){
            logger.warn("用户未登录!");
            //request.setAttribute("msg","傻逼，不登录还想访问");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
        logger.info("用户已登录，放行!");
        return true;
    }
}
