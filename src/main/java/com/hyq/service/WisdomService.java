package com.hyq.service;

import com.hyq.entity.Wisdom;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface WisdomService {

    //添加一条至理名言
    public void save(Wisdom wisdom);
    //删除一条至理名言
    public void delete(Wisdom wisdom);

    //获取实体列表
    public List<Wisdom> findWisdomList();

    //根据id获取实体
    public Wisdom getWisdomById(int wisdomId);

    //把某一个实体设置为显示
    public void changeStatus(int wisdomId);

    //获取要显示的那条实体
    public Wisdom getTheShowOne();
}
