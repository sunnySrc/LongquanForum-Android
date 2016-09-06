package com.mobcent.discuz.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.common.ImageLoader;
import com.mobcent.common.TimeUtil;
import com.mobcent.discuz.base.EmoticonHelper;
import com.mobcent.discuz.bean.TopicReply;
import com.mobcent.discuz.ui.ReplyActionPopup;
import com.mobcent.discuz.widget.ComAdapter;
import com.mobcent.discuz.widget.ViewHolder;

import java.util.List;

/**
 * Created by sun on 2016/9/1.
 *  帖子回复列表
 */

public class TopicReplyAdapter extends ComAdapter<TopicReply> {

    private  ReplyActionPopup popupWindow;
    View mPopOperationMenu ;
    public TopicReplyAdapter(Context context, List<TopicReply> objects) {
        super(context, R.layout.topic_detail_reply_item, objects);
    }

    @Override
    public void customSet(ViewHolder holder, int position) {
        final TopicReply item = getItem(position);
        ImageView userHead = holder.getView(R.id.reply_user_img);
        ImageLoader.load(item.getIcon(), userHead, 4);
        holder.setText(R.id.reply_user_name_text, item.getReply_name());
        holder.setText(R.id.reply_user_title_text, item.getUserTitle());
        holder.setText(R.id.reply_time_text, TimeUtil.formatDateToDay(Long.valueOf(item.getPosts_date())));

        // 楼层
        holder.setText(R.id.reply_lab_text, getContext().getString(R.string.reply_lab, item.getPosition()));

        // 这个内容列表 TODO 图片
        String content = item.getReply_content().get(0).getInfor();
        final TextView tv = holder.getView(R.id.reply_content_text);
        final int height = (int) tv.getTextSize();
        EmoticonHelper.addEmoticonSpans(getContext(), content, height, new EmoticonHelper.EmoticonCallback() {
            @Override
            public void onEmoticonReady(SpannableStringBuilder builder) {
                tv.setText(builder);
            }
        });


        TextView quoteTv = holder.getView(R.id.reply_quote_content_text);
        quoteTv.setVisibility(item.getIs_quote() > 0 ? View.VISIBLE : View.GONE);
        quoteTv.setText(item.getQuote_content());

        // 更多
        holder.getView(R.id.reply_more_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, item);
            }
        });

    }

    private void showPopupWindow(View anchor, TopicReply item) {
        popupWindow = new ReplyActionPopup(getContext(), item);
        popupWindow.showAsLeft(anchor);
    }

}
