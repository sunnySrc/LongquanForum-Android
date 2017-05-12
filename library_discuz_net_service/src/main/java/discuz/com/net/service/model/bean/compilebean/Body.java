/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.compilebean;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-04-30 20:30:4
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