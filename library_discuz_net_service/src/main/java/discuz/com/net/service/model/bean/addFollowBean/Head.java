/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.addFollowBean;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-05-13 13:51:12
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Head {

    @JsonProperty("errCode")
    private String errCode;
    @JsonProperty("errInfo")
    private String errInfo;
    private String version;
    private int alert;
    public void setErrcode(String errcode) {
         this.errCode = errcode;
     }
     public String getErrcode() {
         return errCode;
     }

    public void setErrinfo(String errinfo) {
         this.errInfo = errinfo;
     }
     public String getErrinfo() {
         return errInfo;
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