package com.mobcent.discuz.bean.manager;

import com.mobcent.discuz.bean.Component;
import com.mobcent.discuz.bean.Link;
import com.mobcent.discuz.bean.TopicLink;
import com.mobcent.discuz.bean.TopicListLink;
import com.mobcent.discuz.bean.WebLink;

/**
 * Created by sun on 2016/8/21.
 * 论坛组件 跳转
 */
public class ComponentLink {
    private final Component component;

    public ComponentLink(Component component) {
        this.component = component;
    }

    public Link buildLink(){
        Link link = null;
        switch (getType()) {
            case Component.TYPE_POST_LIST:
                link = TopicLink.valueOf(component);
                break;
            case Component.TYPE_APP:
                link = WebLink.valueOf(component);
                break;
            case Component.TYPE_TOPIC_LIST:
                link = TopicListLink.valueOf(component);
                break;
        }
        return link;
    }
    private String getType() {
        return  component.getType();
    }



}
