package com.feng.community.controller.advice;


import com.feng.community.util.CommunityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常的处理
 *
 */
//@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class ExceptionAdvice  {
    @ExceptionHandler({Exception.class})
    public void hadelerExceprion(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error("服务器异常：{}"+e.getMessage());
        for(StackTraceElement element:e.getStackTrace()){
            log.error(element.toString());
        }

        String xRequestedWith = request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJSONString(1, "服务器异常!"));
        } else {
            response.sendRedirect(request.getContextPath() + "/error");
        }

    }
}
