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
public class Extparams {

    @JsonProperty("beforeAction")
    private String beforeAction;
    private String recommendAdd;
    private String isHasRecommendAdd;

    public String getBeforeAction() {
        return beforeAction;
    }

    public void setBeforeAction(String beforeAction) {
        this.beforeAction = beforeAction;
    }

    public String getRecommendAdd() {
        return recommendAdd;
    }

    public void setRecommendAdd(String recommendAdd) {
        this.recommendAdd = recommendAdd;
    }

    public String getIsHasRecommendAdd() {
        return isHasRecommendAdd;
    }

    public void setIsHasRecommendAdd(String isHasRecommendAdd) {
        this.isHasRecommendAdd = isHasRecommendAdd;
    }
}