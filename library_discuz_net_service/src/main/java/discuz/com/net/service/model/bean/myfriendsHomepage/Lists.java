/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.myfriendsHomepage;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-04-23 11:23:20
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Lists {

    private String distance;
    private String location;
    @JsonProperty("is_friend")
    private int is_friend;
    @JsonProperty("isFriend")
    private int isFriend;
    @JsonProperty("isFollow")
    private int isFollow;
    private int uid;
    private String name;
    private int status;
    @JsonProperty("is_black")
    private int is_black;
    private int gender;
    private String icon;
    private int level;
    @JsonProperty("userTitle")
    private String userTitle;
    private java.util.List<Verify> verify;
    @JsonProperty("lastLogin")
    private String lastLogin;
    private String dateline;
    private String signature;
    private int credits;


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIs_friend() {
        return is_friend;
    }

    public void setIs_friend(int is_friend) {
        this.is_friend = is_friend;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIs_black() {
        return is_black;
    }

    public void setIs_black(int is_black) {
        this.is_black = is_black;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public java.util.List<Verify> getVerify() {
        return verify;
    }

    public void setVerify(java.util.List<Verify> verify) {
        this.verify = verify;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}