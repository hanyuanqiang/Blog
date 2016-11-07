package com.hyq.service.impl;

import com.hyq.dao.BaseDAO;
import com.hyq.entity.Wisdom;
import com.hyq.service.WisdomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/3.
 */
@Service("wisdomService")
public class WisdomServiceImpl implements WisdomService {

    @Resource
    private BaseDAO<Wisdom> baseDAO;

    public void save(Wisdom wisdom) {
        baseDAO.save(wisdom);
    }

    public void delete(Wisdom wisdom) {
        baseDAO.delete(wisdom);
    }

    public List<Wisdom> findWisdomList() {
        String hql = "from Wisdom order by id desc";
        return baseDAO.find(hql);
    }

    public Wisdom getWisdomById(int wisdomId) {
        return baseDAO.get(Wisdom.class,wisdomId);
    }

    public void changeStatus(int wisdomId) {
        baseDAO.executeSql("update t_wisdom set status=0");
        baseDAO.executeSql("update t_wisdom set status=1 where id="+wisdomId);
    }

    public Wisdom getTheShowOne() {
        String hql = "from Wisdom where status=?";
        List<Object> param = new LinkedList<Object>();
        param.add(1);
        return baseDAO.get(hql,param);
    }
}
