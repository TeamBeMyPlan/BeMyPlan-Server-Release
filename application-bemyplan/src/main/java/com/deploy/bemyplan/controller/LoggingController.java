package com.deploy.bemyplan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoggingController {

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }
}