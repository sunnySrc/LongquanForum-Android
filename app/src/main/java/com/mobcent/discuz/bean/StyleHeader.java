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
    private Component moreComponent;

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

    public Component getMoreComponent() {
        return moreComponent;
    }

    public void setMoreComponent(Component moreComponent) {
        this.moreComponent = moreComponent;
    }

}
