package com.mobcent.discuz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.common.ImageLoader;
import com.mobcent.common.JsonConverter;
import com.mobcent.common.TimeUtil;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.Tasker;
import com.mobcent.discuz.base.constant.BaseIntentConstant;
import com.mobcent.discuz.bean.Topic;
import com.mobcent.discuz.bean.TopicResult;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.widget.ViewHolder;

/**
 * Created by sun on 2016/8/29.
 * 帖子详情
 */

public class TopicDetailActivity extends BaseRefreshActivity {
    private int pageNum = 1;
    private long topicId;
    private ViewHolder mRootViewHolder;

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

    public void onLoadMore() {
    }

    @Override
    protected void showContent(String result) {
        // 注意loadmore
        TopicResult home = JsonConverter.format(result, TopicResult.class);
        setTitle(home.getForumName());
        updateTopicView(home.getTopic());
    }

    // 更新帖子主题
    private void updateTopicView(Topic topic) {
        //标题区域
        mRootViewHolder.setText(R.id.post_title, topic.getTitle());
        mRootViewHolder.getView(R.id.post_is_essence).setVisibility(topic.getEssence() > 0 ? View.VISIBLE : View.GONE);
        mRootViewHolder.setText(R.id.post_read_count, String.valueOf(topic.getHits()));

        //发帖用户区
        mRootViewHolder.setText(R.id.posts_user_name_text, topic.getUser_nick_name());
        mRootViewHolder.setText(R.id.posts_user_title_text, topic.getUserTitle());
        mRootViewHolder.setText(R.id.posts_user_date_text, TimeUtil.formatDateToDay(Long.valueOf(topic.getCreate_date())));

        ImageView userHead = mRootViewHolder.getView(R.id.posts_user_img);
        ImageLoader.load(topic.getIcon(), userHead, 4);
    }

    @Override
    protected View onCreateContentLayout(ViewGroup container, Bundle savedInstanceState) {
        View view =  getLayoutInflater().inflate(R.layout.activity_topic_detail, container, false);
        mRootViewHolder = new ViewHolder(view);
        return view;
    }

    @Override
    public void initParams(Bundle bundle) {
        topicId = bundle.getLong(BaseIntentConstant.BUNDLE_TOPIC_ID);
    }


    public int getPageNum() {
        return pageNum;
    }

}
