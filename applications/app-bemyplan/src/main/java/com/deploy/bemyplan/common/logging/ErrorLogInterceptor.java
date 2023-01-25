package com.deploy.bemyplan.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j(topic = "ERROR_FILE_LOGGER")
@Component
@RequiredArgsConstructor
public class ErrorLogInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
        if (isClientError(response.getStatus()) || isServerError(response.getStatus())) {
            final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
            final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

            log.error(cachingRequest.getMethod() +
                            " URL: {},  STATUSCODE : {}, REQBODY : {}, RESBODY {}",
                    cachingRequest.getRequestURL(),
                    cachingResponse.getStatus(),
                    objectMapper.readTree(cachingRequest.getContentAsByteArray()),
                    objectMapper.readTree(cachingResponse.getContentAsByteArray())
            );
        }
    }

    private boolean isServerError(int status) {
        return status >= 500 && status < 600;
    }

    private boolean isClientError(int status) {
        return status != 404 && status >= 400 && status < 500;
    }
}
