package com.hyq.service;

import com.hyq.entity.Type;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface TypeService {

    public List<Type> findTypeList();

    //保存一条类型
    public void save(Type type);
    //删除一条类型
    public void delete(Type type);
    //根据指定id获取类型
    public Type getTypeById(int id);


}
