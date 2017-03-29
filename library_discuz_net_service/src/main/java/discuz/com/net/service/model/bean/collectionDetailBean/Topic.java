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
public class Topic {

    @JsonProperty("topic_id")
    private int topic_id;
    private String title;
    private String type;
    private int special;
    @JsonProperty("sortId")
    private int sortId;
    @JsonProperty("user_id")
    private int user_id;
    @JsonProperty("user_nick_name")
    private String user_nick_name;
    private int replies;
    private int hits;
    private int essence;
    private int vote;
    private int hot;
    private int price;
    private int top;
    @JsonProperty("is_favor")
    private int is_favor;
    @JsonProperty("create_date")
    private String create_date;
    private String icon;
    private int level;
    @JsonProperty("userTitle")
    private String userTitle;
    @JsonProperty("userColor")
    private String userColor;
    @JsonProperty("isFollow")
    private int isFollow;
    @JsonProperty("zanList")
    private List<String> zanList;
    @JsonProperty("isTopic")
    private int isTopic;
    private List<Content> content;
    @JsonProperty("poll_info")
    private String poll_info;
    @JsonProperty("activityInfo")
    private String activityInfo;
    private String location;
    @JsonProperty("delThread")
    private boolean delThread;
    @JsonProperty("managePanel")
    private List<ManagePanel> managePanel;
    @JsonProperty("extraPanel")
    private List<Extrapanel> extraPanel;
    @JsonProperty("mobileSign")
    private String mobileSign;
    private int status;
    @JsonProperty("reply_status")
    private int reply_status;
    private int flag;
    private int gender;
    @JsonProperty("reply_posts_id")
    private int reply_posts_id;
    @JsonProperty("rateList")
    private Ratelist rateList;
    private Reward reward;
    @JsonProperty("relateItem")
    private List<String> relateItem;

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_nick_name() {
        return user_nick_name;
    }

    public void setUser_nick_name(String user_nick_name) {
        this.user_nick_name = user_nick_name;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getEssence() {
        return essence;
    }

    public void setEssence(int essence) {
        this.essence = essence;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getIs_favor() {
        return is_favor;
    }

    public void setIs_favor(int is_favor) {
        this.is_favor = is_favor;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
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

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public List<String> getZanList() {
        return zanList;
    }

    public void setZanList(List<String> zanList) {
        this.zanList = zanList;
    }

    public int getIsTopic() {
        return isTopic;
    }

    public void setIsTopic(int isTopic) {
        this.isTopic = isTopic;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public String getPoll_info() {
        return poll_info;
    }

    public void setPoll_info(String poll_info) {
        this.poll_info = poll_info;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getMobileSign() {
        return mobileSign;
    }

    public void setMobileSign(String mobileSign) {
        this.mobileSign = mobileSign;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReply_status() {
        return reply_status;
    }

    public void setReply_status(int reply_status) {
        this.reply_status = reply_status;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getReply_posts_id() {
        return reply_posts_id;
    }

    public void setReply_posts_id(int reply_posts_id) {
        this.reply_posts_id = reply_posts_id;
    }

    public Ratelist getRateList() {
        return rateList;
    }

    public void setRateList(Ratelist rateList) {
        this.rateList = rateList;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public List<String> getRelateItem() {
        return relateItem;
    }

    public void setRelateItem(List<String> relateItem) {
        this.relateItem = relateItem;
    }
}