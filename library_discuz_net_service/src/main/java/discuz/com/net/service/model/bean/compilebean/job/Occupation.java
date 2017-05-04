package discuz.com.net.service.model.bean.compilebean.job;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by $Createdbymynameon on 2017/4/30.
 */

public class Occupation {
    private String name;
    private String fieldid;
    private int required;
    private int unchangeable;
    private String description;
    private String type;
    private int size;
    @JsonProperty("nowSet")
    private String nowSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public int getUnchangeable() {
        return unchangeable;
    }

    public void setUnchangeable(int unchangeable) {
        this.unchangeable = unchangeable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getNowSet() {
        return nowSet;
    }

    public void setNowSet(String nowSet) {
        this.nowSet = nowSet;
    }
}
