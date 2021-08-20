package com.xzq.module_base.bean;

import java.util.List;

public class CuiBean {

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
        private String albumId;
        private String completionRate;
        private String createId;
        private String createTime;
        private String id;
        private String imgUrl;
        private String monthDate;
        private String planMoney;
        private String total;
        private int type;

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getCompletionRate() {
            return completionRate;
        }

        public void setCompletionRate(String completionRate) {
            this.completionRate = completionRate;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getMonthDate() {
            return monthDate;
        }

        public void setMonthDate(String monthDate) {
            this.monthDate = monthDate;
        }

        public String getPlanMoney() {
            return planMoney;
        }

        public void setPlanMoney(String planMoney) {
            this.planMoney = planMoney;
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
    }
}
