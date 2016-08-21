package com.mobcent.discuz.bean;

import java.util.List;

/**
 * Created by sun on 2016/8/21.
 * 页面模块
 */
public class Module {
    private long id;
    private String title;
    private List<Component> componentList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Component> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Component> componentList) {
        this.componentList = componentList;
    }
}
