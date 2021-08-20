package com.xzq.module_base.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
    /**
     * param : {"appId":"wx8655133a8d3cb521","channel":"HW","type":3,"unionInfo":"otFsw6rcDzgU3wcjc3dLl0i04Zrk"}
     */

    private ParamDTO param;

    public UserInfo(ParamDTO param) {
        this.param = param;
    }

    public UserInfo() {

    }

    public ParamDTO getParam() {
        return param;
    }

    public void setParam(ParamDTO param) {
        this.param = param;
    }

    public static class ParamDTO implements Serializable {
        /**
         * appId : wx8655133a8d3cb521
         * channel : HW
         * type : 3
         * unionInfo : otFsw6rcDzgU3wcjc3dLl0i04Zrk
         */

        private String appId;
        private String channel;
        private int type;
        private String unionInfo;
        public ParamDTO(){}
        public ParamDTO(String appId, String channel, int type, String unionInfo) {
            this.appId = appId;
            this.channel = channel;
            this.type = type;
            this.unionInfo = unionInfo;
        }



        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUnionInfo() {
            return unionInfo;
        }

        public void setUnionInfo(String unionInfo) {
            this.unionInfo = unionInfo;
        }
    }

}
