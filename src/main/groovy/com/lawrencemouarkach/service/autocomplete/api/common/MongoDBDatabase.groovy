package com.lawrencemouarkach.service.autocomplete.api.common

import groovy.transform.Canonical

@Canonical
class MongoDBDatabase {
    String name
    boolean coloured
    Map<String, List<MongoDBIndex>> indexes

}
