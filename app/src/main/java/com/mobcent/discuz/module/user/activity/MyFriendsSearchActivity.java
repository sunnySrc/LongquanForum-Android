package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.myFriends_adapter.Myfriends_adapter;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.MyFriend.List;
import discuz.com.net.service.model.bean.MyFriend.MyFriends;
import discuz.com.retrofit.library.HTTPSubscriber;

import static android.widget.Toast.makeText;

public class MyFriendsSearchActivity extends BasePopActivity {
    private EditText edittext;
    String nickname;
    Myfriends_adapter adapter;

    XRecyclerView xRecycler;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_my_friends_search);
        edittext= (EditText) findViewById(R.id.myfriends_edittext);
        nickname=edittext.getText().toString();

        xRecycler= (XRecyclerView) findViewById(R.id.xr_test_myfrieds_search);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mRecyclerView.setAdapter(adapter);
//        View header = LayoutInflater.from(this).inflate(R.layout.item_myfriends_listviewhead, (ViewGroup)findViewById(android.R.id.content),false);
//        xRecycler.addHeaderView(header);


        //设置瀑布流管理器
        xRecycler.setLayoutManager(layoutManager);
        //添加分割线，注意位置是会从下拉那个Item的实际位置开始的，所以如果支持下拉需要屏蔽下拉和HeaderView
        //因为集成了下拉item，所以需要从下拉item之后开始计算分割
        //xRecycler.addItemDecoration(new DividerItemDecoration(dip2px(this, 10), DividerItemDecoration.LIST, 1));

        //是否屏蔽下拉
        //xRecycler.setPullRefreshEnabled(false);
        //xRecycler.setRefreshHeader(new MyCustomRefreshHeader(this));
        /*xRecycler.setFootView(new CustomLoadMoreFooter(this));*/
        //上拉加载更多样式，也可以设置下拉
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
        onRefresh(nickname);



        //本身也支持设置空局部
        //xRecycler.setEmptyView(tv_no_data);
        xRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeText(MyFriendsSearchActivity.this,"刷新", Toast.LENGTH_SHORT).show();
                        xRecycler.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                xRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeText(MyFriendsSearchActivity.this,"加载更多",Toast.LENGTH_SHORT).show();
                        xRecycler.loadMoreComplete();
                    }
                }, 1000);

            }
        });
    }

    private void onRefresh(String nickname) {
        DiscuzRetrofit.getUserInfoService(this).requestUserMyFriends(LoginUtils.getInstance().getUserId(), WebParamsMap.map_friend(nickname)).subscribe(new HTTPSubscriber<MyFriends>() {
            @Override
            public void onSuccess(MyFriends myFriends) {
                final java.util.List<List> list=myFriends.getBody().getList();
                adapter=new Myfriends_adapter(MyFriendsSearchActivity.this,list);
                xRecycler.setAdapter(adapter);
                xRecycler.refreshComplete();
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {

            }
        });
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }
}
