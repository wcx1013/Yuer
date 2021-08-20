package com.xzq.module_base.bean;

import java.util.List;

public class ShanBean {
    private ParamBean param;

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public static class ParamBean {
        private List<String> resIdList;

        public List<String> getResIdList() {
            return resIdList;
        }

        public void setResIdList(List<String> resIdList) {
            this.resIdList = resIdList;
        }
    }
}
