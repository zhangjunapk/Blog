package org.zj.Blog.dao;

import org.apache.ibatis.annotations.Delete;

public interface IDeleteDao {

    @Delete("delete from blog where blog_id=#{id}")
    boolean deleteBlog(int id);

    @Delete("delete from type where type_id=#{id}")
    boolean deleteType(int id);

    @Delete("delete from type_set_history where history_id=#{id}")
    boolean deleteTypeSethistory(int id);

    @Delete("delete from tag where tag_id=#{id}")
    boolean deleteTag(int id);

    @Delete("delete from tag_set_history where history_id=#{historyID}")
    boolean deleteTagSetHistory(int historyID);

    @Delete("delete from comment where comment_id=#{commentID}")
    boolean deleteComment(int commentID);
}
