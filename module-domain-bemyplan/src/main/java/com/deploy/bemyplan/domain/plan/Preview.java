package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(length = 1000)
    private String imageUrl;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PreviewContentStatus status;

    private Preview(final Plan plan, final String imageUrl, final String description, final PreviewContentStatus status) {
        this.plan = plan;
        this.imageUrl = imageUrl;
        this.description = description;
        this.status = status;
    }

    public static Preview newInstance(final Plan plan, final String imageUrl, final String description, final PreviewContentStatus status) {
        return new Preview(plan, imageUrl, description, status);
    }
}