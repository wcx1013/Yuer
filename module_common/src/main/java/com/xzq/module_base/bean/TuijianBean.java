package com.xzq.module_base.bean;

public class TuijianBean {

    private String order;
    private int pageNum;
    private int pageSize;
    private ParamBean param;
    private String sort;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ParamBean getParam() {
        return param;
    }

    public void setParam(ParamBean param) {
        this.param = param;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public static class ParamBean {
        private String createEndTime;
        private String createStartTime;
        private String resType;

        public String getCreateEndTime() {
            return createEndTime;
        }

        public void setCreateEndTime(String createEndTime) {
            this.createEndTime = createEndTime;
        }

        public String getCreateStartTime() {
            return createStartTime;
        }

        public void setCreateStartTime(String createStartTime) {
            this.createStartTime = createStartTime;
        }

        public String getResType() {
            return resType;
        }

        public void setResType(String resType) {
            this.resType = resType;
        }
    }
}
