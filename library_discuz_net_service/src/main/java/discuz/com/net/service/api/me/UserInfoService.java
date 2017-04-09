package discuz.com.net.service.api.me;

import java.util.Map;

import discuz.com.net.service.model.bean.collectionBean.ColoectionBean;
import discuz.com.net.service.model.bean.registbean.RegistBean;
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

    /**获取用户收藏*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<ColoectionBean> requestUserCollection(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**注册*/
    @FormUrlEncoded
    @POST("index.php?r=user/register")
    Observable<RegistBean> userregist(@FieldMap Map<String,String> params);
}
