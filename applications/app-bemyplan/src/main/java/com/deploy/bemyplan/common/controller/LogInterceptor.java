package com.deploy.bemyplan.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j(topic = "DEFAULT_FILE_LOGGER")
@Component
public class LogInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("url : {}", request.getRequestURL());
        log.info("request : {} response : {}", request.getMethod(), request.getRequestURL());

        return true;
    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
//                                Object obj, Exception e) {
//        if (null != e) log.error("Exception", e);
//    }
}
