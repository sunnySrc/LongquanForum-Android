package discuz.com.net.service.model.bean.compilebean.job;

import java.util.List;

/**
 * Created by $Createdbymynameon on 2017/5/1.
 */

public class Job {
    private String name;
    private List<JobField> field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JobField> getField() {
        return field;
    }

    public void setField(List<JobField> field) {
        this.field = field;
    }
}
