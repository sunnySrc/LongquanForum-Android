package com.mobcent.discuz.api;

import android.util.Log;

import com.mobcent.common.JsonConverter;
import com.mobcent.discuz.base.constant.DiscuzRequest;
import com.mobcent.discuz.bean.Reply;
import com.mobcent.discuz.fragments.HttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.appbyme.dev.R.id.page;
import static com.litesuits.android.async.AsyncExecutor.handler;

/**
 * 龙泉论坛Api
 * Created by sun on 2016/8/21.
 */

public class LqForumApi {

    public static final int PAGE_SIZE_TOPIC_REPLY = 10; // 帖子详情的回帖页
    /**
     * 首页
     * @param handler
     */
    public static DiscuzRequest home(HttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("moduleId", 6);
        DiscuzRequest request = new DiscuzRequest(UrlFactory.MODULE_CONFIG, params.getJsonStr(), handler);
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
        DiscuzRequest request = new DiscuzRequest(UrlFactory.MODULE_CONFIG, params.getJsonStr(), handler);
        request.begin();
        return request;
    }

    /**
     * 帖子详情 & 帖子评论
     * page > 1 只有评论列表，不包含topic 内容
     */
    public static DiscuzRequest topicDetail( long topicId, int page, HttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("topicId",topicId);
        params.add("page",page);
        params.add("pageSize", PAGE_SIZE_TOPIC_REPLY);
        DiscuzRequest request = new DiscuzRequest(UrlFactory.DETAIL_FORUM, params.getJsonStr(), handler);
        request.begin();
        return request;
    }

    /**
     * 关注TA
     * @param userId
     * @param handler
     */
    public static DiscuzRequest followUser(boolean follow, long userId, HttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.add("uid",userId);
        params.add("type", follow ? "follow" : "unfollow");
        DiscuzRequest request = new DiscuzRequest(UrlFactory.USER_ADMIN, params.getJsonStr(), handler);
        request.begin();
        return request;
    }

    public static DiscuzRequest reply(Reply reply, HttpResponseHandler handler) {
        String json = JsonConverter.toString(reply);
        RequestParams params = new RequestParams();
        params.add("act",  "reply");
        try {
            params.add("json", URLEncoder.encode(json, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("API", "reply json :" + json);
        DiscuzRequest request = new DiscuzRequest(UrlFactory.TOPIC_ADMIN, params.getJsonStr(), handler);
        request.begin();
        return request;
    }
}
