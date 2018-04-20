package org.zj.Blog.service;


import org.zj.Blog.bean.Comment;

import java.util.List;

public interface ICommentService {
    boolean insertComment(Comment comment);

    boolean deleteComment(int commentID);

    List<Comment> getCommentsByBlogID(int blogID);
}
