package com.deploy.bemyplan.common.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DefaultLogInterceptorTest {

    private static final Logger log = LoggerFactory.getLogger(DefaultLogInterceptorTest.class);

    @Test
    void logTest() {
        log.trace("trace test");
        log.debug("debug test");
        log.info("info test");
        log.warn("warn test");
        log.error("error test");
    }
}