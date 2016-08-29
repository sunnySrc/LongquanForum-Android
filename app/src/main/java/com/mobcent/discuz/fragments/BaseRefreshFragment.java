package com.mobcent.discuz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;
import com.mobcent.discuz.ui.EmptyLayout;

/**
 * Created by sun on 2016/8/23.
 * 基础刷新Fragment
 */

public abstract class BaseRefreshFragment extends BaseFragment implements  SwipeRefreshLayout.OnRefreshListener{

    protected SwipeRefreshLayout mRefreshLayout;
    /**
     *  空白 错误 占位布局
     */
    protected EmptyLayout mErrorLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pull_refresh, container, false);
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
        mRefreshLayout.addView(onCreateContentLayout(inflater, container, savedInstanceState));
        mRefreshLayout.setOnRefreshListener(this);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRefresh();
        mRefreshLayout.setRefreshing(true);
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
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    protected abstract View onCreateContentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);




}
