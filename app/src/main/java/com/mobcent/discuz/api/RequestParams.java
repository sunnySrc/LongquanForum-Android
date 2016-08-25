package com.mobcent.discuz.api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sun on 2016/8/21.
 */

public class RequestParams {
    public static final JSONObject json = new JSONObject();

    public void add(String name, Object value) {
        try {
            json.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("json err param :" + name + "," + value + ";" + e.toString());
        }
    }
    public String getJsonStr(){
        return json.toString();
    }
}
