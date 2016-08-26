package com.mobcent.discuz.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.bean.Component;

/**
 * Created by sun on 2016/8/25.
 * 模块的标题头（目前只有法师开示语录有）
 */

public class StyleHeaderView extends FrameLayout{

    private TextView sectionTitle;
    private View moreClick;
    private Component moreComponent;
    private String title;

    public StyleHeaderView(Context context) {
        super(context);
        init();
    }

    public StyleHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StyleHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setData(String title, Component moreComponent) {
        this.title = title;
        sectionTitle.setText(title);
        this.moreComponent= moreComponent;
    }

    private void init() {
        inflate(getContext(), R.layout.layout_component_style_header, this);
        sectionTitle = (TextView)findViewById(R.id.title);
        moreClick = findViewById(R.id.more);
        moreClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查看更多
                UIJumper.jump(getContext(), moreComponent.getType(),
                        moreComponent.getExtParams1().getNewsModuleId(),
                        moreComponent.getExtParams1().getRedirect());
            }
        });
    }

}
