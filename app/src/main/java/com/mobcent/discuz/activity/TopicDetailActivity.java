package com.mobcent.discuz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.common.ImageLoader;
import com.mobcent.common.JsonConverter;
import com.mobcent.common.TimeUtil;
import com.mobcent.discuz.activity.helper.TopicHelper;
import com.mobcent.discuz.adapter.TopicReplyAdapter;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.Tasker;
import com.mobcent.discuz.base.constant.BaseIntentConstant;
import com.mobcent.discuz.bean.Base;
import com.mobcent.discuz.bean.Topic;
import com.mobcent.discuz.bean.TopicReply;
import com.mobcent.discuz.bean.TopicResult;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.widget.LoadMoreViewManager;
import com.mobcent.discuz.widget.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_ERROR;
import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_NORMAL;
import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_NO_MORE;

/**
 * Created by sun on 2016/8/29.
 * 帖子详情
 */

public class TopicDetailActivity extends BaseRefreshActivity {
    private int pageNum ;
    private long topicId;
    private ViewHolder mTopicViewHolder;

    private TextView tvFollow; //关注
    private boolean isFollow;
    private LinearLayout lvContent;
    private ListView listViewReplies;
    private TopicReplyAdapter mReplyAdapter;
    private List<TopicReply> mReplyList = new ArrayList<>();
    private LoadMoreViewManager mMoreViewManager;

    public static void start(Context context, long id) {
        Intent starter = new Intent(context, TopicDetailActivity.class);
        starter.putExtra(BaseIntentConstant.BUNDLE_TOPIC_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected Tasker onExecuteRequest(HttpResponseHandler handler) {
        pageNum = 1;
        mReplyList.clear();
        return LqForumApi.topicDetail(topicId, pageNum, handler);
    }

    public void onLoadMore() {
        add(LqForumApi.topicDetail(topicId, ++pageNum, new HttpResponseHandler() {
            @Override
            public void onSuccess(String result) {

                TopicResult home = JsonConverter.format(result, TopicResult.class);
                int currentCount = home.getTotalNum();
                mReplyList.addAll(home.getList());
                mReplyAdapter.notifyDataSetChanged();

                checkLoadState(home.getList());
            }

            @Override
            public void onFail(String result) {
                mMoreViewManager.setFooterType(TYPE_ERROR);
            }
        }));
    }

    @Override
    protected void showContent(String result) {
        // 注意loadmore
        TopicResult home = JsonConverter.format(result, TopicResult.class);
        setTitle(home.getForumName());
        updateTopicView(home.getTopic());

        // 评论

        mReplyList.addAll(home.getList());
        mReplyAdapter = new TopicReplyAdapter(this, mReplyList);
        listViewReplies.setAdapter(mReplyAdapter);

        checkLoadState(home.getList());
    }

    private void checkLoadState(List<TopicReply> list) {
        mRefreshLayout.onLoadComplete();
        if (list.size() < LqForumApi.PAGE_SIZE_TOPIC_REPLY) {
            mRefreshLayout.setNoMoreData();
            mMoreViewManager.setFooterType(TYPE_NO_MORE);
        } else {
            mRefreshLayout.setCanLoadMore();
            mMoreViewManager.setFooterType(TYPE_NORMAL);
        }
    }

    // 更新帖子主题
    private void updateTopicView(Topic topic) {
        //标题区域
        mTopicViewHolder.setText(R.id.post_title, topic.getTitle());
        mTopicViewHolder.getView(R.id.post_is_essence).setVisibility(topic.getEssence() > 0 ? View.VISIBLE : View.GONE);
        mTopicViewHolder.setText(R.id.post_read_count, String.valueOf(topic.getHits()));

        //发帖用户区
        mTopicViewHolder.setText(R.id.posts_user_name_text, topic.getUser_nick_name());
        mTopicViewHolder.setText(R.id.posts_user_title_text, topic.getUserTitle());
        mTopicViewHolder.setText(R.id.posts_user_date_text, TimeUtil.formatDateToDay(Long.valueOf(topic.getCreate_date())));

        ImageView userHead = mTopicViewHolder.getView(R.id.posts_user_img);
        ImageLoader.load(topic.getIcon(), userHead, 4);

        // 关注TA
        tvFollow = mTopicViewHolder.getView(R.id.follow_btn);
        final long userId = topic.getUser_id();
        isFollow = topic.getIsFollow() == 1;
        tvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followUser(!isFollow, userId);
            }
        });
        updateFollowState(isFollow);

        // 帖子内容
        lvContent = mTopicViewHolder.getView(R.id.topic_content_list);
        TopicHelper.updateContent(this, lvContent, topic.getContent());

    }

    private void updateFollowState(boolean isFollow) {
        if (!isFollow) {
            tvFollow.setText(R.string.mc_forum_follow_ta);
            tvFollow.setClickable(true);
        } else {
            tvFollow.setText(R.string.mc_forum_follow_ta_sucess);
            tvFollow.setClickable(false);
        }
    }

    /**
     *
     * @param toFollow
     * @param userId
     */
    private void followUser(final boolean toFollow, long userId) {
        LqForumApi.followUser(toFollow, userId, new HttpResponseHandler(){

            @Override
            public void onSuccess(String result) {
                Base base = JsonConverter.format(result, Base.class);
                // 提示
                base.checkAlert(getBaseContext());

                // 显示
                if (base.isSuccess()){
                    isFollow = toFollow;
                    updateFollowState(toFollow);
                }
            }

            @Override
            public void onFail(String result) {
                Base base = JsonConverter.format(result, Base.class);
                // 提示
                base.checkAlert(getBaseContext());
            }
        });

    }

    @Override
    protected View onCreateContentLayout(ViewGroup container, Bundle savedInstanceState) {
        listViewReplies = (ListView) getLayoutInflater().inflate(R.layout.activity_topic_replies, container, false);
        View contentView =  getLayoutInflater().inflate(R.layout.topic_detail_header, listViewReplies, false);
        mTopicViewHolder = new ViewHolder(contentView);
        listViewReplies.addHeaderView(contentView);
        mMoreViewManager = new LoadMoreViewManager(listViewReplies);
        mMoreViewManager.setNoMoreDateHintRes(R.string.mc_forum_detail_load_finish);
        return listViewReplies;
    }

    @Override
    public void initParams(Bundle bundle) {
        pageNum = 1;
        topicId = bundle.getLong(BaseIntentConstant.BUNDLE_TOPIC_ID);
    }
}
