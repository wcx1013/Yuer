package com.xzq.module_base.bean;

public class QuBean {
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
        private String albumId;
//        private String createId;
//        private String createTime;
//        private String endTime;
//        private String id;
//        private String imgUrl;
//        private String startTime;
//        private int type;

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

//        public String getCreateId() {
//            return createId;
//        }
//
//        public void setCreateId(String createId) {
//            this.createId = createId;
//        }
//
//        public String getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(String createTime) {
//            this.createTime = createTime;
//        }
//
//        public String getEndTime() {
//            return endTime;
//        }
//
//        public void setEndTime(String endTime) {
//            this.endTime = endTime;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getImgUrl() {
//            return imgUrl;
//        }
//
//        public void setImgUrl(String imgUrl) {
//            this.imgUrl = imgUrl;
//        }
//
//        public String getStartTime() {
//            return startTime;
//        }
//
//        public void setStartTime(String startTime) {
//            this.startTime = startTime;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
    }
}
