package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.componentview.SlidingTabLayout;
import com.mobcent.discuz.module.user.adapter.UserHomeAdapter;
import com.mobcent.discuz.module.user.fragment.UserHomeFragment;
import com.mobcent.discuz.module.user.view.UserHomeCenterFragmentHeader;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnFlingOverListener;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

/**
 * Created by pangxiaomin on 16/11/20
 * 用户资料页
 */
public class UserHomeActivity extends BasePopActivity {

    private ScrollableLayout mScrollableLayout;
    private UserHomeCenterFragmentHeader mUserCenterHeander;
    private SlidingTabLayout mSlideTabLayout;
    private ViewPager mUserViewPager;
    private UserHomeAdapter mUserHomeAdapter;
    private View tabView;

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.user_home_center_fragment);
        initView();
        initData();
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    private void initView(){
        mScrollableLayout = $(R.id.user_home_scrollable_layout);
        mUserCenterHeander = $(R.id.header_layout);
        mUserViewPager = $(R.id.fragment_user_viewpager);
        tabView = $(R.id.fragment_user_viewpager);

        mSlideTabLayout =  $(R.id.sliding_tab_layout);
        mSlideTabLayout.setCustomTabView(R.layout.view_sliding_tab_indicator, android.R.id.text1);
        mSlideTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.dz_skin_custom_main_color));
        mSlideTabLayout.setDistributeEvenly(false);
    }

    private void initData(){
        mUserHomeAdapter = new UserHomeAdapter(getSupportFragmentManager());
        mUserViewPager.setAdapter(mUserHomeAdapter);
        mSlideTabLayout.setViewPager(mUserViewPager);
        mScrollableLayout.setDraggableView(mSlideTabLayout);

        mScrollableLayout.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
            @Override
            public boolean canScrollVertically(int direction) {
                return mUserHomeAdapter.canScrollVertically(mUserViewPager.getCurrentItem(), direction);
            }
        });

        mScrollableLayout.setOnFlingOverListener(new OnFlingOverListener() {
            @Override
            public void onFlingOver(int y, long duration) {
                mUserHomeAdapter.getItem(mUserViewPager.getCurrentItem()).onFlingOver(y, duration);
            }
        });

        mScrollableLayout.setOnScrollChangedListener(new OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int y, int oldY, int maxY) {

                final float tabsTranslationY;
                if (y < maxY) {
                    tabsTranslationY = .0F;
                } else {
                    tabsTranslationY = y - maxY;
                }

                mSlideTabLayout.setTranslationY(tabsTranslationY);

                mUserCenterHeander.setTranslationY(y / 2);
            }
        });
    }


}
