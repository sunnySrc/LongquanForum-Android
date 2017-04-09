package com.mobcent.discuz.module.user.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;

import discuz.com.bean.me.Publish;


/**
 * Created by pangxiaomin on 16/11/20.
 * 我的发表的item数据显示
 */
public class ItemPublishViewHolder extends RecyclerView.ViewHolder {

    public void bindConent(Publish publish,Context ctx){

        if(publish!=null){
            Glide.with(ctx)
                    .load(publish.getUserAvatar())
                    .into(item_publish_avatar);
            item_publish_name.setText(publish.getUser_nick_name());
            item_publish_time.setText(publish.getLast_reply_date()+"");
            tv_content.setText(publish.getTitle());
            tv_look_num.setText(publish.getHits());
            item_publish_reply.setText(publish.getReplies());
        }

    }

    ImageView item_publish_avatar;
    TextView item_publish_name;
    TextView item_publish_time;
    TextView tv_content;
    TextView tv_look_num;
    TextView item_publish_reply;

    public ItemPublishViewHolder(View view) {
        super(view);
        item_publish_avatar = (ImageView) view.findViewById(R.id.item_publish_avatar);
        item_publish_name = (TextView) view.findViewById(R.id.item_publish_name);
        item_publish_time = (TextView) view.findViewById(R.id.item_publish_time);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_look_num = (TextView) view.findViewById(R.id.tv_look_num);
        item_publish_reply = (TextView) view.findViewById(R.id.item_publish_reply);
    }
}
