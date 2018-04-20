package org.zj.Blog.service;

public interface IPageService {

    int getBlogPageCountByType(int typeID,int countnOnePage);

    int getBlogPageCountByTag(int tagID,int countOnePage);

    int getNewBlogPageCount(int countOnePage);
}
