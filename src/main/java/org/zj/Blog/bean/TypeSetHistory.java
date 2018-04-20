package org.zj.Blog.bean;

import java.util.Date;

public class TypeSetHistory {
    private int historyID;
    private int blogID;
    private int typeID;
    private Date date;

    public TypeSetHistory(){}

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeSetHistory(int historyID, int blogID, int typeID, Date date) {
        this.historyID = historyID;
        this.blogID = blogID;
        this.typeID = typeID;
        this.date = date;
    }
}
