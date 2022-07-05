package com.deploy.bemyplan.external.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.deploy.bemyplan")
@Configuration
public class FeignClientConfig {
}
