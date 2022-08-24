package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SpotMoveInfo extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long fromSpotId;

    @Column(nullable = false)
    private Long toSpotId;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TravelMobility mobility;

    @Column(nullable = false)
    private int spentMinute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private DailySchedule schedule;

    SpotMoveInfo(Long id, Long fromSpotId, Long toSpotId, TravelMobility mobility, int spentMinute, DailySchedule schedule) {
        this.id = id;
        this.fromSpotId = fromSpotId;
        this.toSpotId = toSpotId;
        this.mobility = mobility;
        this.spentMinute = spentMinute;
        this.schedule = schedule;
    }

    public SpotMoveInfo(Long fromSpotId, Long toSpotId, TravelMobility mobility, int spentMinute, DailySchedule schedule) {
        this(null, fromSpotId, toSpotId, mobility, spentMinute, schedule);
    }
}
