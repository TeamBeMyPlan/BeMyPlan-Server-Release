package com.deploy.bemyplan.scrap.service;

import com.deploy.bemyplan.auth.application.UserDeleteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ScrapEventSubscriber {
    private final ScrapService scrapService;

    public ScrapEventSubscriber(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    @EventListener
    public void handleUserDeleteEvent(UserDeleteEvent event) {
        scrapService.deleteAllScrapByUser(event.getUserId());
    }
}
