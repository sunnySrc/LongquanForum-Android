/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.searchfriendsinfo;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
/**
 * Auto-generated: 2017-04-19 21:35:37
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class SearchFriendsInfo {

    private int rs;
    private String errcode;
    private Head head;
    private Body body;
    private int flag;
    @JsonProperty("is_black")
    private int is_black;
    @JsonProperty("is_follow")
    private int is_follow;
    @JsonProperty("isFriend")
    private int isFriend;
    private String icon;
    @JsonProperty("level_url")
    private String level_url;
    private String name;
    private String email;
    private int status;
    private int gender;
    private int score;
    private int credits;
    @JsonProperty("gold_num")
    private int gold_num;
    @JsonProperty("topic_num")
    private int topic_num;
    @JsonProperty("photo_num")
    private int photo_num;
    @JsonProperty("reply_posts_num")
    private int reply_posts_num;
    @JsonProperty("essence_num")
    private int essence_num;
    @JsonProperty("friend_num")
    private int friend_num;
    @JsonProperty("follow_num")
    private int follow_num;
    private int level;
    private String sign;
    @JsonProperty("userTitle")
    private String userTitle;
    private List<String> verify;
    private String mobile;
    private List<String> info;

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getIs_black() {
        return is_black;
    }

    public void setIs_black(int is_black) {
        this.is_black = is_black;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLevel_url() {
        return level_url;
    }

    public void setLevel_url(String level_url) {
        this.level_url = level_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getGold_num() {
        return gold_num;
    }

    public void setGold_num(int gold_num) {
        this.gold_num = gold_num;
    }

    public int getTopic_num() {
        return topic_num;
    }

    public void setTopic_num(int topic_num) {
        this.topic_num = topic_num;
    }

    public int getPhoto_num() {
        return photo_num;
    }

    public void setPhoto_num(int photo_num) {
        this.photo_num = photo_num;
    }

    public int getReply_posts_num() {
        return reply_posts_num;
    }

    public void setReply_posts_num(int reply_posts_num) {
        this.reply_posts_num = reply_posts_num;
    }

    public int getEssence_num() {
        return essence_num;
    }

    public void setEssence_num(int essence_num) {
        this.essence_num = essence_num;
    }

    public int getFriend_num() {
        return friend_num;
    }

    public void setFriend_num(int friend_num) {
        this.friend_num = friend_num;
    }

    public int getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(int follow_num) {
        this.follow_num = follow_num;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public List<String> getVerify() {
        return verify;
    }

    public void setVerify(List<String> verify) {
        this.verify = verify;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }
}