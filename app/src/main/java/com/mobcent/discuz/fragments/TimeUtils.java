package com.mobcent.discuz.fragments;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ubuntu on 16-9-6.
 */
public class TimeUtils {

    public static String getTimeText(String reply) {
        String timeText;
        double last_reply_date = Double.parseDouble(reply);
        long current = new Date().getTime();
        int interval = (int)((current - last_reply_date) / 3600 / 1000);
        if (interval < 24) {
            timeText = interval + "小时前";
        } else {
            interval = interval / 24;
            if (interval < 30) {
                timeText = interval + "天前";
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                timeText = format.format(last_reply_date);
            }
        }
        return timeText;
    }
}
