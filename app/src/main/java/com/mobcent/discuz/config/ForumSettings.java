package com.mobcent.discuz.config;

import com.mobcent.common.JsonConverter;
import com.mobcent.discuz.api.LqForumApi;
import com.mobcent.discuz.bean.SettingResult;
import com.mobcent.discuz.fragments.HttpResponseHandler;

/**
 * Created by sun on 2016/9/14.
 */

public class ForumSettings {
    enum State{
         LOADING, FIAL, OK
    }
    private ForumSettings(){
    }
    private static class Holder {
        public static final ForumSettings INSTANCE = new ForumSettings();
    }
    public static ForumSettings getInstance() {
        return Holder.INSTANCE;
    }

    private State mState;

    public  void loadSetting() {
        if (mState == State.LOADING || mState == State.OK) return;

        mState = State.LOADING;
        LqForumApi.getSettings(new HttpResponseHandler() {
            @Override
            public void onSuccess(String result) {
                SettingResult settingResult = JsonConverter.format(result, SettingResult.class);
                if (settingResult.isSuccess()) {
                    mState = State.OK;
                } else {
                    mState = State.FIAL;
                }
            }

            @Override
            public void onFail(String result) {
                mState = State.FIAL;
            }
        });
    }
}
