package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.api.RequestParams;
import com.mobcent.discuz.api.UrlFactory;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.module.user.adapter.CommentRecycleAdapter;
import com.mobcent.discuz.module.user.adapter.SystemRecycleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.BaseResult;
import discuz.com.net.service.model.me.CommentResult;
import discuz.com.net.service.model.me.SystemResult;
import discuz.com.retrofit.library.HTTPSubscriber;

import static android.widget.Toast.makeText;

/**
 * Created by sun on 2017/5/26.
 * 系统消息界面
 */
public class MySystemActivity extends BasePopActivity {

    private XRecyclerView xRecycler;
    private SystemRecycleAdapter adapter;
    ArrayList<SystemResult.Body.SystemAboutMe> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        getAppActionBar().setTitle(R.string.mc_forum_sys_msg);
        onRefresh1();

        xRecycler = (XRecyclerView) findViewById(R.id.xr_test);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        xRecycler.setLayoutManager(layoutManager);
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
        xRecycler.setLoadingListener(new LoadingMore());

        adapter = new SystemRecycleAdapter(list);
        xRecycler.setAdapter(adapter);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_collection;
    }

    private void onRefresh1() {
        Bundle extras = getIntent().getExtras();
        HashMap<String, String> my_comment_map = WebParamsMap.map();
        for (String key : extras.keySet()) {
            my_comment_map.put(key, String.valueOf(extras.get(key)));
        }
        DiscuzRetrofit.getUserInfoService(this).requestSystem(my_comment_map)
                .subscribe(new HTTPSubscriber<SystemResult>() {
                    @Override
                    public void onSuccess(SystemResult commentResult) {
                        if (commentResult.getBody().data.size() > 0) {
                            list.clear();
                            list.addAll(commentResult.getBody().data);
                            adapter.notifyDataSetChanged();
                            xRecycler.refreshComplete();
                        } else
                            showToast("没有人消息你");

                    }

                    @Override
                    public void onFail(int httpCode, int errorUserCode, String message) {
                        showToast(message);
                    }
                });


    }


    @Override
    protected Fragment initContentFragment() {
        return null;
    }


    private class LoadingMore implements XRecyclerView.LoadingListener {
        @Override
        public void onRefresh() {
            onRefresh1();
        }

        @Override
        public void onLoadMore() {
            xRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    makeText(MySystemActivity.this, R.string.mc_forum_loadmore, Toast.LENGTH_SHORT).show();
                    xRecycler.loadMoreComplete();
                }
            }, 1000);

        }
    }
}
