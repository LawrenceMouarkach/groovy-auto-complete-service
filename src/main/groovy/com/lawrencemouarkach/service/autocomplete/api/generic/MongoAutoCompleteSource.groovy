package com.lawrencemouarkach.service.autocomplete.api.generic

import com.google.common.collect.HashBasedTable
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableTable
import com.google.common.collect.Table
import com.lawrencemouarkach.service.autocomplete.api.generic.FieldName
import com.lawrencemouarkach.service.autocomplete.api.generic.MongoAccessor
import com.lawrencemouarkach.service.autocomplete.request.AutoCompleteEntity

import static com.lawrencemouarkach.service.autocomplete.api.generic.KeyGenerator.createCompositeKey

class MongoAutoCompleteSource {
    private final MongoAccessor<AutoCompleteEntity> mongoAccessor

    MongoAutoCompleteSource(MongoAccessor<AutoCompleteEntity> mongoAccessor) {
        this.mongoAccessor = mongoAccessor
    }


    List<AutoCompleteEntity> getData(String query) {
        Set<AutoCompleteEntity> matched = new HashSet<>()

        for (AutoCompleteEntity entity : mongoAccessor.retrieveObjects().collect()) {
            String airportName = entity.getKey().toLowerCase()
            if (airportName.startsWith(query.toLowerCase())) {
                matched.add(entity)
            }
        }

        return ImmutableList.copyOf(matched)
    }

}
