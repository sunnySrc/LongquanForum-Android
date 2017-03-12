package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BaseRefreshActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.Tasker;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.fragments.HttpResponseHandler;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.ColoectionBean;
import discuz.com.retrofit.library.HTTPSubscriber;

public class CollectionActivity extends BaseRefreshActivity {
    ListView listViewCollection;
    private ViewGroup viewGroup;

    @Override
    public void initParams(Bundle bundle) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DiscuzRetrofit.getUserInfoService(this).requestUserCollection( LoginUtils.getInstance().getUserId(),WebParamsMap.map()).subscribe(new HTTPSubscriber<ColoectionBean>() {
            @Override
            public void onSuccess(ColoectionBean coloectionBean) {
                Log.i("TAG", "成功");
                Log.i("TAG", "coloectionBean="+coloectionBean);
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("TAG", "失败");
                Log.i("TAG", "httpCode="+httpCode+"errorUserCode="+errorUserCode+"message="+message);
            }

        });
//            DiscuzRetrofit.getUserInfoService(this).requestUserCollection(LoginUtils.getInstance().getUserId(), WebParamsMap.maps()).subscribe(new HTTPSubscriber<BaseResult<ColoectionBean>>() {
//                @Override
//                public void onSuccess(BaseResult<ColoectionBean> coloectionBeanBaseResult) {
//                    Log.i("TAG", "成功");
//                    Log.i("TAG", "coloectionBeanBaseResult="+coloectionBeanBaseResult);
//                    Toast.makeText(CollectionActivity.this,"成功",Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFail(int httpCode, int errorUserCode, String message) {
//                    Log.i("TAG", "失败");
//                    Log.i("TAG", "httpCode="+httpCode+"errorUserCode="+errorUserCode+"message="+message);
//                }
//
//
//
//            });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected View onCreateContentLayout(ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.fragment_home, container, false);
        return viewGroup;
    }

    @Override
    protected Tasker onExecuteRequest(HttpResponseHandler handler) {
        return null;
    }

    @Override
    protected void showContent(String result) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_topic_refresh;
    }


    public interface LoadCollectionListener{
        public void onFilmLoadEnd(ColoectionBean bean);
    }
}
