package org.zj.Blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.zj.Blog.bean.*;

public interface IInsertDao{
    @Insert("insert into blog (blog_id,title,content,date) values (#{blog_id},#{title},#{content},#{date})")
    boolean insertBlog(Blog blog);


    @Insert("insert into type(type_id,title,info) values(#{type_id},#{title},#{info})")
    boolean insertType(Type type);

    @Insert("insert into type_set_history(history_id,blog_id,type_id,date) values(#{historyID},#{blogID},#{typeID},#{date})")
    boolean insertTypeSetHistory(TypeSetHistory typeSetHistory);

    @Insert("insert into tag(tag_id,content) values(#{tagID},#{content})")
    boolean insertTag(Tag tag);

    @Insert("insert into tag_set_history(history_id,blog_id,tag_id) values(#{historyID},#{blogID},#{tagID})")
    boolean insertTagSetHistory(TagSetHistory tagSetHistory);

    @Insert("insert into read_history(blog_id,date) values(#{blogID},#{date})")
    boolean insertReadHistory(ReadHistory readHistory);

    @Insert("insert into comment(comment_id,blog_id,content,date) values(#{commentID},#{blogID},#{content},#{date})")
    boolean insertComment(Comment comment);
}
