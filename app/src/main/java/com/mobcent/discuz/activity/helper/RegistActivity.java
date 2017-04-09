package com.mobcent.discuz.activity.helper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.WebParamsMap;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.registbean.RegistBean;
import discuz.com.retrofit.library.HTTPSubscriber;
import library.component.actionbar.AppActionBar;

public class RegistActivity extends Activity {
    private AppActionBar appActionBar;
    private EditText username,pwd,emails;
    private Button regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_bound_fragment);
        appActionBar = (AppActionBar)findViewById(R.id.app_action_bar);
        pwd = (EditText) findViewById(R.id.mc_forum_user_input_pwd_btn);
        emails = (EditText) findViewById(R.id.mc_forum_user_input_user_email_edit);
        regist = (Button) findViewById(R.id.mc_forum_user_save_info_submit_btn);
        username = (EditText) findViewById(R.id.mc_forum_user_input_username_btn);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uname=username.getText().toString();
                String password=pwd.getText().toString();
                String email=emails.getText().toString();
                DiscuzRetrofit.getUserInfoService(RegistActivity.this).userregist(WebParamsMap.regist_map(uname,password,email)).subscribe(new HTTPSubscriber<RegistBean>() {
                    @Override
                    public void onSuccess(RegistBean registBean) {
                        String errorCode=registBean.getHead().getErrcode();
                        Log.i("TAG", "errorcode="+errorCode);
                        Log.i("TAG", "registBean="+registBean);
                        Log.i("TAG", "username="+registBean.getBody());
                        Log.i("TAG", "errorCode="+errorCode);
                        Log.i("TAG", "注册成功");
                        if (errorCode.equals("00000000")){
                            Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Log.i("TAG", "registBean="+registBean);
                            Toast.makeText(RegistActivity.this,"注册失败:"+registBean.getErrcode(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(int httpCode, int errorUserCode, String message) {
                        Toast.makeText(RegistActivity.this,"错误:"+httpCode,Toast.LENGTH_SHORT).show();
                        Log.i("TAG", "错误:httpCode="+httpCode+", errorUserCode="+errorUserCode+"; message="+message);
                    }
                });
            }
        });

    }

}
