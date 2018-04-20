package org.zj.Blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zj.Blog.bean.Tag;
import org.zj.Blog.bean.TagSetHistory;
import org.zj.Blog.dao.IDeleteDao;
import org.zj.Blog.dao.IInsertDao;
import org.zj.Blog.dao.IQueryDao;

import java.util.*;

@Service
public class ITagServiceImpl implements ITagService {

    @Autowired
    IInsertDao insertDao;

    @Autowired
    IDeleteDao deleteDao;

    @Autowired
    IQueryDao queryDao;
    @Override
    public boolean setTagForBlog(int blogID, List<Tag> tags) {
        for(Tag tag:tags){
            insertDao.insertTagSetHistory(new TagSetHistory(blogID,tag.getTag_id(),new Date()));
        }
        return true;
    }

    @Override
    public boolean deleteTagSetHistory(int historyID) {
        return deleteDao.deleteTagSetHistory(historyID);
    }

    @Override
    public List<Tag> getTagByBlogID(int blogID) {
        return queryDao.getAllTagForBlog(blogID);
    }

    @Override
    public boolean insertTag(Tag tag) {
       tag.setTag_id(getLastTagIndex()+1);
       insertDao.insertTag(tag);
       return true;
    }

    @Override
    public List<Tag> getAllTag() {
        return queryDao.getAllTag();
    }

    @Override
    public int getLastTagIndex() {
        int result=-1;

        if(queryDao.getAllTagID().size()==0){
            return 0;
        }

        for(int i:queryDao.getAllTagID()){
            if(i>result){
                result=i;
            }
        }
        return result;
    }

    @Override
    public int getLastTagSetHistoryIndex() {
        int result=-1;
        if(queryDao.getAllTagID().size()==0){
            return 0;
        }
        for(Integer i:queryDao.selectAllTagHistoryID()){
            if(i>result){
                result=i;
        }
        }
        return result;
    }

    @Override
    public List<Map.Entry<Tag,Integer>> getTagMapAndCountListTop(int top) {
        List<Map.Entry<Tag,Integer>> result=new ArrayList<>();
       for(Tag tag:getAllTag()){
           result.add(new Map.Entry<Tag, Integer>() {
               @Override
               public Tag getKey() {
                   return tag;
               }

               @Override
               public Integer getValue() {
                   return queryDao.getBlogCountByTagID(tag.getTag_id());
               }

               @Override
               public Integer setValue(Integer value) {
                   return value;
               }
           });
       }

       //sort
       for(int i=0;i<result.size();i++){
           for(int j=0;j<i;j++){
               if(result.get(i).getValue()<result.get(j).getValue()){
                   Map.Entry<Tag, Integer> tagIntegerEntry = result.get(i);
                   result.set(i,result.get(j));
                   result.set(j,tagIntegerEntry);
               }
           }
       }
       //you know
       return result.subList(0,result.size()<top?result.size():top);
    }

    @Override
    public Tag getTagByID(int id) {
        return queryDao.getTagByID(id);
    }

    @Override
    public boolean insertTagSetHistory(TagSetHistory tagSetHistory) {
        tagSetHistory.setHistoryID(getLastTagSetHistoryIndex()+1);
        return insertDao.insertTagSetHistory(tagSetHistory);
    }


}
