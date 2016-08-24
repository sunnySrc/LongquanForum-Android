package com.mobcent.discuz.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by sun on 2016/8/23.
 */
public class Loading extends ProgressBar{
    public Loading(Context context) {
        super(context);
        init();
    }

    public Loading(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Loading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    public void start() {
        setVisibility(VISIBLE);
    }

    public void stop() {
        setVisibility(GONE);
    }
}
