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

    private final JwtService jwtService;

    Long getUserId(final HttpServletRequest request) {
        final String token = request.getHeader(JwtHeader.AUTH);
        if (jwtService.verifyToken(token)) {
            final String subject = jwtService.getSubject(token);
            return convertToUserId(subject);
        }

        final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasLength(sessionId)) {
            throw new NotAuthException();
        }

        final Session session = sessionRepository.findById(sessionId);
        if (null == session) {
            throw new NotAuthException();
        }

        final Long userId = session.getAttribute(USER_ID);
        if (null != userId) {
            return userId;
        }

        throw new NotAuthException();
    }

    private long convertToUserId(final String subject) {
        try {
            return Long.parseLong(subject);
        } catch (final NumberFormatException e) {
            throw new NotAuthException();
        }
    }
}
