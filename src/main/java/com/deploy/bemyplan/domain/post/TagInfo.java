package com.deploy.bemyplan.domain.post;

import com.deploy.bemyplan.domain.common.Money;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class TagInfo {

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TravelTheme theme;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TravelPartner partner;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TravelMobility mobility;

    @Embedded
    private Money budget;
}
