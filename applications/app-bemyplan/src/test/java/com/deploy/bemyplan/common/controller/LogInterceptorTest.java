package com.deploy.bemyplan.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class LogInterceptorTest {

    @Test
    void logTest() {
        log.trace("trace test");
        log.debug("debug test");
        log.info("info test");
        log.warn("warn test");
        log.error("error test");
    }
}