package com.appbyme.dev;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sun on 2016/10/14.
 */

public class DemoTest {
    @Test
    public void do_some() {
        String s  = "{\"body\":{\"json\":{\"fid\":525,\"tid\":64550,\"location\":\"北京市海淀区马连洼北路辅路\",\"aid\":\"156869\",\"content\":\"[{\\\"type\\\":0,\\\"infor\\\":\\\"解决\\\"},{\\\"type\\\":1,\\\"infor\\\":\\\"http:\\\\\\/\\\\\\/forum.longquanzs.org\\\\\\/data\\\\\\/attachment\\\\\\/\\\\\\/forum\\\\\\/201610\\\\\\/14\\\\\\/125735kt4goboyr67aorco.jpg\\\"}]\",\"longitude\":\"116.27349090576172\",\"latitude\":\"40.037715911865234\",\"isHidden\":0,\"isAnonymous\":0,\"isOnlyAuthor\":0,\"isShowPostion\":1,\"replyId\":0,\"isQuote\":0}}}";
        boolean result = s.startsWith("");
        Assert.assertTrue(result);
    }

}
