package com.lawrencemouarkach.service.autocomplete.api.common

import groovy.transform.Canonical

@Canonical
class ServiceErrorResponse<T> {

    ServiceError<T> error

}
