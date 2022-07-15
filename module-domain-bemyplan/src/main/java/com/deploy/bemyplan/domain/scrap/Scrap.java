package com.deploy.bemyplan.domain.scrap;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private Scrap(Long planId, Long userId, ScrapStatus status) {
        this.planId = planId;
        this.userId = userId;
        this.status = status;
    }

    public static Scrap of(Long planId, Long userId) {
        return new Scrap(planId, userId, ScrapStatus.ACTIVE);
    }
}