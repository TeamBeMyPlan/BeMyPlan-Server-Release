package com.deploy.bemyplan.config.resolver;

import com.deploy.bemyplan.config.interceptor.Auth;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.deploy.bemyplan.common.type.VisitOptionConstants.GUEST_MODE;
import static com.deploy.bemyplan.config.session.SessionConstants.USER_ID;

@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class) && Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getMethodAnnotation(Auth.class) == null) {
            throw new RuntimeException("인증이 필요한 컨트롤러 입니다. @Auth 어노테이션을 붙여주세요.");
        }

        Object object = webRequest.getAttribute(USER_ID, 0);
        if (object == null) {
            throw new RuntimeException(String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(), parameter.getMethod()));
        }
        return object.equals(GUEST_MODE) ? null : object;
    }
}