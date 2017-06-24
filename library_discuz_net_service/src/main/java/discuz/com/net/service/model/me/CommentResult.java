package discuz.com.net.service.model.me;

import java.util.ArrayList;

import discuz.com.net.service.model.BaseResult;

/**
 * Created by sun on 2017/6/24.
 */
public class CommentResult extends BaseResult<CommentResult.Body> {


    public class Body {
        public ArrayList<CommentAboutMe> data;

        public class CommentAboutMe {
            public String icon;
            public String replied_date;
            public String user_name;
            public String topic_content;
            public String reply_content;
            public long topic_id;
            public int board_id;
            public int reply_remind_id;
        }


    }
}
