package com.mobcent.discuz.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;
import com.mobcent.common.JsonConverter;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.bean.HomeResult;
import com.mobcent.discuz.ui.ComponentBuilder;

/**
 * Created by ubuntu on 16-6-21.
 */
public class HomeFragment extends BaseRefreshFragment {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private DiscuzRequest request;
    private ViewGroup viewGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View onCreateContentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        return viewGroup;
    }


    @Override
    public void onRefreshing() {
    }
    @Override
    public void onLoadMore() {
    }

    @Override
    protected void onExecuteRequest(HttpResponseHandler handler) {
        request = LqForumApi.home(this);
    }

    @Override
    protected void showContent(String result) {
        HomeResult home = JsonConverter.format(result, HomeResult.class);
        ComponentBuilder builder = new ComponentBuilder(getContext());
        builder.setRefreshLayout(mRefreshLayout);
        View comView = builder.buildComponentGroup(home.getComponentList(), true);
        viewGroup.removeAllViews();
        viewGroup.addView(comView);
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
