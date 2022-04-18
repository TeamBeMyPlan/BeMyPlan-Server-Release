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
public class SpotImage extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private SpotImageStatus status;

    @Builder(access = AccessLevel.PACKAGE)
    private SpotImage(Spot spot, String url, SpotImageStatus status) {
        this.spot = spot;
        this.url = url;
        this.status = SpotImageStatus.ACTIVE;
    }

    public static SpotImage newInstance(Spot spot, String url) {
        return SpotImage.builder()
                .spot(spot)
                .url(url)
                .build();
    }
}