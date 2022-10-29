package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.common.type.JsonValueType;
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
@Deprecated
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

    @Column(nullable = false, length = 700)
    private String value;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PreviewContentStatus status;

    public PreviewContent(Plan plan, JsonValueType valueType, String value) {
        this.plan = plan;
        this.valueType = valueType;
        this.value = value;
        this.status = PreviewContentStatus.ACTIVE;
    }
}
