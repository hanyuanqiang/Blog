package com.hyq.action;

import com.hyq.entity.Article;
import com.hyq.entity.Type;
import com.hyq.entity.User;
import com.hyq.service.ArticleService;
import com.hyq.service.TypeService;
import com.hyq.service.WisdomService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/2.
 */
@Component
public class SystemAction extends ActionSupport implements ServletContextAware,ServletRequestAware {

    private ServletContext servletContext;
    private HttpServletRequest request;

    @Resource
    private TypeService typeService;
    @Resource
    private ArticleService articleService;
    @Resource
    private WisdomService wisdomService;
    public String backrefresh()throws Exception{

        //更新首页右侧博客分类中包含的博客数量
        List<Type> typeList = typeService.findTypeList();       //首先获取分类列表
        //遍历每一个分类
        for (Type type:typeList) {
            Article s_article = new Article();
            //下面查询指定类别和权限的文章的数量
            s_article.setType(type);
            s_article.setVisitAuth(0);
            Long articleNum = articleService.getArticleCount(s_article);
            //更新类别表中每个类别下包含的文章的数量
            type.setArticleNum(articleNum);
            typeService.save(type);
        }


        //更新存在application中的admin的信息
        User adimn = (User)request.getSession().getAttribute("currentUser");
        servletContext.setAttribute("admin",adimn);
        //再次从数据库中获取类别信息(用于首页右侧显示)
        List<Type> appli_typeList = typeService.findTypeList();
        servletContext.setAttribute("appli_typeList",appli_typeList);

        //更新首页左侧博客的按日期分类
        List<Object> year_month_list = articleService.searchGroupByYM();
        Map<String,String> year_month = new LinkedHashMap<String, String>();    //LinkedHashMap为有序图，数据会按插入数据存储在year_month中
        //从数据库中查询到的多个字段存储在Object[]中，其中第一个“*年*月”，
        for (Object ym:year_month_list) {
            year_month.put(((Object[])ym)[0].toString(),((Object[])ym)[1].toString());
        }
        servletContext.setAttribute("year_month",year_month);

        servletContext.setAttribute("wisdom",wisdomService.getTheShowOne());

        return SUCCESS;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
