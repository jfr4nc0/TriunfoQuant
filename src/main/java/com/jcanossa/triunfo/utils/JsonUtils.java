package com.jcanossa.triunfo.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

	public JsonUtils() {
		super();
	}

	@SuppressWarnings("deprecation")
	public JsonObject getJsonObjectFromString(String jsonString) {
		JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(jsonString).getAsJsonObject();
        return jsonObject;
	}
}
