package com.learn.domain.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.learn.domain.conf.AuditableEntity;

import java.io.IOException;

public class AuditableEntitySimpleSerializer extends JsonSerializer<AuditableEntity<Integer>> {
    @Override
    public void serialize(AuditableEntity<Integer> value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeEndObject();
    }
}
