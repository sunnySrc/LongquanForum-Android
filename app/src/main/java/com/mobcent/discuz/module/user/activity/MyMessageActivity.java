package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.api.RequestParams;
import com.mobcent.discuz.api.UrlFactory;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.module.user.adapter.MessageRecycleAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.me.MessageResult;
import discuz.com.retrofit.library.HTTPSubscriber;

import static android.widget.Toast.makeText;


/**
 * Created by sun on 2017/5/20.
 * 我的->我的消息
 */
public class MyMessageActivity extends BasePopActivity {

    private XRecyclerView xRecycler;
    private MessageRecycleAdapter adapter;

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);

        getAppActionBar().setTitle(R.string.mc_forum_my_message);
        getAppActionBar().setRightIcon(R.drawable.dz_board_icon_follow, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //右上角两个小人图标，进入找朋友界面
                //// TODO: 2017/5/25
                Toast.makeText(MyMessageActivity.this, "我的好友，周边，推荐", Toast.LENGTH_LONG).show();
            }
        });

        xRecycler = (XRecyclerView) findViewById(R.id.xr_test);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        xRecycler.setLayoutManager(layoutManager);
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
        xRecycler.setLoadingListener(new LoadingMore());
        adapter = new MessageRecycleAdapter();
        xRecycler.setAdapter(adapter);
        onRefresh1();
    }

    @Override
    public int initLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }


    private void onRefresh1() {
        String str = "{\"pageSize\":50,\"page\":1}";
        HashMap<String, String> my_message_map = WebParamsMap.map();
        try {
            my_message_map.put("json", URLEncoder.encode(str, "utf-8"));
        } catch (UnsupportedEncodingException e) {
        }
        DiscuzRetrofit.getUserInfoService(this).requestMessage(my_message_map)
                .subscribe(new HTTPSubscriber<MessageResult>() {
                    @Override
                    public void onSuccess(MessageResult userResult) {
                        ArrayList<MessageResult.Body.MyMessage> list = userResult.getBody().list;
                        if (list != null && list.size() > 0) {
                            //表示有好友消息
                            adapter.setData(list);
                        }
                        xRecycler.refreshComplete();

                    }

                    @Override
                    public void onFail(int httpCode, int errorUserCode, String message) {
                        showToast(message);
                    }
                });


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
                    makeText(MyMessageActivity.this, R.string.mc_forum_loadmore, Toast.LENGTH_SHORT).show();
                    xRecycler.loadMoreComplete();
                }
            }, 1000);

        }
    }


}
