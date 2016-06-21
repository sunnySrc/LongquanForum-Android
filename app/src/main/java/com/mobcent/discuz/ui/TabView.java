package com.mobcent.discuz.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbyme.app178470.R;


public class TabView extends LinearLayout implements OnClickListener {

    private OnTabChangeListener mOnTabChangedListener;

    private int mState = -1;

    private final TextView mStateButton1;
    private final TextView mStateButton2;
    private final TextView mStateButton3;
    private final TextView mStateButton4;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.tab_view, this);
        mStateButton1 = (TextView) findViewById(R.id.first);
        mStateButton2 = (TextView) findViewById(R.id.second);
        mStateButton3 = (TextView) findViewById(R.id.third);
        mStateButton4 = (TextView) findViewById(R.id.fourth);

        mStateButton1.setOnClickListener(this);
        mStateButton2.setOnClickListener(this);
        mStateButton3.setOnClickListener(this);
        mStateButton4.setOnClickListener(this);

        switchState(0);
    }

    public void setOnTabChangeListener(OnTabChangeListener listener) {
        mOnTabChangedListener = listener;
    }

    public void setCurrentTab(int index) {
        switchState(index);
    }

    private void switchState(int state) {
        if (mState == state) {
            return;
        } // else continue

        mState = state;
        mStateButton1.setSelected(false);
        mStateButton2.setSelected(false);
        mStateButton3.setSelected(false);
        mStateButton4.setSelected(false);

        switch (mState) {
            case 0:
                mStateButton1.setSelected(true);
                break;

            case 1:
                mStateButton2.setSelected(true);
                break;

            case 2:
                mStateButton3.setSelected(true);
                break;

            case 3:
                mStateButton4.setSelected(true);
                break;

            default:
                break;
        }

        if (mOnTabChangedListener != null) {
            if (mState != -1 && mOnTabChangedListener != null) {
                mOnTabChangedListener.onTabChange(mState);
            } else {
                mOnTabChangedListener.onTabChange(-1);
            }
        } // else ignored
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.first:
                switchState(0);
                break;

            case R.id.second:
                switchState(1);
                break;

            case R.id.third:
                switchState(2);
                break;

            case R.id.fourth:
                switchState(3);
                break;

            default:
                break;
        }
    }

    public interface OnTabChangeListener {
        void onTabChange(int position);
    }
}
