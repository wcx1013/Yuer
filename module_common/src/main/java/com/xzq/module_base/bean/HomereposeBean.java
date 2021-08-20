package com.xzq.module_base.bean;

import java.util.List;

public class HomereposeBean {
    private String code;
    private List<ListBean> list;
    private String msg;
    private int total;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class ListBean {
        private String completionRate;
        private String coverImg;
        private String createId;
        private String createTime;
        private int deleted;
        private String id;
        private int isTop;
        private String matching;
        private String name;
        private int planMoney;
        private String planTime;
        private String propose;
        private String remarks;
        private String source;
        private int status;
        private String total;
        private int type;
        private String updateTime;
        private String monthDate;

        public String getMonthDate() {
            return monthDate;
        }

        public void setMonthDate(String monthDate) {
            this.monthDate = monthDate;
        }

        public ListBean(){}
        public String getCompletionRate() {
            return completionRate;
        }

        public void setCompletionRate(String completionRate) {
            this.completionRate = completionRate;
        }

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
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
