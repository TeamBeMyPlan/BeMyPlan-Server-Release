package com.deploy.bemyplan.config.auth;

import com.deploy.bemyplan.jwt.JwtHeader;
import com.deploy.bemyplan.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class LoginCheckHandler {

    private final JwtService jwtService;

    Long getUserId(final HttpServletRequest request) {
        final String token = request.getHeader(JwtHeader.AUTH);
        if (jwtService.verifyToken(token)) {
            final String subject = jwtService.getSubject(token);
            return convertToUserId(subject);
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
