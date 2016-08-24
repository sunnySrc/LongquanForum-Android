package com.mobcent.discuz.bean;

import com.mobcent.discuz.api.UrlFactory;

/**
 * Created by sun on 2016/8/23.
 */

public class TopicLink extends Link {

    private long topicId;

    @Override
    public String getUrl() {
        return UrlFactory.TOPIC_LIST;
    }

    @Override
    public long getId() {
        return topicId;
    }

    public static TopicLink valueOf(Component component){
        TopicLink link = new TopicLink();
        link.topicId = component.getExtParams1().getTopicId();
        return link;
    }
}
