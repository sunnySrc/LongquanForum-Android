package com.mobcent.discuz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.widget.LoadMoreViewManager;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.mobcent.discuz.widget.LoadMoreViewManager.TYPE_ERROR;

/**
 * Created by ubuntu on 16-6-21.
 */
public class Discovery3Fragment extends BaseRefreshFragment {

    private DiscuzRequest request;
    private ListView mListView;
    private LoadMoreViewManager mMoreViewManager;
    private MyAdapter myAdapter = new MyAdapter();
    private int page = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View onCreateContentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = (ListView) inflater.inflate(R.layout.listview_base, container, false);
        mListView.setAdapter(myAdapter);
        mMoreViewManager = new LoadMoreViewManager(mListView);
        mMoreViewManager.setNoMoreDateHintRes(R.string.mc_forum_detail_load_finish);
        return mListView;
    }

    @Override
    public void onRefreshing() {
    }
    @Override
    public void onLoadMore() {
        LqForumApi.newsList(++page, "3", new HttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                updateList(result);
            }

            @Override
            public void onFail(String result) {
                mMoreViewManager.setFooterType(TYPE_ERROR);
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        public JSONArray array = new JSONArray();
        @Override
        public int getCount() {
            return array.length();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.discovery3_item, parent, false);
            }
            try {
                final JSONObject object = array.getJSONObject(position);
                ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
                TextView auth = (TextView)convertView.findViewById(R.id.auth);
                TextView bankuai = (TextView)convertView.findViewById(R.id.bankuai);
                TextView title = (TextView)convertView.findViewById(R.id.title);
                TextView time = (TextView)convertView.findViewById(R.id.time);
                TextView vote = (TextView)convertView.findViewById(R.id.vote);
                TextView reply = (TextView)convertView.findViewById(R.id.reply);
                final RelativeLayout gridView = (RelativeLayout)convertView.findViewById(R.id.imagegride);
                Glide.with(getContext()).load(object.getString("userAvatar")).into(imageView);
                auth.setText(object.getString("user_nick_name"));
                title.setText(object.getString("title"));
                bankuai.setText(object.getString("board_name"));
                String timeText = TimeUtils.getTimeText(object.getString("last_reply_date"));
                time.setText(timeText);
                vote.setText(object.getString("hits"));
                reply.setText(object.getString("replies"));
                ViewTreeObserver vto = gridView.getViewTreeObserver();
                vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    boolean hasMeasured;

                    @Override
                    public boolean onPreDraw() {
                        if (!hasMeasured) {
                            // 获得初始宽高
                            try {
                                int width = gridView.getMeasuredWidth();
                                int height = (int) (width / 3 * Math.ceil(object.getJSONArray("imageList").length() / 3.0) * Double.parseDouble(object.getString("ratio")));
                                gridView.setMinimumHeight(height);
                                hasMeasured = true;
                            } catch (Exception e) {

                            }
                        }
                        return true;
                    }
                });
            } catch (Exception e) {

            }
            return convertView;
        }
    }

    @Override
    protected void onExecuteRequest(HttpResponseHandler handler) {
        page = 1;
        request = LqForumApi.newsList(page, "3", this);
    }

    @Override
    protected void showContent(String result) {
        myAdapter.array = new JSONArray();
        updateList(result);
    }

    private void updateList(String result) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("rs") == 1) {
                JSONArray array = object.getJSONArray("list");
                for (int i = 0; i < array.length(); i++) {
                    myAdapter.array.put(array.getJSONObject(i));
                }
                mRefreshLayout.setCanLoadMore();
                mRefreshLayout.onLoadComplete();
                myAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onDestroy() {
        if (request != null) {
            request.cancel(true);
        }
        super.onDestroy();
    }
}
