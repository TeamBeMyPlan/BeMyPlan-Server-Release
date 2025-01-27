package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
    public TagInfo(TravelTheme theme, TravelPartner partner, TravelMobility mobility, Money budget, final int month, final int totalDay) {
        this.theme = theme;
        this.partner = partner;
        this.mobility = mobility;
        this.budget = budget;
        this.month = month;
        this.totalDay = totalDay;
    }
}
