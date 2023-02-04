package com.deploy.bemyplan.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoggingController {

    @GetMapping("/health")
    public ResponseDTO checkHealth() {
        return ResponseDTO.of(" ٩(๑˃̵ᴗ˂̵)و ✧*.◟(ˊᗨˋ)◞.*✧ (°∀°)b We're be my plan Server!");
    }
}
