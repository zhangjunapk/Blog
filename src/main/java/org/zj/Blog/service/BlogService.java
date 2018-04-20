package org.zj.Blog.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.stereotype.Service;
import org.zj.Blog.bean.Blog;
import org.zj.Blog.dao.IDeleteDao;
import org.zj.Blog.dao.IInsertDao;
import org.zj.Blog.dao.IQueryDao;
import org.zj.Blog.dao.IUpdateDao;

import java.util.List;

@Service
@CacheConfig(cacheNames = "blogg")
public class BlogService implements IBlogService{

    @Autowired
    IUpdateDao updateDao;

    @Autowired
    IQueryDao queryDao;

    @Autowired
    IInsertDao insertDao;

    @Autowired
    IDeleteDao deleteDao;
    @Override
    public Blog getBlogWithID(int id) {
        return queryDao.selectBlogByID(id);
    }

    @Override
    public List<Blog> getAll() {
        return queryDao.selectAll();
    }

    @Override
    public Blog addBlog(Blog blog) {
        blog.setBlog_id(getLastID()+1);
        insertDao.insertBlog(blog);
        return getBlogWithID(getLastID());
    }

    @Override
    public int getLastID() {
        int result=-1;
        if(queryDao.getAllID().size()==0){
            return 0;
        }
        for(Integer i:queryDao.getAllID()){
            if(i>result){
                result=i;
            }
        }
        return result;
    }

    @Override
    public boolean deleteBlog(int blogID) {
        return deleteDao.deleteBlog(blogID);
    }

    @Override
    public List<Blog> getBlogByType(int typeID) {
        return queryDao.getBLogByType(typeID);
    }

    @Override
    public List<Blog> getBLogByTag(int tagID) {
        return queryDao.getBlogByTag(tagID);
    }

    @Override
    public boolean modifyBlog(Blog blog) {
        return updateDao.updateBlog(blog);
    }

    @Override
    public List<Blog> searchBlog(String str) {
        return queryDao.searchBlog(str);
    }

    @Override
    public List<Blog> getBlogNew() {
        return queryDao.selectBlogNew();
    }

    @Override
    public List<Blog> getBlogByTypeIDAndPage(int typeID, int pageNum, int pageCount) {
        PageHelper.startPage(pageNum,pageCount);
        return getBlogByType(typeID);
    }

    @Override
    public List<Blog> getBlogByTagIDAndPage(int tagID, int pageNum, int pageCount) {
        PageHelper.startPage(pageNum,pageCount);
        List<Blog> bLogByTag = getBLogByTag(tagID);
        return bLogByTag;
    }

    @Override
    public List<Blog> getBlogNewAndPage(int pageNum, int pageCount) {
        PageHelper.startPage(pageNum,pageCount);
        return getBlogNew();
    }

    @Override
    public Blog getBlogByTag(int tagID, int blogID, int action) {
        List<Blog> blogByTag = getBLogByTag(tagID);
        for(int i=0;i<blogByTag.size();i++){
            if(blogByTag.get(i).getBlog_id()==blogID){
                if(action==0){
                    if(i==0){
                        return blogByTag.get(blogByTag.size()-1);
                    }else{
                        return blogByTag.get(i-1);
                    }
                }else{
                    if(i==blogByTag.size()-1){
                        return blogByTag.get(0);
                    }else{
                        return blogByTag.get(i+1);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Blog getBlogByType(int typeID, int blogID, int action) {
        List<Blog> blogByType = getBlogByType(typeID);
        for(int i=0;i<blogByType.size();i++){
            if(blogByType.get(i).getBlog_id()==blogID){
                if(action==0){
                    if(i==0){
                        return blogByType.get(blogByType.size()-1);
                    }else{
                        return blogByType.get(i-1);
                    }
                }else{
                    if(i==blogByType.size()-1){
                        return blogByType.get(0);
                    }else{
                        return blogByType.get(i+1);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Blog getBlogByNew(int blogID, int action) {
        List<Blog> blogNew = getBlogNew();
        for(int i=0;i<blogNew.size();i++){
            if(blogNew.get(i).getBlog_id()==blogID){
                if(action==0){
                    if(i==0){
                        return blogNew.get(blogNew.size()-1);
                    }else{
                        return blogNew.get(i-1);
                    }
                }else{
                    if(i==blogNew.size()-1){
                        return blogNew.get(0);
                    }else{
                        return blogNew.get(i+1);
                    }
                }
            }

        }
        return null;
    }


    /**
     * genju leixing id boke ID lai huode suoyin
     * @param type
     * @param id
     * @param blogID
     * @return
     */
    @Override
    public int getIndex(String type, int id, int blogID){
        if(type.equals("type")){
            List<Blog> blogByType = getBlogByType(id);
            for(int i=0;i<blogByType.size();i++){
                if(blogByType.get(i).getBlog_id()==blogID){
                    return i;
                }
            }
        }
        if(type.equals("tag")){
            List<Blog> blogByType = getBLogByTag(id);
            for(int i=0;i<blogByType.size();i++){
                if(blogByType.get(i).getBlog_id()==blogID){
                    return i;
                }
            }
        }
        if(type.equals("new")){
            List<Blog> blogByType = getBlogNew();
            for(int i=0;i<blogByType.size();i++){
                if(blogByType.get(i).getBlog_id()==blogID){
                    return i;
                }
            }
        }
        return -1;
    }
}
