package com.deploy.bemyplan.external.client.apple.social;

import org.jetbrains.annotations.NotNull;

public interface AppleTokenDecoder {

    String getSocialIdFromIdToken(@NotNull String idToken);
}
