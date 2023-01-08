package com.deploy.bemyplan.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ResponseProperties.class)
public class ResponseAutoConfiguration {
}
