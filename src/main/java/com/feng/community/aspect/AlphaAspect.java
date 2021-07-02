package com.feng.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Deprecated
//@Component
//@Aspect
public class AlphaAspect {


    @Pointcut("execution(* com.feng.community.service.*.*(..))")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(){
        System.out.println("before");
    }

    @After("pointCut()")
    public void after(){
        System.out.println("after");
    }

    @AfterReturning("pointCut()")
    public void afterRetuning(){
        System.out.println("afterRetuning");
    }
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("AfterThrowing");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
        System.out.println("around before");
        Object proceed = joinPoint.proceed();
        System.out.println("around after");
        return proceed;
    }
}
