package discuz.com.net.service.model.me;

import java.util.ArrayList;

import discuz.com.net.service.model.BaseResult;

/**
 * Created by sun on 2017/6/24.
 */
public class SystemResult extends BaseResult<SystemResult.Body> {


    public class Body {
        public ArrayList<SystemAboutMe> data;

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
    }
}
