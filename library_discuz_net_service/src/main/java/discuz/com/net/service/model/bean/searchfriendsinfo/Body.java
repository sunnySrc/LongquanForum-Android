/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.searchfriendsinfo;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
/**
 * Auto-generated: 2017-04-19 21:35:37
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Body {

    @JsonProperty("externInfo")
    private Externinfo externInfo;
    @JsonProperty("repeatList")
    private List<String> repeatList;
    @JsonProperty("profileList")
    private List<Profilelist> profileList;
    @JsonProperty("creditList")
    private List<Creditlist> creditList;
    @JsonProperty("creditShowList")
    private List<Creditshowlist> creditShowList;

    public Externinfo getExternInfo() {
        return externInfo;
    }

    public void setExternInfo(Externinfo externInfo) {
        this.externInfo = externInfo;
    }

    public List<String> getRepeatList() {
        return repeatList;
    }

    public void setRepeatList(List<String> repeatList) {
        this.repeatList = repeatList;
    }

    public List<Profilelist> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Profilelist> profileList) {
        this.profileList = profileList;
    }

    public List<Creditlist> getCreditList() {
        return creditList;
    }

    public void setCreditList(List<Creditlist> creditList) {
        this.creditList = creditList;
    }

    public List<Creditshowlist> getCreditShowList() {
        return creditShowList;
    }

    public void setCreditShowList(List<Creditshowlist> creditShowList) {
        this.creditShowList = creditShowList;
    }
}