package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private SpotCategoryType category;
    @Embedded
    private Location location;
    @Column(nullable = true, columnDefinition = "MEDIUMTEXT")
    private String tip;
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String review;
    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SpotImage> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Column(nullable = false)
    private int day;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private TravelMobility vehicle;

    @Column
    private Integer spentMinute;

    public Spot(Long id, String title, SpotCategoryType category, Location location, String tip, String review,
                Plan plan,
                int day,
                TravelMobility vehicle,
                Integer spentMinute) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.location = location;
        this.tip = tip;
        this.review = review;
        this.images.addAll(images);
        this.plan = plan;
        this.day = day;
        this.vehicle = vehicle;
        this.spentMinute = spentMinute;
    }

    public Spot(String title, SpotCategoryType category, Location location, String tip, String review,
                Plan plan,
                int day,
                TravelMobility vehicle,
                Integer spentMinute) {
        this(null, title, category, location, tip, review, plan, day, vehicle, spentMinute);
    }

    public void setImage(List<SpotImage> spotImages) {
        this.images.addAll(spotImages);
    }
}