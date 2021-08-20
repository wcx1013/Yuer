package com.xzq.module_base.bean;

import java.util.List;

public class SplistBean {

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
        private String createId;
        private String createTime;
        private String days;
        private int deleted;
        private String expirationDate;
        private String goodsName;
        private String imgUrl;
        private int isBeOverdue;
        private String produceTime;
        private String product;
        private int soonBeOverdue;

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

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getIsBeOverdue() {
            return isBeOverdue;
        }

        public void setIsBeOverdue(int isBeOverdue) {
            this.isBeOverdue = isBeOverdue;
        }

        public String getProduceTime() {
            return produceTime;
        }

        public void setProduceTime(String produceTime) {
            this.produceTime = produceTime;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public int getSoonBeOverdue() {
            return soonBeOverdue;
        }

        public void setSoonBeOverdue(int soonBeOverdue) {
            this.soonBeOverdue = soonBeOverdue;
        }
    }
}
