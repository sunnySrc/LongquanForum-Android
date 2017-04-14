package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.CollectionRecycle_adapter;

import java.util.List;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.collectionBean.CollectionList;
import discuz.com.net.service.model.bean.collectionBean.ColoectionBean;
import discuz.com.retrofit.library.HTTPSubscriber;

import static android.widget.Toast.makeText;

public class CollectionActivity extends BasePopActivity {
    int num = 0;
    int num2 = 0;
    private final Object lock = new Object();
    private ViewGroup viewGroup;
    private String errCode;
    private int totalNum;
    XRecyclerView xRecycler;
    CollectionRecycle_adapter adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        //list=new ArrayList<CollectionList>();
        xRecycler= (XRecyclerView) findViewById(R.id.xr_test);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mRecyclerView.setAdapter(adapter);
//        View header =   LayoutInflater.from(this).inflate(R.layout.item_collection_follow, (ViewGroup)findViewById(android.R.id.content),false);
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
        onRefresh();



        //本身也支持设置空局部
        //xRecycler.setEmptyView(tv_no_data);
        xRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeText(CollectionActivity.this,"刷新", Toast.LENGTH_SHORT).show();
                        xRecycler.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                xRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeText(CollectionActivity.this,"加载更多",Toast.LENGTH_SHORT).show();
                        xRecycler.loadMoreComplete();
                    }
                }, 1000);

            }
        });

       /* commonRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Context context, int position) {
                //需要减去你的header和刷新的view的数量
                Toast.makeText(context, "点击了！！　" + (position - 2), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    private void onRefresh() {
        DiscuzRetrofit.getUserInfoService(this).requestUserCollection(LoginUtils.getInstance().getUserId(), WebParamsMap.maps(123456)).subscribe(new HTTPSubscriber<ColoectionBean>() {

            @Override
            public void onSuccess(ColoectionBean coloectionBean) {
//                errCode=coloectionBean.getHead().getErrCode();
//                Log.i("TAG","errCode="+errCode);
//                totalNum=coloectionBean.getTotalNum();
//                Log.i("TAG","totalNum="+totalNum);
//                Log.i("TAG","ColList="+coloectionBean.getColList().toString());
                Log.i("TAG", "errcode="+coloectionBean.getHead().getErrCode());
                final List<CollectionList> list=coloectionBean.getList();
                adapter=new CollectionRecycle_adapter(CollectionActivity.this,list);
                // adapter.addAll(list,false);
                //adapter.addAll(list,true);

                //设置点击事件
                adapter.setOnItemClickLitener(new CollectionRecycle_adapter.OnItemClickLitener() {
                    @Override
                    public void onitemclick(View view, int pos) {
                        Toast.makeText(CollectionActivity.this,"pos="+pos,Toast.LENGTH_SHORT).show();
                        int userid=list.get(pos-1).getUser_id();
                        int topid=list.get(pos-1).getTopic_id();
                        int boardid=list.get(pos-1).getBoard_id();
                        // TODO: 2017/3/28
//                        Intent intent=new Intent(CollectionActivity.this,TopicDetailActivity.class);
//                        intent.putExtra("userid",userid);
//                        intent.putExtra("topid",topid);
//                        intent.putExtra("boardid",boardid);
//                        startActivity(intent);
                        UIJumper.jumpTopic(CollectionActivity.this,topid);
                    }

                    @Override
                    public void onitemlongclick(View view, int pos) {
                        makeText(CollectionActivity.this,"view="+view,Toast.LENGTH_SHORT).show();
                        makeText(CollectionActivity.this,"pos="+pos,Toast.LENGTH_SHORT).show();
                    }
                });

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
