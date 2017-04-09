package com.mobcent.discuz.module.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BasePopActivity;

public class EditActivity extends BasePopActivity {
    private String  title;
    private EditText single;
    private EditText doubles;
    //private String edit;
    private String from;
    private Boolean isSingle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intiial();
        Intent  intent =getIntent();
        title=intent.getStringExtra("title");
        getAppActionBar().setTitle(title);
        isSingle=intent.getBooleanExtra("single",false);
        Boolean iscellphone=intent.getBooleanExtra("phone",false);
        from=intent.getStringExtra("from");
        if (isSingle){
            single.setVisibility(View.VISIBLE);
            doubles.setVisibility(View.GONE);
            if(iscellphone){
                single.setInputType(InputType.TYPE_CLASS_PHONE);//只能输入手机号码
            }
        }else {
            single.setVisibility(View.GONE);
            doubles.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int initLayout() {
        return R.layout.activity_edit;
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    private void toSend(final String edit, final String from) {

        switch (from){
            case "signature":
                //签名
                //TODO
                Toast.makeText(EditActivity.this,"签名:"+edit,Toast.LENGTH_SHORT).show();
                break;
            case "cellphone":
                //手机
                // TODO: 2017/3/8
                break;
            case "compile_qq":
                //QQ
                // TODO: 2017/3/8
                break;
            case "compile_email":
                //常用邮箱
                // TODO: 2017/3/8
                break;
            case "compile_graduate_institutions":
                //毕业学校
                // TODO: 2017/3/8
                break;
            case "compile_company":
                //公司
                // TODO: 2017/3/8
                break;
            case "compile_profession":
                //职业
                // TODO: 2017/3/8
                break;
            case "compile_nickname":
                //昵称
                // TODO: 2017/3/8
                break;

            default:
                break;
        }

    }



    private void intiial() {
        single= (EditText) findViewById(R.id.edit_single);
        doubles= (EditText) findViewById(R.id.edit_double);
        getAppActionBar().setRightTitle("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSingle){
                    String edit=single.getText().toString();
                    if (edit!=null&&!edit.equals("")) {
                        toSend(edit,from);
                    }else {
                        Toast.makeText(EditActivity.this,"输入内容不能为空",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    String edit=doubles.getText().toString();
                    if (edit!=null&&!edit.equals("")) {
                        toSend(edit,from);
                    }else {
                        Toast.makeText(EditActivity.this,"输入内容不能为空",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

    }

}
