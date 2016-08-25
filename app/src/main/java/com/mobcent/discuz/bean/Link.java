package com.mobcent.discuz.bean;

import java.io.Serializable;

/**
 * Created by sun on 2016/8/23.
 * 跳转信息，提供给跳转页面使用
 */

public abstract class Link implements Serializable{
    public abstract String getUrl();
    public abstract long getId();

}
