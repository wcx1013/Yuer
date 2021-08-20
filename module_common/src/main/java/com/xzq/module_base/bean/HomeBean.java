package com.xzq.module_base.bean;

public class HomeBean {
    public HomeBean(){}
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
        private String coverImg;
        private String createId;
        private String createTime;
        private int deleted;
        private String endTime;
        private String id;
        private int isTop;
        private String matching;
        private String name;
        private int planMoney;
        private String planTime;
        private String propose;
        private String remarks;
        private String source;
        private String startTime;
        private int status;
        private int type;
        private String updateTime;
        public ParamBean(){}
        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getCreateId() {
            return createId;
        }

        public void setCreateId(String createId) {
            this.createId = createId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIsTop() {
            return isTop;
        }

        public void setIsTop(int isTop) {
            this.isTop = isTop;
        }

        public String getMatching() {
            return matching;
        }

        public void setMatching(String matching) {
            this.matching = matching;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlanMoney() {
            return planMoney;
        }

        public void setPlanMoney(int planMoney) {
            this.planMoney = planMoney;
        }

        public String getPlanTime() {
            return planTime;
        }

        public void setPlanTime(String planTime) {
            this.planTime = planTime;
        }

        public String getPropose() {
            return propose;
        }

        public void setPropose(String propose) {
            this.propose = propose;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
