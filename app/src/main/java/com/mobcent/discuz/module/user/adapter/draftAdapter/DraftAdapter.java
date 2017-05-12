package com.mobcent.discuz.module.user.adapter.draftAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbyme.dev.R;

/**
 * Created by $Createdbymynameon on 2017/5/8.
 */

public class DraftAdapter extends RecyclerView.Adapter<DraftAdapter.ViewHolder>{

    @Override
    public DraftAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.draft_fragment_item,viewGroup,false);
        DraftAdapter.ViewHolder vh = new DraftAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(DraftAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

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

}
