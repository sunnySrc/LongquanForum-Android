package com.mobcent.discuz.module.user.adapter.myFriends_adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.CollectionRecycle_adapter;
import com.mobcent.discuz.uitls.DateUtils;

import discuz.com.net.service.model.bean.myfriendsHomepage.List;

/**
 * Created by $Createdbymynameon on 2017/4/22.
 */

public class Myfriends_homepage_adapter extends RecyclerView.Adapter<Myfriends_homepage_adapter.MyfriendViewHolder> {

    private java.util.List <List>datas;
    private Context context;

    public Myfriends_homepage_adapter(Context context, java.util.List datass) {
        this.datas = datass;
        this.context=context;
    }

    @Override
    public MyfriendViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_myfriends,viewGroup,false);
        Myfriends_homepage_adapter.MyfriendViewHolder vh = new Myfriends_homepage_adapter.MyfriendViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyfriendViewHolder holder, int position) {
        String date= DateUtils.stampToDate(datas.get(position).getDateline());
        String signture=datas.get(position).getSignature();
        holder.nickname.setText(datas.get(position).getName());
        holder.grade.setText(datas.get(position).getUserTitle());
        if (!signture.equals("")){
            holder.other.setText(signture);
        }else{
            holder.other.setText("暂无签名");
        }
        holder.date.setText(date);
        Glide.with(context).load(datas.get(position).getIcon()).into(holder.head);
        //设置点击事件
        holder.total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                onItemClickLitener.onitemclick(holder.total, pos);
            }
        });

        //设置长按点击事件
        holder.total.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                onItemClickLitener.onitemlongclick(holder.total, pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class MyfriendViewHolder extends RecyclerView.ViewHolder {
        ImageView head;
        TextView nickname,grade,other,date;
        LinearLayout total;
        public MyfriendViewHolder(View view){
            super(view);
            total= (LinearLayout) view.findViewById(R.id.linearlayout_myfriends_search_total);
            nickname= (TextView) view.findViewById(R.id.item_myfriends_nickname);
            grade= (TextView) view.findViewById(R.id.item_myfriends_grade);
            other= (TextView) view.findViewById(R.id.item_myfriends_other);
            date= (TextView) view.findViewById(R.id.item_myfriends_date);
            head = (ImageView)view.findViewById(R.id.item_myfriends_header);
        }
    }

    private CollectionRecycle_adapter.OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(CollectionRecycle_adapter.OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }
}
