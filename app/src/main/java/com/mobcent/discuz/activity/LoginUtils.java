package com.mobcent.discuz.activity;

import android.content.Context;
import android.content.SharedPreferences;

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

    public void setLogin(String secret, String token) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("secret", secret);
        editor.putString("token", token);
        editor.commit();
    }
}
