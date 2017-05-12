package discuz.com.net.service.model.bean.compilebean.defaults;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by $Createdbymynameon on 2017/4/30.
 */

public class Header {
    private String name;
    private String fieldid;
    private int required;
    private int unchangeable;
    private String description;
    private String type;
    private int size;
    @JsonProperty("nowSet")
    private String nowSet;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }
    public String getFieldid() {
        return fieldid;
    }

    public void setRequired(int required) {
        this.required = required;
    }
    public int getRequired() {
        return required;
    }

    public void setUnchangeable(int unchangeable) {
        this.unchangeable = unchangeable;
    }
    public int getUnchangeable() {
        return unchangeable;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public String getNowSet() {
        return nowSet;
    }

    public void setNowSet(String nowSet) {
        this.nowSet = nowSet;
    }


}
