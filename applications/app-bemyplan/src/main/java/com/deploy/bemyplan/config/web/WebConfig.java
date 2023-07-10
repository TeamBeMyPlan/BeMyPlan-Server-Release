package com.deploy.bemyplan.config.web;

import com.deploy.bemyplan.common.logging.DefaultLogInterceptor;
import com.deploy.bemyplan.common.logging.ErrorLogFilter;
import com.deploy.bemyplan.common.logging.ErrorLogInterceptor;
import com.deploy.bemyplan.config.auth.AuthInterceptor;
import com.deploy.bemyplan.config.auth.UserIdResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final DefaultLogInterceptor defaultLogInterceptor;
    private final UserIdResolver userIdResolver;
    private final ErrorLogInterceptor errorLogInterceptor;

    public WebConfig(AuthInterceptor authInterceptor, DefaultLogInterceptor defaultLogInterceptor, UserIdResolver userIdResolver, ErrorLogInterceptor errorLogInterceptor) {
        this.authInterceptor = authInterceptor;
        this.defaultLogInterceptor = defaultLogInterceptor;
        this.userIdResolver = userIdResolver;
        this.errorLogInterceptor = errorLogInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
        registry.addInterceptor(defaultLogInterceptor);
        registry.addInterceptor(errorLogInterceptor);
    }

    @Bean
    public FilterRegistrationBean filterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new ErrorLogFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userIdResolver);
    }
}
