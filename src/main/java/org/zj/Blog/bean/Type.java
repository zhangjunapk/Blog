package org.zj.Blog.bean;

public class Type {
    private int type_id;
    private String title;
    private String info;

    public Type(){}

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Type(int type_id, String title, String info) {
        this.type_id = type_id;
        this.title = title;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type_id=" + type_id +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
