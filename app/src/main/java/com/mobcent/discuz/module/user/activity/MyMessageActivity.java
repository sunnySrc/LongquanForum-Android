package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BasePopActivity;

/**
 * Created by sun on 2017/5/20.
 */
public class MyMessageActivity extends BasePopActivity {


    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        getAppActionBar().setTitle(R.string.mc_forum_my_message);

        getAppActionBar().setRightIcon(R.drawable.dz_board_icon_follow, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2017/5/25
                Toast.makeText(MyMessageActivity.this, "我的好友，周边，推荐", Toast.LENGTH_LONG).show();
            }
        });


        ((ImageView) findViewById(R.id.my_msg_comment).findViewById(R.id.my_msg_iv_type)).setImageResource(R.drawable.dz_msg_icon_reply);
        ((TextView) findViewById(R.id.my_msg_comment).findViewById(R.id.my_msg_tv_type)).setText(R.string.mc_forum_comment);

        ((ImageView) findViewById(R.id.my_msg_sytem).findViewById(R.id.my_msg_iv_type)).setImageResource(R.drawable.dz_msg_icon_friend);
        ((TextView) findViewById(R.id.my_msg_sytem).findViewById(R.id.my_msg_tv_type)).setText(R.string.mc_forum_sys_msg);


    }

    @Override
    public int initLayout() {
        return R.layout.my_activity_mymessage;
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }


}
