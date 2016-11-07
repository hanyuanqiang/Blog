package com.hyq.service;

import com.hyq.entity.LeaveWords;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface LeaveWordsService {

    //删除用户留言
    public void delete(LeaveWords leaveWords);

    //保存一条留言
    public void save(LeaveWords leaveWords);

    //根据id获取实体
    public LeaveWords getLeaveWordsById(int leaveWordsId);

    //获取所有留言
    public List<LeaveWords> findLeaveWords(LeaveWords s_leaveWords);

    //设置留言的状态为已读
    public void changeStatus(int status);

    //查询未读留言的数目
    public int getCountWithNoRead();
}
