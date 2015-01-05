package com.learn.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Set;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String JSON_CLASS = "clazz";

    static {
        MAPPER.registerModule(new JodaModule());
    }

    @Override
    public String toJson(T item) throws Exception {
        StringWriter sw = new StringWriter();
        MAPPER.writeValue(sw, item);
        return sw.toString();
    }

    @Override
    public <E extends Enum> String toJson(T item, Set<E> permissions) {
        JsonNode tree = MAPPER.valueToTree(item);
        ArrayNode arrayNode = ((ObjectNode) tree).putArray("$permissions");
        for (E permission : permissions) {
            arrayNode.add(permission.toString());
        }

        return tree.toString();
    }

}
