package org.zj.Blog.service;

import org.zj.Blog.bean.Tag;
import org.zj.Blog.bean.TagSetHistory;

import java.util.List;
import java.util.Map;

public interface ITagService {
    boolean setTagForBlog(int blogID,List<Tag> tags);
    boolean deleteTagSetHistory(int historyID);
    List<Tag> getTagByBlogID(int blogID);

    boolean insertTag(Tag tag);
    List<Tag> getAllTag();

    int getLastTagIndex();

    boolean insertTagSetHistory(TagSetHistory tagSetHistory);

    int getLastTagSetHistoryIndex();

    List<Map.Entry<Tag,Integer>> getTagMapAndCountListTop(int top);

    Tag getTagByID(int id);
}
