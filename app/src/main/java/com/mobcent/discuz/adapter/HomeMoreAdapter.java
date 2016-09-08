package com.mobcent.discuz.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.common.TimeUtil;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.bean.MoreNewResult;
import com.mobcent.discuz.widget.ComAdapter;
import com.mobcent.discuz.widget.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2016/9/1.
 *  更多话题
 */

public class HomeMoreAdapter extends ComAdapter<MoreNewResult.ListBean> {
    final String replyCountSuffix;
    public List<MoreNewResult.ListBean> mListDataSet = new ArrayList<>();
    public HomeMoreAdapter(Context context) {
        super(context, R.layout.topic_list_neteasenews_none_desc_item);
        replyCountSuffix = getContext().getString(R.string.mc_forum_reply);
    }

    @Override
    public int getCount() {
        return mListDataSet.size();
    }

    @Override
    public void customSet(ViewHolder holder, int position) {
        final MoreNewResult.ListBean item = mListDataSet.get(position);
        holder.getView(R.id.recommend_layout).setVisibility(View.GONE);
        holder.getView(R.id.mc_forum_topic_state).setVisibility(View.GONE);

        final ImageView imageView = holder.getView(R.id.mc_forum_thumbnail_img);
        // 配图
        if (!TextUtils.isEmpty(item.getPic_path())) {
            imageView.setVisibility(View.VISIBLE);
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    Glide.with(getContext()).load(item.getPic_path()).into(imageView);
                }
            });
        } else {
            imageView.setVisibility(View.GONE);
        }
        // 标题
        TextView tvTitle = (TextView) holder.getView(R.id.mc_forum_topic_title_text);
        tvTitle.setText(item.getTitle());
        // 用户
        holder.setText(R.id.mc_forum_portal_user, item.getUser_nick_name());
        // 日期
        holder.setText(R.id.mc_forum_portal_time, TimeUtil.formatDateToDay(Long.valueOf(item.getLast_reply_date())));
        // 评论次数
        holder.setText(R.id.mc_forum_reply_num, String.valueOf(item.getReplies()) + replyCountSuffix);

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIJumper.jumpTopic(getContext(), item.getSource_id() != 0 ? item.getSource_id() : item.getTopic_id());
            }
        });

    }


}
