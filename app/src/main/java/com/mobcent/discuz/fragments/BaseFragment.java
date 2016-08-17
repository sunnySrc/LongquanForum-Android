package com.mobcent.discuz.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by ubuntu on 16-8-18.
 */
public class BaseFragment extends Fragment implements HttpResponseHandler {
    @Override
    public void onSuccess(String result) {
        assert 1 == 0;
    }

    @Override
    public void onFail(String result) {
        assert 1 == 0;
    }
}
