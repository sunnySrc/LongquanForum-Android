package com.mobcent.discuz.module.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.myFriends_adapter.Myfriends_homepage_adapter;
import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.myfriendsHomepage.List;
import discuz.com.net.service.model.bean.myfriendsHomepage.MyfriendsHomepage;
import discuz.com.retrofit.library.HTTPSubscriber;


public class MyFriendsActivity extends BasePopActivity {
    private java.util.List<List> list;
    Myfriends_homepage_adapter adapter;
    TextView btn_myfriends;
    XRecyclerView xRecycler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);

        xRecycler= (XRecyclerView) findViewById(R.id.xr_test_myfrieds);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        View header = LayoutInflater.from(this).inflate(R.layout.item_myfriendearch_head, (ViewGroup)findViewById(android.R.id.content),false);
        xRecycler.addHeaderView(header);
        btn_myfriends= (TextView) header.findViewById(R.id.myfriends_button);
        btn_myfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyFriendsActivity.this,MyFriendsSearchActivity.class);
                startActivity(intent);
            }
        });
        xRecycler.setPullRefreshEnabled(true);
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
        netWork();
        
    }

    private void netWork() {
        DiscuzRetrofit.getUserInfoService(this).friendList(LoginUtils.getInstance().getUserId(), WebParamsMap.myfriends_homepage()).subscribe(new HTTPSubscriber<MyfriendsHomepage>() {

            @Override
            public void onSuccess(MyfriendsHomepage myFriends) {
                list=myFriends.getList();
                //adapter=new Myfriends_homepage_adapter(MyFriendsActivity.this,list);

            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
            }

        });
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }
}
