package com.mobcent.discuz.module.user.adapter.compileAdapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class CompileAdapter extends RecyclerView.Adapter<CompileAdapter.ViewHolder> {
    private List<CollectionList> datas;
    private Context context;
    public CompileAdapter(Context context, List datas) {
        this.datas = datas;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_collection_follow,viewGroup,false);
//        ViewHolder vh = new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.mTextView.setText(datas.get(position));

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
        return 0;
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
