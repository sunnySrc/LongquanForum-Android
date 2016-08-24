package com.mobcent.discuz.bean;

/**
 * Created by sun on 2016/8/23.
 * 首页轮播 Banner
 */
public class Banner {
    private String type;
    private long id;
    private String name;
    private String img;
    private boolean showTitle;

    public static Banner valueOf(Component component) {
        if (component.hasChildComponent())
            throw new IllegalArgumentException("Component has children!");

        Banner banner = new Banner();
        banner.img = component.getIcon();
        banner.name = component.getDesc();
        banner.type = component.getType();
        banner.id = component.getExtParams1().getTopicId();
        banner.showTitle = component.getExtParams1().getIsShowTopicTitle() == 1;
        return banner;
    }


    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public boolean isShowTitle() {
        return showTitle;
    }
}
