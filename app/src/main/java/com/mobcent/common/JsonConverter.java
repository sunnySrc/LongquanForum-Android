package com.mobcent.common;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by sun on 2016/8/23.
 */

public class JsonConverter {
    public static <T> T format(String json, Class<T> type) {
        Gson gson = new Gson();
        return  gson.fromJson(json,type);
    }

}
