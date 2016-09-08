package com.mobcent.discuz.fragments;

import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by ubuntu on 16-9-6.
 */
public class ImageUtils {


    public static void getImageViewSizeInit(final ImageView imageView, final double ratio){
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            boolean hasMeasured;
            @Override
            public boolean onPreDraw() {
                if (!hasMeasured) {
                    // 获得初始宽高
                    int height = (int)(imageView.getMeasuredWidth() * ratio);
                    imageView.setMaxHeight(height);
                    imageView.setMinimumHeight(height);
                    hasMeasured = true;
                }
                return true;
            }
        });
    }
}
