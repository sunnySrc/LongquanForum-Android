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

    //我的-我的收藏
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
    //我的-我的好友
    public static HashMap<String,String> map_friend(String keyword){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, "com.appbyme.app178470");//
        map.put("forumType", "7");//
        map.put("searchid", "0");//
        map.put("pageSize", "10");//
        map.put(WebParamsKey.accessToken, "b8c746f3a931e0d0ffdbcc76c6360");//
        map.put(WebParamsKey.appName, WebParamsValue.appName);//
        map.put(WebParamsKey.egnVersion, "v2035.2");//
        map.put(WebParamsKey.accessSecret,  "6e9f2606bed4b530dcb58ff210299");//
        map.put("sdkVersion", "2.4.3.0");//
        map.put("imei", "868029029800109");//
        map.put("apphash", "38b69752");//
        //map.put("uid", "222436");
        map.put(WebParamsKey.forumKey,"BW0L5ISVRsOTVLCTJx");//
        //map.put("type", "favorite");
        map.put("page", "1");//
        map.put("platType", "1");//
        map.put("imsi", "460001001651621");//
        map.put("keyword", "我");//
        return map;
    }
}
