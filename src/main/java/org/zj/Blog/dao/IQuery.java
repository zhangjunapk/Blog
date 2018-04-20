package org.zj.Blog.dao;

import org.springframework.data.repository.Repository;
import org.zj.Blog.bean.Blog;

import java.util.List;

public interface IQuery extends Repository<Blog, String> {
        List<Blog> findByContent(String content);

        }