package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import com.deploy.bemyplan.domain.common.Location;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private DailySchedule schedule;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private SpotCategoryType category;

    @Embedded
    private Location location;

    @Column(nullable = false)
    private String tip;

    @Column(nullable = false)
    private String review;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SpotImage> images = new ArrayList<>();

    @Builder(builderMethodName = "testBuilder", access = AccessLevel.PUBLIC)
    private Spot(DailySchedule schedule, String title, SpotCategoryType category, Location location, String tip, String review) {
        this.schedule = schedule;
        this.title = title;
        this.category = category;
        this.location = location;
        this.tip = tip;
        this.review = review;
    }
}