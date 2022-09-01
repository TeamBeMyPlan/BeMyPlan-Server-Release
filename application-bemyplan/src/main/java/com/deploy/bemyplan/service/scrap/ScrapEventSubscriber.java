package com.deploy.bemyplan.service.scrap;

import com.deploy.bemyplan.service.user.UserDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScrapEventSubscriber {
    private final ScrapService scrapService;

    @EventListener
    public void handleUserDeleteEvent(UserDeleteEvent event) {
        scrapService.deleteAllScrapByUser(event.getUserId());
    }
}