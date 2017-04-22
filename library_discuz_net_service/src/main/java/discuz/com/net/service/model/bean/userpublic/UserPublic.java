/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.userpublic;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
/**
 * Auto-generated: 2017-04-22 13:54:43
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class UserPublic {

    private int rs;
    private String errcode;
    private Head head;
    private Body body;
    private List<String> list;
    private int page;
    @JsonProperty("has_next")
    private int has_next;
    @JsonProperty("total_num")
    private int total_num;

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

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public int getTotal_num() {
        return total_num;
    }

    public void setTotal_num(int total_num) {
        this.total_num = total_num;
    }
}