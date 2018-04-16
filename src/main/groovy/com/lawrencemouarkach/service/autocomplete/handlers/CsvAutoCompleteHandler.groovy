package com.lawrencemouarkach.service.autocomplete.handlers

import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.google.common.collect.ImmutableList
import com.google.common.io.Resources

import com.lawrencemouarkach.service.autocomplete.request.CsvAirport
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

import java.nio.charset.StandardCharsets

class CsvAutoCompleteHandler extends GroovyHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CsvAutoCompleteHandler.class)


    static List<CsvAirport> getAirportData() {
        List<CsvAirport> airports = new ArrayList<>()
        try {
            airports = ImmutableList.copyOf(read(CsvAirport.class, "airport-codes.csv"))
        } catch (IOException e) {
            e.printStackTrace()
        }
        return airports
    }

    private static <T> List<T> read(Class<T> type, String file) throws IOException {
        CsvMapper mapper = new CsvMapper()
        String json = Resources.toString(Resources.getResource(file), StandardCharsets.UTF_8)
        CsvSchema schema = CsvSchema.emptySchema().withHeader()
        MappingIterator<T> it = mapper.readerFor(type).with(schema)
                .readValues(json)
        return it.readAll()
    }

    @Override
    protected void handle(GroovyContext context) {

    }
}
