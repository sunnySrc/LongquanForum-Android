package com.mobcent.discuz.fragments;

/**
 * Created by ubuntu on 16-8-18.
 */
public interface HttpResponseHandler {
    public void onSuccess(String result);
    public void onFail(String result);
}
