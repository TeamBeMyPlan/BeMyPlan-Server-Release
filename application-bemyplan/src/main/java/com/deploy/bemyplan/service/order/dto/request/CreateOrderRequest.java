package com.deploy.bemyplan.service.order.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrderRequest {

    @NotNull(message = "{plan.id.notNull}")
    private Long planId;
}