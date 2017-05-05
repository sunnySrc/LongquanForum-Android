package com.mobcent.discuz.module.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.uitls.ApphashUtils;

import java.net.URLEncoder;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.editInfoBean.EditInfoBean;
import discuz.com.retrofit.library.HTTPSubscriber;

public class EditActivity extends BasePopActivity {
    private String edit;
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
                Intent mIntent102 = new Intent();
                mIntent102.putExtra("text", edit);
                this.setResult(102, mIntent102);
                netWorkEdit(edit);
                finish();
                break;
            case "cellphone":
                //手机
                Intent mIntent103 = new Intent();
                mIntent103.putExtra("text", edit);
                this.setResult(103, mIntent103);
                netWork("mobile",edit);
                finish();
                break;
            case "compile_qq":
                //QQ
                Intent mIntent104 = new Intent();
                mIntent104.putExtra("text", edit);
                this.setResult(104, mIntent104);
                netWork("qq",edit);
                finish();
                break;
            case "compile_email":
                //常用邮箱
                Intent mIntent105 = new Intent();
                mIntent105.putExtra("text", edit);
                this.setResult(105, mIntent105);
                netWork("field",edit);
                finish();
                break;
            case "compile_graduate_institutions":
                //毕业学校
                Intent mIntent106 = new Intent();
                mIntent106.putExtra("text", edit);
                this.setResult(106, mIntent106);
                netWork("graduateschool",edit);
                finish();
                break;
            case "compile_company":
                //公司
                Intent mIntent107 = new Intent();
                mIntent107.putExtra("text", edit);
                this.setResult(107, mIntent107);
                netWork("company",edit);
                finish();
                break;
            case "compile_profession":
                //职业
                Intent mIntent108 = new Intent();
                mIntent108.putExtra("text", edit);
                this.setResult(108, mIntent108);
                netWork("occupation",edit);
                finish();
                break;
            case "compile_nickname":
                //昵称
                Intent mIntent109 = new Intent();
                mIntent109.putExtra("text", edit);
                this.setResult(109, mIntent109);
                netWork("field14",edit);
                finish();
                break;

            default:
                break;
        }

    }

    private void netWork(String param,String edit){
        ApphashUtils apphash=new ApphashUtils();
        String time=apphash.appHashs();
        String md5=apphash.encrypt(time);
        String apphashs=md5.substring(8,16);

        String ecode=URLEncoder.encode(edit);
        String num="%7B%22"+param+"%22%3A%22"+ecode+"%22%7D";
        DiscuzRetrofit.getUserInfoService(this).myselfInfoedit( WebParamsMap.myselfInfoedit(num,apphashs)).subscribe(new HTTPSubscriber<EditInfoBean>() {

            @Override
            public void onSuccess(EditInfoBean coloectionBean) {
                String errInfo=coloectionBean.getHead().getErrinfo();
                if (coloectionBean.getHead().getErrcode().equals("00000000")){
                    Toast.makeText(EditActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("TAG", "错误");
                Log.i("TAG", "errorUserCode="+errorUserCode);
                Log.i("TAG", "message="+message);
            }

        });
    }
    private void netWorkEdit(String edit){
        ApphashUtils apphash=new ApphashUtils();
        String time=apphash.appHashs();
        String md5=apphash.encrypt(time);
        String apphashs=md5.substring(8,16);
        String ecode=URLEncoder.encode(edit);
        Log.i("TAG", "ecode="+ecode);
        DiscuzRetrofit.getUserInfoService(this).myselfInfoedited( WebParamsMap.myselfInfoedited(ecode,apphashs)).subscribe(new HTTPSubscriber<EditInfoBean>() {

            @Override
            public void onSuccess(EditInfoBean coloectionBean) {
                String errInfo=coloectionBean.getHead().getErrinfo();
                Log.i("TAG", "errInfo="+errInfo);
                if (coloectionBean.getHead().getErrcode().equals("00000000")){
                    Toast.makeText(EditActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("TAG", "错误");
                Log.i("TAG", "errorUserCode="+errorUserCode);
                Log.i("TAG", "message="+message);
            }

        });
    }
    private void intiial() {
        single= (EditText) findViewById(R.id.edit_single);
        doubles= (EditText) findViewById(R.id.edit_double);
        getAppActionBar().setRightTitle("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSingle){
                    edit=single.getText().toString();
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
