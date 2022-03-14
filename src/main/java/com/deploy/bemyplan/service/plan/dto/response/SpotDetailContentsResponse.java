package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.common.type.JsonValueType;
import com.deploy.bemyplan.domain.plan.SpotContent;
import com.deploy.bemyplan.domain.plan.SpotContentType;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotDetailContentsResponse {

    private SpotContentType category;
    private JsonValueType type;
    private String value;

    public static SpotDetailContentsResponse of(SpotContent content) {
        return new SpotDetailContentsResponse(content.getContentType(), content.getValueType(), content.getValue());
    }
}
