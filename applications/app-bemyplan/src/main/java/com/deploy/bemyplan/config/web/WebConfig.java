package com.deploy.bemyplan.config.web;

import com.deploy.bemyplan.common.controller.LogInterceptor;
import com.deploy.bemyplan.config.auth.AuthInterceptor;
import com.deploy.bemyplan.config.auth.UserIdResolver;
import com.deploy.bemyplan.test.TestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final LogInterceptor logInterceptor;
    private final UserIdResolver userIdResolver;
    private final TestInterceptor testInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(testInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userIdResolver);
    }
}
