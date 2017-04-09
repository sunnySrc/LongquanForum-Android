package com.mobcent.discuz.module.user.adapter.collectionAdapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.uitls.DateUtils;

import java.util.List;

import discuz.com.net.service.model.bean.collectionBean.CollectionList;

/**
 * Created by $Createdbymynameon on 2017/3/23.
 */

public class CollectionRecycle_adapter extends RecyclerView.Adapter<CollectionRecycle_adapter.ViewHolder> {
    private List<CollectionList> datas;
    private Context context;
    public  CollectionRecycle_adapter(Context context, List datas) {
        this.datas = datas;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_collection_follow,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.mTextView.setText(datas.get(position));
//        Log.i("TAG","getpic_path()="+datas.get(position).getPic_path());
//        Log.i("TAG","getBoardId()="+datas.get(position).getBoard_id());
//        Log.i("TAG","getBoardName()="+datas.get(position).getBoard_name());
//        Log.i("TAG","getTopicId()="+datas.get(position).getTopic_id());
//        Log.i("TAG","getTypeId()="+datas.get(position).getType_id());
//        Log.i("TAG","getSortId()="+datas.get(position).getSort_id());
//        Log.i("TAG","getTitle()="+datas.get(position).getTitle());
//        Log.i("TAG","getSubject()="+datas.get(position).getSubject());
//        Log.i("TAG","getUserId()="+datas.get(position).getUser_id());
//        Log.i("TAG","getLastReplyDate()="+datas.get(position).getLast_reply_date());
//        Log.i("TAG","getUserNickName()="+datas.get(position).getUser_nick_name());
//        Log.i("TAG","getHits()="+datas.get(position).getHits());
//        Log.i("TAG","getReplies()="+datas.get(position).getReplies());
//        Log.i("TAG","getTop()="+datas.get(position).getTop());
//        Log.i("TAG","getStatus()="+datas.get(position).getStatus());
//        Log.i("TAG","getEssence()="+datas.get(position).getEssence());
//        Log.i("TAG","getHot()="+datas.get(position).getHot());
//        Log.i("TAG","getUseravatar()="+datas.get(position).getUserAvatar());
//        Log.i("TAG","getSpecial()="+datas.get(position).getSpecial());
        int essence=datas.get(position).getEssence();
        if (essence==0){
            holder.cream.setVisibility(View.GONE);
        }else {
            holder.cream.setVisibility(View.VISIBLE);
        }
        String date= DateUtils.stampToDate(datas.get(position).getLast_reply_date());
        Log.i("TAG", "date="+date);
        holder.title.setText(datas.get(position).getTitle());
        holder.date.setText(date);
        holder.name.setText(datas.get(position).getBoard_name());
        holder.readed.setText(datas.get(position).getHits()+"阅读");
        //holder.readed.setText(datas.get(position).getEssence());
        Glide.with(context).load(datas.get(position).getPic_path()).into(holder.head);
        //Glide.with(context).load(datas.get(position).getUserAvatar()).into(holder.cream);


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

    //设置回调接口
    public interface OnItemClickLitener {
        void onitemclick(View view, int pos);
        void onitemlongclick(View view, int pos);
    }

    private  OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout total;
        ImageView head,cream;
        TextView title,date,name,readed;
        public ViewHolder(View view){
            super(view);
            total = (RelativeLayout) view.findViewById(R.id.item_collection_total);
            title = (TextView) view.findViewById(R.id.item_collection_title);
            date = (TextView) view.findViewById(R.id.item_collection_date);
            name = (TextView) view.findViewById(R.id.item_collection_name);
            readed = (TextView) view.findViewById(R.id.item_collection_readed);
            cream = (ImageView) view.findViewById(R.id.item_collection_imageview_cream);
            head = (ImageView)view.findViewById(R.id.item_collection_imageview_head);
        }
    }
    public void addAll(List list, boolean isClear){
        if(isClear){
            datas.clear();
        }
        datas.addAll(list);
        notifyDataSetChanged();
    }


}
