package com.mobcent.discuz.module.user.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.appbyme.dev.R;

/**
 * Created by pangxiaomin on 16/11/20.
 */
public class UserHomeCenterFragmentHeader extends RelativeLayout {

    public UserHomeCenterFragmentHeader(Context context) {
        super(context);
        initView();
    }

    public UserHomeCenterFragmentHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public UserHomeCenterFragmentHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.user_home_center_fragment_header,this,true);
    }
}
