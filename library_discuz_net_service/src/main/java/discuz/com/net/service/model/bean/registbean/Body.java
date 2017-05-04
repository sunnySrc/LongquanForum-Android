/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.registbean;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-03-16 14:16:36
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Body {

    @JsonProperty("externInfo")
    private Externinfo externInfo;
    public void setExterninfo(Externinfo externinfo) {
         this.externInfo = externinfo;
     }
     public Externinfo getExterninfo() {
         return externInfo;
     }

}