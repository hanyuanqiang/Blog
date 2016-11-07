<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/30
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
    <p style="margin-left: 20px;margin-top: 15px;margin-bottom: 2px;">
        <img src="${pageContext.request.contextPath}/images/文章列表.png">
        &nbsp;<span style="font-size: 16px;vertical-align: middle">博客列表</span>
    </p>
    <p style="border-top:1px solid #808080;margin: 0px 20px;"></p>
    <p></p>
    <ul style="padding: 6px 10px;">
        <c:forEach items="${articleList}" var="article">
            <li style="margin-bottom: 20px;list-style: none;font-size: 16px;">

                <c:choose>
                    <c:when test="${article.foreUrl!=null&&article.foreUrl!=''}">
                        <a href="${article.foreUrl}">
                            <img src="${pageContext.request.contextPath}/images/article4.png">&nbsp;&nbsp;${article.title}
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/article_foreDetail?articleId=${article.id}">
                            <img src="${pageContext.request.contextPath}/images/article4.png">&nbsp;&nbsp;${article.title}
                        </a>
                    </c:otherwise>
                </c:choose>


                <span style="margin-left: 30px;"><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd" type="date"/></span>
                <p style="padding-left: 25px;margin-top: 5px;">
                    <c:choose>
                        <c:when test="${fn:length(article.content) > 100}">
                            ${fn:substring(article.content, 0, 100)}...

                            <c:choose>
                                <c:when test="${article.foreUrl!=null&&article.foreUrl!=''}">
                                    <a href="${article.foreUrl}">阅读全文</a>
                                </c:when>
                                <c:otherwise>
                                    <a href='${pageContext.request.contextPath}/article_foreDetail?articleId=${article.id}'>阅读全文</a>
                                </c:otherwise>
                            </c:choose>

                        </c:when>
                        <c:otherwise>
                            <c:out value="${article.content}" />
                        </c:otherwise>
                    </c:choose>
                </p>
            </li>
        </c:forEach>
    </ul>

    <div align="center">
        <nav>
            <ul class="list-inline" style="font-size: large">
                ${pageCode}
                <%--翻页标签--%>
            </ul>
        </nav>
    </div>
</div>