package org.zj.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zj.Blog.bean.Blog;
import org.zj.Blog.bean.Comment;
import org.zj.Blog.service.IBlogService;
import org.zj.Blog.service.ICommentService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentControllerJson {

    @Autowired
    ICommentService commentService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    IBlogService blogService;

    @RequestMapping(value = "/get_comment_by_page/{page_num}",method = RequestMethod.GET)
    public Map<Integer,List<Comment>> getCommentsByPage(@PathVariable("page_num")int pageNum){
        //获得Cookie
        //获得COokie中的method
        //获得Cookie中的ID

        String method="";
        int id=-1;

        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("method")){
                method=cookie.getValue();
            }
            if(cookie.getName().equals("id")){
                id=Integer.parseInt(cookie.getValue());
            }
        }
        List<Blog> blogs=new ArrayList<>();
        if(method.equals("type")){
            blogs=blogService.getBlogByTypeIDAndPage(id,pageNum,40);
        }
        if(method.equals("tag")){
            blogs=blogService.getBlogByTagIDAndPage(id,pageNum,40);
        }

        Map<Integer,List<Comment>> map=new HashMap<>();
        for(Blog blog:blogs){
            map.put(blog.getBlog_id(),commentService.getCommentsByBlogID(blog.getBlog_id()));
        }
        return map;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public List<Comment> getCommentByBLogID(@PathVariable("id")int id){
        return commentService.getCommentsByBlogID(id);
    }
}
