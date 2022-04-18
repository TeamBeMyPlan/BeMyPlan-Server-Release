package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import com.deploy.bemyplan.domain.common.Money;
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
public class Plan extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RegionType region;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false)
    private String description;

    @Embedded
    private TagInfo tagInfo;

    @Embedded
    private TravelPeriod period;

    @Column(nullable = false)
    private int orderCnt;

    @Column(nullable = false)
    private int viewCnt;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RcmndStatus rcmndStatus;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DailySchedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PreviewContent> previews = new ArrayList<>();

    @Builder(builderMethodName = "testBuilder", access = AccessLevel.PUBLIC)
    private Plan(Long userId, RegionType region, String thumbnailUrl, String title, String description, TagInfo tagInfo, TravelPeriod period, int orderCnt, int viewCnt, PlanStatus status, RcmndStatus rcmndStatus) {
        this.userId = userId;
        this.region = region;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.tagInfo = tagInfo;
        this.period = period;
        this.orderCnt = orderCnt;
        this.viewCnt = viewCnt;
        this.status = status;
        this.rcmndStatus = rcmndStatus;
    }
}