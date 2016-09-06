package com.mobcent.discuz.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
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
    private LoadMoreDetector detector;

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

    public void setNoMoreDateHintRes(int noMoreDateHintRes) {
        this.noMoreDateHintRes = noMoreDateHintRes;
    }

    public void enableLoadMoreDetector() {
        detector = new LoadMoreDetector();
    }

    /**
     * call {@link #enableLoadMoreDetector()} first.
     * <p>
     * 加载结束记得调用
     */
    public void onLoadComplete() {
        if (detector != null) {
            detector.setIsOnLoading(false);
        }
    }
    /**
     * call {@link #enableLoadMoreDetector()} first.
     * <p>
     */
    public void setCanLoadMore() {
        if (detector != null) {
            detector.setCanLoadMore();
        }
        setFooterType(TYPE_LOADING);
    }
    /**
     * call {@link #enableLoadMoreDetector()} first.
     * <p>
     */
    public void setNoMoreData() {
        if (detector != null) {
            detector.setNoMoreData();
        }
        setFooterType(TYPE_NO_MORE);
    }
    /**
     * call {@link #enableLoadMoreDetector()} first.
     * <p>
     */
    public void setLoadListener(LoadMoreListener loadListener) {
        if (detector != null) {
            detector.setLoadListener(loadListener);
        }
    }

    private Context getContext() {
        return mListView.getContext();
    }

    private Resources getResources() {
        return mListView.getResources();
    }

    /**
     *  加载更多检测
     */
    private class LoadMoreDetector implements View.OnTouchListener, AbsListView.OnScrollListener {


        private boolean mIsOnLoading = false;

        private boolean mCanLoadMore = false;

        private int mYDown;

        private int mLastY;

        private LoadMoreListener mListener;
        private int mTouchSlop;
        private boolean mIsMoving;
        AbsListView.OnScrollListener onScrollListener;
        public LoadMoreDetector() {
            mListView.setOnTouchListener(this);
            mListView.setOnScrollListener(this);
            mTouchSlop = ViewConfiguration.get(mListView.getContext()).getScaledTouchSlop();
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if(onScrollListener != null) {
                onScrollListener.onScrollStateChanged(view, scrollState);
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(onScrollListener != null) {
                onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
            // 滚动时到了最底部也可以加载更多
            if (canLoad()) {
                loadData();
            }
        }



        /**
         * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
         *
         * @return 是否可以加载更多
         */
        private boolean canLoad() {
            return isInBottom() && ! mIsOnLoading && isPullUp() && mCanLoadMore;
        }

        /**
         * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
         */
        private void loadData() {
            if (mListener != null) {
                setIsOnLoading(true);
                mListener.onLoadMore();
            }
        }

        /**
         * 是否是上拉操作
         *
         * @return 是否是上拉操作
         */
        private boolean isPullUp() {
            return (mYDown - mLastY) >= mTouchSlop;
        }


        /**
         * 判断是否到了最底部
         */
        private boolean isInBottom() {
            return (mListView != null && mListView.getAdapter() != null)
                    && mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }

        //-----------------------外部 API------------------------

        public void setCanLoadMore() {
            this.mCanLoadMore = true;
        }

        public void setNoMoreData() {
            this.mCanLoadMore = false;
        }


        /**
         * 设置正在加载
         *
         * @param loading loading
         */
        private void setIsOnLoading(boolean loading) {
            mIsOnLoading = loading;
            if (!mIsOnLoading) {
                mYDown = 0;
                mLastY = 0;
            }
        }


        /**
         * set
         *
         * @param loadListener loadListener
         */
        public void setLoadListener(LoadMoreListener loadListener) {
            mListener = loadListener;
        }

        public boolean isMoving() {
            return mIsMoving;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    // 按下
                    mYDown = (int) event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    // 移动
                    mIsMoving = true;
                    mLastY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_UP:
                    mIsMoving = false;
                    break;
                default:
                    break;
            }
            return false;
        }
    }

    public  interface LoadMoreListener {
        void onLoadMore();
    }
}
