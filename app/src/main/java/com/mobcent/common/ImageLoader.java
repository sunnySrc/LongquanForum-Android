package com.mobcent.common;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.mobcent.discuz.application.DiscuzApplication;

/**
 * Created by sun on 2016/8/20.
 *  图片加载
 */
public class ImageLoader {
    // 示例
    public static void load(String url, ImageView target) {
        Glide.with(DiscuzApplication._instance).load(url).into(target);
    }

    /**
     * 圆角图片
     * @param url
     * @param target
     * @param radius  dp value
     */
    public static void load(String url, ImageView target, int radius) {
        Glide.with(DiscuzApplication._instance)
                .load(url)
                .centerCrop()
                .transform(new CenterCrop(DiscuzApplication._instance), new GlideRoundTransform(DiscuzApplication._instance, radius))
                .into(target);

    }
}
