package org.zj.Blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.stereotype.Service;
import org.zj.Blog.dao.IQueryDao;

@Service
public class IPageServiceImpl implements IPageService {

    @Autowired
    IQueryDao queryDao;

    @Override
    public int getBlogPageCountByType(int typeID, int countOnePage) {
        return (int) Math.ceil(queryDao.getBlogCountByTypeID(typeID)/countOnePage);
    }

    @Override
    public int getBlogPageCountByTag(int tagID, int countOnePage) {
        return (int) Math.ceil(queryDao.getBlogCountByTagID(tagID)/countOnePage);
    }

    @Override
    public int getNewBlogPageCount(int countOnePage) {
        return (int) Math.ceil(queryDao.getBlogCount()/countOnePage);
    }
}
