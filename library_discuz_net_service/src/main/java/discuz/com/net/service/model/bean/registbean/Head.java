/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.registbean;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-03-16 14:16:36
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Head {

    @JsonProperty("errCode")
    private String errcode;
    @JsonProperty("errInfo")
    private String errinfo;
    private String version;
    private int alert;
    public void setErrcode(String errcode) {
         this.errcode = errcode;
     }
     public String getErrcode() {
         return errcode;
     }

    public void setErrinfo(String errinfo) {
         this.errinfo = errinfo;
     }
     public String getErrinfo() {
         return errinfo;
     }

    public void setVersion(String version) {
         this.version = version;
     }
     public String getVersion() {
         return version;
     }

    public void setAlert(int alert) {
         this.alert = alert;
     }
     public int getAlert() {
         return alert;
     }

}