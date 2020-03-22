package com.ssm.activity.filter;


import com.ssm.activity.domain.po.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户请求过滤器
 */
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //请求的url
        String requestURI = request.getRequestURI();
        //登录接口直接放行
        if (requestURI.endsWith("/activity/login")
                || requestURI.endsWith("/activity/forget")
                || requestURI.endsWith("/activity/getCode")
                || requestURI.endsWith("/activity/password")
                || requestURI.contains("/static/")


        ) {

            chain.doFilter(request, response);
            return;
        }
        User userLogin = (User) request.getSession().getAttribute("userLogin");
        if (userLogin != null) {
            //判断url里面是否带有admin，去判断当前登录用户是否是管理员，否，则不允许放行
            if(requestURI.contains("admin")){
                int isAdmin = userLogin.getIsAdmin();
                if (isAdmin != 1) {
                    //不是管理员，重定向到登录页面
                    requestURI = "/activity/login";
                    response.sendRedirect(requestURI);
                }
            }
            //登录了，则放行
            chain.doFilter(request,response);
            return;
        }
        //重定向到登录页面
        requestURI = "/activity/login";
        response.sendRedirect(requestURI);

    }
}
