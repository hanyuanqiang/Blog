package com.hyq.service;

import com.hyq.entity.Article;
import com.hyq.entity.PageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface ArticleService {

    //保存一篇文章
    public void save(Article article);

    //删除一篇文章
    public void delete(Article article);

    //根据id获取文章
    public Article getArticleById(int id);

    //获取指定条件的文章
    public List<Article> findArticleList(Article s_article, PageBean pageBean);

    //获取指定条件的文章的总数
    public Long getArticleCount(Article s_article);

    //查看某分类下面是否有文章
    public boolean existArticleWithTypeId(int typeId);

    //按照年月分组查询
    public List<Object> searchGroupByYM();

    //获取文章列表中的最大id(即为了获取最新文章)
    public int getNewestId();

    public void changePageViews(int articleId);



}
