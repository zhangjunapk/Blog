package org.zj.Blog.bean;

public class PicUploadBean{
private String success;
private String message;
private String url;


public PicUploadBean(){}

        public PicUploadBean(String success, String message, String url) {
            this.success = success;
            this.message = message;
            this.url = url;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
}
