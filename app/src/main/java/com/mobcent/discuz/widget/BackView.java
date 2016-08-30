package com.mobcent.discuz.widget;

import android.view.View;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
/**
 * Created by sun on 2016/8/29.
 *  页面Header 返回View
 */

public class BackView extends ImageView{

    public BackView(Context context) {
        super(context);
    }

    public BackView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                InputMethodManager imm =
                        (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                ((Activity) getContext()).onBackPressed();
            }
        });
    }
}
