package com.mobcent.discuz.base;

import com.mobcent.discuz.activity.LoginUtils;

import java.util.HashMap;

import discuz.com.net.service.config.WebParamsKey;
import discuz.com.net.service.config.WebParamsValue;

/**
 * Created by pangxiaomin on 16/11/27.
 */
public class WebParamsMap {

    public static HashMap<String,String> map(){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.egnVersion, WebParamsValue.egnVersion);
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put(WebParamsKey.sdkType, WebParamsValue.sdkType);
        map.put(WebParamsKey.packageName, WebParamsValue.packageName);

        map.put(WebParamsKey.forumKey, WebParamsValue.forumKey);
        map.put(WebParamsKey.accessSecret,  LoginUtils.getInstance().getAccessSecret());
        map.put(WebParamsKey.accessToken, LoginUtils.getInstance().getAccessToken());
        return map;
    }

    //用户发表
    public static HashMap<String,String> user_public(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, WebParamsValue.packageName);
        map.put("forumType", "7");
        map.put("longitude", "117");
        map.put("pageSize", "20");
        map.put(WebParamsKey.accessToken,"b8c746f3a931e0d0ffdbcc76c6360");
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put("isImageList", "1");
        map.put(WebParamsKey.egnVersion, "v2091.5");
        map.put(WebParamsKey.accessSecret,  "6e9f2606bed4b530dcb58ff210299");
        map.put("sdkVersion",  "2.5.0.0");
        map.put("imei",  "868029029800109");
        map.put("apphash",  "83837a08");
        map.put("latitude",  "40");
        map.put("uid", uid);
        map.put(WebParamsKey.forumKey, WebParamsValue.forumKey);
        map.put("type", "topic");
        map.put("page", "1");
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        return map;
    }

    public static HashMap<String,String> maps(int uid){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, "com.appbyme.app178470");
        map.put("forumType", "7");
        map.put("pageSize", "20");
        map.put(WebParamsKey.accessToken, "b8c746f3a931e0d0ffdbcc76c6360");
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put(WebParamsKey.egnVersion, "v2035.2");
        map.put(WebParamsKey.accessSecret,  "6e9f2606bed4b530dcb58ff210299");
        map.put("sdkVersion", "2.4.3.0");
        map.put("imei", "868029029800109");
        map.put("apphash", "d32cf2e2");
        map.put("uid", "222436");
        map.put(WebParamsKey.forumKey,"BW0L5ISVRsOTVLCTJx");
        map.put("type", "favorite");
        map.put("page", "1");
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        return map;
    }
    //注册
    public static HashMap<String,String> regist_map(String name,String pwd,String email) {
        HashMap<String, String> map = new HashMap<>();

        map.put(WebParamsKey.forumKey, "BW0L5ISVRsOTVLCTJx");
        map.put("isValidation","1");
        map.put("accessSecret","");
        map.put("accessToken","");

        map.put("sdkVersion", "2.4.0");
        map.put("username", name);
        map.put("password", pwd);
        map.put("email", email);
        map.put("apphash", "44c00ca2");
        map.put("forumType", "7");
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        map.put("egnVersion", "v2035.2");
        map.put("fastRegister", "0");
        map.put("packageName", "com.appbyme.app178470");
        return map;
    }
    //我的好友
    public static HashMap<String,String> map_friend(String name,String page){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, "com.appbyme.app178470");
        map.put("searchid", "0");
        map.put("pageSize", "10");
        map.put(WebParamsKey.accessToken, "b8c746f3a931e0d0ffdbcc76c6360");
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put(WebParamsKey.egnVersion, "v2035.2");
        map.put(WebParamsKey.accessSecret,  "6e9f2606bed4b530dcb58ff210299");
        map.put("sdkVersion", "2.4.3.0");
        map.put("imei", "868029029800109");
        map.put("apphash", "38b69752");
        map.put("keyword", name);
        map.put(WebParamsKey.forumKey,"BW0L5ISVRsOTVLCTJx");
        map.put("page", page);
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        map.put("forumType", "7");
        return map;
    }
    //我的好友详情页
    public static HashMap<String,String> userinfo(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, WebParamsValue.packageName);
        map.put("forumType", "7");
        map.put("userId", uid);
        map.put(WebParamsKey.accessToken, "b8c746f3a931e0d0ffdbcc76c6360");
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put(WebParamsKey.egnVersion, WebParamsValue.egnVersion);
        map.put(WebParamsKey.accessSecret,"6e9f2606bed4b530dcb58ff210299");
        map.put("sdkVersion", "2.4.3.0");
        map.put("imei", "868029029800109");
        map.put("apphash", "5bd6ff26");
        map.put(WebParamsKey.forumKey, WebParamsValue.forumKey);
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        return map;
    }

    //拉黑
    public static HashMap<String,String> block(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, "com.appbyme.app178470");
        map.put("forumType", "7");
        map.put(WebParamsKey.accessToken, "b8c746f3a931e0d0ffdbcc76c6360");
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put(WebParamsKey.egnVersion, "v2091.5");
        map.put(WebParamsKey.accessSecret,  "6e9f2606bed4b530dcb58ff210299");
        map.put("sdkVersion", "2.5.0.0");
        map.put("imei", "868029029800109");
        map.put("apphash", "ab970d53");
        map.put("uid", "196601");
        map.put(WebParamsKey.forumKey,"BW0L5ISVRsOTVLCTJx");
        map.put("type", "black");
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        return map;
    }

    //取消拉黑
    public static HashMap<String,String> blockcancle(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, "com.appbyme.app178470");
        map.put("forumType", "7");
        map.put(WebParamsKey.accessToken, "b8c746f3a931e0d0ffdbcc76c6360");
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put(WebParamsKey.egnVersion, "v2091.5");
        map.put(WebParamsKey.accessSecret,  "6e9f2606bed4b530dcb58ff210299");
        map.put("sdkVersion", "2.5.0.0");
        map.put("imei", "868029029800109");
        map.put("apphash", "ab970d53");
        map.put("uid", "196601");
        map.put(WebParamsKey.forumKey,"BW0L5ISVRsOTVLCTJx");
        map.put("type", "delblack");
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        return map;
    }

    //举报
    public static HashMap<String,String> report(String id,String message){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, "com.appbyme.app178470");
        map.put("forumType", "7");
        map.put(WebParamsKey.accessToken, "b8c746f3a931e0d0ffdbcc76c6360");
        map.put("idType", "user");
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put(WebParamsKey.egnVersion, "v2091.5");
        map.put(WebParamsKey.accessSecret,  "6e9f2606bed4b530dcb58ff210299");
        map.put("sdkVersion", "2.5.0.0");
        map.put("imei", "868029029800109");
        map.put("id", id);
        map.put("apphash", "ab970d53");
        map.put("message", message);
        map.put(WebParamsKey.forumKey,"BW0L5ISVRsOTVLCTJx");
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        return map;
    }

}
