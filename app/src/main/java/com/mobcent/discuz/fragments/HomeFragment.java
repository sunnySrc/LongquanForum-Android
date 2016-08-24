package com.mobcent.discuz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;
import com.litesuits.android.log.Log;
import com.mobcent.common.JsonConverter;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.bean.HomeResult;

/**
 * Created by ubuntu on 16-6-21.
 */
public class HomeFragment extends BaseRefreshFragment {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private DiscuzRequest request;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View onCreateContentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    protected void onExcuteRequest(HttpResponseHandler handler) {
        request = LqForumApi.home(this);
    }

    @Override
    protected void showContent(String result) {
        HomeResult home = JsonConverter.format(result, HomeResult.class);
        home.getComponentList();
        Log.d(TAG,"showContent()!" );
    }

    @Override
    public void onDestroy() {
        if (request != null) {
            request.cancel(true);
        }
        super.onDestroy();
    }
}
