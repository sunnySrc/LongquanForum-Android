/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.registbean;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
/**
 * Auto-generated: 2017-03-16 14:16:36
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class RegistBean {

    private int rs;
    private String errcode;
    private Head head;
    private Body body;
    private String token;
    private String secret;
    private int score;
    private int uid;
    @JsonProperty("userName")
    private String username;
    private String avatar;
    private int gender;
    @JsonProperty("userTitle")
    private String usertitle;
    @JsonProperty("repeatList")
    private List<String> repeatlist;
    private List<String> verify;
    @JsonProperty("creditShowList")
    private List<Creditshowlist> creditshowlist;
    private String mobile;
    private int groupid;
    public void setRs(int rs) {
         this.rs = rs;
     }
     public int getRs() {
         return rs;
     }

    public void setErrcode(String errcode) {
         this.errcode = errcode;
     }
     public String getErrcode() {
         return errcode;
     }

    public void setHead(Head head) {
         this.head = head;
     }
     public Head getHead() {
         return head;
     }

    public void setBody(Body body) {
         this.body = body;
     }
     public Body getBody() {
         return body;
     }

    public void setToken(String token) {
         this.token = token;
     }
     public String getToken() {
         return token;
     }

    public void setSecret(String secret) {
         this.secret = secret;
     }
     public String getSecret() {
         return secret;
     }

    public void setScore(int score) {
         this.score = score;
     }
     public int getScore() {
         return score;
     }

    public void setUid(int uid) {
         this.uid = uid;
     }
     public int getUid() {
         return uid;
     }

    public void setUsername(String username) {
         this.username = username;
     }
     public String getUsername() {
         return username;
     }

    public void setAvatar(String avatar) {
         this.avatar = avatar;
     }
     public String getAvatar() {
         return avatar;
     }

    public void setGender(int gender) {
         this.gender = gender;
     }
     public int getGender() {
         return gender;
     }

    public void setUsertitle(String usertitle) {
         this.usertitle = usertitle;
     }
     public String getUsertitle() {
         return usertitle;
     }

    public void setRepeatlist(List<String> repeatlist) {
         this.repeatlist = repeatlist;
     }
     public List<String> getRepeatlist() {
         return repeatlist;
     }

    public void setVerify(List<String> verify) {
         this.verify = verify;
     }
     public List<String> getVerify() {
         return verify;
     }

    public void setCreditshowlist(List<Creditshowlist> creditshowlist) {
         this.creditshowlist = creditshowlist;
     }
     public List<Creditshowlist> getCreditshowlist() {
         return creditshowlist;
     }

    public void setMobile(String mobile) {
         this.mobile = mobile;
     }
     public String getMobile() {
         return mobile;
     }

    public void setGroupid(int groupid) {
         this.groupid = groupid;
     }
     public int getGroupid() {
         return groupid;
     }

}