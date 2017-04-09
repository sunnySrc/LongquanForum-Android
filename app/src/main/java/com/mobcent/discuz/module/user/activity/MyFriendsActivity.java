package com.mobcent.discuz.module.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BasePopActivity;

public class MyFriendsActivity extends BasePopActivity {
    TextView btn_myfriends;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);
        btn_myfriends= (TextView) findViewById(R.id.myfriends_button);
        btn_myfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyFriendsActivity.this,MyFriendsSearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }
}
