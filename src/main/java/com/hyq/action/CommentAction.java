package com.hyq.action;

import com.hyq.entity.Comment;
import com.hyq.service.ArticleService;
import com.hyq.service.CommentService;
import com.hyq.util.PrintException;
import com.hyq.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/2.
 */
@Controller
public class CommentAction extends ActionSupport implements ServletRequestAware{

    @Resource
    private CommentService commentService;
    @Resource
    private ArticleService articleService;
    private HttpServletRequest request;

    private Comment comment;
    private int articleId;


    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String save()throws Exception{

        if (comment==null|| StringUtil.isEmpty(comment.getContent())){
            PrintException.print(ServletActionContext.getResponse(),"错误操作");
            return null;
        }
        articleId = comment.getArticle().getId();
        comment.setIp(getRemortIP(request));
        comment.setCommentTime(new Date());
        commentService.save(comment);
        return "save";
    }

    public String backdelete()throws Exception{
        articleId = comment.getArticle().getId();
        commentService.delete(commentService.getCommentById(comment.getId()));
        return "delete";
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
