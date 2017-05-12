package discuz.com.net.service.model.bean.compilebean.Contact;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 */

public class Contact {
    private String name;
    private List<ContactField> field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContactField> getField() {
        return field;
    }

    public void setField(List<ContactField> field) {
        this.field = field;
    }
}
