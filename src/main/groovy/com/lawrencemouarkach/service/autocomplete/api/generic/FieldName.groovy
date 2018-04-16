package com.lawrencemouarkach.service.autocomplete.api.generic

import static java.util.Collections.unmodifiableMap

enum FieldName {
    LARGE_AIRPORT("5ad3d9700000000000000000")

    private String fieldId
    private static final Map<String, FieldName> ENUM_MAP

    FieldName(String fieldId) {
        this.fieldId = fieldId
    }

    String getFieldId() {
        return fieldId
    }

    static Optional<FieldName> fromId(String fieldId) {
        return Optional.ofNullable(ENUM_MAP.get(fieldId))
    }

    static {
        Map<String, FieldName> map = new HashMap<>()
        for (FieldName instance : values()) {
            map.put(instance.getFieldId(), instance)
        }
        ENUM_MAP = unmodifiableMap(map)
    }

}