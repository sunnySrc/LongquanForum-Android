package com.mobcent.discuz.bean;

/**
 * Created by sun on 2016/8/21.
 */

public class StyleHeader {

    /**
     * isShow : 1
     * title : 师父法语开示
     * position : 1
     * isShowMore : 1
     * moreComponent : {"px":0,"type":"newslist","style":"neteaseNews","icon":"","iconStyle":"image","title":"","desc":""}
     */

    private int isShow;
    private String title;
    private int position;
    private int isShowMore;
    /**
     *  更多 点击信息
     */
    private MoreComponentBean moreComponent;

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIsShowMore() {
        return isShowMore;
    }

    public void setIsShowMore(int isShowMore) {
        this.isShowMore = isShowMore;
    }

    public MoreComponentBean getMoreComponent() {
        return moreComponent;
    }

    public void setMoreComponent(MoreComponentBean moreComponent) {
        this.moreComponent = moreComponent;
    }

    public static class MoreComponentBean {
        private String type;
        private String style;
        private String icon;
        private String iconStyle;
        private String title;
        private String desc;
        private Component.ExtParams extParams;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIconStyle() {
            return iconStyle;
        }

        public void setIconStyle(String iconStyle) {
            this.iconStyle = iconStyle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Component.ExtParams getExtParams() {
            return extParams;
        }

        public void setExtParams(Component.ExtParams extParams) {
            this.extParams = extParams;
        }
    }
}
