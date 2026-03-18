package com.cli;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

// format and validate JSON

public class JsonFormatter {

    private int indentSize;

    public JsonFormatter(int indentSize){
        this.indentSize = indentSize;
    }

    // format JSON string with indentation
    public String format(String json) throws JsonSyntaxException {

        JsonElement je = JsonParser.parseString(json);

        Gson prettyGson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        return prettyGson.toJson(je);
    }

    // JSON validation
    public boolean isValid(String json){
        try{
            JsonParser.parseString(json);
            return true;
        }
        catch (JsonSyntaxException e){
            return false;
        }
    }

    // error message
    public String getValidationError(String json){
        try {
            JsonParser.parseString(json);
            return null;
        } catch (JsonSyntaxException e) {
            return e.getMessage();
        }
    }
}