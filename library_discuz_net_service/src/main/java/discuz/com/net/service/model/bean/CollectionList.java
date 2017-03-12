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
public class CollectionList {

    @JsonProperty("pic_path")
    private String picPath;
    @JsonProperty("board_id")
    private int boardId;
    @JsonProperty("board_name")
    private String boardName;
    @JsonProperty("topic_id")
    private int topicId;
    @JsonProperty("type_id")
    private int typeId;
    @JsonProperty("sort_id")
    private int sortId;
    private String title;
    private String subject;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("last_reply_date")
    private String lastReplyDate;
    @JsonProperty("user_nick_name")
    private String userNickName;
    private int hits;
    private int replies;
    private int top;
    private int status;
    private int essence;
    private int hot;
    @JsonProperty("userAvatar")
    private String useravatar;
    private int special;
    public void setPicPath(String picPath) {
         this.picPath = picPath;
     }
     public String getPicPath() {
         return picPath;
     }

    public void setBoardId(int boardId) {
         this.boardId = boardId;
     }
     public int getBoardId() {
         return boardId;
     }

    public void setBoardName(String boardName) {
         this.boardName = boardName;
     }
     public String getBoardName() {
         return boardName;
     }

    public void setTopicId(int topicId) {
         this.topicId = topicId;
     }
     public int getTopicId() {
         return topicId;
     }

    public void setTypeId(int typeId) {
         this.typeId = typeId;
     }
     public int getTypeId() {
         return typeId;
     }

    public void setSortId(int sortId) {
         this.sortId = sortId;
     }
     public int getSortId() {
         return sortId;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setSubject(String subject) {
         this.subject = subject;
     }
     public String getSubject() {
         return subject;
     }

    public void setUserId(int userId) {
         this.userId = userId;
     }
     public int getUserId() {
         return userId;
     }

    public void setLastReplyDate(String lastReplyDate) {
         this.lastReplyDate = lastReplyDate;
     }
     public String getLastReplyDate() {
         return lastReplyDate;
     }

    public void setUserNickName(String userNickName) {
         this.userNickName = userNickName;
     }
     public String getUserNickName() {
         return userNickName;
     }

    public void setHits(int hits) {
         this.hits = hits;
     }
     public int getHits() {
         return hits;
     }

    public void setReplies(int replies) {
         this.replies = replies;
     }
     public int getReplies() {
         return replies;
     }

    public void setTop(int top) {
         this.top = top;
     }
     public int getTop() {
         return top;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setEssence(int essence) {
         this.essence = essence;
     }
     public int getEssence() {
         return essence;
     }

    public void setHot(int hot) {
         this.hot = hot;
     }
     public int getHot() {
         return hot;
     }

    public void setUseravatar(String useravatar) {
         this.useravatar = useravatar;
     }
     public String getUseravatar() {
         return useravatar;
     }

    public void setSpecial(int special) {
         this.special = special;
     }
     public int getSpecial() {
         return special;
     }

}