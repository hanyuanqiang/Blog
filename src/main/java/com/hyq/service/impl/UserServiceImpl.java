package com.hyq.service.impl;

import com.hyq.dao.BaseDAO;
import com.hyq.entity.User;
import com.hyq.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Resource
    private BaseDAO<User> baseDAO;

    public void save(User user) {
        baseDAO.merge(user);
    }

    public User login(User user) {
        List<Object> param = new LinkedList<Object>();
        StringBuffer hql = new StringBuffer("from User u where u.userName=? and u.password=?");
        param.add(user.getUserName());
        param.add(user.getPassword());
        return baseDAO.get(hql.toString(),param);   //如果没找到则返回null
    }

    public User getUserById(int userId) {
        return baseDAO.get(User.class,userId);
    }

    public void updateTotalOnlineTime(long min) {
        baseDAO.executeSql("UPDATE t_user SET totalOnlineTime=totalOnlineTime+"+min+" WHERE id=1");
    }
}
