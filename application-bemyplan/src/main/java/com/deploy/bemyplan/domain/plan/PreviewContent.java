package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.common.type.JsonValueType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PreviewContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private JsonValueType valueType;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PreviewContentStatus status;
}
