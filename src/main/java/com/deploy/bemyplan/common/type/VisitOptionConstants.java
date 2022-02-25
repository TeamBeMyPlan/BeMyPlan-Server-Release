package com.deploy.bemyplan.common.type;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitOptionConstants {

    public static final String VISIT_OPTION_HEADER = "Visit-Option";

    public static final String MEMBERSHIP = "MEMBERSHIP";
    public static final String GUEST = "GUEST";

    public static final Long GUEST_MODE = -1L;
}