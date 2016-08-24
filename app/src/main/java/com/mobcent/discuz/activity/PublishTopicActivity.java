package com.mobcent.discuz.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.base.constant.LocationProvider;
import com.mobcent.discuz.fragments.HttpResponseHandler;
import com.zejian.emotionkeyboard.fragment.EmotionMainFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Vector;


/**
 * Created by ubuntu on 16-8-18.
 */
public class PublishTopicActivity extends FragmentActivity {

    private GridView mGridView;
    private ImageAdapter mImageAdapter;
    private ImageView mLocation;
    private TextView mLocationText;
    private LocationClickListener mLocationClickListener;
    private int mCate;

    private String mLongitude;
    private String mLatitude;
    private UploadFileHandler mUploadFileHandler = new UploadFileHandler();
    private LocationHandler mLocationHandler = new LocationHandler();
    private LocationCheckHandler mLocationCheckHandler = new LocationCheckHandler();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.publish_topic_activity);
        String type = getIntent().getExtras().getString("Type");
        if (type.equals("1")) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        } else if (type.equals("2")) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 2);
        }
        mGridView = (GridView) findViewById(R.id.imagegride);
        mImageAdapter = new ImageAdapter(this);
        mImageAdapter.addItem(new Item("", BitmapFactory.decodeResource(getResources(), R.drawable.dz_publish_add_picture_h)));
        mGridView.setAdapter(mImageAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (position == mImageAdapter.getCount() - 1) {
                    final Dialog dialog = new Dialog(PublishTopicActivity.this, R.style.mc_forum_home_publish_dialog);
                    final LayoutInflater in = LayoutInflater.from(PublishTopicActivity.this);
                    View view = in.inflate(R.layout.publish_pic_select, null);
                    view.findViewById(R.id.mc_forun_publish_from_pictures).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent, 1);
                        }
                    });
                    view.findViewById(R.id.mc_forun_publish_from_camera).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 2);
                        }
                    });

                    dialog.setContentView(view);
                    dialog.show();
                } else {

                }
            }
        });

        mLocation = (ImageView) findViewById(R.id.mc_forum_location_img);
        mLocationClickListener = new LocationClickListener();
        mLocationText = (TextView) findViewById(R.id.mc_forum_loction_text);
        mLocationText.setOnClickListener(mLocationClickListener);
        mLocation.setOnClickListener(mLocationClickListener);

        findViewById(R.id.mc_forum_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.mc_forum_public_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCate == 0) {
                    Toast.makeText(PublishTopicActivity.this, "请先选择板块", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mImageAdapter.getCount() > 1) {
                    String url = "forum/sendattachmentex&type=image&forumKey=BW0L5ISVRsOTVLCTJx&accessSecret=" + LoginUtils.getInstance().getAccessSecret() + "&accessToken=" + LoginUtils.getInstance().getAccessToken() +
                            "&module=forum&egnVersion=v2035.2&sdkVersion=2.4.3.0&fid=" + mCate + "&apphash=4c37ae6f";
                    Vector<String> files = new Vector<String>();
                    for (int i = 1; i < mImageAdapter.getCount(); i++) {
                        files.add(((Item) mImageAdapter.getItem(i)).mPath);
                    }
                    new DiscuzRequest(url, files, mUploadFileHandler).execute();
                } else {
                    doPublish();
                }
            }
        });

        findViewById(R.id.mc_forum_public_label_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublishTopicActivity.this, BoardListActivity.class);
                startActivityForResult(intent, 3);
            }
        });

        final EditText content = (EditText)findViewById(R.id.mc_forum_content_edit);
        content.setFocusable(true);
        content.setFocusableInTouchMode(true);
        content.requestFocus();
        InputMethodManager im = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        im.showSoftInput(content, 0);

        //构建传递参数
        Bundle fragmentBundle = new Bundle();
        //绑定主内容编辑框
        fragmentBundle.putBoolean(EmotionMainFragment.BIND_TO_EDITTEXT, false);
        //隐藏控件
        fragmentBundle.putBoolean(EmotionMainFragment.HIDE_BAR_EDITTEXT_AND_BTN,true);
        //替换fragment
        //创建修改实例
        EmotionMainFragment emotionMainFragment = EmotionMainFragment.newInstance(EmotionMainFragment.class, fragmentBundle);
        emotionMainFragment.bindToContentView(content);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in thefragment_container view with this fragment,
        // and add the transaction to the backstack
        transaction.replace(R.id.fl_emotionview_main, emotionMainFragment);
        transaction.addToBackStack(null);
        //提交修改
        transaction.commit();
    }

    class UploadFileHandler implements HttpResponseHandler {
        private DiscuzRequest mLocationRequest;

        @Override
        public void onSuccess(String result) {
            try {
                JSONObject object = new JSONObject(result);
                if (!"1".equals(object.getString("rs"))) {
                    onFail(object.getString("errcode"));
                } else {
                    doPublish();
                }
            } catch (Exception e) {

            }
        }

        @Override
        public void onFail(String result) {
            Toast.makeText(PublishTopicActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    private void doPublish() {
        EditText title = (EditText)findViewById(R.id.mc_forum_title_edit);
        String titleString = title.getText().toString().trim();
        EditText content = (EditText)findViewById(R.id.mc_forum_content_edit);
        String contentString = content.getText().toString().trim();
        try {
            JSONObject contentJson = new JSONObject();
            contentJson.put("infor", contentString);
            contentJson.put("type", "0");
            JSONArray contentArray = new JSONArray();
            contentArray.put(contentJson);
            String contentArrayString = URLEncoder.encode(contentArray.toString());
            JSONObject total = new JSONObject();
            JSONObject body = new JSONObject();
            JSONObject json = new JSONObject();
            json.put("typeId", "80");
            json.put("isShowPostion", "1");
            json.put("content", contentArrayString);
            json.put("aid", "");
            json.put("isQuote", "0");
            json.put("fid", mCate);
            json.put("longitude", mLongitude);
            json.put("latitude", mLatitude);
            json.put("title", URLEncoder.encode(titleString));
            total.put("body", body);
            body.put("json", json);
            JSONObject param = new JSONObject();
            param.put("platType", 5);
            param.put("act", "new");
            param.put("json", URLEncoder.encode(total.toString()));
            new DiscuzRequest("forum/topicadmin", param.toString(), new PublishHandler()).execute();
        } catch (Exception e) {

        }

    }

    private class PublishHandler implements HttpResponseHandler {
        @Override
        public void onSuccess(String result) {
            try {
                JSONObject object = new JSONObject(result);
                if (!"1".equals(object.getString("rs"))) {
                    onFail(object.getString("errcode"));
                } else {
                    onFail(object.getString("errcode"));
                    finish();
                }
            } catch (Exception e) {

            }
        }
        @Override
        public void onFail(String result) {
            Toast.makeText(PublishTopicActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    class LocationClickListener implements View.OnClickListener {
        private DiscuzRequest mLocationRequest;

        private class MyLocationHandler implements LocationProvider.LocationHandler {
            @Override
            public void onLocationUpdate(Location location) {
                sendLocationRequest(location);
            }
        }

        @Override
        public void onClick(View view) {
            mLocation.setImageResource(R.drawable.dz_publish_location_selected);
            mLocationText.setText("定位中");
            Location location = LocationProvider.getInstance().provideLocation(new MyLocationHandler());
            if (location != null) {
                sendLocationRequest(location);
            }
        }

        private void sendLocationRequest(Location location) {
            if (mLocationRequest != null) {
                mLocationRequest.cancel(true);
            }
            mLocationHandler.setPosition(location.getLongitude(), location.getLatitude());
            mLocationRequest = new DiscuzRequest("http://api.map.baidu.com/geocoder/v2/?ak=GT5EmhOircF8diYLKDrIezIp&location=" + location.getLatitude() + "," + location.getLongitude() + "&output=json&pois=1", "", mLocationHandler, "get");
            mLocationRequest.execute();
        }
    }

    private class LocationHandler implements HttpResponseHandler {
        private DiscuzRequest mLocationCheckRequest;
        public LocationHandler() {

        }
        public void setPosition(double longitude, double latitude) {
            mLongitude = String.valueOf(longitude);
            mLatitude = String.valueOf(latitude);
        }
        @Override
        public void onSuccess(String result) {
            try {
                JSONObject object = new JSONObject(result);
                String location = object.getJSONObject("result").getString("formatted_address");
                JSONObject object1 = new JSONObject();
                object1.put("location", location);
                object1.put("longitude", mLongitude);
                object1.put("latitude", mLatitude);
                mLocationCheckHandler.setLocation(location);
                if (mLocationCheckRequest != null) {
                    mLocationCheckRequest.cancel(true);
                }
                mLocationCheckRequest = new DiscuzRequest("user/location", object1.toString(), mLocationCheckHandler);
                mLocationCheckRequest.execute();
            } catch (Exception e) {
                onFail("");
            }
        }

        @Override
        public void onFail(String result) {
            onLocationFail();
        }
    }

    private class LocationCheckHandler implements HttpResponseHandler {
        private String mLocationString;
        public LocationCheckHandler() {

        }
        public void setLocation(String location) {
            mLocationString = location;
        }
        @Override
        public void onSuccess(String result) {
            try {
                JSONObject object = new JSONObject(result);
                String rs = object.getString("rs");
                if ("1".equals(rs)) {
                    mLocation.setImageResource(R.drawable.dz_publish_location_unselected);
                    mLocationText.setText(mLocationString);
                }
            } catch (Exception e) {
                onFail("");
            }
        }

        @Override
        public void onFail(String result) {
            onLocationFail();
        }
    }

    private void onLocationFail() {
        mLocation.setImageResource(R.drawable.dz_publish_location_unselected);
        mLocationText.setText(R.string.mc_forum_rapid_publish_show_location);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==2)
        {
            if (resultCode==Activity.RESULT_OK && data != null) {
                Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
                mImageAdapter.addItem(new Item("", cameraBitmap));
                mImageAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Uri selectImageUri  = data.getData();
                String[] filePathColumn = new String[]{MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectImageUri,filePathColumn,null,null,null);
                String pirPath = null;
                while(cursor.moveToNext()){
                    pirPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                    Bitmap bitmap = BitmapFactory.decodeFile(pirPath);
                    mImageAdapter.addItem(new Item(pirPath, bitmap));
                }
                mImageAdapter.notifyDataSetChanged();
                cursor.close();
            }
        } else if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                int cate = data.getIntExtra("cate", 0);
                String cateName = data.getStringExtra("cateName");
                if (cate != 0) {
                    mCate = cate;
                    ((TextView)findViewById(R.id.mc_forum_public_label_text)).setText(cateName);
                }
            }
        }
    }

    class Item {
        public final String mPath;
        public final Bitmap mBitmap;

        public Item(String path, Bitmap bitmap) {
            mPath = path;
            mBitmap = bitmap;
        }
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context = null;

        private Vector<Item> mThumbIds = new Vector<Item>();

        public ImageAdapter(Context context){
            this.context = context;
        }

        public int getCount() {
            return mThumbIds.size();
        }

        public void addItem(Item bitmap) {
            mThumbIds.add(bitmap);
        }

        public Object getItem(int position) {
            return mThumbIds.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = null;
            if(convertView == null){
                imageView = new ImageView(context);
                // 设置View的height和width：这样保证无论image原来的尺寸，每个图像将重新适合这个指定的尺寸。
                imageView.setLayoutParams(new GridView.LayoutParams(85,85));
                /* ImageView.ScaleType.CENTER 但不执行缩放比例
                 * ImageView.ScaleType.CENTER_CROP 按比例统一缩放图片（保持图片的尺寸比例）便于图片的两维（宽度和高度）等于或大于相应的视图维度
                 * ImageView.ScaleType.CENTER_INSIDE 按比例统一缩放图片（保持图片的尺寸比例）便于图片的两维（宽度和高度）等于或小于相应的视图维度 */
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }else{
                imageView = (ImageView)convertView;
            }
            if (position == getCount() - 1) {
                imageView.setImageBitmap(mThumbIds.get(0).mBitmap);
            } else {
                imageView.setImageBitmap(mThumbIds.get(position + 1).mBitmap);
            }
            return imageView;
        }

    }

}
