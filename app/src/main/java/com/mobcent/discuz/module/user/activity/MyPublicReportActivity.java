package com.mobcent.discuz.module.user.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.appbyme.dev.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.activity.PublishTopicActivity;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.MyPublic_adapter;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.mypublicbean.Lists;
import discuz.com.net.service.model.bean.mypublicbean.MyPublicBean;
import discuz.com.retrofit.library.HTTPSubscriber;

/**
 * 我的发表
 */
public class MyPublicReportActivity extends BasePopActivity {
    RelativeLayout nothingReport;
    XRecyclerView xRecycler;
    MyPublic_adapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_public_report);
        xRecycler= (XRecyclerView) findViewById(R.id.xr_my_public);
        nothingReport= (RelativeLayout) findViewById(R.id.activity_my_public_nothingReport);
        xRecycler.setVisibility(View.INVISIBLE);
        nothingReport.setVisibility(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //设置瀑布流管理器
        xRecycler.setLayoutManager(layoutManager);
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
        newWork();
    }



    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    private void newWork() {
        String uid= LoginUtils.getInstance().getUserId();
        DiscuzRetrofit.getUserInfoService(this).myPublic(WebParamsMap.myPublic(uid)).subscribe(new HTTPSubscriber<MyPublicBean>() {
            @Override
            public void onSuccess(MyPublicBean myPublicBean) {
                if (myPublicBean.getList().size()!=0){
                    xRecycler.setVisibility(View.VISIBLE);
                    nothingReport.setVisibility(View.GONE);
                    String errInfo=myPublicBean.getHead().getErrInfo();
                    String errCode=myPublicBean.getHead().getErrCode();
                java.util.List<Lists> list=myPublicBean.getList();
                adapter=new MyPublic_adapter(MyPublicReportActivity.this,list);
                xRecycler.setAdapter(adapter);
                if (myPublicBean.getHead().getErrCode().equals("00000000")){
                    xRecycler.refreshComplete();
                }
            }else {
                    // 点击跳转到发表页面
                    xRecycler.setVisibility(View.GONE);
                    nothingReport.setVisibility(View.VISIBLE);

            }

            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {

            }
        });
    }

    //点击
    public void mypublic(View view){
        Intent intent_publish=new Intent(this, PublishTopicActivity.class);
        intent_publish.putExtra("Type", "0");
        startActivity(intent_publish);
    }

}
