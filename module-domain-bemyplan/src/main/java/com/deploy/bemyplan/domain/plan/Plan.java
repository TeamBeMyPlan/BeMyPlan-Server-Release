package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DailySchedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PreviewContent> previews = new ArrayList<>();

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

    public void updateOrderCnt(){
        this.orderCnt += 1;
    }
}