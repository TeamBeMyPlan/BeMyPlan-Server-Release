package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.Money;
import lombok.*;

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

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int totalDay;

    @Builder(builderMethodName = "testBuilder", access = AccessLevel.PUBLIC)
    public TagInfo(TravelTheme theme, TravelPartner partner, TravelMobility mobility, Money budget) {
        this.theme = theme;
        this.partner = partner;
        this.mobility = mobility;
        this.budget = budget;
    }
}
