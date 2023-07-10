package com.deploy.bemyplan.common.logging;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultLogInterceptor implements AsyncHandlerInterceptor {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger("DEFAULT_FILE_LOGGER");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("url : {}", request.getRequestURL());
        log.info("request : {} response : {}", request.getMethod(), request.getRequestURL());

        return true;
    }
}
