package com.mobcent.discuz.module.user.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.activity.WebActivity;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.UserHomeAdapter;
import com.mobcent.discuz.module.user.fragment.UserHomeInformationFragment;
import com.mobcent.discuz.module.user.view.UserHomeCenterHeader;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.addFollowBean.AddFollowBean;
import discuz.com.net.service.model.me.UserResult;
import discuz.com.retrofit.library.HTTPSubscriber;
import library.component.actionbar.sliding.SlidingTabLayout;
import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnFlingOverListener;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

/**
 * Created by pangxiaomin on 16/11/20
 * 用户资料页
 */
public class UserHomeActivity extends BasePopActivity implements View.OnClickListener{
    private String userName;
    private String uid;
    private int isFollow=0;//0关注  1 未关注
    private int friend_num;//好友编号(为0则不是好友)
    private ScrollableLayout mScrollableLayout;
    private UserHomeCenterHeader mUserCenterHeader;
    private SlidingTabLayout mSlideTabLayout;
    private ViewPager mUserViewPager;
    private UserHomeAdapter mUserHomeAdapter;
    private LinearLayout userinfo_bottom;
    private View tabView;

    private ImageView imageView_isblack;
    private ImageView imageView_isfollow;
    private TextView textView_isblack;
    private TextView textView_isFollow;

    public static Boolean from;
    public static String uid_myfriendsSearch;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        //判断该页面来源
        judgementfrom();
        initData();
        //requestUserInfo();
    }

    private void judgementfrom() {
        userinfo_bottom = $(R.id.item_userinfo_bottom);
        Intent intent=getIntent();
        from=intent.getBooleanExtra("from",false);
        uid_myfriendsSearch=intent.getStringExtra("uid");
        if (from){
            myfriend_initView();
            myFriends_initView();
            visible_userInfo_bottom();

        }else {
            initView();
            requestUserInfo();
            userinfo_bottom.setVisibility(View.GONE);

        }


        mScrollableLayout = $(R.id.user_home_scrollable_layout);
        mUserCenterHeader = $(R.id.header_layout);
        mUserViewPager = $(R.id.fragment_user_viewpager);
        tabView = $(R.id.fragment_user_viewpager);

        mSlideTabLayout =  $(R.id.sliding_tab_layout);
        mSlideTabLayout.setCustomTabView(R.layout.view_sliding_tab_indicator, android.R.id.text1);
        mSlideTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.dz_skin_custom_main_color));
        mSlideTabLayout.setDistributeEvenly(false);
    }

    private void visible_userInfo_bottom() {
        //显示
        userinfo_bottom.setVisibility(View.VISIBLE);

    }

    @Override
    public int initLayout() {
        return R.layout.user_home_center_fragment;
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }


    //我的好友的详情页
    private void myFriends_initView(){
        LinearLayout linearLayout_addFriends= (LinearLayout) findViewById(R.id.linearlayout_userinfo_bottom_addfriends);
        LinearLayout linearLayout_follow= (LinearLayout) findViewById(R.id.linearlayout_userinfo_bottom_follow);
        linearLayout_addFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (friend_num==0){
                    Toast.makeText(UserHomeActivity.this,"添加好友",Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "uid_myfriendsSearch="+uid_myfriendsSearch);
                    Log.i("TAG", "friend_num="+friend_num);
                    //添加好友
                    String url_add="http://forum.longquanzs.org///mobcent/app/web/index.php?r=user/" +
                            "useradminview&accessToken=b8c746f3a931e0d0ffdbcc76c6360&accessSecret=6e9f2606bed4b530dcb58ff210299" +
                            "&uid="+uid_myfriendsSearch+"&act=add";
                    WebActivity.start(UserHomeActivity.this,url_add,getResources().getString(R.string.mc_forum_add_friends));
                }else {
                    Log.i("TAG", "friend_num="+friend_num);
                    String url_delete="http://forum.longquanzs.org///mobcent/app/web/index.php?r=user/useradminview&" +
                            "accessToken=b8c746f3a931e0d0ffdbcc76c6360&" +
                            "accessSecret=6e9f2606bed4b530dcb58ff210299&" +
                            "uid="+uid_myfriendsSearch+"&act=ignore";
                    WebActivity.start(UserHomeActivity.this,url_delete,getResources().getString(R.string.mc_forum_del_friends));
                    friend_num=0;
                }

            }
        });
        linearLayout_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(UserHomeActivity.this,"加关注",Toast.LENGTH_SHORT).show();
                if (isFollow==1){
                    //添加关注
                    addFollow();
                    imageView_isfollow.setImageResource(R.drawable.dz_personal_friend_unfollow);
                    textView_isFollow.setText(R.string.mc_forum_cancle_follow);
                    isFollow=0;
                }else {
                    //取消关注
                    unFollow();
                    imageView_isfollow.setImageResource(R.drawable.dz_personal_friend_follow);
                    textView_isFollow.setText(R.string.mc_forum_add_follow);
                    isFollow=1;
                }
            }
        });
        DiscuzRetrofit.getUserInfoService(this).requestUserInfo(uid_myfriendsSearch,WebParamsMap.map()).subscribe(new HTTPSubscriber<UserResult>() {
            @Override
            public void onSuccess(UserResult userResult) {
                mUserCenterHeader.setContent(userResult);
                ((UserHomeInformationFragment)mUserHomeAdapter.getItem(1)).setContent(userResult.getBody().getProfileList(),
                        userResult.getBody().getCreditList());
                userName=userResult.getName();
                isFollow=userResult.getIs_follow();
                friend_num=userResult.getFriend_num();
                Log.i("TAG", "status="+userResult.getStatus());
                Log.i("TAG", "userTitle="+userResult.getUserTitle());
                Log.i("TAG", "flag="+userResult.getFlag());
                Log.i("TAG", "getEssence_num="+userResult.getEssence_num());
                Log.i("TAG", "getCredits="+userResult.getCredits());
                Log.i("TAG", "getIcon="+userResult.getIcon());
                Log.i("TAG", "getSign="+userResult.getSign());
                Log.i("TAG", "getIs_black="+userResult.getIs_black());
                Log.i("TAG", "getIs_follow="+userResult.getIs_follow());
                Log.i("TAG", "getFriend_num="+userResult.getFriend_num());
                Log.i("TAG", "getIs_friend="+userResult.getIs_friend());
                Log.i("TAG", "getScore="+userResult.getScore());
                Log.i("TAG", "getTopic_num="+userResult.getTopic_num());
                Log.i("TAG", "getReply_posts_num="+userResult.getReply_posts_num());
                judgementfriends(isFollow,friend_num);
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                showToast(message);
            }
        });

    }
    //添加关注
    private void addFollow() {
        DiscuzRetrofit.getUserInfoService(this).addFollow(WebParamsMap.addFollow(uid_myfriendsSearch)).subscribe(new HTTPSubscriber<AddFollowBean>() {
            @Override
            public void onSuccess(AddFollowBean userResult) {
                String errCode=userResult.getHead().getErrcode();

                Log.i("TAG", "errCode="+errCode);
                Log.i("TAG", "errInfo="+userResult.getHead().getErrinfo());
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {

            }
        });

    }
    //取消关注
    private void unFollow() {
        DiscuzRetrofit.getUserInfoService(this).addFollow(WebParamsMap.unFollow(uid_myfriendsSearch)).subscribe(new HTTPSubscriber<AddFollowBean>() {
            @Override
            public void onSuccess(AddFollowBean userResult) {
                String errCode=userResult.getHead().getErrcode();
                if (errCode.equals("02000024")){

                }

                Log.i("TAG", "errCode="+errCode);
                Log.i("TAG", "errInfo="+userResult.getHead().getErrinfo());
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {

            }
        });

    }
    private void judgementfriends(int isFollows, int isBlack){
        imageView_isblack= (ImageView) findViewById(R.id.userinfo_imageview_isblack);
        imageView_isfollow= (ImageView) findViewById(R.id.userinfo_imageview_isFollow);
        textView_isblack= (TextView) findViewById(R.id.userinfo_textview_isblack);
        textView_isFollow= (TextView) findViewById(R.id.userinfo_textview_isFollow);
        //关注
        if (isFollows==1){
            imageView_isfollow.setImageResource(R.drawable.dz_personal_friend_unfollow);
            textView_isFollow.setText(R.string.mc_forum_cancle_follow);
            isFollow=0;
        }else if (isFollows==0){
            imageView_isfollow.setImageResource(R.drawable.dz_personal_friend_follow);
            textView_isFollow.setText(R.string.mc_forum_add_follow);
            isFollow=1;
        };

        //好友
        if (friend_num>0){
            textView_isblack.setText(R.string.mc_forum_cancel_friend);
            imageView_isblack.setImageResource(R.drawable.dz_personal_friend_del);
        }else if (friend_num==0){
            textView_isblack.setText(R.string.mc_forum_add_friend);
            imageView_isblack.setImageResource(R.drawable.dz_personal_friend_add);
        }
    }

    private void alertDialogs() {
        Intent intent = new Intent(this, MainWeixinTitleRightActivity.class);
        intent.putExtra("from","UserHomeActivity");
        intent.putExtra("uid",uid_myfriendsSearch);
        startActivity(intent);
    }

    //我的详情页
    private void initView(){
            getAppActionBar().setTitle(R.string.user_center);
            getAppActionBar().setBackgroundAlpha(0);
            getAppActionBar().setRightTitle(R.string.mc_forum_userifo_update,this);
    }
    //好友详情页
    private void myfriend_initView(){
        getAppActionBar().setTitle(userName);
        getAppActionBar().setBackgroundAlpha(0);
        //getAppActionBar().setRightTitle("",this);
        getAppActionBar().setRightIcon(R.drawable.dz_personal_more_h, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogs();
            }
        });

    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.action_bar_right_title){
            if (from){
                alertDialogs();
            }else {
                Intent intents=new Intent(UserHomeActivity.this, CompileActivity.class);
                startActivity(intents);
            }
        }
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

                invalidateActionBarBackground(y,maxY);
                mSlideTabLayout.setTranslationY(tabsTranslationY);
                mUserCenterHeader.setTranslationY(y / 2);
            }
        });
    }

    //背景颜色渐变
    private void invalidateActionBarBackground(int transY,int maxY){
        //0-maxY 0-255
        getAppActionBar().setBackgroundAlpha(transY*255/maxY);
    }

    private void requestUserInfo(){
        DiscuzRetrofit.getUserInfoService(this).requestUserInfo(LoginUtils.getInstance().getUserId(),WebParamsMap.map()).subscribe(new HTTPSubscriber<UserResult>() {
            @Override
            public void onSuccess(UserResult userResult) {
                mUserCenterHeader.setContent(userResult);
                ((UserHomeInformationFragment)mUserHomeAdapter.getItem(1)).setContent(userResult.getBody().getProfileList(),
                        userResult.getBody().getCreditList());
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                showToast(message);
            }
        });
    }


}
