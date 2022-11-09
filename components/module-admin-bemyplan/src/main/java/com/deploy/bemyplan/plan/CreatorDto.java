package com.deploy.bemyplan.plan;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatorDto {
    private String name;

    @Builder(builderMethodName = "testBuilder")
    CreatorDto(final String name) {
        this.name = name;
    }
}