package com.lawrencemouarkach.service.autocomplete.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Canonical
class AutoCompleteEntity {
    @JsonProperty(value = "type")
    String fieldName
    @JsonProperty(value = "name")
    String key
    @JsonProperty(value = "iata_code")
    String value
}
