package org.zj.Blog.dao;

import org.apache.ibatis.annotations.Update;
import org.zj.Blog.bean.Blog;
import org.zj.Blog.bean.Type;

public interface IUpdateDao {
    @Update("update blog set title=#{title},set content=#{content} where blog_id=#{id}")
    boolean updateBlog(Blog blog);

    @Update("update type set title=#{title},set info=#{info}")
    boolean updateType(Type type);
}
