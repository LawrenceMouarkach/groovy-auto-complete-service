package com.lawrencemouarkach.service.autocomplete.api.generic

class KeyGenerator {
    static String createCompositeKey(FieldName fieldName, String key) {
        return fieldName.getFieldId() + ":" + key
    }
}
