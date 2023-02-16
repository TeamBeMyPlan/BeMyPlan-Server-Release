package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Preview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)

    private Plan plan;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PreviewContentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    public Preview(final Long id, final Plan plan, final String description, final Spot spot) {
        this.id = id;
        this.plan = plan;
        this.description = description;
        this.spot = spot;
        this.status = PreviewContentStatus.ACTIVE;
    }

    public Preview(final Plan plan, final String description, final Spot spot) {
        this(null, plan, description, spot);
    }
}