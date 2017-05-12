/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.MyFriend;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Auto-generated: 2017-04-07 16:13:27
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Body {

    @JsonProperty("externInfo")
    private Externinfo externInfo;
    private java.util.List<Lists> list;

    public Externinfo getExternInfo() {
        return externInfo;
    }

    public void setExternInfo(Externinfo externInfo) {
        this.externInfo = externInfo;
    }

    public java.util.List<Lists> getList() {
        return list;
    }

    public void setList(java.util.List<Lists> list) {
        this.list = list;
    }
}