package discuz.com.net.service.model.bean.compileBeans.defaults;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 */

public class Defaults {
    private String name;
    private List<DefaultField> field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DefaultField> getField() {
        return field;
    }

    public void setField(List<DefaultField> field) {
        this.field = field;
    }
}
