package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import com.deploy.bemyplan.domain.common.ListToStringConverter;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@Setter
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Plan extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long creatorId;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RegionCategory regionCategory;

    @Column(nullable = true, length = 30)
    @Enumerated(EnumType.STRING)
    private Region region;

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
    private final List<Spot> spots = new ArrayList<>();

    private Plan(final Long creatorId, final RegionCategory regionCategory, final Region region, final String thumbnailUrl,
                 final String title, final String description, final TagInfo tagInfo,
                 final int orderCnt, final int viewCnt, final int price,
                 final PlanStatus status, final RcmndStatus rcmndStatus,
                 final List<String> hashtags, final List<String> recommendTargets) {
        this.creatorId = creatorId;
        this.regionCategory = regionCategory;
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

    public static Plan newInstance(Long creatorId, RegionCategory regionCategory, Region region, String thumbnailUrl,
                                   String title, String description, TagInfo tagInfo,
                                   int price, PlanStatus status, RcmndStatus rcmndStatus,
                                   List<String> hashtags, List<String> recommendTargets) {
        return new Plan(creatorId, regionCategory, region, thumbnailUrl, title, description, tagInfo, 0, 0, price, status, rcmndStatus, hashtags, recommendTargets);
    }

    public void updateOrderCnt() {
        this.orderCnt += 1;
    }

    public int getRestaurantCount() {
        return (int)spots.stream().filter(spot -> SpotCategoryType.RESTAURANT == spot.getCategory()).count();
    }

    public int getSpotCount() {
        return spots.size();
    }
}