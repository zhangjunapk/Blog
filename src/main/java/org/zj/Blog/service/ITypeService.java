package org.zj.Blog.service;

import org.zj.Blog.bean.Type;

import java.util.List;

public interface ITypeService {

    boolean insertType(Type type);

    List<Type> getAllType();

    boolean deleteType(int typeID);

    boolean setTypeForBlog(int blogID,int typeID);

    int getLastIndex();

    boolean modifyType(Type type);


    int getBlogCountByTypeID(int typeID);

    boolean deleteTypeSetHistory(int historyID);

    Type getTypeByID(int id);
}
