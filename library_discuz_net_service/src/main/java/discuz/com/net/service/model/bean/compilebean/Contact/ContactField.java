package discuz.com.net.service.model.bean.compilebean.Contact;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 * 联系方式
 */

public class ContactField {
    private List<Mobile> mobile;
    private List<QQ> qq;
    private List<Email> email;

    public List<Mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }

    public List<QQ> getQq() {
        return qq;
    }

    public void setQq(List<QQ> qq) {
        this.qq = qq;
    }

    public List<Email> getEmail() {
        return email;
    }

    public void setEmail(List<Email> email) {
        this.email = email;
    }
}
