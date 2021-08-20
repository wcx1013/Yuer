package com.xzq.module_base.bean;

public class TuppianBean {
    private String code;
    private String msg;
    private ResultBean result;
    public TuppianBean(){

    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String id;
        private String createTime;
        private String updateTime;
        private String name;
        private int ifImg;
        private String contentType;
        private String extName;
        private int size;
        private String url;
        public ResultBean(){}
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIfImg() {
            return ifImg;
        }

        public void setIfImg(int ifImg) {
            this.ifImg = ifImg;
        }

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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
