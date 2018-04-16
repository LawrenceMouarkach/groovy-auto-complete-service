package com.lawrencemouarkach.service.module

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Scopes
import com.google.inject.Singleton
import com.lawrencemouarkach.service.autocomplete.api.common.MongoClientFactory
import com.lawrencemouarkach.service.autocomplete.api.common.MongoDBConfiguration
import com.lawrencemouarkach.service.autocomplete.api.generic.AutoCompleteAccessor
import com.lawrencemouarkach.service.autocomplete.api.generic.MongoAccessor
import com.lawrencemouarkach.service.autocomplete.api.generic.MongoAutoCompleteSource
import com.lawrencemouarkach.service.autocomplete.handlers.MongoAutoCompleteHandler
import com.lawrencemouarkach.service.autocomplete.handlers.ServiceErrorHandler
import com.lawrencemouarkach.service.autocomplete.renderers.InvalidFieldRenderer
import com.lawrencemouarkach.service.autocomplete.renderers.AutoCompleteRenderer
import com.lawrencemouarkach.service.autocomplete.renderers.NotFoundRenderer
import com.lawrencemouarkach.service.autocomplete.request.AutoCompleteEntity
import com.mongodb.MongoClient
import org.jongo.Jongo
import org.jongo.MongoCollection

import javax.inject.Named

class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ServiceErrorHandler.class).in(Scopes.SINGLETON)
        bind(AutoCompleteRenderer.class).in(Scopes.SINGLETON)
        bind(NotFoundRenderer.class).in(Scopes.SINGLETON)
        bind(InvalidFieldRenderer.class).in(Scopes.SINGLETON)
    }

    @Provides
    @Singleton
    @Named("database")
    MongoClient mongoClient() {
        MongoDBConfiguration mongoDBConfiguration = new MongoDBConfiguration()
        mongoDBConfiguration.setUseSSL(false)
        mongoDBConfiguration.setHostPorts("localhost:27017")
        return MongoClientFactory.create(mongoDBConfiguration)
    }

    @Provides
    @Singleton
    @Named("database")
    @SuppressWarnings("deprecation")
    Jongo jongo(final @Named("database") MongoClient mongoClient) {
        return new Jongo(mongoClient.getDB("database"))
    }

    /**
     *
     * @param jongo {@link Jongo}
     * @return {@link MongoCollection}
     */
    @Provides
    @Named("autoComplete")
    MongoCollection mongoCollection(final @Named("database") Jongo jongo) {
        return jongo.getCollection("autoComplete")
    }

    @Provides
    @Singleton
    MongoAccessor<AutoCompleteEntity> autoCompleteMongoAccessor(final @Named("autoComplete") MongoCollection mongoCollection) {
        return new AutoCompleteAccessor(mongoCollection)
    }

    /**
     * @param mongoAccessor the {@link MongoAccessor}
     * @return the {@link MongoAutoCompleteSource}
     */
    @Provides
    @Singleton
    MongoAutoCompleteSource mongoProviderMappingSource(final MongoAccessor<AutoCompleteEntity> mongoAccessor) {
        return new MongoAutoCompleteSource(mongoAccessor)
    }

    /**
     * @param mongoAccessor the {@link MongoAutoCompleteSource}
     * @return the {@link MongoAutoCompleteHandler}
     */
    @Provides
    @Singleton
    MongoAutoCompleteHandler mappingHandler(final MongoAutoCompleteSource autoCompleteSource) {
        return new MongoAutoCompleteHandler(autoCompleteSource)
    }

}
