package com.xzq.module_base.bean;

import java.util.List;

public class PlBean {

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
        private String contentType;
        private String extName;
        private String id;
        private int ifImg;
        private String name;
        private String path;
        private int size;
        private String thumbImgPath;
        private String thumbImgUrl;
        private String url;

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getExtName() {
            return extName;
        }

        public void setExtName(String extName) {
            this.extName = extName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIfImg() {
            return ifImg;
        }

        public void setIfImg(int ifImg) {
            this.ifImg = ifImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getThumbImgPath() {
            return thumbImgPath;
        }

        public void setThumbImgPath(String thumbImgPath) {
            this.thumbImgPath = thumbImgPath;
        }

        public String getThumbImgUrl() {
            return thumbImgUrl;
        }

        public void setThumbImgUrl(String thumbImgUrl) {
            this.thumbImgUrl = thumbImgUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
