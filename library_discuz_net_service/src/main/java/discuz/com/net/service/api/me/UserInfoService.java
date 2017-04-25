package discuz.com.net.service.api.me;

import java.util.Map;

import discuz.com.net.service.model.bean.MyFriend.MyFriends;
import discuz.com.net.service.model.bean.block.Block;
import discuz.com.net.service.model.bean.collectionBean.ColoectionBean;
import discuz.com.net.service.model.bean.myfriendsHomepage.MyfriendsHomepage;
import discuz.com.net.service.model.bean.searchfriendsinfo.*;
import discuz.com.net.service.model.bean.registbean.RegistBean;
import discuz.com.net.service.model.bean.userpublic.UserPublic;
import discuz.com.net.service.model.me.PublishResult;
import discuz.com.net.service.model.me.UserResult;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by pangxiaomin on 16/11/27.
 */
public interface UserInfoService {

    /**获取用户资料*/
    @FormUrlEncoded
    @POST("index.php?r=user/userinfo")
    Observable<UserResult> requestUserInfo(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**获取用户发表*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<PublishResult> requestUserPublish(@FieldMap Map<String,String> params);

    /**获取(查询)用户发表*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<UserPublic> requestUserPublishs(@FieldMap Map<String,String> params);

    /**获取用户收藏*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<ColoectionBean> requestUserCollection(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**注册*/
    @FormUrlEncoded
    @POST("index.php?r=user/register")
    Observable<RegistBean> userregist(@FieldMap Map<String,String> params);

    /**获取我的好友*/
    @FormUrlEncoded
    @POST("index.php?r=user/searchuser")
    Observable<MyFriends> requestUserMyFriends(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**拉黑*/
    @FormUrlEncoded
    @POST("index.php?r=user/useradmin")
    Observable<Block> block(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**取消拉黑*/
    @FormUrlEncoded
    @POST("index.php?r=user/useradmin")
    Observable<Block> blockcancle(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**举报*/
    @FormUrlEncoded
    @POST("index.php?r=user/report")
    Observable<Block> report(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**获取我的好友详情(资料)*/
    @FormUrlEncoded
    @POST("index.php?r=user/userinfo")
    Observable<SearchFriendsInfo> friendsinfo(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**我的好友(我的-我的好友页面)*/
    @FormUrlEncoded
    @POST("index.php?r=user/userlist")
    Observable<MyfriendsHomepage> friendList( @FieldMap Map<String,String> params);
}
