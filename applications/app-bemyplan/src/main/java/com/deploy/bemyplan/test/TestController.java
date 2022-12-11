package com.deploy.bemyplan.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @GetMapping("/test")
    public String test() {
        log.error("헬로");
        log.info("설명입니당");
        log.debug("디버깅");
        return "test";
    }

    @GetMapping("/test2")
    public String test2() {
        throw new RuntimeException();
    }
}
