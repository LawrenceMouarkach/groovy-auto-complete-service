package com.lawrencemouarkach.service.autocomplete.api.generic

interface MongoAccessor<T> {
    List<T> retrieveObjects()
}