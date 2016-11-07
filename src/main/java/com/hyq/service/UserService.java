package com.hyq.service;

import com.hyq.entity.User;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface UserService {

    //保存一个用户
    public void save(User user);
    //用户登录
    public User login(User user);

    //根据id获取用户
    public User getUserById(int userId);

    //更新用户在线时长
    public void updateTotalOnlineTime(long min);

}
