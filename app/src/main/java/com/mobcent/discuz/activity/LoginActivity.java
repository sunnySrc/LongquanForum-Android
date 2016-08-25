package com.mobcent.discuz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.fragments.HttpResponseHandler;

import org.json.JSONObject;

/**
 * Created by ubuntu on 16-6-29.
 */
public class LoginActivity extends Activity {

    private TextView mUsername;
    private TextView mPassword;

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.user_login_fragment);
        mUsername = (TextView)findViewById(R.id.user_login_name_edit);
        mPassword = (TextView)findViewById(R.id.user_login_password_edit);

        Button button = (Button)findViewById(R.id.login_submit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = mUsername.getText().toString().trim();
                String p = mPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(u) && !TextUtils.isEmpty(p)) {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("type", "login");
                        obj.put("isValidation", "1");
                        obj.put("username", u);
                        obj.put("password", p);
                        new DiscuzRequest("user/login", obj.toString(), new Handler()).begin();
                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    private class Handler implements HttpResponseHandler {
        @Override
        public void onSuccess(String result) {
            finish();
            LoginUtils.getInstance().setLogin(result);
        }

        @Override
        public void onFail(String result) {
            Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
