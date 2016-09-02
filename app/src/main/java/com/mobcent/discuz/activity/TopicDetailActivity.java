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
import com.bumptech.glide.Glide;
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
import com.mobcent.discuz.bean.TopicResult;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.widget.ViewHolder;

import static com.appbyme.dev.R.id.tv;

/**
 * Created by sun on 2016/8/29.
 * 帖子详情
 */

public class TopicDetailActivity extends BaseRefreshActivity {
    private int pageNum = 1;
    private long topicId;
    private ViewHolder mRootViewHolder;

    private TextView tvFollow; //关注
    private boolean isFollow;
    private LinearLayout lvContent;
    private ListView listViewReplies;

    public static void start(Context context, long id) {
        Intent starter = new Intent(context, TopicDetailActivity.class);
        starter.putExtra(BaseIntentConstant.BUNDLE_TOPIC_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected Tasker onExecuteRequest(HttpResponseHandler handler) {
        topicId = 64551;
        return LqForumApi.topicDetail(topicId, getPageNum(), handler);
    }

    public void onLoadMore() {
    }

    @Override
    protected void showContent(String result) {
        // 注意loadmore
        TopicResult home = JsonConverter.format(result, TopicResult.class);
        setTitle(home.getForumName());
        updateTopicView(home.getTopic());


        // 评论
        listViewReplies.setAdapter(new TopicReplyAdapter(this, home.getList()));
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

        // 关注TA
        tvFollow = mRootViewHolder.getView(R.id.follow_btn);
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
        lvContent = mRootViewHolder.getView(R.id.topic_content_list);
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
        View view =  getLayoutInflater().inflate(R.layout.activity_topic_detail, container, false);
        mRootViewHolder = new ViewHolder(view);
        listViewReplies = mRootViewHolder.getView(R.id.topic_reply_list);
        listViewReplies.setFocusable(false);
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
