/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import discuz.com.net.service.model.BaseResult;

/**
 * Auto-generated: 2017-03-09 15:48:46
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class ColoectionBean extends BaseResult {

    private List<CollectionList> list;
    private int page;
    @JsonProperty("has_next")
    private int hasNext;
    @JsonProperty("total_num")
    private int totalNum;

    public void setList(List<CollectionList> list) {
         this.list = list;
     }
     public List<CollectionList> getList() {
         return list;
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