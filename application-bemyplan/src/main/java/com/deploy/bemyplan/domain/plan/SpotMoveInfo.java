package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SpotMoveInfo extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private DailySchedule schedule;

    @Column(nullable = false)
    private Long fromSpotId;

    @Column(nullable = false)
    private Long toSpotId;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TravelMobility mobility;

    @Column(nullable = false)
    private int spentMinute;

    @Builder(builderMethodName = "testBuilder", access = AccessLevel.PUBLIC)
    private SpotMoveInfo(DailySchedule schedule, Long fromSpotId, Long toSpotId, TravelMobility mobility, int spentMinute) {
        this.schedule = schedule;
        this.fromSpotId = fromSpotId;
        this.toSpotId = toSpotId;
        this.mobility = mobility;
        this.spentMinute = spentMinute;
    }
}
