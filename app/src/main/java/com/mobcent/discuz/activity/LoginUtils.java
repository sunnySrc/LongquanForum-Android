package com.mobcent.discuz.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ubuntu on 16-6-29.
 */
public class LoginUtils {

    private static LoginUtils instance = new LoginUtils();
    private Context mContext;
    private SharedPreferences sp;
    private LoginUtils() {

    }

    public static LoginUtils getInstance() {
        return instance;
    }

    public void init(Context context) {
        mContext = context;
        sp = mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
    }

    public boolean isLogin() {
        String name = sp.getString("secret", "");
        return !name.equals("");
    }

    public String getAccessSecret() {
        return sp.getString("secret", "");
    }

    public String getAccessToken() {
        return sp.getString("token", "");
    }

    public void setLogin(String info) throws JSONException {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userinfo", info);
        JSONObject obj = new JSONObject(info);
        editor.putString("secret", obj.getString("secret"));
        editor.putString("token", obj.getString("token"));
        editor.commit();
    }
}
