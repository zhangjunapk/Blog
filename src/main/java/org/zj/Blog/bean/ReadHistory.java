package org.zj.Blog.bean;

import java.util.Date;

public class ReadHistory {
    private int blogID;
    private Date date;

    public ReadHistory(){}

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReadHistory(int blogID, Date date) {
        this.blogID = blogID;
        this.date = date;
    }
}
