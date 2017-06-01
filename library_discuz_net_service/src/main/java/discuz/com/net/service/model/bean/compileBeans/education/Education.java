package discuz.com.net.service.model.bean.compileBeans.education;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 */

public class Education {
    private String name;
    private List<EducationField> field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EducationField> getField() {
        return field;
    }

    public void setField(List<EducationField> field) {
        this.field = field;
    }
}
