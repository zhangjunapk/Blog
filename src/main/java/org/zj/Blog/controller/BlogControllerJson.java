package org.zj.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zj.Blog.bean.Blog;
import org.zj.Blog.service.IBlogService;
import org.zj.Blog.service.IPageService;
import org.zj.Blog.service.ITagService;
import org.zj.Blog.service.ITypeService;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogControllerJson {

    @Autowired
    IPageService pageService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    ServletContext servletContext;

    @Autowired
    ITagService tagService;

    @Autowired
    IBlogService blogService;

    @Autowired
    ITypeService typeService;


    /**
     * 搜索
     * @param str
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public List<Blog> searchBlog(@RequestBody String str){
        return blogService.searchBlog(str);
    }

    /**
     * 根据类型id来获得博客列表
     * @param typeID
     * @return
     */
    @RequestMapping(value = "/get_blog_by_type/{id}",method = RequestMethod.GET)
    public List<Blog> getBlogByType(@PathVariable(value="id") int typeID){
        return blogService.getBlogByType(typeID);
    }

    /**
     * 根据标签id来获得博客列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/get_blog_by_tag/{id}",method = RequestMethod.GET)
    public List<Blog> getBlogByTag(@PathVariable("id") int id){
        return blogService.getBLogByTag(id);
    }

    /**
     * 根据页码来获得最新的博客列表(分页)
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/page/{page_num}",method = RequestMethod.GET)
    public List<Blog> getBlogByPageNum(@PathVariable("page_num")int pageNum){
        request.setAttribute("current_page",pageNum);
        request.setAttribute("total_page",pageService.getNewBlogPageCount(40));
        return blogService.getBlogNewAndPage(pageNum,40);
    }

    /**
     * 获得上一个或者下一个博客
     * @param id
     * @param method
     * @param blogID
     * @param action
     * @return
     */
    @RequestMapping(value = "/get_blog",method = RequestMethod.GET)
    public Blog getBlogByTagIDAndPageNum(@RequestParam("id")int id,@RequestParam("method")String method,@RequestParam("blogID")int blogID,@RequestParam("action")int action){
        if(method.equals("type")){
            return blogService.getBlogByType(id,blogID,action);
        }
        if(method.equals("tag")){
            return blogService.getBlogByTag(id,blogID,action);
        }
        if(method.equals("new")){
            return blogService.getBlogByNew(blogID,action);
        }
        return null;
    }

    @RequestMapping(value = "/index/{method}/{id}/{blog_id}",method = RequestMethod.GET)
    public int getIndex(@PathVariable("method")String method,int id,int blogID){
        return blogService.getIndex(method,id,blogID);
    }

}
