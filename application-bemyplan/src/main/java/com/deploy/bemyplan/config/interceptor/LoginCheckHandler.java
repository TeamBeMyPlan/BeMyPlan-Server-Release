package com.deploy.bemyplan.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.deploy.bemyplan.config.session.SessionConstants.USER_ID;

@RequiredArgsConstructor
@Component
public class LoginCheckHandler {

    private final SessionRepository<? extends Session> sessionRepository;
    private static final long TEMP_GUEST_MODE = -1L;

    Long getUserId(HttpServletRequest request) {
        String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasLength(sessionId)) {
            return TEMP_GUEST_MODE;
        }

        Session session = sessionRepository.findById(sessionId);
        if (session == null) {
            return TEMP_GUEST_MODE;
        }

        Long userId = session.getAttribute(USER_ID);
        if (userId != null) {
            return userId;
        }

        return TEMP_GUEST_MODE;
    }
}
