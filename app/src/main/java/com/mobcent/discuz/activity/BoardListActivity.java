package com.mobcent.discuz.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.base.constant.LocationProvider;
import com.mobcent.discuz.fragments.HttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ubuntu on 16-8-23.
 */
public class BoardListActivity extends Activity {

    private ForumAdapter mForumAdapter;

    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.board_list_activity);

        findViewById(R.id.mc_forum_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        new DiscuzRequest("forum/forumlist", new JSONObject().toString(), new ForumHandler()).execute();
        ListView list = (ListView)findViewById(R.id.list);
        mForumAdapter = new ForumAdapter(this);
        list.setAdapter(mForumAdapter);
    }

    class Item {
        final String mName;
        final int mType;

        public Item(String name, int type) {
            mName = name;
            mType = type;
        }
    }

    private class ForumAdapter extends BaseAdapter {

        private Context context = null;

        private Vector<Item> mThumbIds = new Vector<Item>();

        public ForumAdapter(Context context){
            this.context = context;
        }

        public int getCount() {
            return mThumbIds.size();
        }

        public void addItem(Item forum) {
            mThumbIds.add(forum);
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView tv = null;
            if(convertView == null){
                tv = new TextView(context);
            }else{
                tv = (TextView)convertView;
            }
            tv.setText(mThumbIds.get(position).mName);
            if (0 == mThumbIds.get(position).mType) {
                tv.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setBackgroundResource(R.color.com_facebook_blue);
                tv.setPadding(10, 10, 10, 10);
                tv.setGravity(Gravity.LEFT);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } else {
                tv.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));
                tv.setBackgroundResource(android.R.color.white);
                tv.setGravity(Gravity.CENTER);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent();
                        it.putExtra("cate", mThumbIds.get(position).mType);
                        it.putExtra("cateName", mThumbIds.get(position).mName);
                        setResult(RESULT_OK, it);
                        finish();
                    }
                });
            }
            return tv;
        }
    }

    private class ForumHandler implements HttpResponseHandler {
        @Override
        public void onSuccess(String result) {
            try {
                JSONObject object = new JSONObject(result);
                if ("1".equals(object.getString("rs"))) {
                    JSONArray array = object.getJSONArray("list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject l = array.getJSONObject(i);
                        mForumAdapter.addItem(new Item(l.getString("board_category_name"), 0));
                        JSONArray lists = l.getJSONArray("board_list");
                        for (int j = 0; j < lists.length(); j++) {
                            JSONObject ll = lists.getJSONObject(j);
                            mForumAdapter.addItem(new Item(ll.getString("board_name"), ll.getInt("board_id")));
                        }
                    }
                    mForumAdapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                onFail("");
            }
        }

        @Override
        public void onFail(String result) {

        }
    }
}
