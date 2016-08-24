package com.mobcent.discuz.base;

import android.content.Context;

import com.mobcent.discuz.bean.Component;

/**
 * Created by sun on 2016/8/23.
 * 页面跳转工具类
 */
public class UIJumper {
    public static void jump(Context context, String type, long id) {
        switch (type) {
            case Component.TYPE_POST_LIST:
              jumpTopic(context, id);
                break;
            case Component.TYPE_APP:
                throw new IllegalArgumentException("need url, not id");
            case Component.TYPE_TOPIC_LIST:
                jumpForumSection(context, id);
                break;
        }

    }

    /**
     * 跳转论坛版块
     * @param context
     * @param id
     */
    public static void jumpForumSection(Context context, long id) {
        //TODO
    }

    /**
     * 跳转H5页面
     *
     * @param context
     * @param url
     */
    public static void jumpWebView(Context context, String url) {
//TODO
    }

    /**
     * 跳转话题详情
     *
     * @param context
     * @param id
     */
    public static void jumpTopic(Context context, long id) {
//TODO
    }
}
