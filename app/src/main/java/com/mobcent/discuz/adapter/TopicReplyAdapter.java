package com.mobcent.discuz.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.common.ImageLoader;
import com.mobcent.common.TimeUtil;
import com.mobcent.discuz.bean.TopicReply;
import com.mobcent.discuz.widget.ComAdapter;
import com.mobcent.discuz.widget.ViewHolder;

import java.util.List;

/**
 * Created by sun on 2016/9/1.
 *  帖子回复列表
 */

public class TopicReplyAdapter extends ComAdapter<TopicReply>{
    public TopicReplyAdapter(Context context, List<TopicReply> objects) {
        super(context, R.layout.topic_detail_reply_item, objects);
    }

    @Override
    public void customSet(ViewHolder holder, TopicReply item, int position) {
        ImageView userHead = holder.getView(R.id.reply_user_img);
        ImageLoader.load(item.getIcon(), userHead, 4);
        holder.setText(R.id.reply_user_name_text, item.getReply_name());
        holder.setText(R.id.reply_user_title_text, item.getUserTitle());
        holder.setText(R.id.reply_time_text, TimeUtil.formatDateToDay(Long.valueOf(item.getPosts_date())));

        // 楼层
        holder.setText(R.id.reply_lab_text, getContext().getString(R.string.reply_lab, item.getPosition()));

        // 这个内容列表
        holder.setText(R.id.reply_content_text, item.getReply_content().get(0).getInfor());
        TextView quoteTv = holder.getView(R.id.reply_quote_content_text);
        quoteTv.setVisibility(item.getIs_quote() > 0 ? View.VISIBLE : View.GONE);
        quoteTv.setText(item.getQuote_content());

    }
}
