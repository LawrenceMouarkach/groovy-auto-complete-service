import com.lawrencemouarkach.service.autocomplete.handlers.MongoAutoCompleteHandler
import com.lawrencemouarkach.service.module.ServiceModule

import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        module ServiceModule
    }

    handlers {
        all(registry.get(MongoAutoCompleteHandler))
    }
}
