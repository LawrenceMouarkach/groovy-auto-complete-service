package com.lawrencemouarkach.service.autocomplete.renderers

import com.lawrencemouarkach.service.autocomplete.request.AutoCompleteEntity
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.render.GroovyRendererSupport
import ratpack.jackson.Jackson

class AutoCompleteRenderer extends GroovyRendererSupport<List<?>> {

    @Override
    void render(GroovyContext context, List<?> entities) throws Exception {
        context.byContent {
            json {
                context.render Jackson.json(entities)
            }
        }
    }
}
