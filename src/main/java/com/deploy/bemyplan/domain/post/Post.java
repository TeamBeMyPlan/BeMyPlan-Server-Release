package com.deploy.bemyplan.domain.post;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import com.deploy.bemyplan.domain.common.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false)
    private String description;

    @Embedded
    private Money price;

    @Embedded
    private TravelPeriod period;

    @Column(nullable = false)
    private int orderCnt;

    @Column(nullable = false)
    private int viewCnt;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RcmndStatus rcmndStatus;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<DailySchedule> schedules = new ArrayList<>();
}