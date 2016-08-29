package com.mobcent.discuz.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.ui.EmptyLayout;

/**
 * Created by sun on 2016/8/29.
 */

public abstract class BaseRefreshActivity extends BaseActivity implements  SwipeRefreshLayout.OnRefreshListener,
        HttpResponseHandler {
    protected SwipeRefreshLayout mRefreshLayout;
    /**
     *  空白 错误 占位布局
     */
    protected EmptyLayout mErrorLayout;

    @Override
    public int bindLayout() {
        return R.layout.activity_pull_refresh;
    }

    @Override
    public void initView(ViewGroup root, Bundle savedInstanceState) {
        mRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swiperefreshlayout);
        mErrorLayout = (EmptyLayout) root.findViewById(R.id.error_layout);
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mState = STATE_REFRESH;
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                onRefresh();
            }
        });
        mRefreshLayout.addView(onCreateContentLayout(mRefreshLayout, savedInstanceState));
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(true);

        findViewById(R.id.nav_btn_back).setVisibility(View.VISIBLE);

    }

    @Override
    public void initData(Context mContext) {
        onRefresh();
    }


    @Override
    public void onRefresh() {
        onExecuteRequest(this);
    }

    @Override
    public void onSuccess(String result) {
        mRefreshLayout.setRefreshing(false);
        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        showContent(result);
    }

    @Override
    public void onFail(String result) {
        mRefreshLayout.setRefreshing(false);
        mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
    }

    /**
     * 设置Navi Bar 标题
     * @param title
     */
    public void setTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.nav_title);
        textView.setText(title);
    }

    /**
     * 请求接口
     * @param handler
     */
    protected abstract void onExecuteRequest(HttpResponseHandler handler);

    /**
     * 显示结果
     * @param result
     */
    protected abstract void showContent(String result);

    /**
     * 刷新内容布局
     * @return
     * @param container
     * @param savedInstanceState
     */
    protected abstract View onCreateContentLayout( ViewGroup container, Bundle savedInstanceState);


}
