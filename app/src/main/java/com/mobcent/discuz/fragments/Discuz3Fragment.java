package com.mobcent.discuz.fragments;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.appbyme.dev.R;
import com.litesuits.android.log.Log;
import com.mobcent.common.JsonConverter;
import com.mobcent.discuz.adapter.HomeMoreAdapter;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.bean.HomeResult;
import com.mobcent.discuz.bean.MoreNewResult;
import com.mobcent.discuz.ui.ComponentBuilder;
import com.mobcent.discuz.widget.LoadMoreViewManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_ERROR;
import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_NORMAL;
import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_NO_MORE;

/**
 * Created by ubuntu on 16-6-21.
 */
public class Discuz3Fragment extends BaseRefreshFragment {

    public static final String TAG = Discuz2Fragment.class.getSimpleName();
    private DiscuzRequest request;
    private int page = 1;
    private ListView mListView;
    private HomeMoreAdapter mAdapter;
    private LoadMoreViewManager mMoreViewManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View onCreateContentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = (ListView) inflater.inflate(R.layout.listview_base, container, false);
        mMoreViewManager = new LoadMoreViewManager(mListView);
        mMoreViewManager.setNoMoreDateHintRes(R.string.mc_forum_detail_load_finish);
        mAdapter = new HomeMoreAdapter(getContext());
        mListView.setAdapter(mAdapter);
        return mListView;
    }

    @Override
    protected void onExecuteRequest(HttpResponseHandler handler) {
        page = 1;
        request = LqForumApi.topicList(page, "marrow", "0", this);
    }

    @Override
    public void onRefreshing() {
    }

    @Override
    public void onLoadMore() {
        LqForumApi.topicList(++page, "marrow", "0", new HttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                MoreNewResult news = JsonConverter.format(result, MoreNewResult.class);
                updateListView(news);
            }

            @Override
            public void onFail(String result) {
                mMoreViewManager.setFooterType(TYPE_ERROR);
            }
        });
    }

    private void updateListView(MoreNewResult news) {
        mAdapter.mListDataSet.addAll(news.getList());
        mAdapter.notifyDataSetChanged();
        updateReplyLoadState(news);
    }

    private void updateReplyLoadState(MoreNewResult news) {
        mRefreshLayout.onLoadComplete();
        if (news.getHas_next() == 0) {
            mRefreshLayout.setNoMoreData();
            mMoreViewManager.setFooterType(TYPE_NO_MORE);
        } else {
            mRefreshLayout.setCanLoadMore();
            mMoreViewManager.setFooterType(TYPE_NORMAL);
        }
    }

    @Override
    protected void showContent(String result) {
        mAdapter.mListDataSet.clear();
        MoreNewResult news = JsonConverter.format(result, MoreNewResult.class);
        updateListView(news);
    }

    @Override
    public void onDestroy() {
        if (request != null) {
            request.cancel(true);
        }
        super.onDestroy();
    }
}
