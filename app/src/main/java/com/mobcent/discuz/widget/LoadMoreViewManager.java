package com.mobcent.discuz.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appbyme.dev.R;

/**
 * Created by sun on 2016/8/24.
 *  加载更多辅助类
 */

public class LoadMoreViewManager {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_LOADING = 1;
    public static final int TYPE_NO_MORE = 2;
    public static final int TYPE_ERROR = 3;
    public static final int TYPE_NET_ERROR = 4;

    private ListView mListView;

    private View mFooterView;
    private ProgressBar mFooterProgressBar;
    private TextView mFooterText;
    private int noMoreDateHintRes;

    public LoadMoreViewManager(ListView mListView) {
        this.mListView = mListView;
        initView();
    }

    private void initView() {
        mFooterView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_view_footer, null);
        mFooterText = (TextView) mFooterView.findViewById(R.id.tv_footer);
        mFooterProgressBar = (ProgressBar) mFooterView.findViewById(R.id.pb_footer);
        mListView.addFooterView(mFooterView);
    }

    public void setFooterType(int type) {
        switch (type) {
            case TYPE_NORMAL:
            case TYPE_LOADING:
                mFooterText.setText(getResources().getString(R.string.footer_type_loading));
                mFooterProgressBar.setVisibility(View.VISIBLE);
                break;
            case TYPE_NET_ERROR:
                mFooterText.setText(getResources().getString(R.string.footer_type_net_error));
                mFooterProgressBar.setVisibility(View.GONE);
                break;
            case TYPE_ERROR:
                mFooterText.setText(getResources().getString(R.string.footer_type_error));
                mFooterProgressBar.setVisibility(View.GONE);
                break;
            case TYPE_NO_MORE:
                mFooterText.setText(getResources().getString(noMoreDateHintRes > 0 ? noMoreDateHintRes : R.string.footer_type_not_more));
                mFooterProgressBar.setVisibility(View.GONE);
                break;
        }
    }

    private Context getContext() {
        return mListView.getContext();
    }

    private Resources getResources() {
        return mListView.getResources();
    }

    public void setNoMoreDateHintRes(int noMoreDateHintRes) {
        this.noMoreDateHintRes = noMoreDateHintRes;
    }
}
