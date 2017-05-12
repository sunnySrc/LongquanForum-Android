/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.block;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-04-17 14:25:5
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Body {

    @JsonProperty("externInfo")
    private Externinfo externInfo;

    public Externinfo getExternInfo() {
        return externInfo;
    }

    public void setExternInfo(Externinfo externInfo) {
        this.externInfo = externInfo;
    }
}