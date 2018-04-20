package org.zj.Blog.bean;

import java.util.Date;

public class TagSetHistory {
    private int historyID;
    private int blogID;
    private int tagID;
    private Date date;

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public TagSetHistory(){}

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TagSetHistory(int blogID, int tagID, Date date) {
        this.blogID = blogID;
        this.tagID = tagID;
        this.date = date;
    }
}
