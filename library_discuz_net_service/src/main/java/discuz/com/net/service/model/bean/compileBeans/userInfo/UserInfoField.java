package discuz.com.net.service.model.bean.compileBeans.userInfo;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 * 个人信息
 */

public class UserInfoField {
    private List<UserInfoFieldNickName> nickname;

    public List<UserInfoFieldNickName> getNickname() {
        return nickname;
    }

    public void setNickname(List<UserInfoFieldNickName> nickname) {
        this.nickname = nickname;
    }
}
