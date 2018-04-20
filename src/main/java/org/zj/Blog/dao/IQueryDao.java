package org.zj.Blog.dao;

import org.apache.ibatis.annotations.Select;
import org.zj.Blog.bean.Blog;
import org.zj.Blog.bean.Comment;
import org.zj.Blog.bean.Tag;
import org.zj.Blog.bean.Type;

import java.util.List;

public interface IQueryDao {


    @Select("select * from tag where tag_id=#{tagID}")
    Tag getTagByID(int tagID);

    @Select("select * from blog where blog_id in(select blog_id from tag_set_history where tag_id=#{tagID}) limit #{pageNum}-1,#{countOnePage} ")
    List<Blog> selectBlogByTagIDAndPage(int tagID,int pageNum,int countOnePage);

    @Select("select * from blog where blog_id in(select blog_id from type_set_history where tag_id=#{typeID}) limit #{pageNum}-1,#{countOnePage} ")
    List<Blog> selectBlogByTypeIDAndPage(int typeID,int pageNum,int countOnePage);


    @Select("select * from type where type_id=#{id}")
    Type selectTypeByID(int id);
    @Select("select count(*) from blog")
    int getBlogCount();

    @Select("select count(*) from tag_set_history where tag_id=#{id}")
    int getBlogCountByTagID(int id);

    @Select("select history_id from tag_set_history")
    List<Integer> selectAllTagHistoryID();
    @Select("select count(*) from type_set_history where type_id=#{id}")
    int getBlogCountByTypeID(int id);

    @Select("select tag_id from tag")
    List<Integer> getAllTagID();
    @Select("select * from blog")
    List<Blog> selectAll();

    @Select("select * from blog where blog_id=#{id}")
    Blog selectBlogByID(int id);

    @Select("select blog_id from blog")
    List<Integer> getAllID();


    @Select("select * from type")
    List<Type> getAllType();

    @Select("select type_id from type")
    List<Integer> getAllTypeID();

    @Select("select * from blog where blog_id in (select blog_id from type_set_history where type_id=#{id})")
    List<Blog> getBLogByType(int id);

    @Select("select history_id from tag_set_history")
    List<Integer> getTagIDs();

    @Select("select * from tag where tag_id in(select tag_id from tag_set_history where blog_id=#{id})")
    List<Tag> getAllTagForBlog(int id);

    @Select("select * from tag")
    List<Tag> getAllTag();

    @Select("select * from blog where blog_id in (select blog_id from tag_set_history where tag_id=#{tagID})")
    List<Blog> getBlogByTag(int tagID);

    @Select("select count(*) from read_history where blog_id=#{blogID}")
    int getBlogReadCount(int blogID);

    @Select("select * from comment where blogID=#{blogID}")
    List<Comment> getCommentsByBlogID(int blogID);

    @Select("select * from blog where content like #{str}*")
    List<Blog> searchBlog(String str);

    @Select("select * from blog order by date")
    List<Blog> selectBlogNew();

}
