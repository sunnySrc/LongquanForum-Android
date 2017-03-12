/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Auto-generated: 2017-03-09 15:48:46
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Body {

    @JsonProperty("externInfo")
    private Externinfo externinfo;
    public void setExterninfo(Externinfo externinfo) {
         this.externinfo = externinfo;
     }
     public Externinfo getExterninfo() {
         return externinfo;
     }

}