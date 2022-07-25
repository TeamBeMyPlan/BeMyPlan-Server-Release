package com.deploy.bemyplan.service.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
@EqualsAndHashCode
public class UserDeleteEvent extends ApplicationEvent {
    private Long userId;

    public UserDeleteEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }
}
