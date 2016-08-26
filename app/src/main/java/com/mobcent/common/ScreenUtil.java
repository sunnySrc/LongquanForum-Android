package com.mobcent.common;

import android.content.Context;
import android.view.WindowManager;

import com.mobcent.discuz.application.DiscuzApplication;

/**
 * Created by sun on 2016/8/26.
 */

public class ScreenUtil {
    private static int screenHeight;
    private static int screenWidth;

    public static int getScreenHeight() {
        if(screenHeight == 0) {
            init();
        }
        return screenHeight;
    }
    public static int getScreenWidth() {
        if (screenWidth == 0) {
            init();
        }
        return screenWidth;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private static void init() {
        WindowManager wm = (WindowManager) DiscuzApplication._instance.getSystemService(Context.WINDOW_SERVICE);
        screenHeight = wm.getDefaultDisplay().getHeight();
        screenWidth = wm.getDefaultDisplay().getWidth();
    }
}
