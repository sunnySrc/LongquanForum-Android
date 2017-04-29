package com.mobcent.discuz.module.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbyme.dev.R;

import discuz.com.net.service.model.bean.mypublicbean.List;

/**
 * Created by $Createdbymynameon on 2017/4/27.
 */

public class MyPublic_adapter extends RecyclerView.Adapter<MyPublic_adapter.ViewHolder> {
    private Context context;
    private java.util.List<List> list;
    public MyPublic_adapter(Context context, java.util.List list){
        this.context=context;
        this.list=list;
    }
    @Override
    public MyPublic_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mypublic,parent,false);
        MyPublic_adapter.ViewHolder vh = new MyPublic_adapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyPublic_adapter.ViewHolder holder, int position) {
        String title=list.get(position).getTitle();
        String date=list.get(position).getLast_reply_date();
        String name=list.get(position).getUser_nick_name();
        int hits=list.get(position).getHits();
        holder.date.setText(date);
        holder.name.setText(name);
        holder.title.setText(title);
        holder.readed.setText(Integer.toString(hits)+"阅读");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout total;
        TextView title,date,name,readed;
        public ViewHolder(View view){
            super(view);
            total = (LinearLayout) view.findViewById(R.id.item_mypublic_total);
            title = (TextView) view.findViewById(R.id.mypublic_title);
            date = (TextView) view.findViewById(R.id.my_public_time);
            name = (TextView) view.findViewById(R.id.my_public_name);
            readed = (TextView) view.findViewById(R.id.my_public_readed);
        }
    }
}
