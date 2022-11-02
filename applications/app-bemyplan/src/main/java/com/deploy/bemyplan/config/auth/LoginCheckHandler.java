package com.deploy.bemyplan.config.auth;

import com.deploy.bemyplan.jwt.JwtHeader;
import com.deploy.bemyplan.jwt.JwtService;
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

    private final JwtService jwtService;

    Long getUserId(final HttpServletRequest request) {
        final String token = request.getHeader(JwtHeader.AUTH);
        if (jwtService.verifyToken(token)) {
            final String subject = jwtService.getSubject(token);
            return convertToUserId(subject);
        }

        final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasLength(sessionId)) {
            return TEMP_GUEST_MODE;
        }

        final Session session = sessionRepository.findById(sessionId);
        if (null == session) {
            return TEMP_GUEST_MODE;
        }

        final Long userId = session.getAttribute(USER_ID);
        if (null != userId) {
            return userId;
        }

        return TEMP_GUEST_MODE;
    }

    private long convertToUserId(final String subject) {
        try {
            return Long.parseLong(subject);
        } catch (final NumberFormatException e) {
            return TEMP_GUEST_MODE;
        }
    }
}
