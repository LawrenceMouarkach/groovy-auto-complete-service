package com.lawrencemouarkach.service.autocomplete.api.common

import groovy.transform.Canonical

@Canonical
class MongoDBIndex {
    List<String> keys
    boolean unique
    boolean sparse
    Integer expireAfterSeconds
}
