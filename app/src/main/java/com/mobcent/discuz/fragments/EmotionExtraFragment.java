package com.mobcent.discuz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;
import com.mobcent.common.FragmentBackHandler;
import com.zejian.emotionkeyboard.fragment.EmotionMainFragment;

import java.util.List;

/**
 * Created by sun
 @see EmotionMainFragment
 */
public class EmotionExtraFragment extends com.zejian.emotionkeyboard.fragment.EmotionMainFragment implements FragmentBackHandler {


    @Override
    protected void addOtherPanelFragment(List<Fragment> fragments) {
        fragments.add(new FunctionFragment());
    }

    @Override
    protected boolean enableExtraPanel() {
        return true;
    }

    public void setConfirmClick(View.OnClickListener listener) {
        bar_btn_send.setOnClickListener(listener);
    }

    public void hideAllKeyBoard() {
        mEmotionKeyboard.hideAllPanel();
    }


    /**
     * Extra 面板，添加 照片
     */
    public static class FunctionFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_inputpanel_function, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            view.findViewById(R.id.pan_camera).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //相机
                }
            });
            view.findViewById(R.id.pan_album).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击
                }
            });
        }
    }
}


