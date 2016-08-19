package com.mobcent.discuz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;

/**
 * Created by ubuntu on 16-6-21.
 */
public class HomeFragment extends BaseFragment {

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);
        return view;
    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onFail(String result) {

    }
}
