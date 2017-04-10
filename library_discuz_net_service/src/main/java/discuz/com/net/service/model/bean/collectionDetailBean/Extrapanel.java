/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.collectionDetailBean;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-03-28 16:9:34
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Extrapanel {

    private String action;
    private String title;
    private String recommendAdd;
    @JsonProperty("extParams")
    private Extparams extParams;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecommendAdd() {
        return recommendAdd;
    }

    public void setRecommendAdd(String recommendAdd) {
        this.recommendAdd = recommendAdd;
    }

    public Extparams getExtParams() {
        return extParams;
    }

    public void setExtParams(Extparams extParams) {
        this.extParams = extParams;
    }




}