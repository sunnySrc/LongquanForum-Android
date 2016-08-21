package com.mobcent.discuz.base.constant;

import android.os.AsyncTask;

import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.fragments.HttpResponseHandler;

import org.json.JSONObject;

import java.util.Iterator;

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
    // Global instance
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
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
            Request request = new Request.Builder()
                    .url(baseUrl + mUrl)
                    .post(body)
                    .addHeader("content-type", "multipart/form-data; boundary=---011000010111000001101001")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "fe43cc1d-e869-cb45-7596-c260e599b337")
                    .build();

            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            if (obj.getInt("rs") == 0) {
                mHandler.onFail(obj.getString("errcode"));
                return;
            }
            mHandler.onSuccess(result);
        } catch (Exception e) {
            mHandler.onFail(result);
        }
    }

    @Override
    protected void onCancelled(String s) {
        // TODO 应该添加 mHandler.onCancelled() ?
        mHandler = null;
    }
}

