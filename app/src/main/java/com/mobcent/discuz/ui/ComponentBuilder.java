package com.mobcent.discuz.ui;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.common.ScreenUtil;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.bean.*;
import com.mobcent.discuz.bean.StyleHeader;
import com.mobcent.discuz.widget.FitHeightImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2016/8/23.
 * 首页模块组合
 * TODO 刷新增量更新
 */

public class ComponentBuilder {
    private final Context context;
    private final LayoutInflater inflater;
    private SwipeRefreshLayout mRefreshLayout;

    public ComponentBuilder(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    /**
     * 构建ViewGroup
     * @param divider
     * @return
     */
    public ViewGroup buildComponentGroup(List<Component> components,  boolean divider){
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout group = new LinearLayout(context);
        group.setOrientation(LinearLayout.VERTICAL);
        if (!divider) {
            // 没有分割栏, 则添加组边距
            int padding = ScreenUtil.dip2px(context ,8);
            group.setPadding(0, padding, 0, padding);
        }
        final int count = components.size();
        for (int i = 0;i < count; i++) {
            Component component = components.get(i);
            // 是否添加Header 分栏
            checkStyleHeader(component, group);
            // 添加组件
            View cView = buildView(component);
            group.addView(cView);
            if (divider && i < count -1  ) {
                group.addView(initDivider(group),params);
            }
        }
        return group;
    }

    private View initDivider(ViewGroup container) {
        return inflater.inflate(R.layout.item_divider, container, false);
    }

    private void checkStyleHeader(Component component, ViewGroup container) {
        StyleHeader styleHeader = component.getStyleHeader();
        if (needShowHeader(styleHeader)) {
            StyleHeaderView headerView = new StyleHeaderView(context);
            headerView.setData(styleHeader.getTitle(), styleHeader.getMoreComponent());
            container.addView(headerView);
        }
    }

    private boolean needShowHeader(StyleHeader styleHeader) {
        return styleHeader != null
                && styleHeader.getIsShow() > 0
                && styleHeader.getMoreComponent() !=  null && styleHeader.getPosition() > 0;
    }

    public View buildView(Component component){
        final String style = component.getStyle();
        if (Component.STYLE_LAYOUT_NORMAL.equals(style)) {
            // 容器
            List<Component> children = component.getComponentList();
            if (children == null || children.size() == 0) return defaultContainer();

            if (children.size() == 1) {
               return buildView(children.get(0));
            }else {
                return buildComponentGroup(children, false);
            }
        } else if (Component.STYLE_LAYOUT_LINE.equals(style)){
            // 容器 ，细线分割
        } else if (Component.STYLE_NEWS_AUTO.equals(style)) {
            // 法语开示
            buildNewsList();
        } else if (component.STYLE_Col_FOUR.equals(style)) {
            // 一行四 链接
           return buildColFour(component.getComponentList());
        } else if (Component.STYLE_Col_TWO.equals(style)){
            // 一行二 链接
        } else if (Component.STYLE_Col_ONE.equals(style)) {
            return buildColOne(component.getComponentList().get(0));
        }else if (Component.STYLE_Slider.equals(style)) {
            // 宽高比 2
            double ratio = 2;
            return buildBanner(component.getComponentList(), ratio, true);
        }else if (Component.STYLE_Slide_LOW.equals(style)) {
            // 窄版Banner
            double ratio = 4;
            return buildBanner(component.getComponentList(), ratio, false);
        }
        return defaultContainer();
    }

    /**
     * 行四
     * @param componentList
     */
    private View buildColFour(List<Component> componentList) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        final int padding = ScreenUtil.dip2px(context, 8);
        linearLayout.setPadding(padding, padding ,padding, padding);
        for (final Component c : componentList) {
            View child = inflater.inflate(R.layout.item_web_app, linearLayout, false);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIJumper.jump(context, c);
                }
            });
            final ImageView imageView = (ImageView) child.findViewById(R.id.home_app_icon);
            int r = ScreenUtil.getScreenWidth() / 8;
            imageView.getLayoutParams().height = r;
            imageView.getLayoutParams().width = r;
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    Glide.with(context).load(c.getIcon()).into(imageView);
                }
            });
            final TextView textView = (TextView) child.findViewById(R.id.home_app_title);
            textView.setText(c.getContent());
            linearLayout.addView(child);
        }
        return linearLayout;
    }

    /**
     * 新贴列表
     */
    private void buildNewsList() {

    }

    /**
     * 一个元素
     * @param component
     * @return
     */
    private View buildColOne(final Component component) {
        FitHeightImageView imageView = new FitHeightImageView(context);
        imageView.setUrl(component.getIcon());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIJumper.jump(v.getContext(), component);
            }
        });
        return imageView;
    }

    /**
     * 最底层节点
     * @param component
     * @return
     */
    private View buildElement(Component component) {
        String type = component.getType();

        return  null;
    }

    private ViewGroup defaultContainer() {
        ViewGroup group = new FrameLayout(context);
        group.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return group;
    }

    /**
     * Banner 根据图片宽高比例，设置Banner高度
     * @return
     */
    private View buildBanner(List<Component> components, double ratio, boolean showTitle){
        BannerView header = new BannerView(context);
        header.showTitle(showTitle);
        int height = (int) ((double)ScreenUtil.getScreenWidth()/ratio);
        header.setLayoutParams(new ViewGroup.LayoutParams(ScreenUtil.getScreenWidth(),height ));
        header.setRefreshLayout(mRefreshLayout);
        List<Banner> datas = new ArrayList<>();
        for (Component component : components) {
            datas.add(Banner.valueOf(component));
        }
        header.initData(Glide.with(context),datas);
        return header;
    }

    public void setRefreshLayout(SwipeRefreshLayout refreshLayout) {
        this.mRefreshLayout = refreshLayout;
    }
}
