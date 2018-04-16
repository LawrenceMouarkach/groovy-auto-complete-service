package com.lawrencemouarkach.service.autocomplete.handlers

import com.lawrencemouarkach.service.autocomplete.api.common.ServiceError
import com.lawrencemouarkach.service.autocomplete.api.common.ServiceErrorResponse
import groovy.util.logging.Slf4j
import ratpack.error.ServerErrorHandler
import ratpack.handling.Context
import ratpack.jackson.Jackson

import static com.lawrencemouarkach.service.autocomplete.api.common.ErrorDetail.COMMON_SERVICE_ERROR

@Slf4j
class ServiceErrorHandler implements ServerErrorHandler {

    @Override
    void error(Context context, Throwable throwable) {
        log.error "Many Code, Such Error, Wow", throwable
        context.with {
            render Jackson.json(new ServiceErrorResponse(
                    new ServiceError<>(
                            COMMON_SERVICE_ERROR.getMessage(),
                            throwable.getMessage(),
                            null)))
        }
    }
}