package com.mobcent.discuz.module.user.adapter.myFriends_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.uitls.DateUtils;

import java.util.List;


/**
 * Created by $Createdbymynameon on 2017/4/7.
 */

public class Myfriends_adapter extends RecyclerView.Adapter<Myfriends_adapter.ViewHolder> {
    private List<discuz.com.net.service.model.bean.MyFriend.List> datas;
    private Context context;
    public  Myfriends_adapter(Context context, List datas) {
        this.datas = datas;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_myfriends,viewGroup,false);
        Myfriends_adapter.ViewHolder vh = new Myfriends_adapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String date= DateUtils.stampToDate(datas.get(position).getDateline());
        String signture=datas.get(position).getSignture();
        holder.nickname.setText(datas.get(position).getName());
        holder.grade.setText(datas.get(position).getUserTitle());
        if (!signture.equals("")){
            holder.other.setText(datas.get(position).getSignture());
        }

        holder.date.setText(date);
        Glide.with(context).load(datas.get(position).getIcon()).into(holder.head);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView head;
        TextView nickname,grade,other,date;
        public ViewHolder(View view){
            super(view);
            nickname= (TextView) view.findViewById(R.id.item_myfriends_nickname);
            grade= (TextView) view.findViewById(R.id.item_myfriends_grade);
            other= (TextView) view.findViewById(R.id.item_myfriends_other);
            date= (TextView) view.findViewById(R.id.item_myfriends_date);
            head = (ImageView)view.findViewById(R.id.item_myfriends_header);
        }
    }
}
