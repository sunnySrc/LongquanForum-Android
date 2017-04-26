package com.mobcent.discuz.module.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.UserPublishAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import discuz.com.bean.me.Publish;
import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.me.PublishResult;
import discuz.com.retrofit.library.HTTPSubscriber;

import static com.mobcent.discuz.module.user.activity.UserHomeActivity.from;
import static com.mobcent.discuz.module.user.activity.UserHomeActivity.uid_myfriendsSearch;


/**
 * Created by pangxiaomin on 16/11/20.
 * 我-个人-发表
 * @author 张春生
 */
public class UserHomePublishFragment extends BaseUserInnerScrollFragment  {


    private RecyclerView mPublishRecyclerView;
    UserPublishAdapter adapter;
    List<Publish> datas;

    public static UserHomePublishFragment newInstance() {
        return new UserHomePublishFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestUserPublishInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home_publish, container, false);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPublishRecyclerView = $(view,R.id.fragment_publish_recyclerview);
        mPublishRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (from==true){
            Intent intent=getActivity().getIntent();
            String uid_SearchFriends=intent.getStringExtra("uid");
            Toast.makeText(getContext(),"uid="+uid_SearchFriends,Toast.LENGTH_SHORT).show();
            DiscuzRetrofit.getUserInfoService(getActivity()).requestUserPublish(WebParamsMap.user_public(uid_myfriendsSearch)).subscribe(new HTTPSubscriber<PublishResult>() {
                @Override
                public void onSuccess(PublishResult userResult) {
                    if(userResult!=null && userResult.list!=null){
                        datas.addAll(userResult.list);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFail(int httpCode, int errorUserCode, String message) {

                }
            });
            datas = new ArrayList<>();
            adapter = new UserPublishAdapter(getActivity(),datas);
            mPublishRecyclerView.setAdapter(adapter);
        }else {
            datas = new ArrayList<>();
            adapter = new UserPublishAdapter(getActivity(),datas);
            mPublishRecyclerView.setAdapter(adapter);
        }

    }

    @Override
    public boolean canScrollVertically(int direction) {
        return mPublishRecyclerView != null && mPublishRecyclerView.canScrollVertically(direction);
    }

    @Override
    public void onFlingOver(int y, long duration) {
        if (mPublishRecyclerView != null) {
            mPublishRecyclerView.smoothScrollBy(y, (int) duration);
        }
    }

    private void requestUserPublishInfo(){
        final HashMap<String,String> params = WebParamsMap.map();
        DiscuzRetrofit.getUserInfoService(getActivity()).requestUserPublish(WebParamsMap.map()).subscribe(new HTTPSubscriber<PublishResult>() {
            @Override
            public void onSuccess(PublishResult userResult) {
                if(userResult!=null && userResult.list!=null){
                    datas.addAll(userResult.list);
                    adapter.notifyDataSetChanged();
                }
                Log.i("longquan","获取数据成功");
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("longquan","获取失败");
            }
        });
    }
}
