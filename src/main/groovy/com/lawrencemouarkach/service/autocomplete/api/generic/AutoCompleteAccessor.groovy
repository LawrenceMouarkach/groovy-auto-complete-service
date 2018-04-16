package com.lawrencemouarkach.service.autocomplete.api.generic

import com.google.common.collect.ImmutableList
import com.lawrencemouarkach.service.autocomplete.request.AutoCompleteEntity
import org.jongo.MongoCollection
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AutoCompleteAccessor implements  MongoAccessor<AutoCompleteEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoCompleteAccessor.class)
    private final Iterable<AutoCompleteEntity> mappingFields
    private List<AutoCompleteEntity> autoCompleteEntities

    AutoCompleteAccessor(final MongoCollection autoCompleteCollection) {
        this.mappingFields = autoCompleteCollection.find().as(AutoCompleteEntity.class)
    }

    @Override
    List<AutoCompleteEntity> retrieveObjects() {
        return autoCompleteEntities = isNull(autoCompleteEntities) ? getAutoCompleteEntities(mappingFields) : autoCompleteEntities
    }

    private static ImmutableList<AutoCompleteEntity> getAutoCompleteEntities(Iterable<AutoCompleteEntity> mappingFields) {
        final ImmutableList<AutoCompleteEntity> mappingFieldsList = ImmutableList.copyOf(mappingFields.asList())
        LOGGER.info("Successfully loaded {}, mapped fields", mappingFieldsList.size())
        return mappingFieldsList
    }

    private static boolean isNull(List<AutoCompleteEntity> mappingFieldsList) {
        return mappingFieldsList == null
    }
}