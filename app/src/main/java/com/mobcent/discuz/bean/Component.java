package com.mobcent.discuz.bean;

import java.util.List;

/**
 * Created by sun on 2016/8/21.
 * 论坛组件（首页）
 */

public class Component {
    public static final String STYLE_Slider = "layoutSlider"; // Banner 轮播
    public static final String STYLE_Slide_LOW = "layoutSlider_Low"; // Banner 轮播 矮

    public static final String STYLE_Normal = "layoutDefault";
    public static final String STYLE_Col_FOUR = "layoutFourCol"; // 一行4个
    public static final String STYLE_Col_ONE = "layoutOneCol_Low"; // 一行1个
    public static final String STYLE_Col_TWO = "layoutTwoCol_Low";

    /**
     * 【活动报名】
     */
    public static final String STYLE_NETNEASE_NEWS = "neteaseNews";

    /**
     * 【师傅法语开示】
     *
     */
    public static final String STYLE_NEWS_AUTO = "layoutNewsAuto";

    /**
     * 这个需要取 ExtParam url
     */
    public static final String TYPE_APP = "webapp"; // url 网站
    public static final String TYPE_REF = "moduleRef"; // 模块引用
    /**
     * 版块话题列表
     * url:forum/topiclist
     * forumType = 7, boardId 取Ext .forumId
     */
    public static final String TYPE_TOPIC_LIST = "topiclistSimple";
    /**
     * 话题详情
     * url:forum/postlist
     * forumType = 7,topicId 取 Ext topicId
     * [和尚答疑]
     * [龙泉闻思修]
     */
    public static final String TYPE_POST_LIST = "postlist";

    /**
     * 布局容器，取 style 决定ViewGroup样式，取componentList 填充child views
     */
    public static final String TYPE_LAYOUT = "layout"; // 普通布局

    private String title; // 标题 （
    private String icon; // 图标
    private String style;
    private String type;
    private Object extParams;

    /**
     * @see com.mobcent.discuz.android.constant.StyleConstant
     */
    private String iconStyle;
    private List<Component> componentList;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconStyle() {
        return iconStyle;
    }

    public void setIconStyle(String iconStyle) {
        this.iconStyle = iconStyle;
    }

    public List<Component> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Component> componentList) {
        this.componentList = componentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Object getExtParams() {
        return extParams;
    }

    public void setExtParams(Object extParams) {
        this.extParams = extParams;
    }

    /**
     * 额外参数
     */
    public static class ExtParams {

        /**
         * dataId : 0
         * titlePosition : left
         * newsModuleId : 0
         * forumId : 394
         * moduleId : 10
         * topicId : 61635
         * articleId : 0
         * fastpostForumIds : []
         * isShowTopicTitle : 1
         * isShowMessagelist : 0
         * filterId : 140
         * talkId : 0
         * filter :
         * orderby :
         * order : 0
         * redirect : http://t.qq.com/xuechengfashi
         * listTitleLength : 40
         * listSummaryLength : 0
         * listImagePosition : 0
         * subListStyle :
         * subDetailViewStyle :
         * listBoardNameState : 0
         */

        private int dataId;
        private String titlePosition;
        private int newsModuleId;
        private int forumId;
        private int moduleId;
        private int topicId;
        private int articleId;
        private int isShowTopicTitle;
        private int isShowMessagelist;
        private int filterId;
        private int talkId;
        private String filter;
        private String orderby;
        private int order;
        private String redirect;
        private int listTitleLength;
        private int listSummaryLength;
        private int listImagePosition;
        private String subListStyle;
        private String subDetailViewStyle;
        private int listBoardNameState;
        private List<?> fastpostForumIds;

        public int getDataId() {
            return dataId;
        }

        public void setDataId(int dataId) {
            this.dataId = dataId;
        }

        public String getTitlePosition() {
            return titlePosition;
        }

        public void setTitlePosition(String titlePosition) {
            this.titlePosition = titlePosition;
        }

        public int getNewsModuleId() {
            return newsModuleId;
        }

        public void setNewsModuleId(int newsModuleId) {
            this.newsModuleId = newsModuleId;
        }

        public int getForumId() {
            return forumId;
        }

        public void setForumId(int forumId) {
            this.forumId = forumId;
        }

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public int getIsShowTopicTitle() {
            return isShowTopicTitle;
        }

        public void setIsShowTopicTitle(int isShowTopicTitle) {
            this.isShowTopicTitle = isShowTopicTitle;
        }

        public int getIsShowMessagelist() {
            return isShowMessagelist;
        }

        public void setIsShowMessagelist(int isShowMessagelist) {
            this.isShowMessagelist = isShowMessagelist;
        }

        public int getFilterId() {
            return filterId;
        }

        public void setFilterId(int filterId) {
            this.filterId = filterId;
        }

        public int getTalkId() {
            return talkId;
        }

        public void setTalkId(int talkId) {
            this.talkId = talkId;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public String getOrderby() {
            return orderby;
        }

        public void setOrderby(String orderby) {
            this.orderby = orderby;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getRedirect() {
            return redirect;
        }

        public void setRedirect(String redirect) {
            this.redirect = redirect;
        }

        public int getListTitleLength() {
            return listTitleLength;
        }

        public void setListTitleLength(int listTitleLength) {
            this.listTitleLength = listTitleLength;
        }

        public int getListSummaryLength() {
            return listSummaryLength;
        }

        public void setListSummaryLength(int listSummaryLength) {
            this.listSummaryLength = listSummaryLength;
        }

        public int getListImagePosition() {
            return listImagePosition;
        }

        public void setListImagePosition(int listImagePosition) {
            this.listImagePosition = listImagePosition;
        }

        public String getSubListStyle() {
            return subListStyle;
        }

        public void setSubListStyle(String subListStyle) {
            this.subListStyle = subListStyle;
        }

        public String getSubDetailViewStyle() {
            return subDetailViewStyle;
        }

        public void setSubDetailViewStyle(String subDetailViewStyle) {
            this.subDetailViewStyle = subDetailViewStyle;
        }

        public int getListBoardNameState() {
            return listBoardNameState;
        }

        public void setListBoardNameState(int listBoardNameState) {
            this.listBoardNameState = listBoardNameState;
        }

        public List<?> getFastpostForumIds() {
            return fastpostForumIds;
        }

        public void setFastpostForumIds(List<?> fastpostForumIds) {
            this.fastpostForumIds = fastpostForumIds;
        }
    }

    /**
     * 话题Item
     */
    public static class ExtParams2 {

        /**
         * topicId : 63875
         * special : 0
         * fid : 394
         * board_id : 394
         * board_name : 学佛问答
         * source_type : topic
         * source_id : 63875
         * title : 无分别的智慧——学诚大和尚微博答问合集之佛学篇
         * user_id : 207952
         * last_reply_date : 1471706592000
         * user_nick_name : hai0070
         * hits : 0
         * summary : 此为学诚法师微博答问合集，内容涉及家庭生活、工作学习、为
         * replies : 0
         * pic_path : http://forum.longquanzs.org/data/attachment/forum/201607/02/213914zmsynxqbyoyq50yn.jpg.thumb.jpg
         * ratio : 1.4785992217899
         * redirectUrl :
         * userAvatar : http://forum.longquanzs.org/uc_server/avatar.php?uid=207952&size=middle
         * gender : 2
         * recommendAdd : 0
         * isHasRecommendAdd : 0
         * distance :
         * location :
         * sourceWebUrl : http://forum.longquanzs.org/forum.php?mod=viewthread&tid=63875
         */

        private int topicId;
        private int special;
        private int fid;
        private int board_id;
        private String board_name;
        private String source_type;
        private int source_id;
        private String title;
        private int user_id;
        private String last_reply_date;
        private String user_nick_name;
        private int hits;
        private String summary;
        private int replies;
        private String pic_path;
        private String ratio;
        private String redirectUrl;
        private String userAvatar;
        private int gender;
        private String sourceWebUrl;

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public int getSpecial() {
            return special;
        }

        public void setSpecial(int special) {
            this.special = special;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public int getBoard_id() {
            return board_id;
        }

        public void setBoard_id(int board_id) {
            this.board_id = board_id;
        }

        public String getBoard_name() {
            return board_name;
        }

        public void setBoard_name(String board_name) {
            this.board_name = board_name;
        }

        public String getSource_type() {
            return source_type;
        }

        public void setSource_type(String source_type) {
            this.source_type = source_type;
        }

        public int getSource_id() {
            return source_id;
        }

        public void setSource_id(int source_id) {
            this.source_id = source_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getLast_reply_date() {
            return last_reply_date;
        }

        public void setLast_reply_date(String last_reply_date) {
            this.last_reply_date = last_reply_date;
        }

        public String getUser_nick_name() {
            return user_nick_name;
        }

        public void setUser_nick_name(String user_nick_name) {
            this.user_nick_name = user_nick_name;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getReplies() {
            return replies;
        }

        public void setReplies(int replies) {
            this.replies = replies;
        }

        public String getPic_path() {
            return pic_path;
        }

        public void setPic_path(String pic_path) {
            this.pic_path = pic_path;
        }

        public String getRatio() {
            return ratio;
        }

        public void setRatio(String ratio) {
            this.ratio = ratio;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getSourceWebUrl() {
            return sourceWebUrl;
        }

        public void setSourceWebUrl(String sourceWebUrl) {
            this.sourceWebUrl = sourceWebUrl;
        }
    }
}
