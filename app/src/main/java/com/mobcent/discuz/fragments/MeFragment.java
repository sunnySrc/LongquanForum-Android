package com.mobcent.discuz.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.activity.LoginActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.module.user.activity.CollectionActivity;
import com.mobcent.discuz.module.user.activity.DraftActivity;
import com.mobcent.discuz.module.user.activity.MyFriendsActivity;
import com.mobcent.discuz.module.user.activity.MyMessageActivity;
import com.mobcent.discuz.module.user.activity.MyPublicReportActivity;
import com.mobcent.discuz.module.user.activity.SettingActivity;
import com.mobcent.discuz.module.user.activity.UserHomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.appbyme.dev.R.id.mc_forum_user_publish;

/**
 * Created by ubuntu on 16-6-21.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private String userinfo;
    private TextView user_name;
    private TextView user_level;
    private TextView user_integral;
    TextView login_regist;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_self_header_item, container, false);
        view.findViewById(R.id.draft).setOnClickListener(this);
        view.findViewById(R.id.message).setOnClickListener(this);
        view.findViewById(R.id.manage).setOnClickListener(this);
        view.findViewById(R.id.settings).setOnClickListener(this);
        view.findViewById(mc_forum_user_publish).setOnClickListener(this);
        view.findViewById(R.id.mc_forum_user_friend).setOnClickListener(this);
        view.findViewById(R.id.mc_forum_user_collect).setOnClickListener(this);
        view.findViewById(R.id.mc_forum_user_info).setOnClickListener(this);
        user_name = (TextView) view.findViewById(R.id.mc_forum_user_name);
        user_level = (TextView) view.findViewById(R.id.mc_forum_user_level);
        user_integral = (TextView) view.findViewById(R.id.mc_forum_user_integral);
        login_regist = (TextView) view.findViewById(R.id.mc_forum_user_isloging);

        userInfo();

        return view;
    }

    private void setLogout() {
        Glide.with(getContext()).load(R.drawable.dz_icon_head_default).into((ImageView) view.findViewById(R.id.mc_forum_user_header_icon));
        login_regist.setVisibility(View.VISIBLE);
        user_name.setText("");
        user_level.setText("");
        user_integral.setText("");
    }

    @Override
    public void onResume() {
        super.onResume();
        userInfo();
    }

    private void userInfo() {
        userinfo = LoginUtils.getInstance().getUserInfo();
        if (!TextUtils.isEmpty(userinfo)) {
            try {
                login_regist.setVisibility(View.GONE);
                JSONObject object = new JSONObject(userinfo);
                Glide.with(getContext()).load(object.getString("avatar")).into((ImageView) view.findViewById(R.id.mc_forum_user_header_icon));

                user_name.setText(object.getString("userName"));
                user_level.setText(object.getString("userTitle"));
                JSONArray array = object.getJSONArray("creditShowList");
                String integral = "";
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);
                    integral += o.getString("title") + ":" + o.getString("data");
                }
                user_integral.setText(integral);
            } catch (Exception e) {

            }
        } else {
            setLogout();

        }


    }


    private void startLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        String uid = LoginUtils.getInstance().getUserId();
        if (uid == null) {
            startLoginActivity();
            return;
        }
        int id = v.getId();
        switch (id) {
            case R.id.draft:
                Intent draftIntent = new Intent(getActivity(), DraftActivity.class);
                startActivity(draftIntent);
                break;
            case R.id.message:
                startActivity(new Intent(getActivity(), MyMessageActivity.class));
                break;
            case R.id.manage:
                if (LoginUtils.getInstance().isLogin()) {
                    final Dialog dialog = new Dialog(getContext(), R.style.mc_forum_home_publish_dialog_nofullscreen);
                    final LayoutInflater in = LayoutInflater.from(getContext());
                    View view = in.inflate(R.layout.user_home_switch_user_dialog, null);
                    view.findViewById(R.id.logout_layout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(R.string.mc_forum_logout_dialog)
                                    .setCancelable(true)
                                    .setPositiveButton(R.string.mc_forum_dialog_confirm, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int hich) {
                                            try {
                                                setLogout();
                                                LoginUtils.getInstance().setLogout();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    })
                                    .setNegativeButton(R.string.vp_dialog_cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            builder.create().show();


                        }
                    });
                    dialog.setContentView(view);
                    dialog.show();
                } else {
                    Toast.makeText(getContext(), R.string.mc_forum_warn_loginfirst, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.settings:
                Intent SettingIntent = new Intent(getContext(), SettingActivity.class);
                startActivity(SettingIntent);
                break;
            case mc_forum_user_publish:
                //我的发表
                if (!LoginUtils.getInstance().isLogin()) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent_public = new Intent(getContext(), MyPublicReportActivity.class);
                    startActivity(intent_public);
                }

                break;
            case R.id.mc_forum_user_friend:
                Intent intent2 = new Intent(getActivity(), MyFriendsActivity.class);
                startActivity(intent2);
                break;
            case R.id.mc_forum_user_collect:
                Intent intent1 = new Intent(getContext(), CollectionActivity.class);
                startActivity(intent1);
                break;
            case R.id.mc_forum_user_info:
                if (LoginUtils.getInstance().isLogin()) {
                    Intent intent_user_info = new Intent(getContext(), UserHomeActivity.class);
                    startActivity(intent_user_info);
                } else {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }

    class LogoutHandler implements HttpResponseHandler {
        @Override
        public void onSuccess(String result) {
            try {
                JSONObject object = new JSONObject(result);
                if ("1".equals(object.getString("rs"))) {
                    LoginUtils.getInstance().setLogout();
                }
                onFail(object.getString("errcode"));
            } catch (Exception e) {
                onFail(getContext().getString(R.string.mc_forum_login_fail_interfError));
            }
        }

        @Override
        public void onFail(String result) {
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
