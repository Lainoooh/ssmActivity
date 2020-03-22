package com.ssm.activity.config;

import com.ssm.activity.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Component
public class WebConfig implements WebMvcConfigurer {
    /*
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new ResourceInterceptor()).excludePathPatterns("/static/**");
//    }

//    @Override
//    //需要告知系统，这是要被当成静态文件的！
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 设置文件上传的文件不拦截
////	        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ TaleUtils.getUplodFilePath()+"upload/");
//        //第一个方法设置访问路径前缀，第二个方法设置资源路径
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//
//    }

    /**
     * 重写路径配置方法，添加自定义的路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/webStatic/** 访问都映射到classpath:/webStatic/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public FilterRegistrationBean<UserFilter> filterAuthorizedUser() {
        FilterRegistrationBean<UserFilter> registration = new FilterRegistrationBean<>(new UserFilter());
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        return registration;
    }
}
