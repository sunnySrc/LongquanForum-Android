package com.mobcent.discuz.module.person.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.fragments.UserHomeFragment;

import org.json.JSONObject;

/**
 * Created by ubuntu on 16-9-20.
 */
public class UserHomeActivity extends BasePopActivity {

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.user_home_center_fragment);

    }

    protected Fragment initContentFragment() {
        return new UserHomeFragment();
    }
}
