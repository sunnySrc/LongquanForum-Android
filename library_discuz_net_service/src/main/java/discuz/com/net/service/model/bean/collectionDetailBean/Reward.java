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
public class Reward {

    private List<Score> score;
    @JsonProperty("userList")
    private List<Userlist> userList;
    @JsonProperty("userNumber")
    private int userNumber;
    @JsonProperty("showAllUrl")
    private String showAllUrl;

    public String getShowAllUrl() {
        return showAllUrl;
    }

    public void setShowAllUrl(String showAllUrl) {
        this.showAllUrl = showAllUrl;
    }

    public List<Score> getScore() {
        return score;
    }

    public void setScore(List<Score> score) {
        this.score = score;
    }

    public List<Userlist> getUserList() {
        return userList;
    }

    public void setUserList(List<Userlist> userList) {
        this.userList = userList;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

}