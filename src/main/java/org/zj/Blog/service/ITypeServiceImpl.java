package org.zj.Blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zj.Blog.bean.Type;
import org.zj.Blog.bean.TypeSetHistory;
import org.zj.Blog.dao.IDeleteDao;
import org.zj.Blog.dao.IInsertDao;
import org.zj.Blog.dao.IQueryDao;
import org.zj.Blog.dao.IUpdateDao;

import java.util.Date;
import java.util.List;

@Service
public class ITypeServiceImpl implements ITypeService {

    @Autowired
    private IUpdateDao updateDao;

    @Autowired
    private IQueryDao queryDao;

    @Autowired
    private IInsertDao insertDao;

    @Autowired
    private IDeleteDao deleteDao;
    @Override
    public boolean insertType(Type type) {
        type.setType_id(getLastIndex()+1);
        return insertDao.insertType(type);
    }

    @Override
    public List<Type> getAllType() {
        return queryDao.getAllType();
    }

    @Override
    public boolean deleteType(int typeID) {
        return deleteDao.deleteType(typeID);
    }

    @Override
    public boolean setTypeForBlog(int blogID, int typeID) {
        return insertDao.insertTypeSetHistory(new TypeSetHistory(getLastIndex()+1,blogID,typeID,new Date()));
    }



    @Override
    public int getLastIndex() {
        int result=-1;
        for(Integer i:queryDao.getAllTypeID()){
            if(i>result){
                result=i;
            }
        }
        return result;
    }

    @Override
    public boolean modifyType(Type type) {
        return updateDao.updateType(type);
    }

    @Override
    public int getBlogCountByTypeID(int typeID) {
        return queryDao.getBlogCountByTypeID(typeID);
    }

    @Override
    public boolean deleteTypeSetHistory(int historyID) {
        return deleteDao.deleteTypeSethistory(historyID);
    }

    @Override
    public Type getTypeByID(int id) {
        return queryDao.selectTypeByID(id);
    }
}
