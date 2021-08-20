package com.xzq.module_base.bean;

public class ZyBean {

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
        private String contentType;
        private String createEndTime;
        private String createStartTime;

        private String name;
        private String parentId;
        private String tagId;
        private String type;

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

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


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
