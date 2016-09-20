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
import com.mobcent.discuz.api.UrlFactory;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.module.person.activity.UserHomeActivity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ubuntu on 16-6-21.
 */
public class UserHomeFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home_center_fragment, container, false);
        return view;
    }
}
