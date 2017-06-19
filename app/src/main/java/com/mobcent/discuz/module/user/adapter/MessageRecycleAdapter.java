package com.mobcent.discuz.module.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.common.TimeUtil;
import com.mobcent.discuz.module.user.activity.MyCommentActivity;
import com.mobcent.discuz.module.user.activity.MySystemActivity;

import java.util.ArrayList;

import discuz.com.net.service.config.WebParamsKey;
import discuz.com.net.service.config.WebParamsValue;
import discuz.com.net.service.model.me.MyMessage;

/**
 * Created by sun on 2017/5/29.
 * 我的消息，评论item布局
 */
public class MessageRecycleAdapter extends RecyclerView.Adapter<MessageRecycleAdapter.ViewHolder> {
    private ArrayList<MyMessage> comment_list = new ArrayList<>();

    public MessageRecycleAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_activity_mymessage, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setPosition(position);
        switch (position) {
            case 0:
                holder.setonClick(R.drawable.dz_msg_icon_at, R.string.mc_forum_at_me);
                break;
            case 1://
                holder.setonClick(R.drawable.dz_msg_icon_reply, R.string.mc_forum_comment);
                break;
            case 2:
                holder.setonClick(R.drawable.dz_msg_icon_friend, R.string.mc_forum_sys_msg);
                break;
            default:
                holder.setData(comment_list.get(position - 3));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return comment_list.size() + 3;
    }

    public void setData(ArrayList<MyMessage> data) {
        this.comment_list = data;
        notifyDataSetChanged();
    }


    class FriendMsgHolder {
        private final ImageView my_msg_iv_type;
        private final TextView my_msg_tv_type;
        private final TextView my_msg_time;
        private final TextView my_msg_tv_content;
        private MyMessage data;

        private Context mContext;

        public FriendMsgHolder(View view) {
            mContext = view.getContext();
            my_msg_iv_type = (ImageView) view.findViewById(R.id.my_msg_iv_type);
            my_msg_tv_type = (TextView) view.findViewById(R.id.my_msg_tv_type);
            my_msg_time = (TextView) view.findViewById(R.id.my_msg_time);
            my_msg_tv_content = (TextView) view.findViewById(R.id.my_msg_tv_content);


        }

        public void setData(MyMessage data) {
            this.data = data;
            my_msg_tv_type.setText(data.toUserName);
            Glide.with(mContext).load(data.toUserAvatar).into(my_msg_iv_type);
            my_msg_tv_content.setText(data.lastSummary);
            my_msg_time.setText(TimeUtil.friendTime(Long.parseLong(data.lastDateline)));

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        FriendMsgHolder friendMsgHolder;


        private int position;
        private Context mContext;
        private final View msg_fixed_view;
        private final View msg_friend_view;

        public ViewHolder(View view) {
            super(view);
            mContext = view.getContext();

            msg_friend_view = view.findViewById(R.id.my_msg_friend);

            msg_fixed_view = view.findViewById(R.id.my_msg_fixed);
            msg_friend_view.setOnClickListener(this);
            msg_fixed_view.setOnClickListener(this);
        }

        public void setonClick(int image_res, int tip_res) {
            msg_fixed_view.setBackgroundColor(Color.WHITE);
//            msg_fixed_view.setBackgroundResource(R.drawable.dz_my_msg_click_bg);
            msg_fixed_view.setClickable(true);
            msg_fixed_view.setVisibility(View.VISIBLE);
            msg_friend_view.setVisibility(View.GONE);
            ((ImageView) msg_fixed_view.findViewById(R.id.my_msg_iv_type)).
                    setImageResource(image_res);
            ((TextView) msg_fixed_view.findViewById(R.id.my_msg_tv_type)).setText(tip_res);

        }

        public void setData(MyMessage data) {
            msg_fixed_view.setVisibility(View.GONE);
            msg_friend_view.setVisibility(View.VISIBLE);
            friendMsgHolder = new FriendMsgHolder(msg_friend_view);
            friendMsgHolder.setData(data);
        }

        @Override
        public void onClick(View v) {
            //// TODO: 2017/6/3
            if (position > 3) {
                //表示好友消息
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("pageSize", "20");
            bundle.putString("page", "1");
            bundle.putString(WebParamsKey.forumType, WebParamsValue.forumType);
            switch (position) {
                case 0:
                    //跳转"提到我的"
                    bundle.putString("type", "at");
                    mContext.startActivity(new Intent(mContext, MyCommentActivity.class).putExtras(bundle));
                    break;
                case 1://
                    //跳转"评论"
                    bundle.putString("type", "post");
                    mContext.startActivity(new Intent(mContext, MyCommentActivity.class).putExtras(bundle));
                    break;
                case 2:
                    bundle.putString("type", "system");
                    //跳转"系统消息"
                    mContext.startActivity(new Intent(mContext, MySystemActivity.class).putExtras(bundle));

                    break;
                default:
                    //根据好友消息跳转
                    Toast.makeText(mContext, "根据好友消息跳转", Toast.LENGTH_LONG).show();
                    break;
            }
        }


        public void setPosition(int position) {
            this.position = position;
        }
    }
}
