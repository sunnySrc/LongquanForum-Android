package discuz.com.net.service.model.me;

import java.util.ArrayList;

import discuz.com.net.service.model.BaseResult;

/**
 * Created by sun on 2017/6/24.
 */
public class MessageResult extends BaseResult<MessageResult.Body> {


    public class Body {
        public ArrayList<MyMessage> list;

        public class MyMessage {

            public String toUserName;
            public String lastDateline;
            public String toUserAvatar;
            public String lastSummary;
        }

    }
}
