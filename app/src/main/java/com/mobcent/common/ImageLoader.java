package com.mobcent.common;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
}
