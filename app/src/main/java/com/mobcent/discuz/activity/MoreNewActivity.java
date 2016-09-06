package com.mobcent.discuz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appbyme.dev.R;
import com.mobcent.common.JsonConverter;
import com.mobcent.discuz.adapter.HomeMoreAdapter;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.Tasker;
import com.mobcent.discuz.bean.MoreNewResult;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.widget.LoadMoreViewManager;

import java.util.ArrayList;
import java.util.List;

import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_ERROR;
import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_NORMAL;
import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_NO_MORE;

/**
 * Created by sun on 2016/9/6.
 */

public class MoreNewActivity extends BaseRefreshActivity {
    private int pageNum;
    private ListView mListView;
    private LoadMoreViewManager mMoreViewManager;
    private List<MoreNewResult.ListBean> mListDataSet = new ArrayList<>();
    private HomeMoreAdapter mAdapter;
    private long mBoardId;

    public static void start(Context context, long id) {
        Intent starter = new Intent(context, MoreNewActivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);
    }
    @Override
    public void initParams(Bundle bundle) {
       mBoardId = bundle.getLong("id");
    }

    @Override
    protected View onCreateContentLayout(ViewGroup container, Bundle savedInstanceState) {
        mListView = (ListView) getLayoutInflater().inflate(R.layout.listview_base, container, false);
        mMoreViewManager = new LoadMoreViewManager(mListView);
        mMoreViewManager.setNoMoreDateHintRes(R.string.mc_forum_detail_load_finish);
        return mListView;
    }

    @Override
    public void onLoadMore() {
        add(LqForumApi.moreTopics(mBoardId, ++pageNum, new HttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                MoreNewResult news = JsonConverter.format(result, MoreNewResult.class);
                updateListView(news);
            }

            @Override
            public void onFail(String result) {
                mMoreViewManager.setFooterType(TYPE_ERROR);
            }
        }));
    }

    private void updateListView(MoreNewResult news) {
        mListDataSet.addAll(news.getList());
        if (mAdapter == null) {
            mAdapter = new HomeMoreAdapter(this, mListDataSet);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
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
    protected Tasker onExecuteRequest(HttpResponseHandler handler) {
        pageNum = 1;
        mListDataSet.clear();
        return LqForumApi.moreTopics(mBoardId, pageNum, handler);
    }

    @Override
    protected void showContent(String result) {
        MoreNewResult news = JsonConverter.format(result, MoreNewResult.class);
        updateListView(news);
    }


}
