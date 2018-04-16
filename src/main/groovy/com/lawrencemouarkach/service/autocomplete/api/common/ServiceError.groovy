package com.lawrencemouarkach.service.autocomplete.api.common

import groovy.transform.Canonical

@Canonical
class ServiceError<T> {

    String code
    String message
    T details
}
