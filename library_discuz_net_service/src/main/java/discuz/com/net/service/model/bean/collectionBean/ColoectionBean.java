/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.collectionBean;


import org.codehaus.jackson.annotate.JsonProperty;

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
    private int has_Next;
    @JsonProperty("total_num")
    private int total_Num;

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
         this.has_Next = hasNext;
     }
     public int getHasNext() {
         return has_Next;
     }

    public void setTotalNum(int totalNum) {
         this.total_Num = totalNum;
     }
     public int getTotalNum() {
         return total_Num;
     }

}