/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.compilebean;
import java.util.List;

/**
 * Auto-generated: 2017-04-30 20:30:4
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class CompileBean {

    private int rs;
    private String errcode;
    private Head head;
    private Body body;
    private List<Lists> list;
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

    public List<Lists> getList() {
        return list;
    }

    public void setList(List<Lists> list) {
        this.list = list;
    }
}