package com.mobcent.discuz.bean;

/**
 * Created by sun on 2016/8/21.
 */

public abstract class BaseResult {
    private int result;
    private Head head;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public static class Head{

        /**
         * errCode : 00000000
         * errInfo :
         * version : 2.5.0.0
         * alert : 0
         */

        private String errCode;
        private String errInfo;
        private String version;

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
    }
}
