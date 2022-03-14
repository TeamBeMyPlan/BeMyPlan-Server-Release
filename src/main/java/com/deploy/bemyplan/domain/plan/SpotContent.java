package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.common.type.JsonValueType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private SpotContentType contentType;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private JsonValueType valueType;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private SpotContentStatus status;
}