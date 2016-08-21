package com.mobcent.discuz.base.constant;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.fragments.HttpResponseHandler;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.logging.Handler;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiscuzRequest extends AsyncTask<Void, Integer, String> {
    private String mUrl;
    private String mBody;
    private HttpResponseHandler mHandler;
    private String mMethod = "post";
    public final String baseUrl = "http://forum.longquanzs.org//mobcent/app/web/index.php?r=";

    public DiscuzRequest(String url, String body, HttpResponseHandler handler) {
        mUrl = url;
        mBody = body;
        mHandler = handler;
    }

    public DiscuzRequest(String url, String body, HttpResponseHandler handler, String method) {
        this(url, body, handler);
        mMethod = method;
    }

    @Override
    protected String doInBackground(Void... params) {
        // TODO Auto-generated method stub
        // String request = "type=login&forumKey=BW0L5ISVRsOTVLCTJx&accessSecret=&accessToken=&isValidation=1&password=Mrzl2009&sdkVersion=2.4.0&apphash=85eb3e4b&username=17710275730";
        try {
            OkHttpClient client = new OkHttpClient();

            Request.Builder builder= new Request.Builder()
                    .url(mUrl.startsWith("http") ? mUrl : baseUrl + mUrl)
                    .addHeader("cache-control", "no-cache");
            if (mMethod.equals("get")) {
                builder = builder.get();
            } else {

                MediaType mediaType = MediaType.parse("multipart/form-data; boundary=---011000010111000001101001");
                String bodyString = "";
                if (mMethod == "post") {
                    try {
                        JSONObject obj = new JSONObject(mBody);
                        obj.put("forumKey", "BW0L5ISVRsOTVLCTJx");
                        obj.put("sdkVersion", "2.4.0");
                        obj.put("apphash", "85eb3e4b");
                        obj.put("accessSecret", LoginUtils.getInstance().getAccessSecret());
                        obj.put("accessToken", LoginUtils.getInstance().getAccessToken());
                        Iterator it = obj.keys();
                        while (it.hasNext()) {
                            String key = (String) it.next();
                            String value = obj.getString(key);
                            bodyString += "-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"" + key + "\"\r\n\r\n" + value + "\r\n";
                        }
                        bodyString += "-----011000010111000001101001--";
                    } catch (Exception e) {

                    }
                }
                RequestBody body = RequestBody.create(mediaType, bodyString);
                builder = builder.post(body)
                        .addHeader("content-type", "multipart/form-data; boundary=---011000010111000001101001");
            }
            Request request = builder.build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (!TextUtils.isEmpty(result)) {
            mHandler.onSuccess(result);
        } else {
            mHandler.onFail(result);
        }
    }
}

