package com.lawrencemouarkach.service.autocomplete.renderers

import com.lawrencemouarkach.service.autocomplete.api.generic.InvalidField
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.render.GroovyRendererSupport
import ratpack.jackson.Jackson

class InvalidFieldRenderer extends GroovyRendererSupport<InvalidField> {

    @Override
    void render(GroovyContext context, InvalidField invalidField) throws Exception {
        context.byContent {
            json {
                context.render Jackson.json(invalidField)
            }
        }
    }
}
