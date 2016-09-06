package com.mobcent.discuz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
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
import com.mobcent.discuz.bean.Reply;
import com.mobcent.discuz.bean.Topic;
import com.mobcent.discuz.bean.TopicReply;
import com.mobcent.discuz.bean.TopicResult;
import com.mobcent.discuz.fragments.EmotionExtraFragment;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.mobcent.discuz.widget.LoadMoreViewManager;
import com.mobcent.discuz.widget.ViewHolder;
import com.mobcent.lowest.base.utils.MCToastUtils;
import com.zejian.emotionkeyboard.fragment.EmotionMainFragment;

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
    private View mBottomLayout;
    private TextView mBottomCommentTv;
    private EmotionExtraFragment emotionMainFragment; //表情键盘
    private TopicResult resultBean;

    public static void start(Context context, long id) {
        Intent starter = new Intent(context, TopicDetailActivity.class);
        starter.putExtra(BaseIntentConstant.BUNDLE_TOPIC_ID, id);
        context.startActivity(starter);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_topic_refresh;
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
                updateReplyListView(home);
            }

            @Override
            public void onFail(String result) {
                mMoreViewManager.setFooterType(TYPE_ERROR);
            }
        }));
    }

    @Override
    protected void showContent(String result) {
        resultBean = JsonConverter.format(result, TopicResult.class);

        setTitle(resultBean.getForumName());
        // 帖子主题
        updateTopicView(resultBean.getTopic());
        // 评论列表
        updateReplyListView(resultBean);

        enableBottomPlaceHolderLayout(true);

        emotionMainFragment.setConfirmClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendContent();
            }
        });
    }

    // 底部加载样式
    private void updateReplyLoadState(List<TopicReply> list) {
        mRefreshLayout.onLoadComplete();
        if (list.size() < LqForumApi.PAGE_SIZE_TOPIC_REPLY) {
            mRefreshLayout.setNoMoreData();
            mMoreViewManager.setFooterType(TYPE_NO_MORE);
        } else {
            mRefreshLayout.setCanLoadMore();
            mMoreViewManager.setFooterType(TYPE_NORMAL);
        }
    }


    // 更新回帖
    private void updateReplyListView(TopicResult resultBean) {

        mBottomCommentTv.setText(String.valueOf(resultBean.getTotalNum())
                + getString(R.string.mc_forum_topic_detail_bottom_commnet_num_text));

        mReplyList.addAll(resultBean.getList());
        if (mReplyAdapter == null) {
            mReplyAdapter = new TopicReplyAdapter(this, mReplyList);
            listViewReplies.setAdapter(mReplyAdapter);
        } else {
            mReplyAdapter.notifyDataSetChanged();
        }
        updateReplyLoadState(resultBean.getList());
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

    // 更新关注状态
    private void updateFollowState(boolean isFollow) {
        if (!isFollow) {
            tvFollow.setText(R.string.mc_forum_follow_ta);
        } else {
            tvFollow.setText(R.string.mc_forum_follow_ta_sucess);
        }
    }

    /**
     * 关注
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

    /**
     * 回复
     */
    private void sendContent() {
        if (noInputContent()) return;

        Reply re = Reply.build(resultBean.getBoardId(), resultBean.getTopic().getTopic_id(), getInputContent());
        add(LqForumApi.reply(re, new HttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                // 回复成功
                JsonConverter.format(result, Base.class).checkAlert(getBaseContext());
                resetBottomLayout();
            }
            @Override
            public void onFail(String result) {
                MCToastUtils.toast(getBaseContext(), result);
            }
        }));
    }


    @Override
    protected View onCreateContentLayout(ViewGroup container, Bundle savedInstanceState) {
        listViewReplies = (ListView) getLayoutInflater().inflate(R.layout.listview_base, container, false);
        View contentView =  getLayoutInflater().inflate(R.layout.topic_detail_header, listViewReplies, false);
        mTopicViewHolder = new ViewHolder(getContentView());
        listViewReplies.addHeaderView(contentView);
        mMoreViewManager = new LoadMoreViewManager(listViewReplies);
        mMoreViewManager.setNoMoreDateHintRes(R.string.mc_forum_detail_load_finish);

        // 底部评论bar
        mBottomLayout = getContentView().findViewById(R.id.bottom_over_layout);
        mBottomCommentTv = (TextView)getContentView().findViewById(R.id.bottom_comment_text);
        mRefreshLayout.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if( SCROLL_STATE_TOUCH_SCROLL == scrollState && noInputContent()) {
                    enableBottomPlaceHolderLayout(true);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        // 输入键盘bar
        initEmotionLayout();
        // 显示评论输入提示bar
       mBottomLayout.findViewById(R.id.bottom_comment_layout).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

        return listViewReplies;
    }

    private boolean noInputContent() {
        return TextUtils.isEmpty(getInputContent());
    }

    private String getInputContent() {
        return  emotionMainFragment.getEditText().getText().toString().trim();
    }

    /**
     * 底部评论
     */
    private void enableBottomPlaceHolderLayout(boolean enable) {
        mBottomLayout.setVisibility(enable ? View.VISIBLE : View.GONE);
        View v = mBottomLayout.findViewById(R.id.bottom_comment_layout);
        if (enable) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enableBottomPlaceHolderLayout(false);
                    showKeyBoard(true);
                }

            });
            showKeyBoard(false);
        }
    }

    private void resetBottomLayout() {
        enableBottomPlaceHolderLayout(true);
        emotionMainFragment.getEditText().setText("");
    }


    private void showKeyBoard(boolean show) {
        InputMethodManager im = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        if (show) {
            im.showSoftInput(emotionMainFragment.getEditText(), 0);
        } else {
            emotionMainFragment.hideAllKeyBoard();
        }
    }

    @Override
    public void initParams(Bundle bundle) {
        pageNum = 1;
        topicId = bundle.getLong(BaseIntentConstant.BUNDLE_TOPIC_ID);
//        topicId = 64551;
    }

    /**
     * 初始化表情键盘输入
     */
    private void initEmotionLayout() {
        //构建传递参数
        Bundle fragmentBundle = new Bundle();
        //绑定主内容编辑框
        fragmentBundle.putBoolean(EmotionMainFragment.BIND_TO_EDITTEXT, true);
        //隐藏控件
        fragmentBundle.putBoolean(EmotionMainFragment.HIDE_BAR_EDITTEXT_AND_BTN,false);
        //替换fragment
        //创建修改实例
        emotionMainFragment = EmotionExtraFragment.newInstance(EmotionExtraFragment.class, fragmentBundle);
        emotionMainFragment.bindToContentView(mRefreshLayout);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_emotionview_main, emotionMainFragment);
        /**
         * 此地方会有bug fragment被移除
         */
//        transaction.addToBackStack(null);
        //提交修改
        transaction.commit();
    }
}
