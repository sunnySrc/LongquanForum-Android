package discuz.com.net.service.model.me;

import java.util.ArrayList;

/**
 * Created by sun on 2017/5/29.
 */
public class SystemAboutMe {
    public String icon;
    public String note;
    public String user_name;
    public ArrayList<SystemMsgAction> actions;

    public class SystemMsgAction {
        public String redirect;
        public String title;
    }
}
