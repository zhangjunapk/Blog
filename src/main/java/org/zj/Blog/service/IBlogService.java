package org.zj.Blog.service;

import org.zj.Blog.bean.Blog;
import org.zj.Blog.bean.Tag;

import java.util.List;

public interface IBlogService {

    int getIndex(String method,int id,int blogID);
    Blog getBlogWithID(int id);

    List<Blog> getAll();

    Blog addBlog(Blog blog);

    int getLastID();

    boolean deleteBlog(int blogID);

    List<Blog> getBlogByType(int typeID);

    List<Blog> getBLogByTag(int tagID);

    boolean modifyBlog(Blog blog);


    List<Blog> searchBlog(String str);

    List<Blog> getBlogNew();


    List<Blog> getBlogByTypeIDAndPage(int typeID,int pageNum,int pageCount);

    List<Blog> getBlogByTagIDAndPage(int tagID,int pageNum,int pageCount);

    List<Blog> getBlogNewAndPage(int pageNum,int pageCount);

    Blog getBlogByTag(int tagID,int blogID,int action);

    Blog getBlogByType(int tagID,int blogID,int action);

    Blog getBlogByNew(int blogID,int action);
}
