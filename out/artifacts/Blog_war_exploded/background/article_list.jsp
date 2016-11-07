<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/30
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    function searchEvent(){
        var searchContent = $("#articleContent").val();
        var typeId = $("#typeId").val();
        window.location.href = "${pageContext.request.contextPath}/article_backlist.action?s_article.content="+searchContent+"&s_article.type.id="+typeId;
    }
    function genPage(id){
        if(confirm("确定要生成静态页面？")){
            $.post("${pageContext.request.contextPath}/article_backGenHtmlPage.action",{articleId:id},function(result){
                if(result.success){
                    layer.alert('静态页面生成成功', {
                        title:'系统提示',
                        icon: 1,
                        skin: 'layui-layer-molv'
                    });
                }else{
                    layer.alert(result.errorMsg, {
                        title:'系统提示',
                        icon: 5,
                        skin: 'layui-layer-molv'
                    });
                }
            },"json");
        }
    }
</script>

<div>
    <p align="center">
        <div style="float: right;margin-top: 8px;">
            <select name="article.type.id" id="typeId">
                <option value="">选择分类</option>
                <c:forEach items="${typeList}" var="type">
                    <option value="${type.id}" ${type.id==s_article.type.id?'selected':''}>${type.typeName}</option>
                </c:forEach>
            </select>
            <input type="text" name="s_article.content" id="articleContent" value="${s_article.content}">
            <button style="margin-right: 30px;" class="btn btn-success btn-xs" onclick="searchEvent()">搜&nbsp;索</button>
        </div>
    <h3 align="center">博客管理</h3>
    </p>
    <div style="border-top:1px dashed #cccccc;height: 1px;overflow:hidden;margin: 5px 0px;"></div>
    <div style="margin-left: 30px;">
        <ul class="list-unstyled">
            <c:forEach items="${articleList}" var="article">
                <li style="margin-top: 20px;">
                    <c:choose>
                        <c:when test="${article.backUrl!=null&&article.backUrl!=''}">
                            <a href="${article.backUrl}">${article.title}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/article_backdetail.action?articleId=${article.id}">${article.title}</a>
                        </c:otherwise>
                    </c:choose>
                    <span style="margin-left: 20px;"><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
                    <a class="btn btn-info btn-xs" style="margin-left: 30px;" href="${pageContext.request.contextPath}/article_backwrite.action?articleId=${article.id}">
                        修改
                    </a>&nbsp;&nbsp;
                    <a class="btn btn-danger btn-xs" href="${pageContext.request.contextPath}/article_backdelete.action?articleId=${article.id}" onclick="return confirm('确定删除这篇文章？')">
                        删除
                    </a>&nbsp;&nbsp;
                    <c:if test="${article.backUrl==null||article.backUrl==''||article.foreUrl==null||article.foreUrl==''}">
                        <a class="btn btn-primary btn-xs" href="#" onclick="genPage(${article.id})">
                            静态化
                        </a>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
        <div align="center">
            <nav>
                <ul class="pagination">
                    ${pageCode}
                    <%--翻页标签--%>
                </ul>
            </nav>
        </div>
    </div>
</div>