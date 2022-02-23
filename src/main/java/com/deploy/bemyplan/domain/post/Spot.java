package com.deploy.bemyplan.domain.post;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import com.deploy.bemyplan.domain.common.Location;
import lombok.AccessLevel;
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

    @Embedded
    private TagInfo tagInfo;

    private String title;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private SpotCategoryType category;

    @Embedded
    private Location location;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SpotContent> contents = new ArrayList<>();
}