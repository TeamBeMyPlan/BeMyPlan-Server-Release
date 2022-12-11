package com.deploy.bemyplan.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j(topic = "ERROR_FILE_LOGGER")
@Component
@RequiredArgsConstructor
public class TestInterceptor implements HandlerInterceptor {


    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
        log.error("test");
    }

    private boolean isServerError(int status) {
        return status >= 500 && status < 600;
    }

    private boolean isClientError(int status) {
        return status >= 400 && status < 500;
    }
}
