package com.mobcent.discuz.module.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.common.TimeUtil;
import com.mobcent.discuz.activity.PublishTopicActivity;
import com.mobcent.discuz.base.EmoticonHelper;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.bean.Component;
import com.mobcent.discuz.bean.Reply;

import java.util.ArrayList;

import discuz.com.net.service.model.me.CommentAboutMe;

/**
 * Created by sun on 2017/5/29.
 * 我的消息，评论item布局
 */
public class CommentRecycleAdapter extends RecyclerView.Adapter<CommentRecycleAdapter.ViewHolder> {
    ArrayList<CommentAboutMe> comment_list;

    public CommentRecycleAdapter(ArrayList<CommentAboutMe> list) {
        comment_list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.at_list1_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(comment_list.get(position));
    }

    @Override
    public int getItemCount() {
        return comment_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView user_icon_img;
        private final TextView user_name_text;
        private final TextView time_text;
        private final TextView topic_content_text;
        private final TextView reply_content_text;
        private final Context mContext;
        CommentAboutMe data;

        public ViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            user_icon_img = (ImageView) view.findViewById(R.id.user_icon_img);
            user_name_text = (TextView) view.findViewById(R.id.user_name_text);
            time_text = (TextView) view.findViewById(R.id.time_text);
            reply_content_text = (TextView) view.findViewById(R.id.reply_content_text);
            topic_content_text = (TextView) view.findViewById(R.id.topic_content_text);
            view.findViewById(R.id.reply_btn).setOnClickListener(this);
            view.findViewById(R.id.comment_at_item_layout).setOnClickListener(this);
        }

        public void setData(CommentAboutMe data) {
            this.data = data;
            Glide.with(mContext).load(data.icon).into(user_icon_img);
            user_name_text.setText(data.user_name);
            time_text.setText(TimeUtil.friendTime(Long.parseLong(data.replied_date)));
            int height = (int) reply_content_text.getTextSize();
            EmoticonHelper.addEmoticonSpans(mContext, data.reply_content, height, new EmoticonHelper.EmoticonCallback() {
                @Override
                public void onEmoticonReady(SpannableStringBuilder builder) {
                    reply_content_text.setText(builder);
                }
            });
            if (TextUtils.isEmpty(data.topic_content)) {
                ((View) topic_content_text.getParent()).setVisibility(View.GONE);
            } else {
                height = (int) topic_content_text.getTextSize();
                EmoticonHelper.addEmoticonSpans(mContext, data.topic_content, height, new EmoticonHelper.EmoticonCallback() {
                    @Override
                    public void onEmoticonReady(SpannableStringBuilder builder) {
                        topic_content_text.setText(builder);
                    }
                });
            }


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.reply_btn) {
                // TODO: 2017/5/29  点击回复跳转
//                最后一个参数如果是>1表示显示下面的子评论，
                int isShowchildcomment = TextUtils.isEmpty(data.topic_content) ? 0 : 1;
                Reply.JsonBean json = new Reply.JsonBean();
                json.setFid(data.board_id);
                json.setTid((int) data.topic_id);
                json.setIsQuote(isShowchildcomment);
                json.replyId = data.reply_remind_id;
                PublishTopicActivity.start(mContext, Reply.build(json));

            } else if (v.getId() == R.id.comment_at_item_layout) {
                //跳转到帖子详情页
                UIJumper.jump(mContext, Component.TYPE_POST_LIST, data.topic_id, null);

            }
        }
    }
}
