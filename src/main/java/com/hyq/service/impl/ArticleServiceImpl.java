package com.hyq.service.impl;

import com.hyq.dao.BaseDAO;
import com.hyq.entity.Article;
import com.hyq.entity.PageBean;
import com.hyq.service.ArticleService;
import com.hyq.util.DateUtil;
import com.hyq.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
@Service("articleService")
class ArticleServiceImpl implements ArticleService{

    @Resource
    private BaseDAO<Article> baseDAO;

    public void save(Article article) {
        baseDAO.merge(article);
    }

    public void delete(Article article) {
        baseDAO.delete(article);
    }

    public Article getArticleById(int id) {
        return baseDAO.get(Article.class,id);
    }

    public List<Article> findArticleList(Article s_article, PageBean pageBean) {

        List<Object> param = new LinkedList<Object>();
        StringBuffer hql = new StringBuffer("from Article");

        if(s_article!=null){
            //按照内容查询
            if (StringUtil.isNotEmpty(s_article.getContent())){
                hql.append(" and content like ?");
                param.add("%"+s_article.getContent()+"%");
            }

            //按照年月分类查询
            if (s_article.getCreateTime()!=null){
                hql.append(" and DATE_FORMAT(createTime,'%Y-%m') like ?");
                param.add("%"+ DateUtil.formatDate(s_article.getCreateTime(),"yyyy-MM")+"%");
            }

            //按照文章分类查询
            if (s_article.getType()!=null){
                if(s_article.getType().getId()!=null){
                    hql.append(" and type.id=?");
                    param.add(s_article.getType().getId());
                }
            }

            //按照权限查询
            if (s_article.getVisitAuth()==0){
                hql.append(" and visitAuth=0");
            }
        }
        hql.append(" order by createTime desc");
        if (pageBean!=null){
            return baseDAO.find(hql.toString().replaceFirst("and","where"),param,pageBean);
        }else{
            return baseDAO.find(hql.toString().replaceFirst("and","where"),param);
        }
    }

    public Long getArticleCount(Article s_article) {
        List<Object> param = new LinkedList<Object>();
        StringBuffer hql = new StringBuffer("select count(*) from Article");

        if(s_article!=null){
            //按照内容查询
            if (StringUtil.isNotEmpty(s_article.getContent())){
                hql.append(" and content like ?");
                param.add("%"+s_article.getContent()+"%");
            }

            //按照年月分类查询
            if (s_article.getCreateTime()!=null){
                hql.append(" and DATE_FORMAT(createTime,'%Y-%m') like ?");
                param.add("%"+ DateUtil.formatDate(s_article.getCreateTime(),"yyyy-MM")+"%");
            }

            //按照文章分类查询
            if (s_article.getType()!=null){
                if(s_article.getType().getId()!=null){
                    hql.append(" and type.id=?");
                    param.add(s_article.getType().getId());
                }
            }

            //按照权限查询
            if (s_article.getVisitAuth()==0){
                hql.append(" and visitAuth=0");
            }
        }

        return baseDAO.count(hql.toString().replaceFirst("and","where"),param);

    }

    //查看某分类下面是否有文章
    public boolean existArticleWithTypeId(int typeId) {
        String hql = "from Article where type.id="+typeId;
        if(baseDAO.find(hql).size()>0){
            return true;
        }else {
            return false;
        }
    }

    public List<Object> searchGroupByYM() {
        //按照日期降序插入(离现在时间最近的显示在前面)
        return baseDAO.executeSql2("SELECT DATE_FORMAT(createTime,'%Y年%m月'),COUNT(DATE_FORMAT(createTime,'%Y年%m月')) FROM t_article WHERE visitAuth=0 GROUP BY DATE_FORMAT(createTime,'%Y年%m月') ORDER BY DATE_FORMAT(createTime,'%Y年%m月') DESC");
    }

    public int getNewestId() {
        Object temp = baseDAO.executeSql2("SELECT id FROM t_article WHERE visitAuth=0 ORDER BY createTime DESC LIMIT 1").get(0);
        return Integer.parseInt(temp.toString()) ;
    }

    public void changePageViews(int articleId) {
        baseDAO.executeSql("UPDATE t_article SET pageViews=pageViews+1 WHERE id="+articleId);
    }
}
