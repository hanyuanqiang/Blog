package com.hyq.action;

import com.hyq.entity.Type;
import com.hyq.service.ArticleService;
import com.hyq.service.TypeService;

import com.hyq.util.PrintException;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
@Controller
public class TypeAction extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    @Resource
    private TypeService typeService;
    @Resource
    private ArticleService articleService;
    private String rightPage;
    private List<Type> typeList;
    private Type type;
    private int typeId;


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public String getRightPage() {
        return rightPage;
    }

    public void setRightPage(String rightPage) {
        this.rightPage = rightPage;
    }

    public String backlist()throws Exception{
        typeList = typeService.findTypeList();
        rightPage="/background/typeManager.jsp";
        return SUCCESS;
    }

    public String backsave()throws Exception{
        typeService.save(type);
        return "list";
    }

    public String backdelete()throws Exception{
        boolean exist = articleService.existArticleWithTypeId(typeId);
        if (exist){
            PrintException.print(ServletActionContext.getResponse(),"该分类下面有文章，不能删除");
            return null;
        }else {
            Type type1 = typeService.getTypeById(typeId);
            typeService.delete(type1);
            return "list";
        }
    }


    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
