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
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.module.user.adapter.MessageRecycleAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import discuz.com.net.service.model.BaseResult;
import discuz.com.net.service.model.me.MyMessage;

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
        RequestParams params = new RequestParams();
        String str = "{\"pageSize\":50,\"page\":1}";

        try {
            params.add("json", URLEncoder.encode(str, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        params.setUseCache(true);
        DiscuzRequest request = new DiscuzRequest(UrlFactory.MESSAGE_PMSESSIONLIST, params,
                new HttpResponseHandler() {
                    @Override
                    public void onSuccess(String result) {

                        try {
                            MessageResult resultbean = new Gson().fromJson(result, MessageResult.class);
                            ArrayList<MyMessage> list = resultbean.getBody().list;
                            if (list != null && list.size() > 0) {
                                //表示有好友消息
                                adapter.setData(list);
                                xRecycler.refreshComplete();
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Toast.makeText(MyMessageActivity.this, getString(R.string.mc_forum_json_error), Toast.LENGTH_LONG).show();
                            xRecycler.refreshComplete();
                        }

                    }


                    @Override
                    public void onFail(String result) {
                    }
                });
        request.begin();
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

    class MessageResult extends BaseResult<MessageResult.Body> {


        class Body {
            ArrayList<MyMessage> list;

        }
    }

}
