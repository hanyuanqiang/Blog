package com.hyq.service.impl;

import com.hyq.dao.BaseDAO;
import com.hyq.entity.LeaveWords;
import com.hyq.service.LeaveWordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/2.
 */
@Service("leaveWordsService")
public class LeaveWordsServiceImpl implements LeaveWordsService{

    @Resource
    private BaseDAO<LeaveWords> baseDAO;

    public void delete(LeaveWords leaveWords) {
        baseDAO.delete(leaveWords);
    }

    public void save(LeaveWords leaveWords) {
        baseDAO.save(leaveWords);
    }

    public LeaveWords getLeaveWordsById(int leaveWordsId) {
        return baseDAO.get(LeaveWords.class,leaveWordsId);
    }

    public List<LeaveWords> findLeaveWords(LeaveWords s_leaveWords) {
        List<Object> param = new LinkedList<Object>();
        StringBuffer hql = new StringBuffer("from LeaveWords");
        if (s_leaveWords!=null){
            hql.append(" and content like ?");
            param.add("%"+s_leaveWords.getContent()+"%");
        }
        hql.append(" order by id desc");

        return baseDAO.find(hql.toString().replaceFirst("and","where"),param);
    }

    public void changeStatus(int status) {
        baseDAO.executeSql("UPDATE t_leavewords SET STATUS="+status);
    }

    public int getCountWithNoRead() {
        Object temp = baseDAO.executeSql2("SELECT COUNT(*) FROM t_leavewords WHERE STATUS=0").get(0);
        return Integer.parseInt(temp.toString()) ;
    }
}
