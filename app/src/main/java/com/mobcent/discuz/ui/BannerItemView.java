package com.mobcent.discuz.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.litesuits.android.log.Log;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.bean.Banner;
import com.mobcent.discuz.bean.Component;

import java.util.Locale;

/**
 * Created by huanghaibin
 * on 16-5-23.
 */
public class BannerItemView extends RelativeLayout implements View.OnClickListener {
    private Banner banner;
    private ImageView iv_banner;
    //private TextView tv_title;

    public BannerItemView(Context context) {
        super(context, null);
        init(context);
    }

    private void init(Context context) {
        iv_banner = new ImageView(context);
        iv_banner.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(iv_banner, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setOnClickListener(this);
    }

    public void initData(RequestManager manager, Banner banner) {
        this.banner = banner;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Glide.with(getContext()).load(banner.getImg())
                .into(iv_banner);
    }

    @Override
    public void onClick(View v) {
        if (banner != null) {
            if (banner.getExtra() instanceof Component) {
                UIJumper.jump(getContext(), (Component) banner.getExtra());
            } else {
                String type = banner.getType();
                long id = banner.getId();
                String ref = banner.getRef();
                UIJumper.jump(getContext(), type, id, ref);
            }
        }
    }
    public String getTitle() {
        return banner.getName();
    }

    /**
     * Debug 监听图片加载结果
     */
    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // todo log exception
            android.util.Log.d("GLIDE", String.format(Locale.ROOT,
                    "onException(%s, %s, %s, %s)", e, model, target, isFirstResource), e);
            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            android.util.Log.d("GLIDE", String.format(Locale.ROOT,
                    "onResourceReady(%s, %s, %s, %s, %s)", resource, model, target, isFromMemoryCache, isFirstResource));            return false;
        }
    };
}
