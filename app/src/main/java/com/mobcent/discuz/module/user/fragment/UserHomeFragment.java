package com.mobcent.discuz.module.user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;
import com.mobcent.discuz.componentview.SlidingTabLayout;
import com.mobcent.discuz.fragments.BaseFragment;
import com.mobcent.discuz.module.user.adapter.UserHomeAdapter;
import com.mobcent.discuz.module.user.view.UserHomeCenterFragmentHeader;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnFlingOverListener;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

/**
 * Created by ubuntu on 16-6-21.
 */
public class UserHomeFragment extends BaseFragment {

    private ScrollableLayout mScrollableLayout;
    private UserHomeCenterFragmentHeader mUserCenterHeander;
    private SlidingTabLayout mSlideTabLayout;
    private ViewPager mUserViewPager;
    private UserHomeAdapter mUserHomeAdapter;

    public static UserHomeFragment newInstance() {
        UserHomeFragment userHomeFragment = new UserHomeFragment();
        return userHomeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home_center_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view){
        mScrollableLayout = $(view,R.id.user_home_scrollable_layout);
        mUserCenterHeander = $(view,R.id.header_layout);
        mUserViewPager = $(view,R.id.fragment_user_viewpager);

        mSlideTabLayout =  $(view,R.id.sliding_tab_layout);
        mSlideTabLayout.setCustomTabView(R.layout.view_sliding_tab_indicator, android.R.id.text1);
        mSlideTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.dz_skin_custom_main_color));
        mSlideTabLayout.setDistributeEvenly(false);
    }

    private void initData(){
        mUserHomeAdapter = new UserHomeAdapter(getChildFragmentManager());
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
