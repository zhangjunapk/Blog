package org.zj.Blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zj.Blog.bean.ReadHistory;
import org.zj.Blog.dao.IInsertDao;
import org.zj.Blog.dao.IQueryDao;


@Service
public class IReadHistoryImpl implements IReadHistory {

    @Autowired
    private IQueryDao queryDao;

    @Autowired
    private IInsertDao insertDao;

    @Override
    public int getBlogReadCount(int blogID) {
        return queryDao.getBlogReadCount(blogID);
    }

    @Override
    public boolean insertReadhistory(ReadHistory readHistory) {
        insertDao.insertReadHistory(readHistory);
        return true;
    }
}
