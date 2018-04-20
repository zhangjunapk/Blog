package org.zj.Blog.bean;

import java.util.Date;

public class Comment {
    private int commentID;
    private int blogID;
    private String content;
    private Date date;

    public Comment(){}

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Comment(int commentID, int blogID, String content, Date date) {
        this.commentID = commentID;
        this.blogID = blogID;
        this.content = content;
        this.date = date;
    }
}
