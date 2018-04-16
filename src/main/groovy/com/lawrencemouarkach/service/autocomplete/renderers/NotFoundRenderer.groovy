package com.lawrencemouarkach.service.autocomplete.renderers

import com.lawrencemouarkach.service.autocomplete.api.common.NotFoundDetail
import com.lawrencemouarkach.service.autocomplete.api.common.ServiceError
import com.lawrencemouarkach.service.autocomplete.api.common.ServiceErrorResponse
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.render.GroovyRendererSupport
import ratpack.jackson.Jackson

import javax.ws.rs.NotFoundException

import static com.lawrencemouarkach.service.autocomplete.api.common.ErrorDetail.COMMON_URL_NOT_FOUND

class NotFoundRenderer extends GroovyRendererSupport<NotFoundException> {

    @Override
    void render(GroovyContext context, NotFoundException notFoundException) throws Exception {
        context.byContent {
            json {
                context.render Jackson.json(new ServiceErrorResponse(
                        new ServiceError<>(
                                COMMON_URL_NOT_FOUND.name(),
                                COMMON_URL_NOT_FOUND.getMessage(),
                                new NotFoundDetail(notFoundException.getMessage()))))
            }
        }
    }
}
