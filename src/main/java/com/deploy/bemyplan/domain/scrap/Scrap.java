package com.deploy.bemyplan.domain.scrap;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrap extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private ScrapStatus status;

    public void updateToActive() {
        this.status = ScrapStatus.ACTIVE;
    }

    public void updateToInActive() {
        this.status = ScrapStatus.INACTIVE;
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Scrap(Long planId, Long userId, ScrapStatus status) {
        this.planId = planId;
        this.userId = userId;
        this.status = status;
    }

    public static Scrap of(Long planId, Long userId) {
        return Scrap.builder()
                .planId(planId)
                .userId(userId)
                .status(ScrapStatus.ACTIVE)
                .build();
    }
}