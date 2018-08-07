package com.lawrencemouarkach.service.autocomplete.handlers

import com.lawrencemouarkach.service.autocomplete.api.generic.FieldName
import com.lawrencemouarkach.service.autocomplete.api.generic.MongoAutoCompleteSource
import com.lawrencemouarkach.service.autocomplete.request.AutoCompleteEntity
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

import javax.ws.rs.NotFoundException

class MongoAutoCompleteHandler extends GroovyHandler {
    private MongoAutoCompleteSource autoCompleteSource

    MongoAutoCompleteHandler(MongoAutoCompleteSource autoCompleteSource) {
        this.autoCompleteSource = autoCompleteSource
    }

    @Override
    void handle(GroovyContext context) throws Exception {
        final def request = context.request
        final def queryParameters = request.queryParams
        final String key = queryParameters.get("key")
        final String fieldNameString = request.path

        context.header("Access-Control-Allow-Origin", "*")
        FieldName.fromId(fieldNameString).ifPresent() {
            fieldName ->
                final List<AutoCompleteEntity> autoCompleteEntities = autoCompleteSource.getData(key)
                !autoCompleteEntities.isEmpty() ? context.render(autoCompleteEntities) : context.render(new NotFoundException())
        }
    }

}
