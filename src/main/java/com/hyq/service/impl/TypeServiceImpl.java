package com.hyq.service.impl;

import com.hyq.dao.BaseDAO;
import com.hyq.entity.Type;
import com.hyq.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService{

    @Resource
    private BaseDAO<Type> baseDAO;

    public List<Type> findTypeList() {
        String hql = "from Type";
        return baseDAO.find(hql);
    }

    public void save(Type type) {
        baseDAO.merge(type);
    }

    public void delete(Type type) {
        baseDAO.delete(type);
    }

    public Type getTypeById(int id) {
        return baseDAO.get(Type.class,id);
    }
}
