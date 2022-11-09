package com.deploy.bemyplan.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class KakaoLocationModel {
    private List<Document> documents;

    public Location getLocation() {
        final Optional<Document> maybeDocument = documents.stream().findFirst();
        if (maybeDocument.isEmpty()) {
            return Location.empty();
        }

        final Document document = maybeDocument.get();

        return Location.of(document.getLongitude(), document.getLatitude());
    }

    @Getter
    public static class Document {
        @JsonProperty("address_name")
        private String addressName;
        @JsonProperty("x")
        private double longitude;
        @JsonProperty("y")
        private double latitude;
    }
}
