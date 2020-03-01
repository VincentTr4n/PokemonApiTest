package com.example.pokemonapitest.api_controller.api_adapter;

import com.example.pokemonapitest.AppConstants;
import com.example.pokemonapitest.api_model.common.NamedApiResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class NamedApiAdapter implements JsonDeserializer<NamedApiResponse> {

    @Override
    public NamedApiResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CommonJson commonJson = context.deserialize(json, CommonJson.class);
        String[] suffix = commonJson.url.replace(AppConstants.BASE_API_URL, "")
                                        .split("[/]");
        return new NamedApiResponse(Integer.parseInt(suffix[1]), commonJson.name, suffix[0]);
    }
}
