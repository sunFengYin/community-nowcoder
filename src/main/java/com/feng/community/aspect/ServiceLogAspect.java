package com.feng.community.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
//
//@Component
//@Aspect
@Slf4j
public class ServiceLogAspect {

    @Pointcut("execution(* com.feng.community.service.*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        ServletRequest attributes= (ServletRequest) RequestContextHolder.getRequestAttributes();
        String ip= attributes.getRemoteHost();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.info(String.format("用户[%s],在[%s],访问了[%s].", ip, now, target));

    }
}
