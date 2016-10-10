package com.mobcent.discuz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.constant.BaseIntentConstant;

public abstract class BasePopActivity
  extends FragmentActivity
  implements BaseIntentConstant
{
  private FrameLayout containerBox;
  private Fragment contentFragment;
  
  protected Fragment getContentFragment()
  {
    return this.contentFragment;
  }


  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    setContentView(R.layout.base_pop_activity);
  }
  
  protected String getLayoutName()
  {
    return "base_pop_activity";
  }
  
  protected void initActions() {}
  
  protected abstract Fragment initContentFragment();
  
  protected void initViews()
  {
    this.containerBox = ((FrameLayout)findViewById(R.id.container_layout));
    this.contentFragment = initContentFragment();
    getSupportFragmentManager().beginTransaction().add(this.containerBox.getId(), this.contentFragment);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (getContentFragment() != null) {
      getContentFragment().onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
}