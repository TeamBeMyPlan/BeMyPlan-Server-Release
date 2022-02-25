package com.deploy.bemyplan.config.interceptor;

import com.deploy.bemyplan.common.exception.ErrorCode;
import com.deploy.bemyplan.common.exception.model.UnAuthorizedException;
import com.deploy.bemyplan.common.exception.model.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.deploy.bemyplan.common.exception.ErrorCode.VALIDATION_SESSION_EXCEPTION;
import static com.deploy.bemyplan.common.type.VisitOptionConstants.*;
import static com.deploy.bemyplan.config.session.SessionConstants.USER_ID;

@RequiredArgsConstructor
@Component
public class LoginCheckHandler {

    private final SessionRepository<? extends Session> sessionRepository;

    Long getUserId(HttpServletRequest request) {
        String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        String visitOption = request.getHeader(VISIT_OPTION_HEADER);

        switch (visitOption) {
            case MEMBERSHIP:
                if (StringUtils.hasLength(sessionId)) {
                    Session session = findSessionBySessionId(sessionId);
                    Long userId = session.getAttribute(USER_ID);
                    if (userId != null) {
                        return userId;
                    }
                }
                throw new ValidationException(String.format("잘못된 세션 (%S) 입니다", sessionId), VALIDATION_SESSION_EXCEPTION);
            case GUEST:
                return GUEST_MODE;
            default:
                throw new ValidationException(String.format("허용하지 않는 Visit-Option 헤더값 (%s) 입니다. [MEMBERSHIP 또는 GUEST]", visitOption));
        }
    }

    private Session findSessionBySessionId(String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        if (session == null) {
            throw new UnAuthorizedException(String.format("잘못된 세션 (%S) 입니다", sessionId));
        }
        return session;
    }
}
