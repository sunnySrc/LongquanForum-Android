package com.mobcent.discuz.bean;

/**
 * Created by sun on 2016/8/29.
 * 帖子内容元素
 */

public class TopicContent {
    public static final int TYPE_TEXT = 0; // 普通文本
    public static final int TYPE_IMAGE = 1; // png, jpg,gif 图片
    public static final int TYPE_VIDEO = 2; //视频
    public static final int TYPE_AUDIO = 3; //音频
    public static final int TYPE_AT = 4; // @用户

    private static final String VIDEO_UNKOWN = "unkown"; // 这个直接使用第三方 或 浏览器打开

    /**
     * infor : http://player.youku.com/embed/XMTI4Nzg4MDcyOA
     * type : 2
     * url : http://forum.longquanzs.org/home.php?mod=space&uid=214362
     * originalInfo : http://forum.longquanzs.org/data/attachment/forum/201608/28/143728t5oqd5qwd3m3uvkh.png
     * aid : 0
     * extParams : {"videoType":"youku","videoId":""}
     */

    private String infor;
    private int type;
    /**
     * 其它帖子
     * url=http://forum.longquanzs.org/forum.php?mod=viewthread&tid=50794
     *
     */
    private String url;
    private String originalInfo;
    private int aid;
    /**
     * videoType : youku
     * videoId :
     */

    private ExtParamsBean extParams;

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginalInfo() {
        return originalInfo;
    }

    public void setOriginalInfo(String originalInfo) {
        this.originalInfo = originalInfo;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    /**
     * 视频来源未知
     * @return
     */
    public boolean isVideoUnkown() {
        if (extParams != null && type == TYPE_VIDEO) {
            return VIDEO_UNKOWN.equalsIgnoreCase(extParams.getVideoType());
        }
        return true;
    }


    public ExtParamsBean getExtParams() {
        return extParams;
    }

    public void setExtParams(ExtParamsBean extParams) {
        this.extParams = extParams;
    }

    public static class ExtParamsBean {

        private String videoType;
        private String videoId;

        public String getVideoType() {
            return videoType;
        }

        public void setVideoType(String videoType) {
            this.videoType = videoType;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }
    }
}
