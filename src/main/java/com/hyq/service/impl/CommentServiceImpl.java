package com.hyq.service.impl;

import com.hyq.dao.BaseDAO;
import com.hyq.entity.Comment;
import com.hyq.entity.PageBean;
import com.hyq.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/10/2.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Resource
    private BaseDAO<Comment> baseDAO;

    public void save(Comment comment) {
        baseDAO.save(comment);
    }

    public void delete(Comment comment) {
        baseDAO.delete(comment);
    }

    public List<Comment> findCommentList(Comment s_comment, PageBean pageBean) {

        return null;
    }

    public Comment getCommentById(int commentId) {
        return baseDAO.get(Comment.class,commentId);
    }
}
