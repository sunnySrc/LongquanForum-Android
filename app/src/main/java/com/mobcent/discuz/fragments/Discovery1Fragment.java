package com.mobcent.discuz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.common.TimeUtil;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.bean.Link;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ubuntu on 16-6-21.
 */
public class Discovery1Fragment extends BaseRefreshFragment {

    private DiscuzRequest request;
    private ViewGroup viewGroup;
    private int page = 1;
    private double left;
    private double right;
    private LinearLayout leftView;
    private LinearLayout rightView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View onCreateContentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.two_list_middle, container, false);
        leftView = (LinearLayout)viewGroup.findViewById(R.id.left_list);
        rightView = (LinearLayout)viewGroup.findViewById(R.id.right_list);
        return viewGroup;
    }

    @Override
    public void onRefreshing() {
    }
    @Override
    public void onLoadMore() {
        page++;
        onExecuteRequest(null);
    }

    @Override
    protected void onExecuteRequest(HttpResponseHandler handler) {
        request = LqForumApi.newsList(page, "1", this);
    }

    public View getView(JSONObject object, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.discovery1_item, parent, false);
        }
        try {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            ImageUtils.getImageViewSizeInit(imageView, Double.parseDouble(object.getString("ratio")));
            Glide.with(getContext()).load(object.getString("pic_path")).into(imageView);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(object.getString("title"));
            TextView auth = (TextView) convertView.findViewById(R.id.auth);
            auth.setText(object.getString("user_nick_name"));
            TextView time = (TextView) convertView.findViewById(R.id.time);
            String timeText = TimeUtils.getTimeText(object.getString("last_reply_date"));
            time.setText(timeText);
        } catch (Exception e) {
        }
        return convertView;
    }

    @Override
    protected void showContent(String result) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("rs") == 1) {
                JSONArray array = object.getJSONArray("list");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject item = array.getJSONObject(i);
                    double ratio = Double.parseDouble(item.getString("ratio"));
                    if (left < right) {
                        left += ratio;
                        View view = getView(item, null, leftView);
                        leftView.addView(view);
                    } else {
                        right += ratio;
                        View view = getView(item, null, rightView);
                        rightView.addView(view);
                    }
                }
                mRefreshLayout.setCanLoadMore();
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
