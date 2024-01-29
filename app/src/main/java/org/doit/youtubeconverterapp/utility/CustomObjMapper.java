package org.doit.youtubeconverterapp.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjMapper<T> {

    final Class<T> typeParameterClass;

    public CustomObjMapper(Class<T> typeParameterClass){
        this.typeParameterClass = typeParameterClass;
    }

    public T getObjFromJson(String responseBodyString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T requestedObject = objectMapper.readValue(responseBodyString, typeParameterClass);
        return requestedObject;
    }

    public String getJsonFromObj(T obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(obj);
    }
}
