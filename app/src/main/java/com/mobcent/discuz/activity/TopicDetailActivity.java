package com.mobcent.discuz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;
import com.mobcent.common.JsonConverter;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.Tasker;
import com.mobcent.discuz.base.constant.BaseIntentConstant;
import com.mobcent.discuz.bean.TopicResult;
import com.mobcent.discuz.fragments.HttpResponseHandler;

/**
 * Created by sun on 2016/8/29.
 * 帖子详情
 */

public class TopicDetailActivity extends BaseRefreshActivity {
    private int pageNum = 1;
    private long topicId;

    public static void start(Context context, long id) {
        Intent starter = new Intent(context, TopicDetailActivity.class);
        starter.putExtra(BaseIntentConstant.BUNDLE_TOPIC_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected void onExecuteRequest(HttpResponseHandler handler) {
        Tasker tasker = LqForumApi.topicDetail(topicId, getPageNum(), handler);
        addPendingCancelTask(tasker);
    }

    @Override
    protected void showContent(String result) {
        // 注意loadmore
        TopicResult home = JsonConverter.format(result, TopicResult.class);
        setTitle(home.getForumName());

    }

    @Override
    protected View onCreateContentLayout(ViewGroup container, Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.activity_topic_detail, container, false);
    }

    @Override
    public void initParams(Bundle bundle) {
        topicId = bundle.getLong(BaseIntentConstant.BUNDLE_TOPIC_ID);
    }


    public int getPageNum() {
        return pageNum;
    }

}
