package com.mobcent.discuz.bean;

import android.content.Context;

import com.mobcent.discuz.android.constant.BaseErrorCodeConstant;
import com.mobcent.lowest.base.utils.MCToastUtils;

import java.util.List;

/**
 * Created by sun on 2016/8/21.
 */

public abstract class BaseResult<C extends discuz.com.net.service.model.BaseResult<List>> {

    /**
     * rs : 1
     * errcode : 取消成功
     * head : {"errCode":"02000024","errInfo":"取消成功","version":"2.5.0.0","alert":1}
     */

    private int rs;
    private String errcode;
    /**
     * errCode : 02000024
     * errInfo : 取消成功
     * version : 2.5.0.0
     * alert : 1
     */

    private HeadBean head;

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    /**
     * 检查是否弹出提示
     * @param baseContext
     */
    public void checkAlert(Context baseContext) {
        if (head != null && head.alert == 1) {
            MCToastUtils.toast(baseContext, head.errInfo);
        }
    }

    public boolean isSuccess() {
        return rs == BaseErrorCodeConstant.RS_SUCC ;
    }

    public static class HeadBean {
        private String errCode;
        private String errInfo;
        private String version;
        private int alert;

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getErrInfo() {
            return errInfo;
        }

        public void setErrInfo(String errInfo) {
            this.errInfo = errInfo;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getAlert() {
            return alert;
        }

        public void setAlert(int alert) {
            this.alert = alert;
        }
    }
}
