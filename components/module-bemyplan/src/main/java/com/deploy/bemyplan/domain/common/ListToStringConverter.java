package com.deploy.bemyplan.domain.common;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListToStringConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return attribute == null ? null : String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return isNullOrEmpty(dbData)
                ? Collections.emptyList() : new ArrayList<>(Arrays.asList(dbData.split(",")));
    }

    private boolean isNullOrEmpty(String dbData) {
        return dbData == null || "".equals(dbData);
    }
}
