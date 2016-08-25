package com.mobcent.discuz.bean;

/**
 * Created by sun on 2016/8/23.
 * H5页面
 */

public class WebLink extends Link {

    String url;
    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public long getId() {
        return 0;
    }

    public static WebLink valueOf(Component component){
        WebLink link = new WebLink();
        link.url = component.getExtParams1().getRedirect();
        return link;
    }

}
