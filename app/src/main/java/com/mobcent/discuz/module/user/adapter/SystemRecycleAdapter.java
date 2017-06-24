package com.mobcent.discuz.module.user.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.base.UIJumper;

import java.util.ArrayList;

import discuz.com.net.service.model.me.SystemResult;

/**
 * Created by sun on 2017/5/29.
 * 我的系统消息，item布局
 */
public class SystemRecycleAdapter extends RecyclerView.Adapter<SystemRecycleAdapter.ViewHolder> {
    ArrayList<SystemResult.Body.SystemAboutMe> comment_list;

    public SystemRecycleAdapter(ArrayList<SystemResult.Body.SystemAboutMe> list) {
        comment_list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_include_msg_function2, parent, false);
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
        private final ImageView my_msg_iv_type;
        private final TextView my_msg_tv_type;
        private final TextView my_msg_tv_content;
        private final TextView my_msg_placeholder1;
        private final TextView my_msg_time;
        private final Context mContext;
        SystemResult.Body.SystemAboutMe data;

        public ViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            my_msg_iv_type = (ImageView) view.findViewById(R.id.my_msg_iv_type);
            my_msg_tv_type = (TextView) view.findViewById(R.id.my_msg_tv_type);
            my_msg_tv_content = (TextView) view.findViewById(R.id.my_msg_tv_content);
            my_msg_time = (TextView) view.findViewById(R.id.my_msg_time);
            my_msg_placeholder1 = (TextView) view.findViewById(R.id.my_msg_placeholder1);
        }

        public void setData(SystemResult.Body.SystemAboutMe data) {
            this.data = data;
            Glide.with(mContext).load(data.icon).into(my_msg_iv_type);
            my_msg_tv_type.setText(data.user_name);
            my_msg_tv_content.setText(data.note);
            try {
                my_msg_time.setText(data.actions.get(0).title);
                my_msg_time.setBackgroundResource(R.drawable.corner_style_change_bg_color);
                my_msg_time.setPadding(20, 20, 20, 20);
                my_msg_time.setTextColor(Color.WHITE);
                my_msg_time.setOnClickListener(this);
                my_msg_placeholder1.setVisibility(View.GONE);
            } catch (Exception e) {
                my_msg_time.setVisibility(View.GONE);
                my_msg_placeholder1.setVisibility(View.GONE);
            }


        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.my_msg_time) {
                UIJumper.jumpWebView(mContext, data.actions.get(0).redirect, "");
            }
        }
    }
}
