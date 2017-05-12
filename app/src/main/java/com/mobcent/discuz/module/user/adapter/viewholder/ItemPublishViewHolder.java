package com.mobcent.discuz.module.user.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.CollectionRecycle_adapter;
import com.mobcent.discuz.uitls.DateUtils;

import java.util.ArrayList;
import java.util.List;

import discuz.com.bean.me.Publish;


/**
 * Created by pangxiaomin on 16/11/20.
 * 我的发表的item数据显示
 */
public class ItemPublishViewHolder extends RecyclerView.ViewHolder {

    public void bindConent(Publish publish, final Context ctx, final int position, final List<Publish> datas){
        List<ImageView> imageList=new ArrayList<ImageView>(){};
        imageList.add(image01);
        imageList.add(image02);
        imageList.add(image03);
        if(publish!=null){
            Glide.with(ctx)
                    .load(publish.getUserAvatar())
                    .into(item_publish_avatar);
            item_publish_name.setText(publish.getUser_nick_name());
            item_publish_time.setText(publish.getLast_reply_date()+"");
            item_publish_time.setText(DateUtils.stampToDate(String.valueOf(publish.getLast_reply_date())));
            tv_content.setText(publish.getTitle());
            tv_look_num.setText(Integer.toString(publish.getHits()));
            item_publish_reply.setText(Integer.toString(publish.getReplies()));
            int num=publish.getImageList().size();
            if (num>0){
                visible();
                for (int i=0;i<=2;i++){
                    publish.getImageList().get(i);
                    Glide.with(ctx)
                            .load(publish.getImageList().get(i))
                            .into(imageList.get(i));
                }
            }else {
                invisible();
            }
        }
        item_publish_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onItemClickLitener.onitemclick(item_publish_total, position);
                String topic_id=datas.get(position).getTopic_id();
                UIJumper.jumpTopic(ctx,Long.parseLong(topic_id));
            }
        });
    }
    private CollectionRecycle_adapter.OnItemClickLitener onItemClickLitener;

//    //设置回调接口
//    public interface OnItemClickLitener {
//        void onitemclick(View view, int pos);
//        void onitemlongclick(View view, int pos);
//    }

    private void invisible() {
        image01.setVisibility(View.GONE);
        image02.setVisibility(View.GONE);
        image03.setVisibility(View.GONE);
    }

    private void visible() {
        image01.setVisibility(View.VISIBLE);
        image02.setVisibility(View.VISIBLE);
        image03.setVisibility(View.VISIBLE);
    }

    LinearLayout item_publish_total;
    ImageView item_publish_avatar;
    TextView item_publish_name;
    TextView item_publish_time;
    TextView tv_content;
    TextView tv_look_num;
    TextView item_publish_reply;
    ImageView image01,image02,image03;

    public ItemPublishViewHolder(View view) {
        super(view);
        item_publish_total = (LinearLayout) view.findViewById(R.id.item_publish_total);
        item_publish_avatar = (ImageView) view.findViewById(R.id.item_publish_avatar);
        item_publish_name = (TextView) view.findViewById(R.id.item_publish_name);
        item_publish_time = (TextView) view.findViewById(R.id.item_publish_time);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_look_num = (TextView) view.findViewById(R.id.tv_look_num);
        item_publish_reply = (TextView) view.findViewById(R.id.item_publish_reply);
        image01 = (ImageView) view.findViewById(R.id.item_image_myfriend_infoImage1);
        image02 = (ImageView) view.findViewById(R.id.item_image_myfriend_infoImage2);
        image03 = (ImageView) view.findViewById(R.id.item_image_myfriend_infoImage3);
    }
}
