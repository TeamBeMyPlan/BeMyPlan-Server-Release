package com.deploy.bemyplan.config.auth;

import com.deploy.bemyplan.jwt.JwtHeader;
import com.deploy.bemyplan.jwt.JwtService;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.deploy.bemyplan.config.session.SessionConstants.USER_ID;

@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {

    private final JwtService jwtService;

    public UserIdResolver(final JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class) && Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NotNull final MethodParameter parameter, final ModelAndViewContainer mavContainer, @NotNull final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String token = request.getHeader(JwtHeader.AUTH);
        final Object object = webRequest.getAttribute(USER_ID, 0);
        if (null == object && !jwtService.verifyToken(token)) {
            return null;
        }

        if (null != object) {
            return object;
        }

        final String subject = jwtService.getSubject(token);

        try {
            return Long.parseLong(subject);
        } catch (final NumberFormatException e) {
            return null;
        }
    }
}