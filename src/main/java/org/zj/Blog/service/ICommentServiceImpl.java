package org.zj.Blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zj.Blog.bean.Comment;
import org.zj.Blog.dao.IDeleteDao;
import org.zj.Blog.dao.IInsertDao;
import org.zj.Blog.dao.IQueryDao;

import java.util.List;

@Service
public class ICommentServiceImpl implements ICommentService {

    @Autowired
    private IQueryDao queryDao;
    @Autowired
    private IDeleteDao deleteDao;

    @Autowired
    private IInsertDao insertDao;

    @Override
    public boolean insertComment(Comment comment) {
        return insertDao.insertComment(comment);
    }

    @Override
    public boolean deleteComment(int commentID) {
        return deleteDao.deleteComment(commentID);
    }

    @Override
    public List<Comment> getCommentsByBlogID(int blogID) {
        return queryDao.getCommentsByBlogID(blogID);
    }
}
