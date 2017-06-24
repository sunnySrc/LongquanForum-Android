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
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.module.user.adapter.CommentRecycleAdapter;
import com.mobcent.discuz.module.user.adapter.SystemRecycleAdapter;

import java.util.ArrayList;

import discuz.com.net.service.model.BaseResult;
import discuz.com.net.service.model.me.CommentAboutMe;
import discuz.com.net.service.model.me.SystemAboutMe;

import static android.widget.Toast.makeText;

/**
 * Created by sun on 2017/5/26.
 * 系统消息界面
 */
public class MySystemActivity extends BasePopActivity {

    private XRecyclerView xRecycler;
    private SystemRecycleAdapter adapter;

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
    }

    @Override
    public int initLayout() {
        return R.layout.activity_collection;
    }

    private void onRefresh1() {
        Bundle extras = getIntent().getExtras();
        RequestParams params = new RequestParams();
        for (String key : extras.keySet()) {
            params.add(key, String.valueOf(extras.get(key)));
        }

        params.setUseCache(true);
        DiscuzRequest request = new DiscuzRequest(UrlFactory.MESSAGE_NOTIFYLISTEX, params,
                new HttpResponseHandler() {
                    @Override
                    public void onSuccess(String result) {
                        CommonResult commoResult = new Gson().fromJson(result, CommonResult.class);
                        ArrayList<SystemAboutMe> list = commoResult.getBody().data;
                        if (list.size() > 0) {
                            adapter = new SystemRecycleAdapter(list);
                            xRecycler.setAdapter(adapter);
                            xRecycler.refreshComplete();
                        } else
                            Toast.makeText(MySystemActivity.this, "没有人消息你" + result, Toast.LENGTH_LONG).show();

                    }


                    @Override
                    public void onFail(String result) {
                        Toast.makeText(MySystemActivity.this, getString(R.string.mc_forum_connection_fail) + result, Toast.LENGTH_LONG).show();

                    }
                });
        request.begin();
    }


    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    class CommonResult extends BaseResult<CommonResult.Body> {


        class Body {
            ArrayList<SystemAboutMe> data;

        }
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
