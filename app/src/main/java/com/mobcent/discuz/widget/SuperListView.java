package com.mobcent.discuz.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by sun on 2016/8/30.
 * 嵌套时展开
 */

public class SuperListView extends ListView{
    public SuperListView(Context context) {
        super(context);
    }

    public SuperListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
