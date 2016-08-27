package com.mobcent.discuz.base;

import android.content.Context;
import android.util.Log;

import com.mobcent.discuz.api.UrlFactory;
import com.mobcent.discuz.bean.Component;

/**
 * Created by sun on 2016/8/23.
 * 页面跳转工具类
 */
public class UIJumper {
    private static final String TAG = UIJumper.class.getSimpleName();

    public static void jump(Context context, Component component) {
        final String type = component.getType();
        long id = component.getTargetId();
        Log.d(TAG, "jump:" + type + "-" + id);
        switch (type) {
            case Component.TYPE_POST_LIST:
                id = component.getTargetId();
                jumpTopic(context, id);
                break;
            case Component.TYPE_APP:
                jumpWebView(context, component.getExtParams1().getRedirect());
            case Component.TYPE_TOPIC_LIST:
                jumpForumSection(context, id);
                break;
            case Component.TYPE_NEWS_LIST:
                jumpNewsList(context, id);
                break;
            case Component.TYPE_REF:
                jumpModuleRef(context, id);
                break;
            default:
                break;
        }
    }

    private static void jumpModuleRef(Context context, long id) {
        String url = UrlFactory.MODULE_CONFIG;
    }

    public static void jump(Context context, String type, long id, String urlRef) {
        Log.d(TAG, "jump:" + type + "-" + id);
        switch (type) {
            case Component.TYPE_POST_LIST:
                jumpTopic(context, id);
                break;
            case Component.TYPE_APP:
                jumpWebView(context, urlRef);
            case Component.TYPE_TOPIC_LIST:
                jumpForumSection(context, id);
                break;
            case Component.TYPE_NEWS_LIST:
                jumpNewsList(context, id);
                break;
            case Component.TYPE_REF:
                jumpModuleRef(context, id);
        }
    }

    /**
     *  帖子列表
     * @param context
     * @param id
     */
    private static void jumpNewsList(Context context, long id) {
        String url = UrlFactory.NEWS_LIST;
    }

    /**
     * 跳转论坛版块
     * @param context
     * @param id
     */
    public static void jumpForumSection(Context context, long id) {
        //TODO
        String url = UrlFactory.TOPIC_LIST;
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
        String url = UrlFactory.DETAIL_FORUM;
    }
}
