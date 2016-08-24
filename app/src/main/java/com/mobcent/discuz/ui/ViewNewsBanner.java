package com.mobcent.discuz.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.RequestManager;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.bean.Banner;

/**
 * Created by huanghaibin
 * on 16-5-23.
 */
public class ViewNewsBanner extends RelativeLayout implements View.OnClickListener {
    private Banner banner;
    private ImageView iv_banner;
    //private TextView tv_title;

    public ViewNewsBanner(Context context) {
        super(context, null);
        init(context);
    }

    private void init(Context context) {
        iv_banner = new ImageView(context);
        iv_banner.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(iv_banner, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //tv_title = (TextView) findViewById(R.id.tv_title);
        setOnClickListener(this);
    }

    public void initData(RequestManager manager, Banner banner) {
        this.banner = banner;
        //tv_title.setText(banner.getName());
        manager.load(banner.getImg()).into(iv_banner);
    }

    @Override
    public void onClick(View v) {
        if (banner != null) {
            String type = banner.getType();
            long id = banner.getId();
            UIJumper.jump(getContext(), type, id);
        }
    }


    public String getTitle() {
        return banner.getName();
    }
}
