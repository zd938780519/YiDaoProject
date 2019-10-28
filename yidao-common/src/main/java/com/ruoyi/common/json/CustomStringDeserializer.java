package com.ruoyi.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CustomStringDeserializer extends JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonParser jsonparser,
                              DeserializationContext deserializationcontext) throws IOException,
            JsonProcessingException {

        return jsonparser.getText();

    }

    @Override
    public String getNullValue() {
        return "";
    }

}
