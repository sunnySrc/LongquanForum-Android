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
public class CollectionDetailBean {

    private int rs;
    private String errcode;
    private Head head;
    private Body body;
    private Topic topic;
    private int page;
    @JsonProperty("has_next")
    private int hasNext;
    @JsonProperty("total_num")
    private int totalNum;
    private List<ColList> list;
    @JsonProperty("forumName")
    private String forumname;
    @JsonProperty("boardId")
    private int boardid;
    @JsonProperty("forumTopicUrl")
    private String forumtopicurl;
    @JsonProperty("img_url")
    private String imgUrl;
    @JsonProperty("icon_url")
    private String iconUrl;

    public List<ColList> getList() {
        return list;
    }

    public void setList(List<ColList> list) {
        this.list = list;
    }

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

    public void setTopic(Topic topic) {
         this.topic = topic;
     }
     public Topic getTopic() {
         return topic;
     }

    public void setPage(int page) {
         this.page = page;
     }
     public int getPage() {
         return page;
     }

    public void setHasNext(int hasNext) {
         this.hasNext = hasNext;
     }
     public int getHasNext() {
         return hasNext;
     }

    public void setTotalNum(int totalNum) {
         this.totalNum = totalNum;
     }
     public int getTotalNum() {
         return totalNum;
     }


    public void setForumname(String forumname) {
         this.forumname = forumname;
     }
     public String getForumname() {
         return forumname;
     }

    public void setBoardid(int boardid) {
         this.boardid = boardid;
     }
     public int getBoardid() {
         return boardid;
     }

    public void setForumtopicurl(String forumtopicurl) {
         this.forumtopicurl = forumtopicurl;
     }
     public String getForumtopicurl() {
         return forumtopicurl;
     }

    public void setImgUrl(String imgUrl) {
         this.imgUrl = imgUrl;
     }
     public String getImgUrl() {
         return imgUrl;
     }

    public void setIconUrl(String iconUrl) {
         this.iconUrl = iconUrl;
     }
     public String getIconUrl() {
         return iconUrl;
     }

}