/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.MyFriend;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-04-07 16:13:27
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class MyFriends {

    private int rs;
    private String errcode;
    private Head head;
    private Body body;
    private int searchid;
    private int page;
    @JsonProperty("has_next")
    private int hasNext;
    @JsonProperty("total_num")
    private int totalNum;
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

    public void setSearchid(int searchid) {
         this.searchid = searchid;
     }
     public int getSearchid() {
         return searchid;
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

}