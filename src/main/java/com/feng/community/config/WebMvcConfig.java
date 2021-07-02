package com.feng.community.config;

import com.feng.community.controller.interceptor.DataInterceptor;
import com.feng.community.controller.interceptor.LoginRequiredInterceptor;
import com.feng.community.controller.interceptor.LoginTicketInterceptor;
import com.feng.community.controller.interceptor.MessageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

//    @Autowired
//    private LoginRequiredInterceptor loginRequiredInterceptor;

    @Autowired
    private MessageInterceptor messageInterceptor;

    @Autowired
    private DataInterceptor dataInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/css/**","/js/**");
//                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
//        .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**");

// excludePathPatterns("/", "/login","/css/**","/fonts/**","/images/**","/js/**");

        registry.addInterceptor(messageInterceptor)
                .excludePathPatterns("/css/**","/js/**");
//                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
//                .excludePathPatterns("/", "/login","/css/**","/fonts/**","/images/**","/js/**");
        registry.addInterceptor(dataInterceptor)
                .excludePathPatterns("/css/**","/js/**");
//                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
//                    .excludePathPatterns("/", "/login","/css/**","/fonts/**","/images/**","/js/**");
    }
}
