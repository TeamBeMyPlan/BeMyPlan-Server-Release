package com.deploy.bemyplan.domain.plan;

import java.time.LocalDateTime;

public interface OrderedPlan {

    Long getId();

    String getTitle();

    String getThumbnailUrl();

    Integer getOrderPrice();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
