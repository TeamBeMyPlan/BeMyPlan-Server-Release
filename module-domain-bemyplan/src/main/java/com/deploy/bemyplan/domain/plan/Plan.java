package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import com.deploy.bemyplan.domain.common.ListToStringConverter;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
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

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String description;

    @Embedded
    private TagInfo tagInfo;

    @Column(nullable = false)
    private int orderCnt;

    @Column(nullable = false)
    private int viewCnt;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RcmndStatus rcmndStatus;

    @Column(length = 100)
    @Convert(converter = ListToStringConverter.class)
    private List<String> hashtags = new ArrayList<>();

    @Column(length = 200)
    @Convert(converter = ListToStringConverter.class)
    private List<String> recommendTargets = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DailySchedule> schedules = new ArrayList<>();

    private Plan(final Long userId, final RegionType region, final String thumbnailUrl, final String title, final String description, final TagInfo tagInfo, final int orderCnt, final int viewCnt, final int price, final PlanStatus status, final RcmndStatus rcmndStatus, final List<String> hashtags, final List<String> recommendTargets) {
        this.userId = userId;
        this.region = region;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.tagInfo = tagInfo;
        this.orderCnt = orderCnt;
        this.viewCnt = viewCnt;
        this.price = price;
        this.status = status;
        this.rcmndStatus = rcmndStatus;
        this.hashtags.addAll(hashtags);
        this.recommendTargets.addAll(recommendTargets);
    }

    private Plan(Long userId, RegionType region, String thumbnailUrl, String title, String description, TagInfo tagInfo, int orderCnt, int viewCnt, int price, PlanStatus status, RcmndStatus rcmndStatus) {
        this.userId = userId;
        this.region = region;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.tagInfo = tagInfo;
        this.orderCnt = orderCnt;
        this.viewCnt = viewCnt;
        this.price = price;
        this.status = status;
        this.rcmndStatus = rcmndStatus;
    }

    public static Plan newInstance(Long userId, RegionType region, String thumbnailUrl, String title, String description, TagInfo tagInfo, int price, PlanStatus status, RcmndStatus rcmndStatus) {
        return new Plan(userId, region, thumbnailUrl, title, description, tagInfo, 0, 0, price, status, rcmndStatus);
    }

    public static Plan newInstance(Long userId, RegionType region, String thumbnailUrl, String title, String description, TagInfo tagInfo, int price, PlanStatus status, RcmndStatus rcmndStatus, List<String> hashtags, List<String> recommendTargets) {
        return new Plan(userId, region, thumbnailUrl, title, description, tagInfo, 0, 0, price, status, rcmndStatus, hashtags, recommendTargets);
    }

    public void updateOrderCnt() {
        this.orderCnt += 1;
    }

    public int getRestaurantCount() {
        return (int) schedules.stream()
                .flatMap(dailySchedule -> dailySchedule.getSpots().stream())
                .filter(spot -> SpotCategoryType.RESTAURANT == spot.getCategory())
                .count();
    }

    public int getSpotCount() {
        return schedules.stream()
                .mapToInt(dailySchedule -> dailySchedule.getSpots().size())
                .sum();
    }
}