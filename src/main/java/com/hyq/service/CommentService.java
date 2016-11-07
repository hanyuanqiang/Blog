
package com.hyq.service;

import com.hyq.entity.Comment;
import com.hyq.entity.PageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface CommentService {

    //保存一条评论
    public void save(Comment comment);
    //删除一条评论
    public void delete(Comment comment);

    //获取评论列表
    public List<Comment> findCommentList(Comment s_comment, PageBean pageBean);

    public Comment getCommentById(int commentId);

}
