package com.mobcent.discuz.bean;

import com.mobcent.discuz.api.UrlFactory;

/**
 * Created by sun on 2016/8/23.
 * 版块
 */

public class TopicListLink extends Link {

    private long borardId;
    @Override
    public String getUrl() {
        return UrlFactory.DETAIL_FORUM;
    }

    @Override
    public long getId() {
        return borardId;
    }

    public static TopicListLink valueOf(Component component){

        TopicListLink listLink = new TopicListLink();
        listLink.borardId = component.getExtParams1().getForumId();
        return listLink;
    }

    public long getBorardId() {
        return borardId;
    }
}
