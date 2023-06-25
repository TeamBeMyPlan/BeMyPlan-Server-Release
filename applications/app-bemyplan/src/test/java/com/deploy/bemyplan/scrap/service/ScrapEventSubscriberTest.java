package com.deploy.bemyplan.scrap.service;

import com.bemyplan.auth.application.UserDeleteEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ScrapEventSubscriberTest {
    private ScrapEventSubscriber scrapEventSubscriber;
    private ScrapService spyScrapService;

    @BeforeEach
    void setUp() {
        spyScrapService = mock(ScrapService.class);
        scrapEventSubscriber = new ScrapEventSubscriber(spyScrapService);
    }

    @Test
    @DisplayName("유저 탈퇴시 스크랩 삭제 이벤트 구독")
    void handleDeleteEvent_passesRequestToService() {
        UserDeleteEvent givenEvent = new UserDeleteEvent( 1L);
        scrapEventSubscriber.handleUserDeleteEvent(givenEvent);
        verify(spyScrapService, times(1)).deleteAllScrapByUser(givenEvent.getUserId());
    }
}