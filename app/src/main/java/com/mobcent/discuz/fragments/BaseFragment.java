package com.mobcent.discuz.fragments;

import android.support.v4.app.Fragment;
/**
 * Created by ubuntu on 16-8-18.
 */
public abstract  class BaseFragment extends Fragment implements HttpResponseHandler {
    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public static int mState = STATE_NONE;
        @Override
    public void onSuccess(String result) {
        assert 1 == 0;
    }

    @Override
    public void onFail(String result) {
        assert 1 == 0;
    }
}
