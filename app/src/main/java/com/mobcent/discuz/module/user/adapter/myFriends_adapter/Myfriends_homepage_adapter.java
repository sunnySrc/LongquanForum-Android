package com.mobcent.discuz.module.user.adapter.myFriends_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;

import discuz.com.net.service.model.bean.myfriendsHomepage.List;

/**
 * Created by $Createdbymynameon on 2017/4/22.
 */

public class Myfriends_homepage_adapter extends RecyclerView.Adapter<Myfriends_homepage_adapter.MyfriendViewHolder> {

    private java.util.List <List>datas;
    private Context context;
    public void Myfriends_homepage_adapter(Context context, java.util.List<List> datas) {
            this.datas = datas;
            this.context=context;
    }

    @Override
    public MyfriendViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_myfriends,viewGroup,false);
        Myfriends_homepage_adapter.MyfriendViewHolder vh = new Myfriends_homepage_adapter.MyfriendViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyfriendViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class MyfriendViewHolder extends RecyclerView.ViewHolder {
//        ImageView head;
//        TextView nickname,grade,other,date;
//        LinearLayout total;
        public MyfriendViewHolder(View view){
            super(view);
//            total= (LinearLayout) view.findViewById(R.id.linearlayout_myfriends_search_total);
//            nickname= (TextView) view.findViewById(R.id.item_myfriends_nickname);
//            grade= (TextView) view.findViewById(R.id.item_myfriends_grade);
//            other= (TextView) view.findViewById(R.id.item_myfriends_other);
//            date= (TextView) view.findViewById(R.id.item_myfriends_date);
//            head = (ImageView)view.findViewById(R.id.item_myfriends_header);
        }
    }
}
