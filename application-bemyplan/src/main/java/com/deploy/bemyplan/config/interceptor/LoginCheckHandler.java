package com.deploy.bemyplan.config.interceptor;

import com.deploy.bemyplan.common.exception.model.UnAuthorizedException;
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
        if (StringUtils.hasLength(sessionId)) {
            Session session = findSessionBySessionId(sessionId);
            Long userId = session.getAttribute(USER_ID);
            if (userId != null) {
                return userId;
            }
        }

        return TEMP_GUEST_MODE;
    }

    private Session findSessionBySessionId(String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        if (session == null) {
            throw new UnAuthorizedException(String.format("잘못된 세션 (%S) 입니다", sessionId));
        }
        return session;
    }
}
