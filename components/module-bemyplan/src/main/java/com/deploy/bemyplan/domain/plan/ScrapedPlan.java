package com.deploy.bemyplan.domain.plan;

import java.time.LocalDateTime;

public interface ScrapedPlan {
    Long getId();

    String getTitle();

    String getThumbnailUrl();

    LocalDateTime getCreatedAt();
}
