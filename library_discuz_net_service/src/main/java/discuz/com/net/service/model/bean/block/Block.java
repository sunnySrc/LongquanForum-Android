/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.block;

/**
 * Auto-generated: 2017-04-17 14:25:5
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 * 拉黑功能实体类
 */
public class Block {

    private int rs;
    private String errcode;
    private Head head;
    private Body body;
    private int uid;
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

    public void setUid(int uid) {
         this.uid = uid;
     }
     public int getUid() {
         return uid;
     }

}