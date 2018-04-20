package org.zj.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zj.Blog.bean.Blog;
import org.zj.Blog.bean.Comment;
import org.zj.Blog.bean.Tag;
import org.zj.Blog.service.BlogService;
import org.zj.Blog.service.IBlogService;
import org.zj.Blog.service.ITagService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjun
 */
@RestController
@RequestMapping("/tag")
public class TagControllerJson {

    @Autowired
    HttpServletRequest request;

    @Autowired
    IBlogService blogService;

    @Autowired
    ITagService tagService;

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public List<Tag> addTag(@RequestParam(value="content",required = true) String content){
        Tag tag=new Tag();
        tag.setContent(content);
        tag.setTag_id(tagService.getLastTagIndex()+1);
        tagService.insertTag(tag);
        return tagService.getAllTag();
    }

    @RequestMapping(value = "/get_comment_and_page/{page_num}",method = RequestMethod.GET)
    public Map<Integer,List<Tag>> getCommentMap(@PathVariable("page_num")int pageNum){
        //获得method
        String method="";
        //获得id
        int id=-1;
        //从Cookie中获得方法和id
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

        Map<Integer,List<Tag>> map=new HashMap<>();
        for(Blog blog:blogs){
            map.put(blog.getBlog_id(),tagService.getTagByBlogID(blog.getBlog_id()));
        }
        return map;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public List<Tag> getTagByBlogID(@PathVariable("id")int id){
        return tagService.getTagByBlogID(id);
    }
}
