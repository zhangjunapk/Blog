package org.zj.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.zj.Blog.bean.Blog;
import org.zj.Blog.bean.TagSetHistory;
import org.zj.Blog.bean.Type;
import org.zj.Blog.dao.IQuery;
import org.zj.Blog.service.IBlogService;
import org.zj.Blog.service.IPageService;
import org.zj.Blog.service.ITagService;
import org.zj.Blog.service.ITypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    IQuery query;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ITagService tagService;

    @Autowired
    IBlogService blogService;

    @Autowired
    ITypeService typeService;


    @Autowired
    IPageService pageService;

    @RequestMapping("/test")
    public String tests(){
        return "test";
    }

    /**
     * 主页 你懂得 啦
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        request.setAttribute("blog_list",blogService.getBlogNewAndPage(1,40));
        request.setAttribute("type_list",typeService.getAllType());
        request.setAttribute("current_page",1);
        request.setAttribute("tag_list",tagService.getTagMapAndCountListTop(5));
        request.setAttribute("total_page",pageService.getNewBlogPageCount(40));
        System.out.println("<----------->wozoudeshi index");
        return "index";
    }


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(ModelMap modelMap){
        modelMap.put("type_list",typeService.getAllType());
        modelMap.put("tag_list",tagService.getAllTag());
        System.out.println("<---------->"+typeService.getAllType().size());
        return "add";
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addBlog(@RequestParam("title")String title,
                          @RequestParam("my-editormd-html-code")String content,
                          @RequestParam(value="type_",required = true)String type_,
                          @RequestParam(value="tag_",required = true)String[] tag_){

        blogService.addBlog(new Blog(blogService.getLastID()+1,title,content,new Date()));

        //String[] tagIDs= (String[]) request.getAttribute("tag_");
        if(tag_!=null) {
            for (String id : tag_) {
                tagService.insertTagSetHistory(new TagSetHistory(blogService.getLastID(), Integer.valueOf(id), new Date()));
            }
        }
        System.out.println("<--------->"+type_);
        typeService.setTypeForBlog(blogService.getLastID(),Integer.valueOf(type_));

        return "redirect:/blog/"+blogService.getLastID();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String showOneBlog(@PathVariable("id") int id,ModelMap modelMap){
        modelMap.addAttribute("current_blog",blogService.getBlogWithID(id));
        modelMap.addAttribute("type_list",typeService.getAllType());
        request.setAttribute("tag_list",tagService.getTagMapAndCountListTop(5));
        return "blog";
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteBlog(@PathVariable("id") int id){
       blogService.deleteBlog(id);
       return "redirect:/blog/index";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String modifyBlog(Blog blog){
        blogService.modifyBlog(blog);
        return "redirect:/blog/"+blog.getBlog_id()
                ;
    }

    @RequestMapping(value = "/modify/{id}",method = RequestMethod.GET)
    public String showModifyPage(@PathVariable int id, ModelMap modelMap){
        modelMap.put("current_blog",blogService.getBlogWithID(id));
        return "modify.html";
    }


    @RequestMapping(value = "/markdown",method = RequestMethod.GET)
    public String markdown(){
        return "markdown_test.html";
    }


    @RequestMapping(value = "/testmarkdown",method = RequestMethod.GET)
    public String jj(@RequestParam("title")String title,@RequestParam("my-editormd-html-code")String content,ModelMap modelMap){
        Blog blog=new Blog(blogService.getLastID()+1,title,content,new Date());
        blogService.addBlog(blog);
        modelMap.put("current_blog",blog);
        return "blog";
    }

    /**
     * 显示指定分类的博客
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/{method}/{id}",method = RequestMethod.GET)
    public String getBlogByType(@PathVariable("method")String method,@PathVariable("id")String id,ModelMap modelMap){
        if(method.equals("type")) {
            modelMap.put("blog_list", blogService.getBlogByType(Integer.parseInt(id)));
            for (Blog blog:blogService.getBlogByType(Integer.parseInt(id))) {
                System.out.println(blog);
            }
            modelMap.put("item", typeService.getTypeByID(Integer.parseInt(id)));
            modelMap.put("method", method);
        }
        if(method.equals("tag")){
            modelMap.put("blog_list",blogService.getBLogByTag(Integer.parseInt(id)));
            modelMap.put("item",tagService.getTagByID(Integer.parseInt(id)));
            modelMap.put("method",method);
        }
        return "type";
    }

    /**
     *
     * @param method
     * @param id
     * @param blogID
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/{method}/{id}/{blog_id}",method = RequestMethod.GET)
    public String getBlogByType_(@PathVariable("method")String method,@PathVariable("id")String id,@PathVariable("blog_id")int blogID, ModelMap modelMap){
        if(method.equals("type")) {
            modelMap.put("current_blog", blogService.getBlogWithID(blogID));
            System.out.println(blogService.getBlogWithID(blogID));
            modelMap.put("id",id);
            modelMap.put("type_list",typeService.getAllType());
            modelMap.put("method", method);
        }
        if(method.equals("tag")) {
            modelMap.put("current_blog", blogService.getBlogWithID(blogID));
            System.out.println(blogService.getBlogWithID(blogID));
            modelMap.put("id",id);
            modelMap.put("type_list",typeService.getAllType());
            modelMap.put("method", method);
        }
        return "blog";
    }

    @RequestMapping(value = "/solr",method = RequestMethod.GET)
    public void get(){
        for(Blog blog:query.findByContent("z")){
            System.out.println(blog);
        }
    }

}
