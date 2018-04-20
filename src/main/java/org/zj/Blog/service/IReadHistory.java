package org.zj.Blog.service;

import org.zj.Blog.bean.ReadHistory;

public interface IReadHistory {
    int getBlogReadCount(int blogID);
    boolean insertReadhistory(ReadHistory readHistory);
}
