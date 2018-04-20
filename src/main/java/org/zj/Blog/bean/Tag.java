package org.zj.Blog.bean;

public class Tag {
    private int tag_id;
    private String content;

    public Tag(){}

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tag(int tag_id, String content) {
        this.tag_id = tag_id;
        this.content = content;
    }
}
