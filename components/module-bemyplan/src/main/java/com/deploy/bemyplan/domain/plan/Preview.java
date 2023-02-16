package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.domain.common.ListToStringConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

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
    @Convert(converter = ListToStringConverter.class)
    private List<String> imageUrls = new ArrayList<>();

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PreviewContentStatus status;

    @OneToOne
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    private Preview(final Plan plan, final List<String> imageUrls, final String description, final PreviewContentStatus status, final Spot spot) {
        this.plan = plan;
        this.imageUrls.addAll(imageUrls);
        this.description = description;
        this.status = status;
        this.spot = spot;
    }

    public static Preview newInstance(final Plan plan, final List<String> imageUrls, final String description, final PreviewContentStatus status, final Spot spot) {
        return new Preview(plan, imageUrls, description, status, spot);
    }
}