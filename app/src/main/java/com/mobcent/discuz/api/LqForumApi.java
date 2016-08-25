package com.mobcent.discuz.api;

import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.fragments.HttpResponseHandler;

/**
 * 龙泉论坛Api
 * Created by sun on 2016/8/21.
 */

public class LqForumApi {

    /**
     * 首页
     * @param handler
     */
    public static DiscuzRequest home(HttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("moduleId", 6);
        DiscuzRequest request = new DiscuzRequest(UrlFactory.MOUDLE_HOME, params.getJsonStr(), handler);
        request.begin();
        return request;
    }

    /**
     * 首页 法语开示
     *
     */
    public static DiscuzRequest moreTopics(long newsModelId, HttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("moduleId", 5);
        DiscuzRequest request = new DiscuzRequest(UrlFactory.MOUDLE_HOME, params.getJsonStr(), handler);
        request.begin();
        return request;
    }
    /**
     * 帖子详情 & 帖子评论
     * page > 1 只有评论列表，不包含topic 内容
     */
    public static DiscuzRequest topicDetail( long topicId, int page, HttpResponseHandler handler) {
        int pageSize = 10;
        RequestParams params = new RequestParams();
        params.add("topicId",topicId);
        params.add("page",page);
        params.add("pageSize",pageSize);
        DiscuzRequest request = new DiscuzRequest(UrlFactory.DETAIL_FORUM, params.getJsonStr(), handler);
        request.begin();
        return request;
    }

}
