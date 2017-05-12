package discuz.com.net.service.model.bean.compilebean.userInfo;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 */

public class UserInfo {
    private String name;
    private List<UserInfoField> field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserInfoField> getField() {
        return field;
    }

    public void setField(List<UserInfoField> field) {
        this.field = field;
    }
}
