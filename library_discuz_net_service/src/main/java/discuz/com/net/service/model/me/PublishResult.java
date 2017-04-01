package discuz.com.net.service.model.me;


import java.util.ArrayList;
import java.util.List;

import discuz.com.bean.me.Publish;
import discuz.com.bean.me.UserBodyList;
import discuz.com.net.service.model.BaseResult;

/**
 * 我-个人-发表的接口返回数据
 * @author 张春生
 */
public class PublishResult extends BaseResult {

    public ArrayList<Publish> list;
    public int page;
    public int has_next;
    public int total_num;
}
