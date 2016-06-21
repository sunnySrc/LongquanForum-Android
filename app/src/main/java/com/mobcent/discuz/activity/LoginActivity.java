package com.mobcent.discuz.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appbyme.app178470.R;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                String u = mUsername.getText().toString();
                String p = mPassword.getText().toString();
                if (!TextUtils.isEmpty(u) && !TextUtils.isEmpty(p)) {
                    new LoginRequest(u, p).execute();
                }
            }
        });
    }

    class LoginRequest extends AsyncTask<Void, Integer, String> {
        private String mUsername;
        private String mPassword;

        public LoginRequest(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            // String request = "type=login&forumKey=BW0L5ISVRsOTVLCTJx&accessSecret=&accessToken=&isValidation=1&password=Mrzl2009&sdkVersion=2.4.0&apphash=85eb3e4b&username=17710275730";
            try {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("multipart/form-data; boundary=---011000010111000001101001");
                RequestBody body = RequestBody.create(mediaType, "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"type\"\r\n\r\nlogin\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"forumKey\"\r\n\r\nBW0L5ISVRsOTVLCTJx\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"isValidation\"\r\n\r\n1\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"password\"\r\n\r\nMrzl2009\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"sdkVersion\"\r\n\r\n2.4.0\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"apphash\"\r\n\r\n85eb3e4b\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\n17710275730\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"\"\r\n\r\n\r\n-----011000010111000001101001--");
                Request request = new Request.Builder()
                        .url("http://forum.longquanzs.org//mobcent/app/web/index.php?r=user%2Flogin")
                        .post(body)
                        .addHeader("content-type", "multipart/form-data; boundary=---011000010111000001101001")
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "fe43cc1d-e869-cb45-7596-c260e599b337")
                        .build();

                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject obj = new JSONObject(result);
                doLogin(obj.getString("token"), obj.getString("secret"));
            } catch (Exception e) {
                Log.e("leizhou", e.toString());
            }
        }
    }

    public void doLogin(String token, String secret) {
        finish();
        LoginUtils.getInstance().setLogin(token, secret);
    }

}
