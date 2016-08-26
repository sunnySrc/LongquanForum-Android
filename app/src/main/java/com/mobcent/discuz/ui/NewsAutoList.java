package com.mobcent.discuz.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.bean.Component;

/**
 * Created by sun on 2016/8/25.
 */

public class NewsAutoList extends LinearLayout{
    private View moreClick;
    private TextView sectionTitle;

    public NewsAutoList(Context context) {
        super(context);
        init();
    }

    public NewsAutoList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewsAutoList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 注意设置标题 和 加载更多 的跳转数据
     * @param component
     */
    public void setData(Component component) {
    }


    private void init() {

    }

}
