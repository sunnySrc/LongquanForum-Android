/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.collectionDetailBean;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;


/**
 * Auto-generated: 2017-03-28 16:9:34
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class ColList {

    @JsonProperty("reply_id")
    private int reply_id;
    @JsonProperty("reply_content")
    private List<ReplyContent> reply_content;
    //@JonProperty("reply_type")
    private String reply_type;
    @JsonProperty("reply_name")
    private String reply_name;
    @JsonProperty("reply_posts_id")
    private int reply_posts_id;
    private int poststick;
    private int position;
    @JsonProperty("posts_date")
    private String posts_date;
    private String icon;
    private int level;
    @JsonProperty("userTitle")
    private String userTitle;
    @JsonProperty("userColor")
    private String userColor;
    private String location;
    @JsonProperty("mobileSign")
    private String mobileSign;
    @JsonProperty("reply_status")
    private int reply_status;
    private int status;
    @JsonProperty("role_num")
    private int role_num;
    private String title;
    private int gender;
    @JsonProperty("is_quote")
    private int is_quote;
    @JsonProperty("quote_pid")
    private int quote_pid;
    @JsonProperty("quote_content")
    private String quote_content;
    @JsonProperty("quote_user_name")
    private String quote_user_name;
    @JsonProperty("delThread")
    private boolean delThread;
    @JsonProperty("managePanel")
    private List<ManagePanel> managePanel;
    @JsonProperty("extraPanel")
    private List<Extrapanel> extraPanel;

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public List<ReplyContent> getReply_content() {
        return reply_content;
    }

    public void setReply_content(List<ReplyContent> reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_type() {
        return reply_type;
    }

    public void setReply_type(String reply_type) {
        this.reply_type = reply_type;
    }

    public String getReply_name() {
        return reply_name;
    }

    public void setReply_name(String reply_name) {
        this.reply_name = reply_name;
    }

    public int getReply_posts_id() {
        return reply_posts_id;
    }

    public void setReply_posts_id(int reply_posts_id) {
        this.reply_posts_id = reply_posts_id;
    }

    public int getPoststick() {
        return poststick;
    }

    public void setPoststick(int poststick) {
        this.poststick = poststick;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPosts_date() {
        return posts_date;
    }

    public void setPosts_date(String posts_date) {
        this.posts_date = posts_date;
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

    public String getUserColor() {
        return userColor;
    }

    public void setUserColor(String userColor) {
        this.userColor = userColor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobileSign() {
        return mobileSign;
    }

    public void setMobileSign(String mobileSign) {
        this.mobileSign = mobileSign;
    }

    public int getReply_status() {
        return reply_status;
    }

    public void setReply_status(int reply_status) {
        this.reply_status = reply_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole_num() {
        return role_num;
    }

    public void setRole_num(int role_num) {
        this.role_num = role_num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getIs_quote() {
        return is_quote;
    }

    public void setIs_quote(int is_quote) {
        this.is_quote = is_quote;
    }

    public int getQuote_pid() {
        return quote_pid;
    }

    public void setQuote_pid(int quote_pid) {
        this.quote_pid = quote_pid;
    }

    public String getQuote_content() {
        return quote_content;
    }

    public void setQuote_content(String quote_content) {
        this.quote_content = quote_content;
    }

    public String getQuote_user_name() {
        return quote_user_name;
    }

    public void setQuote_user_name(String quote_user_name) {
        this.quote_user_name = quote_user_name;
    }

    public boolean isDelThread() {
        return delThread;
    }

    public void setDelThread(boolean delThread) {
        this.delThread = delThread;
    }

    public List<ManagePanel> getManagePanel() {
        return managePanel;
    }

    public void setManagePanel(List<ManagePanel> managePanel) {
        this.managePanel = managePanel;
    }

    public List<Extrapanel> getExtraPanel() {
        return extraPanel;
    }

    public void setExtraPanel(List<Extrapanel> extraPanel) {
        this.extraPanel = extraPanel;
    }
}