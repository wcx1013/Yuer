package com.xzq.module_base.bean;

import java.util.List;

public class HscBean {

    private ParamBean param;

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public static class ParamBean {
        private List<?> resIdList;
        private int starType;

        public List<?> getResIdList() {
            return resIdList;
        }

        public void setResIdList(List<?> resIdList) {
            this.resIdList = resIdList;
        }

        public int getStarType() {
            return starType;
        }

        public void setStarType(int starType) {
            this.starType = starType;
        }
    }
}
