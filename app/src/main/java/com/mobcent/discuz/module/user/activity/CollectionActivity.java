package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BaseRefreshActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.Tasker;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.Collection_adapter;

import java.util.ArrayList;
import java.util.List;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.CollectionList;
import discuz.com.net.service.model.bean.ColoectionBean;
import discuz.com.retrofit.library.HTTPSubscriber;

public class CollectionActivity extends BaseRefreshActivity {
    private List <CollectionList>list;
    private ViewGroup viewGroup;
    private String errCode;
    private int totalNum;
    Collection_adapter adapter;

    @Override
    public void initParams(Bundle bundle) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DiscuzRetrofit.getUserInfoService(this).requestUserCollection( LoginUtils.getInstance().getUserId(),WebParamsMap.map()).subscribe(new HTTPSubscriber<ColoectionBean>() {
            @Override
            public void onSuccess(ColoectionBean coloectionBean) {
                errCode=coloectionBean.getHead().getErrCode();
                totalNum=coloectionBean.getTotalNum();
                list=new ArrayList<CollectionList>();
                adapter=new Collection_adapter(CollectionActivity.this,list);
                adapter.addAll(list,true);

            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
            }

        });


    }

    @Override
    protected View onCreateContentLayout(ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.fragment_home, container, false);
        return viewGroup;
    }

    @Override
    protected Tasker onExecuteRequest(HttpResponseHandler handler) {
        return null;
    }

    @Override
    protected void showContent(String result) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_topic_refresh;
    }



}
